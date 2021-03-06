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
package org.eclipse.jst.jsf.metadata.tests.metadataprocessing;

import java.util.List;

import org.eclipse.jst.jsf.metadata.tests.metadataprocessing.features.IBarker;
import org.eclipse.jst.jsf.metadataprocessors.AbstractMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;

/**
 * Test class implementing existing and new fetaure type for
 * testing the MetaDataEnabledFeature Extension
 * 
 * @author Gerry Kessler - Oracle
 *
 */
public class MetaDataEnabledBarkAdapter extends AbstractMetaDataEnabledFeature
	implements
		IBarker, IPossibleValues{

	public MetaDataEnabledBarkAdapter() {
		super();
	}

	public boolean canBark() {
		return true;
	}

	public List<?> getBarks() {
		return getTraitValueAsListOfStrings("barks");
//		//notice that we want to use the bundle id of the extender
//		return CMAnnotationHelper.getCMAttributePropertyValues(MetadataTestsPlugin.ID_BUNDLE, getCMAnnotationContext().getUri(),
//					getCMAnnotationContext().getElementName(), getCMAnnotationContext().getAttributeName(), 
//					"barks");
	}

	public List<?> getPossibleValues() {
		return getBarks();
	}


}
