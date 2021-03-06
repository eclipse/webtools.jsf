/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.page.detail;

import org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage;
import org.eclipse.jst.jsf.facesconfig.ui.section.AttributeSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.IFacesConfigSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.PropertySection;
import org.eclipse.jst.jsf.facesconfig.ui.section.ValidatorGeneralSection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * @author Bryan Yang
 * 
 */
public class ValidatorDetailsPage extends FacesConfigDetailsPage {

	/**
	 * 
	 * @param page
	 */
	public ValidatorDetailsPage(FacesConfigMasterDetailPage page) {
		super(page);
	}

	/**
	 * create the detail sections and set layout for them.
	 */
	protected IFacesConfigSection[] createDetailSections(Composite composite,
			IManagedForm managedForm, FormToolkit toolkit, FacesConfigMasterDetailPage page) {
		ValidatorGeneralSection generalSection = new ValidatorGeneralSection(
				composite, managedForm, getPage(), toolkit);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		generalSection.getSection().setLayoutData(gd);

		AttributeSection attributeSection = new AttributeSection(composite,
				managedForm, getPage(), toolkit);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		attributeSection.getSection().setLayoutData(gd);

		PropertySection propertySection = new PropertySection(composite,
				managedForm, getPage(), toolkit);
		gd = new GridData(GridData.FILL_BOTH);
		propertySection.getSection().setLayoutData(gd);
		return new IFacesConfigSection[] { generalSection, attributeSection,
				propertySection };
	}

}
