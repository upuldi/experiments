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


/**
 * Generates Java test sources from XSD schemas.
 * @author                        <a href="mailto:jerome.bernard@elastic-grid.com">Jerome Bernard</a>
 * @author                        <a href="mailto:don@donandann.com">Don Corley</a>
 * @goal                          test-schema-codegen
 * @phase                         generate-test-sources
 * @requiresDependencyResolution  test-compile
 */
public class TestSchemaCodeGenMojo extends AbstractCodeGenMojo {

    /**
     * The directory which contains XSD files.
     *
     * @parameter  expression="${directory}" default-value="${basedir}/src/test/config"
     * @required
     */
    private String directory;

    /**
     * Target directory where to generate Java source files.
     *
     * @parameter  expression="${targetDirectory}" default-value="${basedir}/target/generated-test-sources"
     */
    private String targetDirectory;

    /**
     * Get the binding files directory.
     * @return The binding files directory.
     */
    protected String getDirectory()
    {
    	return this.directory;
    }

    /**
     * Get the binding files directory.
     * @return The binding files directory.
     */
    protected String getTargetDirectory()
    {
    	return targetDirectory;
    }
}