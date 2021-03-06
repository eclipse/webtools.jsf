/*******************************************************************************
 * Copyright (c) 2004, 2011 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jst.jsf.facesconfig.emf.DescriptionType;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigFactory;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanClassType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanNameType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanScopeType;
import org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage;
import org.eclipse.jst.jsf.facesconfig.ui.page.IFacesConfigPage;
import org.eclipse.jst.jsf.facesconfig.ui.provider.ManagedBeanContentProvider;
import org.eclipse.jst.jsf.facesconfig.ui.provider.ManagedBeanLabelProvider;
import org.eclipse.jst.jsf.facesconfig.ui.wizard.NewManagedBeanWizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.FileEditorInput;

/**
 * @author sfshi
 */
public class ManagedBeanMasterSection extends FacesConfigMasterSection {

	private ManagedBeanMasterSectionAdapter managedBeanMasterSectionAdapter;

	/**
	 * @param parent
	 * @param managedForm
	 * @param toolkit
	 * @param page
	 */
	public ManagedBeanMasterSection(Composite parent, IManagedForm managedForm,
			FormToolkit toolkit, FacesConfigMasterDetailPage page) {
		super(parent, managedForm, toolkit, page, null, null);
		getSection().setText(EditorMessages.ManagedBeanMasterSection_Name);
		getSection().setDescription(
				EditorMessages.ManagedBeanMasterSection_Description);
	}

	protected StructuredViewer createViewer(Composite parent,
			FormToolkit toolkit) {
		Composite treeContainer = toolkit.createComposite(parent);
		toolkit.paintBordersFor(treeContainer);

		GridData gd = new GridData(GridData.FILL_BOTH);
		treeContainer.setLayoutData(gd);
		GridLayout layout = new GridLayout();
		treeContainer.setLayout(layout);

		// Create tree viewer
		TreeViewer treeViewer = new TreeViewer(treeContainer, SWT.SINGLE
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		treeViewer.getControl().setLayoutData(gd);

		//Bug 312727 - [JSF2.0] Add view scope to FacesConfigEditor for Managed Beans
		IContentProvider contentProvider = new ManagedBeanContentProvider();
		final IFacesConfigPage page = getPage();
		if (page != null) {
			final IEditorInput input = page.getEditorInput();
			if (input instanceof FileEditorInput) {
				final IFile file = ((FileEditorInput)input).getFile();
				if (file != null) {
					final IProject project = file.getProject();
					if (project != null) {
						contentProvider = new ManagedBeanContentProvider(project);
					}
				}
			}
		}
		treeViewer.setContentProvider(contentProvider);

		treeViewer.setLabelProvider(new ManagedBeanLabelProvider());
		treeViewer.addSelectionChangedListener(this);

		treeViewer.setAutoExpandLevel(3);
		return treeViewer;
	}

	protected void addButtonSelected(SelectionEvent e) {
		IProject project = (IProject) this.getPage().getEditor().getAdapter(
				IProject.class);

		NewManagedBeanWizard wizard = new NewManagedBeanWizard(project);
		IStructuredSelection selection = (IStructuredSelection) this
				.getSelection();
		if (selection.getFirstElement() instanceof ManagedBeanScopeTreeItem) {
			// if user is selecting a scope tree item, then use this scope as
			// the default scope for the new managed bean.
			ManagedBeanScopeTreeItem scopeTreeItem = (ManagedBeanScopeTreeItem) selection
					.getFirstElement();
			wizard.setDefaultScope(scopeTreeItem.getScope());
		}

		wizard.setForcePreviousAndNextButtons(true);

		Shell shell = EditorPlugin.getActiveShell();
		WizardDialog wizardDialog = new WizardDialog(shell, wizard);
		wizardDialog.create();
		wizardDialog.setBlockOnOpen(true);
		if (wizardDialog.open() == Window.OK) {
			ManagedBeanType managedBean = FacesConfigFactory.eINSTANCE
					.createManagedBeanType();

			ManagedBeanNameType name = FacesConfigFactory.eINSTANCE
					.createManagedBeanNameType();
			name.setTextContent(wizard.getManagedBeanName());
			managedBean.setManagedBeanName(name);

			ManagedBeanClassType clazz = FacesConfigFactory.eINSTANCE
					.createManagedBeanClassType();
			clazz.setTextContent(wizard.getManagedBeanClass());
			managedBean.setManagedBeanClass(clazz);

			ManagedBeanScopeType scope = FacesConfigFactory.eINSTANCE
					.createManagedBeanScopeType();
			scope.setTextContent(wizard.getManagedBeanScope());
			managedBean.setManagedBeanScope(scope);

			String desc = wizard.getManagedBeanDescription();
			if (desc != null && desc.trim().length() > 0) {
				DescriptionType description = FacesConfigFactory.eINSTANCE
						.createDescriptionType();
				description.setTextContent(wizard.getManagedBeanDescription());
				managedBean.getDescription().add(description);
			}

			Command cmd = AddCommand.create(getEditingDomain(), getInput(),
					null, managedBean);
			if (cmd.canExecute()) {
				this.getEditingDomain().getCommandStack().execute(cmd);
			}
		}

	}

	protected void removeButtonSelected(SelectionEvent e) {
		IStructuredSelection ssel = StructuredSelection.EMPTY;
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			ssel = (IStructuredSelection) selection;
		}

		if (!ssel.isEmpty()) {
			if (ssel.getFirstElement() instanceof ManagedBeanType) {
				ManagedBeanType element = (ManagedBeanType) ssel
						.getFirstElement();
				Command command = RemoveCommand.create(getEditingDomain(), this
						.getInput(), FacesConfigPackage.eINSTANCE
						.getFacesConfigType_ManagedBean(), element);
				if (command.canExecute()) {
					getEditingDomain().getCommandStack().execute(command);
				}
			}
		}
	}

	protected void updateButtons() {
		IStructuredSelection ssel = (IStructuredSelection) getStructuredViewer()
				.getSelection();
		boolean isRemoveEnable = false;
		if (!ssel.isEmpty()) {
			Object s1 = ssel.getFirstElement();
			if (s1 instanceof ManagedBeanType) {
				isRemoveEnable = true;
			}
		}
		getRemoveButton().setEnabled(isRemoveEnable);
	}

	/**
	 * Override the super method to ignore the selection on ScopeTreeItem.
	 */
	public void selectionChanged(SelectionChangedEvent event) {

		if (event != null
				&& event.getSelection() != null
				&& ((IStructuredSelection) event.getSelection())
						.getFirstElement() instanceof ManagedBeanScopeTreeItem) {

			event = new SelectionChangedEvent(this, StructuredSelection.EMPTY);
		}
		super.selectionChanged(event);
		updateButtons();

	}

	protected void addAdaptersOntoInput(Object newInput) {
		super.addAdaptersOntoInput(newInput);

		FacesConfigType facesConfig = (FacesConfigType) newInput;
		if (EcoreUtil.getExistingAdapter(facesConfig,
				ManagedBeanMasterSection.class) == null) {

			facesConfig.eAdapters().add(getManagedBeanMasterSectionAdapter());
		}

		List managedbeans = facesConfig.getManagedBean();
		for (Iterator it = managedbeans.iterator(); it.hasNext();) {
			ManagedBeanType managedbean = (ManagedBeanType) it.next();
			if (EcoreUtil.getExistingAdapter(managedbean,
					ManagedBeanMasterSection.class) == null) {

				managedbean.eAdapters().add(
						getManagedBeanMasterSectionAdapter());
			}
		}
	}

	protected void removeAdaptersFromInput(Object oldInput) {		
		FacesConfigType facesConfig = (FacesConfigType) oldInput;
		if (EcoreUtil.getExistingAdapter(facesConfig,
				ManagedBeanMasterSection.class) != null) {

			facesConfig.eAdapters()
					.remove(getManagedBeanMasterSectionAdapter());
		}

		List managedbeans = facesConfig.getManagedBean();
		for (Iterator it = managedbeans.iterator(); it.hasNext();) {
			ManagedBeanType managedbean = (ManagedBeanType) it.next();
			if (EcoreUtil.getExistingAdapter(managedbean,
					ManagedBeanMasterSection.class) != null) {

				managedbean.eAdapters().remove(
						getManagedBeanMasterSectionAdapter());
			}
		}
		super.removeAdaptersFromInput(oldInput);
	}

	private ManagedBeanMasterSectionAdapter getManagedBeanMasterSectionAdapter() {
		if (managedBeanMasterSectionAdapter == null) {
			managedBeanMasterSectionAdapter = new ManagedBeanMasterSectionAdapter();
		}
		return managedBeanMasterSectionAdapter;
	}

	class ManagedBeanMasterSectionAdapter extends AdapterImpl {

		public boolean isAdapterForType(Object type) {
			if (type == ManagedBeanMasterSection.class)
				return true;
			return false;
		}

		public void notifyChanged(Notification msg) {
			super.notifyChanged(msg);

			if (msg.getFeature() == FacesConfigPackage.eINSTANCE
					.getFacesConfigType_ManagedBean()) {
				if (msg.getEventType() == Notification.ADD) {
					final EObject mbean = (EObject) msg.getNewValue();
					if (EcoreUtil.getExistingAdapter(mbean,
							ManagedBeanMasterSection.class) == null) {

						mbean.eAdapters().add(
								getManagedBeanMasterSectionAdapter());
					}
					if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
						getStructuredViewer().refresh(true);
						IStructuredSelection selection = new StructuredSelection(
								mbean);
						getStructuredViewer().setSelection(selection);
					} else {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							public void run() {
								getStructuredViewer().refresh(true);
								IStructuredSelection selection = new StructuredSelection(
										mbean);
								getStructuredViewer().setSelection(selection);
							}
						});
					}
				} else if (msg.getEventType() == Notification.REMOVE) {
					final EObject mbean = (EObject) msg.getOldValue();
					if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
						getStructuredViewer().refresh(true);
						if (EcoreUtil.getExistingAdapter(mbean,
								ManagedBeanMasterSection.class) == null) {
				
								mbean.eAdapters().remove(
										getManagedBeanMasterSectionAdapter());
							}
					} else {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
							public void run() {
								getStructuredViewer().refresh(true);
								if (EcoreUtil.getExistingAdapter(mbean,
										ManagedBeanMasterSection.class) == null) {
						
										mbean.eAdapters().remove(
												getManagedBeanMasterSectionAdapter());
									}
							}
						});
					}

				} else if (msg.getEventType() == Notification.SET) {
					final Object mbean = msg.getNewValue();
					if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
						getStructuredViewer().refresh(mbean, true);
					} else {
						PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							getStructuredViewer().refresh(mbean, true);
						}
					});
				}
			}

			if (msg.getFeature() == FacesConfigPackage.eINSTANCE
					.getManagedBeanType_ManagedBeanClass()
					|| msg.getFeature() == FacesConfigPackage.eINSTANCE
							.getManagedBeanType_ManagedBeanName()) {

				final Object bean = msg.getNotifier();
				if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
					getStructuredViewer().refresh(bean, true);
				} else {
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							getStructuredViewer().refresh(bean, true);
						}	
					});
				}
				

			} else if (msg.getFeature() == FacesConfigPackage.eINSTANCE
					.getManagedBeanType_ManagedBeanScope()) {

				final Object mbean = msg.getNotifier();
				if (Thread.currentThread() == PlatformUI.getWorkbench().getDisplay().getThread()) {
					getStructuredViewer().refresh();
					IStructuredSelection selection = new StructuredSelection(
							mbean);
					getStructuredViewer().setSelection(selection);
				} else {	
					PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
						public void run() {
							getStructuredViewer().refresh();
							IStructuredSelection selection = new StructuredSelection(
									mbean);
							getStructuredViewer().setSelection(selection);
						}
					});
				}				
			}
		}
	}
	}

}
