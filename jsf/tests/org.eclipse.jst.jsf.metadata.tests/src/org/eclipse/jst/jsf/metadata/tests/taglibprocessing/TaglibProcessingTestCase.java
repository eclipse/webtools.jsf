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

import java.util.List;

import org.eclipse.jst.jsf.common.metadata.tests.AbstractBaseMetaDataTestCase;
import org.eclipse.jst.jsf.metadata.tests.MetadataTestsPlugin;
import org.eclipse.jst.jsf.metadataprocessors.IMetaDataEnabledFeature;
import org.eclipse.jst.jsf.metadataprocessors.MetaDataEnabledProcessingFactory;
import org.eclipse.jst.jsf.metadataprocessors.features.ICreateValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IDefaultValue;
import org.eclipse.jst.jsf.metadataprocessors.features.IPossibleValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidELValues;
import org.eclipse.jst.jsf.metadataprocessors.features.IValidValues;
import org.eclipse.jst.pagedesigner.editors.properties.IPropertyPageDescriptor;

public abstract class TaglibProcessingTestCase extends AbstractBaseMetaDataTestCase {
//	protected IStructuredDocumentContext docContext;
	protected String uri = "http://org.eclipse.jsf/tagprocessing";
	protected String bundle = "org.eclipse.jst.jsf.core";
	protected String barkerBundle = MetadataTestsPlugin.ID_BUNDLE;
	protected String tag = "MyTag";
	protected String attributeName;
	
	protected List<?> possibleValueAdapters;
	protected List<?> validValuesAdapters;
	protected List<?> defaultValueAdapters;
	protected List<?> createValuesAdapters;
	protected List<?> validELValuesAdapters;
	protected List<?> propertyPageDescriptorAdapters;
	
	public void setUp() throws Exception{
		super.setUp();
		
		possibleValueAdapters = getProcessorAdapters(IPossibleValues.class);
		validValuesAdapters = getProcessorAdapters(IValidValues.class);
		defaultValueAdapters = getProcessorAdapters(IDefaultValue.class);
		createValuesAdapters = getProcessorAdapters(ICreateValues.class);
		validELValuesAdapters = getProcessorAdapters(IValidELValues.class);
		propertyPageDescriptorAdapters = getProcessorAdapters(IPropertyPageDescriptor.class);
	}

	private String getAttributeNameFromTest(){
		if (attributeName == null){
			attributeName = this.getClass().getName();
			attributeName = attributeName.substring(attributeName.lastIndexOf(".") + 1);
			attributeName = attributeName.substring(0,attributeName.length() - 4);			
		}
        return attributeName;
	}
	
	protected List<? extends IMetaDataEnabledFeature> getProcessorAdapters(Class<?> featureClass) {
		return MetaDataEnabledProcessingFactory.getInstance().
			getAttributeValueRuntimeTypeFeatureProcessors(featureClass, docContext, 
					uri, tag , getAttributeNameFromTest());
	}

}
