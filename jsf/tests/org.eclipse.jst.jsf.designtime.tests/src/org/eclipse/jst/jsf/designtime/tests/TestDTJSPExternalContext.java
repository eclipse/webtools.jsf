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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.symbol.IInstanceSymbol;
import org.eclipse.jst.jsf.context.symbol.ISymbol;
import org.eclipse.jst.jsf.context.symbol.SymbolFactory;
import org.eclipse.jst.jsf.context.symbol.source.ISymbolConstants;
import org.eclipse.jst.jsf.core.IJSFCoreConstants;
import org.eclipse.jst.jsf.core.tests.util.JSFCoreUtilHelper;
import org.eclipse.jst.jsf.core.tests.util.JSFFacetedTestEnvironment;
import org.eclipse.jst.jsf.designtime.DesignTimeApplicationManager;
import org.eclipse.jst.jsf.designtime.context.DTJSPExternalContext;
import org.eclipse.jst.jsf.designtime.context.IDTExternalContext;
import org.eclipse.jst.jsf.test.util.JDTTestEnvironment;
import org.eclipse.jst.jsf.test.util.JSFTestUtil;
import org.eclipse.jst.jsf.test.util.TestFileResource;
import org.eclipse.jst.jsf.test.util.WebProjectTestEnvironment;

public class TestDTJSPExternalContext extends TestCase 
{
	private IFile _testJSP1;
	private JSFFacetedTestEnvironment _jsfFactedTestEnvironment;

	@Override
    protected void setUp() throws Exception 
	{
        super.setUp();
        JSFTestUtil.setValidationEnabled(false);
        JSFTestUtil.setInternetProxyPreferences(true, "www-proxy.us.oracle.com","80");

        final WebProjectTestEnvironment  projectTestEnvironment = 
            new WebProjectTestEnvironment(getProjectName());
        projectTestEnvironment.createProject(false);

        final JDTTestEnvironment jdtTestEnvironment = 
        	new JDTTestEnvironment(projectTestEnvironment);

        final TestFileResource input = new TestFileResource();
        input.load(DesignTimeTestsPlugin.getDefault().getBundle(), 
        		"/testdata/bundle1.resources.data");
        jdtTestEnvironment.addResourceFile("src"
        		, new ByteArrayInputStream(input.toBytes())
        		, "bundles", "bundle1.properties");
        
        final IResource res = projectTestEnvironment.loadResourceInWebRoot(DesignTimeTestsPlugin.getDefault().getBundle()
        		, "/testdata/testdata1.jsp.data", "testdata1.jsp");
        _testJSP1 = (IFile) res;

        _jsfFactedTestEnvironment = new JSFFacetedTestEnvironment(projectTestEnvironment);
        _jsfFactedTestEnvironment.initialize(IJSFCoreConstants.FACET_VERSION_1_1);
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(JSFCoreUtilHelper.createSimpleRegistryFactory());
    }

	@Override
    protected void tearDown() throws Exception 
	{
		super.tearDown();
        JSFCoreUtilHelper.injectTestTagRegistryFactoryProvider(null);
	}

	public void testDefaultDoGetMapForScopeInt() 
	{
		final DesignTimeApplicationManager manager = 
			DesignTimeApplicationManager.getInstance(_testJSP1.getProject());
		
		{
			final IDTExternalContext externalContext = 
				manager.getFacesContext(_testJSP1).getDTExternalContext(_testJSP1);
	
			final Map<String, ISymbol> requestMap = externalContext.getRequestMap();
			final Collection<ISymbol> symbols = requestMap.values();
			// there is a bundle defined in the test JSP
			// also a data table row variable
			assertEquals(2, symbols.size());
			assertContainsVariable(symbols,"bundle");
			assertContainsVariable(symbols,"row");
		}
		
		{
			final TestableDTJSPExternalContext externalContext =
				new TestableDTJSPExternalContext(_testJSP1);
		
			final Map<String, ISymbol> requestMap = externalContext.getRequestMap();
			Collection<ISymbol> symbols = requestMap.values();
			assertEquals(1, symbols.size());
			assertContainsVariable(symbols,"requestSymbol");

			final Map<String, ISymbol> sessionMap = externalContext.getSessionMap();
			symbols = sessionMap.values();
			assertEquals(1, symbols.size());
			assertContainsVariable(symbols,"sessionSymbol");

			final Map<String, ISymbol> applicationMap = externalContext.getApplicationMap();
			symbols = applicationMap.values();
			assertEquals(1, symbols.size());
			assertContainsVariable(symbols,"applicationSymbol");

			final Map<String, ISymbol> noneMap = externalContext.getNoneMap();
			symbols = noneMap.values();
			assertEquals(1, symbols.size());
			assertContainsVariable(symbols,"noneSymbol");
			
//			final Map<String, ISymbol> viewMap = externalContext.getFlashMap();
//			symbols = viewMap.values();
//			assertEquals(1, symbols.size());
//			assertContainsVariable(symbols,"flashSymbol");
			
			externalContext.trace(System.out);
		}
	}

	public void testGetRequestContextPath()
	{
       final DesignTimeApplicationManager manager = 
            DesignTimeApplicationManager.getInstance(_testJSP1.getProject());
       final IDTExternalContext externalContext = 
                manager.getFacesContext(_testJSP1).getDTExternalContext(_testJSP1);
       assertTrue(externalContext instanceof DTJSPExternalContext);
       assertEquals(getProjectName(), externalContext.getRequestContextPath());
	}
	private void assertContainsVariable(final Collection<ISymbol> variables, final String name)
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
	
	private String getProjectName()
	{
	    return "TestDTJSPExternalContext"+getName();
	}
	
	private class TestableDTJSPExternalContext extends DTJSPExternalContext
	{
		protected TestableDTJSPExternalContext(final IAdaptable jspFile) 
		{
			super(jspFile);
		}

		@Override
		protected Map<String, ISymbol> doGetMapForScope(final int scopeMask) 
		{
			switch(scopeMask)
			{
				case ISymbolConstants.SYMBOL_SCOPE_REQUEST:
					return initRequestMap();
					
				case ISymbolConstants.SYMBOL_SCOPE_SESSION:
					return initSessionMap();
					
				case ISymbolConstants.SYMBOL_SCOPE_APPLICATION:
					return initApplicationMap();
					
				case ISymbolConstants.SYMBOL_SCOPE_NONE:
					return initNoneMap();
					
//				case ISymbolConstants.SYMBOL_SCOPE_FLASH:
//					return initFlashMap();	
//					
			}
			
			throw new IllegalArgumentException();
		}
		
		private Map<String, ISymbol> initRequestMap()
		{
			final Map<String, ISymbol> map = new HashMap<String, ISymbol>();
			final ISymbol  symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
			symbol.setName("requestSymbol");
			map.put("requestSymbol", symbol);
			
			return map;
		}
		
		private Map<String, ISymbol> initSessionMap()
		{
			final Map<String, ISymbol> map = new HashMap<String, ISymbol>();

			final ISymbol  symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
			symbol.setName("sessionSymbol");
			map.put("sessionSymbol", symbol);
			
			return map;
		}
		
		private Map<String, ISymbol> initApplicationMap()
		{
			final Map<String, ISymbol> map = new HashMap<String, ISymbol>();

			final ISymbol  symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
			symbol.setName("applicationSymbol");
			map.put("applicationSymbol", symbol);
			
			return map;
		}

		private Map<String, ISymbol> initNoneMap()
		{
			final Map<String, ISymbol> map = new HashMap<String, ISymbol>();

			final ISymbol  symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
			symbol.setName("noneSymbol");
			map.put("noneSymbol", symbol);
			
			return map;
		}
		
//		private Map<String, ISymbol> initFlashMap()
//		{
//			final Map<String, ISymbol> map = new HashMap<String, ISymbol>();
//
//			final ISymbol  symbol = SymbolFactory.eINSTANCE.createIComponentSymbol();
//			symbol.setName("flashSymbol");
//			map.put("flashSymbol", symbol);
//			
//			return map;
//		}
	}
}
