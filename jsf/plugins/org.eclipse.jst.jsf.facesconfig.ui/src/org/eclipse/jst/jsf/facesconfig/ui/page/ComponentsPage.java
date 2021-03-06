/*******************************************************************************
 * Copyright (c) 2004, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.page;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jst.jsf.facesconfig.emf.AttributeType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacetType;
import org.eclipse.jst.jsf.facesconfig.emf.PropertyType;
import org.eclipse.jst.jsf.facesconfig.emf.RendererType;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.page.detail.ComponentDetailsPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.detail.ConverterDetailsPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.detail.RenderkitDetailsPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.detail.ValidatorDetailsPage;
import org.eclipse.jst.jsf.facesconfig.ui.section.ComponentMasterSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.ConverterMasterSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.FacesConfigMasterSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.RenderkitMasterSection;
import org.eclipse.jst.jsf.facesconfig.ui.section.ValidatorMasterSection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IDetailsPage;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

/**
 * The "Components" page of faces config editor.
 * 
 * @author sfshi
 * 
 */
public class ComponentsPage extends FacesConfigMasterDetailPage {

	/**
	 * The id of this page in the faces-config editor.
	 */
	public static final String PAGE_ID = "org.eclipse.jst.jsf.facesconfig.ui.page.ComponentsPage"; //$NON-NLS-1$

	private IContentOutlinePage contentOutlinePage;

	/**
	 * Constructor of ComponentsPage;
	 * 
	 * @param editor
	 *            the facesconfig editor;
	 */
	public ComponentsPage(FacesConfigEditor editor) {
		super(editor, "ComponentsPage", //$NON-NLS-1$
				EditorMessages.FacesConfigEditor_Components_TabName);
	}

	/**
	 * Create the master sections;
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage#createMasterSections(Composite,
	 *      IManagedForm, FormToolkit, FacesConfigMasterDetailPage)
	 */
	protected FacesConfigMasterSection[] createMasterSections(
			Composite composite, IManagedForm managedForm, FormToolkit toolkit,
			FacesConfigMasterDetailPage page) {
		FacesConfigMasterSection componentMaster = new ComponentMasterSection(
				composite, managedForm, toolkit, page);
		componentMaster.getSection().setExpanded(true);
		GridData gd = new GridData(GridData.FILL_BOTH);
		componentMaster.getSection().setLayoutData(gd);

		FacesConfigMasterSection converterMaster = new ConverterMasterSection(
				composite, managedForm, toolkit, page);
		converterMaster.getSection().setExpanded(false);
		GridData convertergd = new GridData(GridData.FILL_HORIZONTAL);
		converterMaster.getSection().setLayoutData(convertergd);

		FacesConfigMasterSection renderkitMaster = new RenderkitMasterSection(
				composite, managedForm, toolkit, page);
		renderkitMaster.getSection().setExpanded(false);
		GridData renderkitgd = new GridData(GridData.FILL_HORIZONTAL);
		renderkitMaster.getSection().setLayoutData(renderkitgd);

		FacesConfigMasterSection validatorMaster = new ValidatorMasterSection(
				composite, managedForm, toolkit, page);
		validatorMaster.getSection().setExpanded(false);
		GridData valicatorgd = new GridData(GridData.FILL_HORIZONTAL);
		validatorMaster.getSection().setLayoutData(valicatorgd);

		return new FacesConfigMasterSection[] { componentMaster,
				converterMaster, renderkitMaster, validatorMaster };
	}

	/**
	 * get the details page;
	 * 
	 * @see org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage#getPageKey(Object
	 *      object)
	 * @param key
	 *            the eClass object of a component.
	 */
	public IDetailsPage getPage(Object key) {
		IDetailsPage detailsPage = null;
		if (key instanceof EClass) {
			EClass eClass = (EClass) key;
			if (eClass.getClassifierID() == FacesConfigPackage.COMPONENT_TYPE) {
				detailsPage = new ComponentDetailsPage(this);
			} else if (eClass.getClassifierID() == FacesConfigPackage.RENDER_KIT_TYPE) {
				detailsPage = new RenderkitDetailsPage(this);
			} else if (eClass.getClassifierID() == FacesConfigPackage.CONVERTER_TYPE) {
				detailsPage = new ConverterDetailsPage(this);
			} else if (eClass.getClassifierID() == FacesConfigPackage.VALIDATOR_TYPE) {
				detailsPage = new ValidatorDetailsPage(this);
			}

			if (detailsPage instanceof ISelectionProvider) {
				((ISelectionProvider) detailsPage)
						.addSelectionChangedListener(this);
			}
		}
		return detailsPage;
	}

	public Object getAdapter(Class adapter) {

		if (adapter == IContentOutlinePage.class) {
			return getOutlinePage();
		}

		return super.getAdapter(adapter);
	}

	private IContentOutlinePage getOutlinePage() {

		if (contentOutlinePage == null) {
			// The content outline is just a tree.
			//
			class ComponentsContentOutlinePage extends ContentOutlinePage {
				public void createControl(Composite parent) {
					super.createControl(parent);
					TreeViewer contentOutlineViewer = getTreeViewer();
					contentOutlineViewer.addSelectionChangedListener(this);

					AdapterFactory adapterFactory = (AdapterFactory) getEditor()
							.getAdapter(AdapterFactory.class);
					// Set up the tree viewer.
					contentOutlineViewer
							.setContentProvider(new AdapterFactoryContentProvider(
									adapterFactory));
					contentOutlineViewer
							.setLabelProvider(new AdapterFactoryLabelProvider(
									adapterFactory));
					contentOutlineViewer.addFilter(new ViewerFilter() {

						public boolean select(Viewer viewer,
								Object parentElement, Object element) {
							return FacesConfigPackage.eINSTANCE
									.getComponentType().isInstance(element)
									|| FacesConfigPackage.eINSTANCE
											.getConverterType().isInstance(
													element)
									|| FacesConfigPackage.eINSTANCE
											.getRenderKitType().isInstance(
													element)
									|| FacesConfigPackage.eINSTANCE
											.getValidatorType().isInstance(
													element)
									|| FacesConfigPackage.eINSTANCE
											.getAttributeType().isInstance(
													element)
									|| FacesConfigPackage.eINSTANCE
											.getPropertyType().isInstance(
													element)
									|| FacesConfigPackage.eINSTANCE
											.getFacetType().isInstance(element)
									|| FacesConfigPackage.eINSTANCE
											.getRendererType().isInstance(
													element);
						}
					});

					contentOutlineViewer.setSorter(new ViewerSorter());
					contentOutlineViewer.setInput(getInput());
				}

			}

			contentOutlinePage = new ComponentsContentOutlinePage();

			// Listen to selection so that we can handle it is a special
			// way.
			//
			contentOutlinePage
					.addSelectionChangedListener(new ISelectionChangedListener() {
						// This ensures that we handle selections correctly.
						public void selectionChanged(SelectionChangedEvent event) {
							setSelection(event.getSelection());
						}
					});

			addSelectionChangedListener((ContentOutlinePage) contentOutlinePage);
		}

		return contentOutlinePage;

	}

	/**
	 * handle the selection changed event from outline page.
	 * 
	 * @param selection
	 */
	public void setSelection(ISelection selection) {

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection ss = (IStructuredSelection) selection;
			IStructuredSelection currentSelection = (IStructuredSelection) this
					.getSelection();
			if ((!ss.isEmpty())
					&& (!currentSelection.isEmpty())
					&& (ss.getFirstElement() == currentSelection
							.getFirstElement())) {
				return;
			}
			EObject component = null;
			if (ss.getFirstElement() instanceof AttributeType
					|| ss.getFirstElement() instanceof PropertyType
					|| ss.getFirstElement() instanceof FacetType
					|| ss.getFirstElement() instanceof RendererType) {
				component = ((EObject) ss.getFirstElement()).eContainer();
			} else if (ss.getFirstElement() instanceof EObject) {
				component = (EObject) ss.getFirstElement();
			}

			if (component != null) {
				FacesConfigMasterSection section = null;
				if (FacesConfigPackage.eINSTANCE.getComponentType().isInstance(
						component)) {
					section = getFacesConfigMasterSections()[0];
				} else if (FacesConfigPackage.eINSTANCE.getConverterType()
						.isInstance(component)) {
					section = getFacesConfigMasterSections()[1];
				} else if (FacesConfigPackage.eINSTANCE.getRenderKitType()
						.isInstance(component)) {
					section = getFacesConfigMasterSections()[2];
				} else if (FacesConfigPackage.eINSTANCE.getValidatorType()
						.isInstance(component)) {
					section = getFacesConfigMasterSections()[3];
				}

				if (!section.getSection().isExpanded()) {
					this.closeOtherSections(section);
					GridData gd = new GridData(GridData.FILL_BOTH);
					section.getSection().setLayoutData(gd);
					section.getSection().setExpanded(true);
				}
				IStructuredSelection newselection = new StructuredSelection(
						component);
				section.getStructuredViewer().setSelection(newselection);
			}
		}

	}
}
