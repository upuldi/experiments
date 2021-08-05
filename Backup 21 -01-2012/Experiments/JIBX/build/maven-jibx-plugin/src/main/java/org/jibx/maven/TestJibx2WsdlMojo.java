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

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;


/**
 * Generates WSDL from java code.
 * @author                        <a href="mailto:don@donandann.com">Don Corley</a>
 * @goal                          test-jibx2wsdl
 * @phase                         process-test-classes
 * @requiresDependencyResolution  test-compile
 */
public class TestJibx2WsdlMojo extends AbstractJibx2WsdlMojo {

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
        	HashSet set = new HashSet(project.getTestClasspathElements());
            // Not sure why this doesn't get all the dependencies, so I'll add them manually
            
            Set artifactsSet = project.getDependencyArtifacts();
            for (Iterator i = artifactsSet.iterator(); i.hasNext(); )
            {
                Artifact a = (Artifact) i.next();
                {
                    // if ( Artifact.SCOPE_TEST.equals( a.getScope() )  // Test scope = all.
                    {
                        File file = a.getFile();
                        if ( file == null )
                        {
                            throw new DependencyResolutionRequiredException( a );
                        }
                        set.add( file.getPath() );
                    }
                }
            }

            return set;
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