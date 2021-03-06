/***************************************************************************************************
 * Copyright (c) 2005, 2006 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 **************************************************************************************************/

package org.eclipse.jst.jsf.facesconfig.tests.read;

import org.eclipse.jst.jsf.facesconfig.emf.ApplicationFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesContextFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.FactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.LifecycleFactoryType;
import org.eclipse.jst.jsf.facesconfig.emf.RenderKitFactoryType;
import org.eclipse.jst.jsf.facesconfig.tests.util.FacesConfigModelUtil;
import org.eclipse.jst.jsf.facesconfig.util.FacesConfigArtifactEdit;


/*
* This Junit class is used to test the FacesConfigFactoryImpl
* class. 
* 
*/
public class ReadFactoryTestCase extends BaseReadTestCase {

	public ReadFactoryTestCase(String name) {
		super(name);
	}

	/*
	 * Test the applicaion element of faces-config.xml
	 * 
	 */
	public void testEmptyFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			assertEquals(1, edit.getFacesConfig().getFactory().size());
            assertNotNull(getFactoryType1(edit.getFacesConfig()));
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    FactoryType getFactoryType1(FacesConfigType facesConfig)
    {
        return (FactoryType) FacesConfigModelUtil
            .findEObjectElementById(facesConfig.getFactory(), "factory1");
    }
	/*
	 * Test the application-factory element of Factory in faces-config.xml
	 * 
	 */
	public void testApplicationFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
			FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
			assertNotNull(factoryType1);
            ApplicationFactoryType applicationFactoryType1 =
                (ApplicationFactoryType) FacesConfigModelUtil.findEObjectElementById
                (factoryType1.getApplicationFactory(), "applicationFactory1");
            assertNotNull(applicationFactoryType1);
			assertEquals("application-factory",
                    applicationFactoryType1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}
	
	/*
	 * Test the  faces-context-factory element of Factory in faces-config.xml
	 */
	public void testFacesContextFactory() {

		FacesConfigArtifactEdit edit = null;
		try {
			edit = getArtifactEditForRead();
			assertNotNull(edit.getFacesConfig());
            FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
            assertNotNull(factoryType1);
            FacesContextFactoryType facesContextFactoryType1 =
                (FacesContextFactoryType) FacesConfigModelUtil.findEObjectElementById
                    (factoryType1.getFacesContextFactory(), "facesContextFactory1");
            assertNotNull(facesContextFactoryType1);
            assertEquals("faces-context-factory",
                    facesContextFactoryType1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}


	/*
	 * Test the application-factory element of Factory in faces-config.xml
	 * 
	 */
	public void testLifeCycleFactory() {
		FacesConfigArtifactEdit edit = null;
		try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
            assertNotNull(factoryType1);
            LifecycleFactoryType lifecycleFactoryType1 =
                (LifecycleFactoryType) FacesConfigModelUtil.findEObjectElementById
                    (factoryType1.getLifecycleFactory(), "lifecycleFactory1");
            assertNotNull(lifecycleFactoryType1);
            assertEquals("lifecycle-factory",
                         lifecycleFactoryType1.getTextContent().trim());
		} finally {
			if (edit != null) {
				edit.dispose();
			}
		}
	}

    /**
     * Test the renderkit-factory element
     */
    public void testRenderkitFactory()
    {
        FacesConfigArtifactEdit edit = null;
        try {
            edit = getArtifactEditForRead();
            assertNotNull(edit.getFacesConfig());
            FactoryType factoryType1 = getFactoryType1(edit.getFacesConfig());
            assertNotNull(factoryType1);
            RenderKitFactoryType renderKitFactoryType1 =
                (RenderKitFactoryType) FacesConfigModelUtil.findEObjectElementById
                    (factoryType1.getRenderKitFactory(), "renderKitFactory1");
            assertNotNull(renderKitFactoryType1);
            assertEquals("render-kit-factory",
                         renderKitFactoryType1.getTextContent().trim());
        } finally {
            if (edit != null) {
                edit.dispose();
            }
        }
    }

}