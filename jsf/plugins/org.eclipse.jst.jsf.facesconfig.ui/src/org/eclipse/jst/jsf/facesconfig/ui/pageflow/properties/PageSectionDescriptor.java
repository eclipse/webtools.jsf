/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties;

import java.util.List;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowNodeEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.impl.PageflowPageImpl;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.properties.section.PageSection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;

/**
 * @author jchoi
 * @version
 */
public class PageSectionDescriptor implements ISectionDescriptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getId()
	 */
	public String getId() {
		return ITabbedPropertiesConstants.PAGE_SECTION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getInputTypes()
	 */
	public List getInputTypes() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getSectionClass()
	 */
	public ISection getSectionClass() {
		return new PageSection();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getTargetTab()
	 */
	public String getTargetTab() {
		return ITabbedPropertiesConstants.ATTRIBUTE_TAB_ID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#appliesTo(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public boolean appliesTo(IWorkbenchPart part, ISelection selection) {
		Object object = null;
		if (selection instanceof StructuredSelection) {
			StructuredSelection structuredSelection = (StructuredSelection) selection;
			object = structuredSelection.getFirstElement();
			if (object instanceof PageflowNodeEditPart) {
				Object model = ((PageflowNodeEditPart) object).getModel();
				if (model instanceof PageflowPageImpl) {
					return true;
				}
			}
		}
		return false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.wst.common.ui.properties.internal.provisional.ISectionDescriptor#getAfterSection()
	 */
	public String getAfterSection() {
		return ITabbedPropertiesConstants.TOP_SECTION;
	}

	public int getEnablesFor() {
		return ENABLES_FOR_ANY;
	}


	public IFilter getFilter() {
		return null;
	}

}
