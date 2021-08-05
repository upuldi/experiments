/*
 * Copyright (c) 2004-2005, Dennis M. Sosnoski All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following
 * disclaimer. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the
 * following disclaimer in the documentation and/or other materials provided with the distribution. Neither the name of
 * JiBX nor the names of its contributors may be used to endorse or promote products derived from this software without
 * specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
 * INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.jibx.maven;

import java.util.HashSet;
import java.util.Set;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;


/**
 * Runs the JiBX binding compiler.
 *
 * @author                        <a href="mailto:mail@andreasbrenk.com">Andreas Brenk</a>
 * @author                        <a href="mailto:frankm.os@gmail.gom">Frank Mena</a>
 * @author                        <a href="mailto:don@donandann.com">Don Corley</a>
 * @goal                          bind
 * @phase                         process-classes
 * @requiresDependencyResolution  compile
 */
public class CompileBindingMojo extends AbstractBindingMojo {

    /**
     * The directory which contains binding files.
     *
     * @parameter  expression="${directory}" default-value="src/main/config"
     * @required
     */
    private String directory;

    /**
     * Returns the build output directory of the given project.
     *
     * @throws  MojoExecutionException  if DependencyResolutionRequiredException occurs
     */
    protected Set getProjectCompileClasspathElements(MavenProject project) throws MojoExecutionException {
        try {
            return new HashSet(project.getCompileClasspathElements());
        } catch (DependencyResolutionRequiredException e) {
            throw new MojoExecutionException(e.getLocalizedMessage(), e);
        }
    }

    /**
     * Get the binding files directory.
     * @return The binding files directory.
     */
    protected String getDirectory()
    {
    	return this.directory;
    }
}
