/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.tests.views;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.tests.DesignTimeTestsPlugin;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestDTUIViewRoot extends TestCase {

    private static final String TESTJSP1_PATH = "testdata1.jsp";

    private WebProjectTestEnvironment _webProjectTestEnv;
    //private IFile                     _testJSP;
    private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true,
                "www-proxy.us.oracle.com", "80");

        _webProjectTestEnv = new WebProjectTestEnvironment(getProjectName());
        _webProjectTestEnv.createProject(false);

        /*final IResource res =*/ _webProjectTestEnv.loadResourceInWebRoot(
                DesignTimeTestsPlugin.getDefault().getBundle(),
                "/testdata/testdata1.jsp.data", TESTJSP1_PATH);
        //_testJSP = (IFile) res;

        new JDTTestEnvironment(_webProjectTestEnv);

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(
                _webProjectTestEnv);
        _jsfFactedTestEnvironment
                .initialize(IJSFCoreConstants.FACET_VERSION_1_1);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private String getProjectName() {
        return "TestDTUIViewRoot" + getName();
    }
}
