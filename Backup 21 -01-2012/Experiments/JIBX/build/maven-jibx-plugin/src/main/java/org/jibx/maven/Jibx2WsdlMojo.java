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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;


/**
 * Generates WSDL from java code.
 * @author                        <a href="mailto:don@donandann.com">Don Corley</a>
 * @goal                          jibx2wsdl
 * @phase                         process-classes
 * @requiresDependencyResolution  compile
 */
public class Jibx2WsdlMojo extends AbstractJibx2WsdlMojo {

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
	        return new HashSet(getCompileClasspathElements());

        } catch (DependencyResolutionRequiredException e) {
	        e.printStackTrace();
	        return null;
        }
    }
    /**
     * This is a copy of a project method with the same same.
     * I call this method, because it does not do what I expected it to.
     * I wanted the classpath for project compile scope, it returns nothing.
     * @return
     * @throws DependencyResolutionRequiredException
     */
	public List getCompileClasspathElements()
	    throws DependencyResolutionRequiredException
	{
	    List list = new ArrayList( project.getDependencyArtifacts().size() );
	
	    list.add( project.getBuild().getOutputDirectory() );
	
	    for ( Iterator i = project.getDependencyArtifacts().iterator(); i.hasNext(); )
	    {
	        Artifact a = (Artifact) i.next();
	
	        if ( a.getArtifactHandler().isAddedToClasspath() )
	        {
	            // TODO: let the scope handler deal with this
	            if ( Artifact.SCOPE_COMPILE.equals( a.getScope() ) || Artifact.SCOPE_PROVIDED.equals( a.getScope() ) ||
	                Artifact.SCOPE_SYSTEM.equals( a.getScope() ) )
	            {
	                String refId = getProjectReferenceId( a.getGroupId(), a.getArtifactId() );
	                MavenProject project = (MavenProject) this.project.getProjectReferences().get( refId );
	                if ( project != null )
	                {
	                    list.add( project.getBuild().getOutputDirectory() );
	                }
	                else
	                {
	                    File file = a.getFile();
	                    if ( file == null )
	                    {
	                        throw new DependencyResolutionRequiredException( a );
	                    }
	                    list.add( file.getPath() );
	                }
	            }
	        }
	    }
	    return list;
	}
	private static String getProjectReferenceId( String groupId, String artifactId )
	{
	    return groupId + ":" + artifactId;
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