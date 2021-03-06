/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Gerry Kessler/Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.metadata.tests.taglibprocessing;

import junit.framework.Assert;

import org.eclipse.jst.jsf.metadataprocessors.features.ELIsNotValidException;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;

public class IntegerTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertTrue(possibleValueAdapters.isEmpty());
	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		
		IValidValues vv =(IValidValues)validValuesAdapters.get(0);
		Assert.assertTrue(vv.isValidValue("0"));
		Assert.assertTrue(vv.getValidationMessages().size()==0);
		Assert.assertTrue(vv.isValidValue("255"));
		Assert.assertFalse(vv.isValidValue("False"));
		Assert.assertFalse(vv.getValidationMessages().size()==0);
		vv.getValidationMessages().clear();
		Assert.assertFalse(vv.isValidValue("-1"));
		Assert.assertTrue(vv.getValidationMessages().size()==1);
		vv.getValidationMessages().clear();
		Assert.assertFalse(vv.isValidValue("555555555555555555"));
		Assert.assertTrue(vv.getValidationMessages().size()==1);
		vv.getValidationMessages().clear();
		Assert.assertFalse(vv.isValidValue("555f9"));
		Assert.assertTrue(vv.getValidationMessages().size()==1);
		vv.getValidationMessages().clear();
		Assert.assertFalse(vv.isValidValue("2e1"));
		Assert.assertTrue(vv.getValidationMessages().size()==1);
	}
	
	public void testDefaultValues(){		
		Assert.assertNotNull(defaultValueAdapters);
		Assert.assertFalse(defaultValueAdapters.isEmpty());
		
		IDefaultValue dv =(IDefaultValue)defaultValueAdapters.get(0);
		Assert.assertTrue(dv.getDefaultValue() != null);
		Assert.assertTrue(dv.getDefaultValue().equals("10"));
	}
	
	public void testCreateValues(){		
		Assert.assertNotNull(createValuesAdapters);
		Assert.assertTrue(createValuesAdapters.isEmpty());
	}
	
	public void testGetExpectedReturnType(){
		Assert.assertNotNull(validELValuesAdapters);
		IValidELValues vvel = (IValidELValues)validELValuesAdapters.get(0);
		try {
			Assert.assertEquals("int", vvel.getExpectedRuntimeType().toUserReadableString());
		} catch (ELIsNotValidException e) {
			fail("testGetExpectedReturnType");
		}
	}
}
