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
package org.eclipse.jst.jsf.core.tests.project.facet;

import java.util.Set;

import junit.framework.Assert;
import junit.framework.TestCase;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.core.internal.jsflibraryconfig.JSFLibraryRegistryUtil;
import org.eclipse.jst.jsf.core.internal.project.facet.IJSFFacetInstallDataModelProperties;
import org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.wst.common.frameworks.datamodel.DataModelFactory;
import org.eclipse.wst.common.frameworks.datamodel.IDataModel;

@SuppressWarnings("deprecation")
public class JSFFacetInstallDataModelProviderTestCases extends TestCase {
	private static final String PROJ_2_3_NAME = "_TEST_2_3_PROJECT";
	//private static final String PROJ_2_4_NAME = "_TEST_2_4_PROJECT";
	
	private JSFFacetInstallDataModelProvider dm;
	
	protected void setUp() throws Exception {		
		//seed JSFLib registry if not present
		JSFCoreUtilHelper.createJSFLibraryRegistry();
		//create a project, if one doesn't exist in the current workspace
		IProject project = JSFCoreUtilHelper.createWebProject(PROJ_2_3_NAME);
		//create lib
		JSFLibraryRegistryUtil.getInstance().getJSFLibraryRegistry().getDefaultImplementation();
		
		dm = new JSFFacetInstallDataModelProvider();
		IDataModel model = DataModelFactory.createDataModel(dm);	
		model.setStringProperty(IJSFFacetInstallDataModelProperties.FACET_PROJECT_NAME, project.getName());
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.getPropertyNames()'
	 */
	public void testGetPropertyNames() {	
		Set<?> names = dm.getPropertyNames();
		Assert.assertNotNull(names);
//		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.IMPLEMENTATION));
//		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.SERVLET_NAME));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS));
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.WEBCONTENT_DIR));
		
		Assert.assertTrue(names.contains(IJSFFacetInstallDataModelProperties.LIBRARY_PROVIDER_DELEGATE));
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.getDefaultProperty(String)'
	 */
	public void testGetDefaultPropertyString() {
//		Assert.assertNotNull(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION));
//		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION) instanceof JSFLibraryInternalReference);
//		JSFLibraryInternalReference ref = (JSFLibraryInternalReference)dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION) ;
//		Assert.assertTrue(ref.getLibrary() == jsfLib);
//		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.DEPLOY_IMPLEMENTATION) == Boolean.TRUE);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH) != null);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME) != null);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_URL_PATTERNS) != null);
		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.WEBCONTENT_DIR) != null);
		
//		Assert.assertTrue(dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.LIBRARY_PROVIDER_DELEGATE) != null);
	}

	/*
	 * Test method for 'org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.validate(String)'

	public void testValidateString() {
		//positive tests		
		IDataModel model = dm.getDataModel();
		
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, (String)dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH));
		model.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, jsfLib);
		model.setStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, (String)dm.getDefaultProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME));
		
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.IMPLEMENTATION).isOK());
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.SERVLET_NAME).isOK());
		
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "foo.xml");
		Assert.assertTrue(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		//negative tests
		//config path
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());

		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "../../../foo.xml");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "WEB-INF/xxx.txt");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		
		String pathWithDevice = new Path(Platform.getLocation().getDevice(), "/temp/faces-config.xml").toOSString();
		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, pathWithDevice );
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());

		model.setStringProperty(IJSFFacetInstallDataModelProperties.CONFIG_PATH, "\\WEB-INF\\xxx.txt");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());

		//impl
		model.setProperty(IJSFFacetInstallDataModelProperties.IMPLEMENTATION, null);				
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.CONFIG_PATH).isOK());
		
		//servlet name		
		//can't check null name because null set returns default value
		model.setStringProperty(IJSFFacetInstallDataModelProperties.SERVLET_NAME, "   ");
		Assert.assertFalse(dm.validate(IJSFFacetInstallDataModelProperties.SERVLET_NAME).isOK());

	}
	 */


    /*
     * Test method for: 
     *     org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.isValidConfigFileName()
     *     org.eclipse.jst.jsf.core.internal.project.facet.JSFFacetInstallDataModelProvider.validateConfigLocation()
     */
    public void testConfigFileNameValidation ()
    {
        assertTrue(JSFFacetInstallDataModelProvider.isValidConfigFileName("faces-config.xml"));
        assertTrue(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml"));
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces config.xml")); // Invalid
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config//faces-config.xml")); // Invalid
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config///faces-config.xml")); // Invalid 
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml,/WEB-INF/config/faces-config2.xml")); // Invalid 
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml, /WEB-INF/config/faces-config2.xml")); // Invalid 
        
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml, ")); // Invalid 
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml. ")); // Invalid 

        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml%")); // Invalid 
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml.&65")); // Invalid 
        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/faces-config.xml*")); // Invalid 

        assertFalse(JSFFacetInstallDataModelProvider.isValidConfigFileName("/WEB-INF/config/face*s-config.xml*")); // Invalid 
    }
}
