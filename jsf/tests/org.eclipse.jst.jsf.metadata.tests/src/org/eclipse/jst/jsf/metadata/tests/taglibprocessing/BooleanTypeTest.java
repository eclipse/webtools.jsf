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

import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;


public class BooleanTypeTest extends TaglibProcessingTestCase {
	public void testPossibleValues(){		
		Assert.assertNotNull(possibleValueAdapters);
		Assert.assertFalse(possibleValueAdapters.isEmpty());
		Assert.assertEquals(2, possibleValueAdapters.size());
		IPossibleValues pv =(IPossibleValues)possibleValueAdapters.get(0);
		Assert.assertEquals(pv.getPossibleValues().size(), 2);//true and false
	
		
		//validate BarkProcessorFeature does not kick in for this tag
		//as the tests have added a second impl of IPossibleVals check
//		pv =(IPossibleValues)getBarkProcessingBundle(possibleValueAdapters);
		pv =(IPossibleValues)possibleValueAdapters.get(1);
		Assert.assertNotNull(pv);
		//bark annotation is not on the MyTag element MyAttr attr
		Assert.assertTrue(pv.getPossibleValues().isEmpty());

	}
	
	public void testValidValues(){		
		Assert.assertNotNull(validValuesAdapters);
		Assert.assertFalse(validValuesAdapters.isEmpty());
		IValidValues vv =(IValidValues)validValuesAdapters.get(0);
		vv.getValidationMessages().clear();
		Assert.assertTrue(vv.isValidValue("true"));
		Assert.assertTrue(vv.getValidationMessages().size()==0);
		vv.getValidationMessages().clear();
		Assert.assertTrue(vv.isValidValue("false"));
		vv.getValidationMessages().clear();
		Assert.assertTrue(vv.isValidValue("False"));
		Assert.assertEquals(vv.getValidationMessages().size(), 0);
		vv.getValidationMessages().clear();
		Assert.assertFalse(vv.isValidValue("bogus"));
		Assert.assertEquals(vv.getValidationMessages().size(), 1);
	}
	
	public void testDefaultValues(){		
		Assert.assertNotNull(defaultValueAdapters);
		Assert.assertFalse(defaultValueAdapters.isEmpty());
		Assert.assertTrue(((IDefaultValue)defaultValueAdapters.get(0)).getDefaultValue().equals("true"));
	}
	
	public void testCreateValues(){		
		Assert.assertNotNull(createValuesAdapters);
		Assert.assertTrue(createValuesAdapters.isEmpty());
	}
	
}
