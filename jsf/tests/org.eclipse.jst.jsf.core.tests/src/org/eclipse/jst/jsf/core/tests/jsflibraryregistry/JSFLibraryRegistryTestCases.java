/*******************************************************************************
 * Copyright (c) 2005, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.jsf.core.tests.jsflibraryregistry;

import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibrary;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.JSFLibraryRegistry;
import org.eclipse.jst.jsf.core.internal.jsflibraryregistry.PluginProvidedJSFLibrary;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;

@SuppressWarnings("deprecation")
public class JSFLibraryRegistryTestCases extends TestCase {
	
	public JSFLibraryRegistryTestCases(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getDefaultImplementationID()'
	 */
	public void testGetDefaultImplementationID() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		Assert.assertEquals("", jsfLibRegistry.getDefaultImplementationID());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.setDefaultImplementationID(String)'
	 */
	public void testSetDefaultImplementationID() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		String updatedImplID = "myfaces_reg";
		jsfLibRegistry.setDefaultImplementationID(updatedImplID);
		Assert.assertEquals(updatedImplID, jsfLibRegistry.getDefaultImplementationID());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getJSFLibraries()'
	 */
	public void testGetJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();

		String[] archivefiles = {
				"faces-all-bogu.jar",
				"faces-api-bogus.jar", 
				"faces-impl-bogus.jar", 
				"tomahawk-bogus.jar"};

		JSFLibrary implJSFLib = JSFCoreUtilHelper.constructJSFLib("impljsflib_id", 
				"impljsflib_name", 
				archivefiles, 
				true);
		JSFLibrary nonimplJSFLib = JSFCoreUtilHelper.constructJSFLib("nonimpljsflib_id",
				"nonimpljsflib_name",
				archivefiles,
				false);
				
		jsfLibRegistry.addJSFLibrary(implJSFLib);
		jsfLibRegistry.addJSFLibrary(nonimplJSFLib);
		
		Assert.assertEquals(2, jsfLibRegistry.getJSFLibraries().size());		
		Assert.assertEquals(1, jsfLibRegistry.getImplJSFLibraries().size());		
		Assert.assertEquals(1, jsfLibRegistry.getNonImplJSFLibraries().size());
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibrariesByName("impljsflib_name").size());
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibrariesByName("nonimpljsflib_name").size());
		Assert.assertNull(jsfLibRegistry.getJSFLibraryByID("nosuchlib_id"));
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getPluginProvidedJSFLibraries()'
	 */
	public void testGetPluginProvidedJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		PluginProvidedJSFLibrary pluginLib = (PluginProvidedJSFLibrary)JSFCoreUtilHelper.constructJSFLib("plugin_provided", "testfiles/JSFLib", true, true);
		jsfLibRegistry.addJSFLibrary(pluginLib);
		JSFLibrary nonPluginLib = JSFCoreUtilHelper.constructJSFLib("non_plugin_provided", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(nonPluginLib);
		Assert.assertEquals(1, jsfLibRegistry.getPluginProvidedJSFLibraries().size());		
		
		Assert.assertEquals(pluginLib.getName(), pluginLib.getLabel());
		pluginLib.setLabel("plugin_providedLABEL");
		Assert.assertEquals("plugin_providedLABEL", pluginLib.getLabel());
		
		Assert.assertEquals(nonPluginLib.getName(), nonPluginLib.getLabel());
		Assert.assertEquals(nonPluginLib.getName(), nonPluginLib.getID());
	}	

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.jsflibraryregistry.internal.internal.impl.JSFLibraryRegistryImpl.getDefaultImplementation()'
	 */
	public void testGetDefaultImplementation() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		jsfLibRegistry.setDefaultImplementation(lib);
		Assert.assertEquals(lib, jsfLibRegistry.getDefaultImplementation());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.setDefaultImplementation(JSFLibrary)'
	 */
	public void testSetDefaultImplementation() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		jsfLibRegistry.setDefaultImplementation(lib);
		Assert.assertEquals(lib, jsfLibRegistry.getDefaultImplementation());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getJSFLibraryByID(String)'
	 */
	public void testGetJSFLibraryByID() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		String libID = "lib";
//		lib.setID(libID);
		Assert.assertEquals(lib, jsfLibRegistry.getJSFLibraryByID(libID));
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getJSFLibrariesByName(String)'
	 */
	public void testGetJSFLibrariesByName() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		String lib1Name = "Sun RI v1.1";
		JSFLibrary lib1 = JSFCoreUtilHelper.constructJSFLib(lib1Name, "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib1);
		String lib2Name = "Another Sun RI v1.1";
		JSFLibrary lib2 = JSFCoreUtilHelper.constructJSFLib(lib2Name, "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib2);
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibrariesByName(lib1Name).size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getImplJSFLibraries()'
	 */
	public void testGetImplJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary implLib = JSFCoreUtilHelper.constructJSFLib("impl_lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(implLib);
		JSFLibrary nonImplLib = JSFCoreUtilHelper.constructJSFLib("non_impl_lib", "testfiles/JSFLib", false, false);
		jsfLibRegistry.addJSFLibrary(nonImplLib);
		Assert.assertEquals(1, jsfLibRegistry.getImplJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getNonImplJSFLibraries()'
	 */
	public void testGetNonImplJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary implLib = JSFCoreUtilHelper.constructJSFLib("impl_lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(implLib);
		JSFLibrary nonImplLib = JSFCoreUtilHelper.constructJSFLib("non_impl_lib", "testfiles/JSFLib", false, false);
		jsfLibRegistry.addJSFLibrary(nonImplLib);
		Assert.assertEquals(1, jsfLibRegistry.getNonImplJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.getAllJSFLibraries()'
	 */
	public void testGetAllJSFLibraries() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib1 = JSFCoreUtilHelper.constructJSFLib("plugin_provided", "testfiles/JSFLib", true, true);
		jsfLibRegistry.addJSFLibrary(lib1);
		JSFLibrary lib2 = JSFCoreUtilHelper.constructJSFLib("non_plugin_provided", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib2);
		Assert.assertEquals(2, jsfLibRegistry.getAllJSFLibraries().size());
	}


	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.addJSFLibrary(JSFLibrary)'
	 */
	public void testAddJSFLibrary() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary pluginLib = JSFCoreUtilHelper.constructJSFLib("plugin_provided", "testfiles/JSFLib", true, true);
		jsfLibRegistry.addJSFLibrary(pluginLib);
		Assert.assertEquals(1, jsfLibRegistry.getPluginProvidedJSFLibraries().size());
		Assert.assertEquals(0, jsfLibRegistry.getJSFLibraries().size());
		JSFLibrary nonPluginLib = JSFCoreUtilHelper.constructJSFLib("non_plugin_provided", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(nonPluginLib);
		Assert.assertEquals(1, jsfLibRegistry.getPluginProvidedJSFLibraries().size());
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibraries().size());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.jsflibraryregistry.impl.JSFLibraryRegistryImpl.removeJSFLibrary(JSFLibrary)'
	 */
	public void testRemoveJSFLibrary() {
		JSFLibraryRegistry jsfLibRegistry = JSFCoreUtilHelper.getNewJSFLibraryRegistry();
		JSFLibrary lib = JSFCoreUtilHelper.constructJSFLib("lib", "testfiles/JSFLib", true, false);
		jsfLibRegistry.addJSFLibrary(lib);
		Assert.assertEquals(1, jsfLibRegistry.getJSFLibraries().size());
		jsfLibRegistry.removeJSFLibrary(lib);
		Assert.assertEquals(0, jsfLibRegistry.getJSFLibraries().size());
	}

    public void testPluginProvidedJSFLibCreationFromExtPt() {
		//2 jar lib
 		JSFLibraryRegistry jsfLibRegistry = JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry();
		List<?> libs = jsfLibRegistry.getJSFLibrariesByName("TEST_PP_LIB_2");
		Assert.assertNotNull(libs);
		Assert.assertEquals(1, libs.size());
		JSFLibrary lib = (JSFLibrary)libs.get(0);
		Assert.assertTrue(lib instanceof PluginProvidedJSFLibrary);
		Assert.assertEquals(2, lib.getArchiveFiles().size());
		Assert.assertEquals("FAKE LIB FROM jsf.core.tests (1)", lib.getLabel());
		Assert.assertTrue(lib.isImplementation());
		
		//empty jar lib
		libs = jsfLibRegistry.getJSFLibrariesByName("TEST_PP_LIB_EMPTY");
		Assert.assertNotNull(libs);
		Assert.assertEquals(1, libs.size());
		lib = (JSFLibrary)libs.get(0);
		Assert.assertTrue(lib instanceof PluginProvidedJSFLibrary);
		Assert.assertEquals(0, lib.getArchiveFiles().size());
		Assert.assertEquals("FAKE LIB FROM jsf.core.tests (2)", lib.getLabel());
		Assert.assertFalse(lib.isImplementation());
		
	}
	
}	// end of JSFLibraryRegistryTestCases
