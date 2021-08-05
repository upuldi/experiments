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
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FilenameUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.jibx.binding.Compile;
import org.jibx.runtime.JiBXException;

/**
 * Runs the JiBX binding compiler.
 *
 * @author                        <a href="mailto:mail@andreasbrenk.com">Andreas Brenk</a>
 * @author                        <a href="mailto:frankm.os@gmail.gom">Frank Mena</a>
 * @author                        <a href="mailto:don@donandann.com">Don Corley</a>
 */
public abstract class AbstractBaseBindingMojo extends AbstractJibxMojo {

    //~ Static fields/initializers -------------------------------------------------------------------------------------

    static final String DEFAULT_INCLUDES = "binding.xml";

    //~ Instance fields ------------------------------------------------------------------------------------------------

    /**
     * Exclude pattern for binding files.
     *
     * @parameter  expression="${excludes}"
     */
    ArrayList excludes;

    /**
     * Include pattern for binding files.<br/>
     * <b>Note: </b>Uses the standard filter format described in the plexus
     * <a href="http://plexus.codehaus.org/plexus-utils/apidocs/org/codehaus/plexus/util/DirectoryScanner.html">DirectoryScanner</a>.<br/>
     * <b>Defaults value is:</b> binding.xml.
     *
     * @parameter  expression="${includes}"
     */
    ArrayList includes;

    /**
     * Control flag for test loading generated/modified classes.
     *
     * @parameter  expression="${load}" default-value="false"
     * @required
     */
    boolean load;

    /**
     * A list of modules to search for binding files in the format: groupID:artifactID
     *
     * @parameter  expression="${modules}"
     */
    HashSet modules;

    /**
     * Control flag multi-module mode.
     *
     * @parameter  expression="${multi-module}" default-value="false"
     * @required
     */
    boolean multimodule;

    /**
     * Control flag for test loading generated/modified classes.
     *
     * @parameter  expression="${validate}" default-value="true"
     * @required
     */
    boolean validate;

    /**
     * Control flag for verbose processing reports.
     *
     * @parameter  expression="${verbose}" default-value="false"
     * @required
     */
    boolean verbose;

    /**
     * Control flag for verifying generated/modified classes with BCEL.
     *
     * @parameter  expression="${verify}" default-value="false"
     * @required
     */
    boolean verify;

    //~ Methods --------------------------------------------------------------------------------------------------------

    /**
     * Determines if running in single- or multi-module mode, collects all bindings and finally runs the binding
     * compiler.
     */
    public void execute() throws MojoExecutionException, MojoFailureException {
        checkConfiguration();

        String mode;
        String[] bindings;
        String[] classpaths;

        if ("pom".equalsIgnoreCase(project.getPackaging()))
        {
            getLog().info("Not running JiBX binding compiler for pom packaging");
        	return;		// Don't bind jibx if pom packaging
        }
        
        if (isMultiModuleMode()) {
            if (isRestrictedMultiModuleMode()) {
                mode = "restricted multi-module";
            } else {
                mode = "multi-module";
            }
            bindings = getMultiModuleBindings();
            classpaths = getMultiModuleClasspaths();
        } else {
            mode = "single-module";
            bindings = getSingleModuleBindings();
            classpaths = getSingleModuleClasspaths();
        }

        if (bindings.length == 0) {
            getLog().info("Not running JiBX binding compiler (" + mode + " mode) - no binding files");
        } else {
            getLog().info("Running JiBX binding compiler (" + mode + " mode) on " + bindings.length
                          + " binding file(s)");
            compile(classpaths, bindings);
        }
    }

    /**
     * Verifies the plugins configuration and sets default values if needed.
     * Note: Remember to call inherited methods first.
     */
    protected void checkConfiguration() {
    	super.checkConfiguration();

    	if ((this.includes == null) || (this.includes.size() == 0)) {
            this.includes = new ArrayList();
            this.includes.add(DEFAULT_INCLUDES);
        }
        if (this.excludes == null) {
            this.excludes = new ArrayList();
        }
        if ((this.modules != null) && (this.modules.size() > 0)) {
            this.multimodule = true;
        } else {
            this.modules = null;
        }
    }

    /**
     * Creates and runs the JiBX binding compiler.
     */
    private void compile(String[] classpaths, String[] bindings) throws MojoExecutionException {
        try {
            Compile compiler = new Compile();
            compiler.setLoad(this.load);
            compiler.setSkipValidate(!this.validate);
            compiler.setVerbose(this.verbose);
            compiler.setVerify(this.verify);
            compiler.compile(classpaths, bindings);
        } catch (JiBXException e) {
            Throwable cause = (e.getRootCause() != null) ? e.getRootCause() : e;
            throw new MojoExecutionException(cause.getLocalizedMessage(), cause);
        }
    }

    /**
     * Normalizes all entries.
     */
    /* package */ String[] normalizeClasspaths(Set classpathSet) {
        String[] classpaths = (String[]) classpathSet.toArray(new String[classpathSet.size()]);

        for (int i = 0; i < classpaths.length; i++) {
            classpaths[i] = FilenameUtils.normalize(classpaths[i]);
        }

        return classpaths;
    }

    /**
     * Returns all bindings in the given directory according to the configured include/exclude patterns.
     */
    List getBindings(String path) throws MojoExecutionException, MojoFailureException {
        return this.getIncludedFiles(path, this.includes, this.excludes);
    }

    /**
     * Returns all binding in the current project and all referenced projects (multi-module mode)
     */
    String[] getMultiModuleBindings() throws MojoExecutionException, MojoFailureException {
        Set basedirSet = getProjectBasedirSet(this.project);
        Set bindingSet = new HashSet();

        for (Iterator iter = basedirSet.iterator(); iter.hasNext();) {
            String basedir = (String) iter.next();
            bindingSet.addAll(getBindings(basedir + File.separator + this.getDirectory()));
        }

        return (String[]) bindingSet.toArray(new String[bindingSet.size()]);
    }

    /**
     * Returns the classpath for the binding compiler running in multi-module mode.
     */
    String[] getMultiModuleClasspaths() throws MojoExecutionException, MojoFailureException {
        Set classpathSet = getProjectCompileClasspathElementsSet(this.project);

        return normalizeClasspaths(classpathSet);
    }

    /**
     * Returns the basedir of the given project and all (or all in "modules" specified) reference projects.
     */
    private Set getProjectBasedirSet(MavenProject project) {
        Set basedirSet = new HashSet();
        basedirSet.add(getProjectBasedir(project));

        Collection projectReferences = project.getProjectReferences().values();
        for (Iterator iter = projectReferences.iterator(); iter.hasNext();) {
            MavenProject projectReference = (MavenProject) iter.next();
            String projectId = projectReference.getGroupId() + ":" + projectReference.getArtifactId();

            if ((this.modules == null) || this.modules.contains(projectId)) {
                basedirSet.add(getProjectBasedir(projectReference));
            }
        }

        return basedirSet;
    }

    /**
     * Returns the build output directory of the given project.
     *
     * @throws  MojoExecutionException  if DependencyResolutionRequiredException occurs
     */
    protected abstract Set getProjectCompileClasspathElements(MavenProject project) throws MojoExecutionException;

    /**
     * Returns the build output directory of the given project and all its reference projects.
     *
     * @throws  MojoExecutionException  if DependencyResolutionRequiredException occurs
     */
    private Set getProjectCompileClasspathElementsSet(MavenProject project) throws MojoExecutionException {
        Set classpathElements = new HashSet();
        classpathElements.addAll(getProjectCompileClasspathElements(project));

        Collection projectReferences = project.getProjectReferences().values();
        for (Iterator iter = projectReferences.iterator(); iter.hasNext();) {
            MavenProject projectReference = (MavenProject) iter.next();
            classpathElements.addAll(getProjectCompileClasspathElements(projectReference));
        }

        return classpathElements;
    }

    /**
     * Returns all bindings in the current project (single-module mode).
     */
    String[] getSingleModuleBindings() throws MojoExecutionException, MojoFailureException {
        String basedir = getProjectBasedir(this.project);
        String bindingdir = basedir + File.separator + this.getDirectory();
        if ((this.getDirectory().startsWith(File.separator))
    		|| (this.getDirectory().startsWith("/")))	// This is valid on windows even though it is not a separator
        		bindingdir = this.getDirectory();	// Absolute path
        List bindingSet = getBindings(bindingdir);

        return (String[]) bindingSet.toArray(new String[bindingSet.size()]);
    }

    /**
     * Returns the classpath for the binding compiler running in single-module mode.
     */
    String[] getSingleModuleClasspaths() throws MojoExecutionException, MojoFailureException {
        Set classpathSet = getProjectCompileClasspathElements(this.project);

        return normalizeClasspaths(classpathSet);
    }

    /**
     * Determine if the plugin is running in "multi-module" mode.
     */
    boolean isMultiModuleMode() {
        return this.multimodule;
    }

    /**
     * Determine if the plugin is running in "restricted multi-module" mode.
     */
    boolean isRestrictedMultiModuleMode() {
        return isMultiModuleMode() && (this.modules != null);
    }

    /**
     * Determine if the plugin is running in "single-module" mode.
     */
    boolean isSingleModuleMode() {
        return !isMultiModuleMode();
    }
    
    /**
     * Get the binding files directory.
     * @return The binding files directory.
     */
    protected abstract String getDirectory();
}
