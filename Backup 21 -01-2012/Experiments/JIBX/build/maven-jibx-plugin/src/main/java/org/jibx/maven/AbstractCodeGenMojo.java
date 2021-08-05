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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProjectHelper;
import org.jibx.runtime.JiBXException;
import org.jibx.schema.codegen.CodeGen;

/**
 * Generates Java sources from XSD schemas.
 * @author                        <a href="mailto:jerome.bernard@elastic-grid.com">Jerome Bernard</a>
 * @author                        <a href="mailto:don@donandann.com">Don Corley</a>
 */
public abstract class AbstractCodeGenMojo extends AbstractJibxMojo {
    private static final String DEFAULT_INCLUDES = "*.xsd";

    /**
     * Exclude pattern for binding files.
     *
     * @parameter  expression="${excludes}"
     */
    private ArrayList excludes;

    /**
     * Include pattern for schema files.<br/>
     * <b>Note: </b>Uses the standard filter format described in the plexus
     * <a href="http://plexus.codehaus.org/plexus-utils/apidocs/org/codehaus/plexus/util/DirectoryScanner.html">DirectoryScanner</a>.<br/>
     * <b>Defaults value is:</b> *.xsd.
     *
     * @parameter  expression="${includes}"
     */
    private ArrayList includes;

    /**
     * Control flag for verbose processing reports.
     *
     * @parameter  expression="${verbose}" default-value="false"
     * @required
     */
    private boolean verbose;

    /**
     * Default package for code generated from schema definitions with no namespace.
     *
     * @parameter
     */
    private String defaultPackage = null;

    /**
     * Include pattern for customization files.
     *
     * @parameter  expression="${customizations}"
     */
    private ArrayList customizations;

    /**
     * Include existing bindings and use mappings from the bindings for matching schema global definitions.
     * (this is the basis for modular code generation)
     * Include base bindings as follows:<br/>
     * &lt;includeBindings&gt;<br/>
     * &lt;includeBinding&gt;base-binding.xml&lt;/includeBinding&gt;<br/>
     * &lt;/includeBindings&gt;<br/>
     * <b>Note:</b> Relative paths start at ${basedir}.
     *
     * @parameter  expression="${includeBindings}"
     */
    private ArrayList includeBindings;
    /**
     * Namespace applied in code generation when no-namespaced schema definitions are found (to generate
     * no-namespaced schemas as though they were included in a particular namespace)
     *
     * @parameter
     */
    private String defaultNamespace = null;


    /**
     * Extra options to be given for customization via CLI.<p/>
     * Enter extra customizations or other command-line options.<br/>
     * The extra customizations are described on the 
     * <a href="/fromschema/codegen-customs.html">CodeGen customizations page</a><br/>
     * The single character CodeGen commands may also be supplied here.<br/>
     * For example, to include a base binding file (-i) and prefer-inline code, supply the following options:<br/>
     * <code>
     * &lt;options&gt;<br/>
     * &nbsp;&nbsp;&lt;i&gt;base-binding.xml&lt;/i&gt;<br/>
     * &nbsp;&nbsp;&lt;prefer-inline&gt;true&lt;/prefer-inline&gt;<br/>
     * &lt;/options&gt;
     * </code>
     *
     * @parameter
     */
    private Map options;

    /**
     * @component
     */
    private MavenProjectHelper projectHelper;

    public void execute() throws MojoExecutionException, MojoFailureException {
        checkConfiguration();

        if ("pom".equalsIgnoreCase(project.getPackaging()))
        {
            getLog().info("Not running JiBX code generator for pom packaging");
        	return;		// Don't bind jibx if pom packaging
        }

        List args = new ArrayList();
        for (Iterator iterator = options.entrySet().iterator(); iterator.hasNext();) {
            Map.Entry entry = (Map.Entry) iterator.next();
            String option = "--" + entry.getKey() + "=" + entry.getValue();
            if ((entry.getKey().toString().length() == 1) && (Character.isLowerCase(entry.getKey().toString().charAt(0))))
            {
            	getLog().debug("Adding option : -" + entry.getKey() + " " + entry.getValue());
            	args.add("-" + entry.getKey());
            	args.add(entry.getValue());
            }
            else
            {
	            getLog().debug("Adding option: " + option);
	            args.add(option);
            }
        }
        if (verbose)
            args.add("-v");
        if (defaultPackage != null) {
            args.add("-n");
            args.add(defaultPackage);
        }
        args.add("-t");
        args.add(getTargetDirectory());
        if (customizations.size() > 0) {
            args.add("-c");
            for (Iterator iterator = customizations.iterator(); iterator.hasNext();) {
                String customization = (String) iterator.next();
                args.add(customization);
            }
        }
        if (defaultNamespace != null) {
            args.add("-u");
            args.add(defaultNamespace);
        }
        if (includeBindings.size() > 0) {
            String basedir = getProjectBasedir(this.project);
            String allBindings = "";
            for (Iterator iterator = includeBindings.iterator(); iterator.hasNext();) {
                String includeBinding = (String) iterator.next();
                if (includeBinding == null)
                	continue;
                
                boolean relativePath = true;
            	if (includeBinding.startsWith("classpath:"))
                    getLog().error("CodeGen Mojo does not support 'classpath:' in binding, file a bug report if you need this capability.");
                if ((!includeBinding.startsWith(File.pathSeparator))
                	&& (!includeBinding.startsWith("/"))
                	&& (!includeBinding.contains(",")))
                {	// Possible relative path
                		try {
	                        File file = new File(basedir + File.separator + includeBinding);
	                        if (file.exists())
	                        	relativePath = false;	// If file exists, use absolute path
                        } catch (Exception e) {
                        	// Exception = use relative path
                        }
                }
                if (!relativePath)
                	includeBinding = basedir + File.separator + includeBinding;
                if (allBindings.length() > 0)
                	allBindings = allBindings + ",";
            	allBindings = allBindings + includeBinding;
            }
            if (allBindings.length() > 0)
            {
            	args.add("-i");
            	args.add(allBindings);
            }
        }
        List schemas = getSchemas(getDirectory());
        for (Iterator iterator = schemas.iterator(); iterator.hasNext();) {
            String schema = (String) iterator.next();
            args.add(new File(schema).toURI().toString());
        }
        try {
            getLog().info("Generating Java sources in " + getTargetDirectory() + " from schemas available in " + getDirectory() + "...");
            CodeGen.main((String[]) args.toArray(new String[args.size()]));
        } catch (JiBXException e) {
            Throwable cause = (e.getRootCause() != null) ? e.getRootCause() : e;
            throw new MojoExecutionException(cause.getLocalizedMessage(), cause);
        } catch (Exception e) {
            Throwable cause = (e.getCause() != null) ? e.getCause() : e;
            throw new MojoExecutionException(cause.getLocalizedMessage(), cause);
        }

        getLog().debug("Adding " + getTargetDirectory() + " as source directory...");
        project.addCompileSourceRoot(getTargetDirectory());
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
        if (this.customizations == null) {
            this.customizations = new ArrayList();
        }
        if (this.includeBindings == null) {
            this.includeBindings = new ArrayList();
        }
        if (this.options == null) {
            this.options = new HashMap();
        }
    }

    /**
     * Returns all bindings in the given directory according to the configured include/exclude patterns.
     */
    private List getSchemas(String path) throws MojoExecutionException, MojoFailureException {
        return this.getIncludedFiles(path, this.includes, this.excludes);
    }

    /**
     * Get the binding files directory.
     * @return The binding files directory.
     */
    protected abstract String getDirectory();

    /**
     * Get the binding files directory.
     * @return The binding files directory.
     */
    protected abstract String getTargetDirectory();

}