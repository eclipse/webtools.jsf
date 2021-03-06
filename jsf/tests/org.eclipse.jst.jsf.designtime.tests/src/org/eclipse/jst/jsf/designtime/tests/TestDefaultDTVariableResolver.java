/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.designtime.tests;

import java.io.ByteArrayInputStream;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jdt.core.IType;
import org.eclipse.jst.jsf.context.symbol.IBeanInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IComponentSymbol;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.IJavaTypeDescriptor2;
import org.eclipse.jst.jsf.context.symbol.IMapTypeDescriptor;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.ITypeDescriptor;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.el.DefaultDTVariableResolver;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestDefaultDTVariableResolver extends TestCase 
{
    private IType                           _testBean1Type;
	private JSFFacetedTestEnvironment 	_jsfFactedTestEnvironment;
	private JDTTestEnvironment 			_jdtTestEnvironment;
	private IFile						_testJSP1;

    private final static String     SRC_FOLDER_NAME = "src";
    private final static String     PACKAGE_NAME = "com.test";
    private final static String     TESTBEAN1_NAME = "TestBean1";

	
	@Override
    @SuppressWarnings("unchecked")
    protected void setUp() throws Exception 
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment("TestDefaultPropertyResolver_"+getName());
        projectTestEnvironment.createProject(false);

        final IResource res = projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP1 = (IFile) res;
        
        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);

        final IProject project = projectTestEnvironment.getTestProject();
        
        FacesConfigArtifactEdit edit = null;
        
        try
        {
        	edit = FacesConfigArtifactEdit.getFacesConfigArtifactEditForWrite(project, null);
        
        	final FacesConfigType model = edit.getFacesConfig();
        	final ManagedBeanClassType beanClass = FacesConfigFactory.eINSTANCE.createManagedBeanClassType();
        	beanClass.setTextContent("com.test.TestBean1");

        	final ManagedBeanNameType beanName = FacesConfigFactory.eINSTANCE.createManagedBeanNameType();
        	beanName.setTextContent("testBean1");
        	
        	final ManagedBeanScopeType beanScope = FacesConfigFactory.eINSTANCE.createManagedBeanScopeType();
        	beanScope.setTextContent("session");
        	
        	final ManagedBeanType bean = FacesConfigFactory.eINSTANCE.createManagedBeanType();
        	bean.setManagedBeanClass(beanClass);
        	bean.setManagedBeanName(beanName);
        	bean.setManagedBeanScope(beanScope);
        	
        	model.getManagedBean().add(bean);
        	
        	edit.save(null);
        }
        finally
        {
        	if (edit != null)
        	{
        		edit.dispose();
        	}
        }
        
        _jdtTestEnvironment = new JDTTestEnvironment(projectTestEnvironment);
        
        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        _jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");

        JSFTestUtil.loadSourceClass(
                DesignTimeTestsPlugin.getDefault().getBundle(), 
                    "/testdata/TestBean1.java.data", TESTBEAN1_NAME, SRC_FOLDER_NAME, PACKAGE_NAME, _jdtTestEnvironment);
        _testBean1Type = _jdtTestEnvironment.getJavaProject().findType(PACKAGE_NAME+"."+TESTBEAN1_NAME);
        assertNotNull(_testBean1Type);
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
	}

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
    }

	public void testResolveVariable() 
	{
		checkBuiltinVariables();
		checkSymbolMaps();
		checkManagedBeanVariable();
	}

	private void checkBuiltinVariables()
	{
        final DesignTimeApplicationManager manager =
        	DesignTimeApplicationManager.getInstance
        		(_jdtTestEnvironment.getProjectEnvironment().getTestProject());

		final DefaultDTVariableResolver  variableResolver = new DefaultDTVariableResolver();
		final ISymbol symbol = variableResolver.resolveVariable
			(manager.getFacesContext(_testJSP1), "applicationScope", _testJSP1);
		assertNotNull(symbol);
	}
	
	private void checkSymbolMaps()
	{
        final DesignTimeApplicationManager manager =
        	DesignTimeApplicationManager.getInstance
        		(_jdtTestEnvironment.getProjectEnvironment().getTestProject());

		final DefaultDTVariableResolver  variableResolver = new DefaultDTVariableResolver();
		final ISymbol symbol = variableResolver.resolveVariable
			(manager.getFacesContext(_testJSP1), "bundle", _testJSP1);
		assertNotNull(symbol);
		assertTrue(symbol instanceof IComponentSymbol);
		
		final IComponentSymbol compSymbol = (IComponentSymbol) symbol;
		assertEquals("bundle", compSymbol.getName());
		final ITypeDescriptor typeDesc = compSymbol.getTypeDescriptor();
		assertTrue(typeDesc instanceof IMapTypeDescriptor);
	}
	
	private void checkManagedBeanVariable()
	{
        final DesignTimeApplicationManager manager =
        	DesignTimeApplicationManager.getInstance
        		(_jdtTestEnvironment.getProjectEnvironment().getTestProject());

		final DefaultDTVariableResolver  variableResolver = new DefaultDTVariableResolver();
		final ISymbol symbol = variableResolver.resolveVariable
			(manager.getFacesContext(_testJSP1), "testBean1", _testJSP1);
		assertNotNull(symbol);
		assertTrue(symbol instanceof IBeanInstanceSymbol);
		
		final IBeanInstanceSymbol compSymbol = (IBeanInstanceSymbol) symbol;
		assertEquals("testBean1", compSymbol.getName());
		final ITypeDescriptor typeDesc = compSymbol.getTypeDescriptor();
		assertTrue(typeDesc instanceof IJavaTypeDescriptor2);
		assertEquals("Lcom.test.TestBean1;", typeDesc.getTypeSignature());
	}
	
	public void testGetAllVariables() 
	{
        final DesignTimeApplicationManager manager =
        	DesignTimeApplicationManager.getInstance
        		(_jdtTestEnvironment.getProjectEnvironment().getTestProject());

		final DefaultDTVariableResolver  variableResolver = new DefaultDTVariableResolver();
		
		final ISymbol[] variables = variableResolver.getAllVariables
			(manager.getFacesContext(_testJSP1), _testJSP1);
		
		assertContainsVariable(variables, "applicationScope");
		assertContainsVariable(variables, "sessionScope");
		assertContainsVariable(variables, "requestScope");
		assertContainsVariable(variables, "cookie");
		assertContainsVariable(variables, "facesContext");
		assertContainsVariable(variables, "header");
		assertContainsVariable(variables, "headerValues");
		assertContainsVariable(variables, "initParam");
		assertContainsVariable(variables, "param");
		assertContainsVariable(variables, "paramValues");
		assertContainsVariable(variables, "view");
		assertContainsVariable(variables, "testBean1");
		assertContainsVariable(variables, "bundle");
		
		//this is a JSF1.x context... there should be no JSF2.0 implicit variables
		assertDoesNotContainVariable(variables, "viewScope");
		assertDoesNotContainVariable(variables, "flash");
		assertDoesNotContainVariable(variables, "cc");
		assertDoesNotContainVariable(variables, "component");
		assertDoesNotContainVariable(variables, "resource");
	}

	private void assertContainsVariable(final ISymbol[] variables, final String name)
	{
		for (final ISymbol variable : variables)
		{
			if (name.equals(variable.getName()))
			{
				assertTrue(variable instanceof IInstanceSymbol);
				return;
			}
		}
		
		fail("Expected variable not found: "+name);
	}
	
	private void assertDoesNotContainVariable(final ISymbol[] variables, final String name)
	{
		for (final ISymbol variable : variables)
		{
			if (name.equals(variable.getName()))
			{
				fail("Variable was not expected to be found: "+name);
				return;
			}
		}
				
	}
}
