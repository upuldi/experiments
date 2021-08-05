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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.util.DirectoryScanner;

/**
 * Runs the JiBX binding compiler.
 *
 * @author                        <a href="mailto:mail@andreasbrenk.com">Andreas Brenk</a>
 * @author                        <a href="mailto:frankm.os@gmail.gom">Frank Mena</a>
 * @author                        <a href="mailto:don@donandann.com">Don Corley</a>
 */
public abstract class AbstractJibxMojo extends AbstractMojo {

    /**
     * The maven project.
     *
     * @resolveTransitiveDependencies
     * @parameter                      expression="${project}"
     * @required
     * @readonly
     */
    protected MavenProject project;

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Determines if running in single- or multi-module mode, collects all bindings and finally runs the binding
     * compiler.
     */
    public abstract void execute() throws MojoExecutionException, MojoFailureException;

    /**
     * Returns the basedir of the given project.
     */
    protected String getProjectBasedir(MavenProject project) {
        return FilenameUtils.normalize(project.getBasedir().getAbsolutePath());
    }

    /**
     * Verifies the plugins configuration and sets default values if needed.
     * Note: Remember to call inherited methods first.
     */
    protected void checkConfiguration() {
    	// Nothing to check here (yet).
    }
    
    /**
     * Returns all bindings in the given directory according to the configured include/exclude patterns.
     */
    protected List getIncludedFiles(String path, ArrayList includeFiles, ArrayList excludeFiles) throws MojoExecutionException, MojoFailureException {
        List bindingSet = new ArrayList();

        File bindingdir = new File(path);
        if (!bindingdir.isDirectory()) {
            return bindingSet;
        }

        DirectoryScanner scanner = new DirectoryScanner();

        scanner.setBasedir(bindingdir);
        String[] includes = (String[]) includeFiles.toArray(new String[includeFiles.size()]);
        scanner.setIncludes(includes);
        String[] excludes = (String[]) excludeFiles.toArray(new String[excludeFiles.size()]);
        scanner.setExcludes(excludes);
        scanner.scan();

        String[] files = scanner.getIncludedFiles();
        String absolutePath = bindingdir.getAbsolutePath();
        for (int i = 0; i < files.length; i++) {
            String file = absolutePath + File.separator + files[i];
            bindingSet.add(file);
        }

        return bindingSet;
    }
    /**
     * Fix this file path.
     * If it is absolute, leave it alone, if it is relative prepend the default path or the base path.
     * @param filePath The path to fix
     * @param defaultPath The base path to use.
     * @return The absolute path to this file.
     */
    protected String fixFilePath(String filePath, String defaultPath)
    {
        boolean relativePath = true;
        
        String basedir = defaultPath;
        if (basedir == null)
        	basedir = getProjectBasedir(this.project);
        if ((!filePath.startsWith(File.pathSeparator))
            	&& (!filePath.startsWith("/")))
            {	// Possible relative path
            		try {
                        File file = new File(basedir + File.separator + filePath);
                        if (file.exists())
                        	relativePath = false;	// If file exists, use absolute path
                    } catch (Exception e) {
                    	// Exception = use relative path
                    }
            }
        if (!relativePath)
        	filePath = basedir + File.separator + filePath;
        return filePath;
    }

}
