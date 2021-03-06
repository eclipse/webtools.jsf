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
package org.eclipse.jst.jsf.facesconfig.ui.section;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jst.jsf.facesconfig.ui.EditorMessages;
import org.eclipse.jst.jsf.facesconfig.ui.page.FacesConfigMasterDetailPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.IManagedForm;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * An default implementation of AbstractFacesConfigSection for Master Section.
 * 
 * @author sfshi
 * 
 */
public abstract class FacesConfigMasterSection extends AbstractFacesConfigSection {

	private StructuredViewer structuredViewer;

	private Button removeButton;

	/**
	 * 
	 * @param parent
	 * @param managedForm
	 * @param toolkit
	 * @param page
	 * @param helpContextId
	 * @param helpTooltip
	 */
	public FacesConfigMasterSection(Composite parent, IManagedForm managedForm,
			FormToolkit toolkit, FacesConfigMasterDetailPage page,
			String helpContextId, String helpTooltip) {
		super(parent, managedForm, page, toolkit, helpContextId, helpTooltip);
	}

	public void dispose() {
		structuredViewer.removeSelectionChangedListener(this);
		super.dispose();
	}
	
	/**
	 * 
	 */
	protected void createContents(Composite container, FormToolkit toolkit) {
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginWidth = layout.marginHeight = 5;
		container.setLayout(layout);
		structuredViewer = createViewer(container, toolkit);
		structuredViewer.addSelectionChangedListener(this);
		createOperationSection(container, toolkit);
	}

	/**
	 * Create the structured viewer, set up content & label provider for it.
	 * Defaultly create a tableviewer.
	 * 
	 * @param parent
	 * @param toolkit
	 * @return the structured viewer
	 */
	protected StructuredViewer createViewer(Composite parent,
			FormToolkit toolkit) {
		Composite tableContainer = toolkit.createComposite(parent);
		toolkit.paintBordersFor(tableContainer);

		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.heightHint = 200;
		tableContainer.setLayoutData(gd);
		GridLayout layout = new GridLayout();
		tableContainer.setLayout(layout);

		TableViewer tableViewer = new TableViewer(tableContainer, SWT.SINGLE
				| SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		gd = new GridData(GridData.FILL_BOTH);
		tableViewer.getControl().setLayoutData(gd);

		tableViewer.setContentProvider(new AdapterFactoryContentProvider(
				getAdapterFactory()));
		tableViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				getAdapterFactory()));

		configViewer(tableViewer);

		return tableViewer;
	}

	/**
	 * Config the viewer, such as set a filter and so on. Sub classes should
	 * override this method to add filter.
	 * 
	 * @param viewer
	 */
	protected void configViewer(StructuredViewer viewer) {
        // do nothing; subs may override
	}

	/**
	 * Defaultly we create a "New" button and a "Remove" button. Sub classes can
	 * overwrite this method if need other functions.
	 * 
	 * @param parent
	 * @param toolkit
	 */
	protected void createOperationSection(Composite parent, FormToolkit toolkit) {
		Composite operationContainer = toolkit.createComposite(parent);
		GridData gd = new GridData(GridData.FILL_VERTICAL);
		operationContainer.setLayoutData(gd);
		GridLayout layout = new GridLayout();

		operationContainer.setLayout(layout);

		Button addButton = toolkit.createButton(operationContainer,
				EditorMessages.UI_Button_Add, SWT.PUSH);
		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		addButton.setLayoutData(gd);

		addButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				addButtonSelected(e);
			}
		});

		setRemoveButton(toolkit.createButton(operationContainer,
				EditorMessages.UI_Button_Remove, SWT.PUSH));

		gd = new GridData(GridData.FILL_HORIZONTAL
				| GridData.VERTICAL_ALIGN_BEGINNING);
		gd.grabExcessHorizontalSpace = false;
		getRemoveButton().setLayoutData(gd);
		getRemoveButton().addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				removeButtonSelected(e);
			}
		});
	}
    
    /**
	 * 
	 * @param e
	 */
    protected abstract void addButtonSelected(SelectionEvent e);

	/**
	 * 
	 * @param e
	 */
	protected void removeButtonSelected(SelectionEvent e) {

		IStructuredSelection ssel = StructuredSelection.EMPTY;
		ISelection selection = getSelection();
		if (selection instanceof IStructuredSelection) {
			ssel = (IStructuredSelection) selection;
		}

		if (!ssel.isEmpty()) {
			List commands = new ArrayList(ssel.size());
			for (Iterator iter = ssel.iterator(); iter.hasNext();) {
				EObject element = (EObject) iter.next();
				Command command = RemoveCommand.create(getEditingDomain(),
						element);
				commands.add(command);
			}

			CompoundCommand command = new CompoundCommand(commands);
			if (command.canExecute()) {
				getEditingDomain().getCommandStack().execute(command);
			}
		}

	}

	/**
	 * 
	 * update the buttons' enable state.
	 */
	protected void updateButtons() {
		IStructuredSelection ssel = (IStructuredSelection) structuredViewer
				.getSelection();

		getRemoveButton().setEnabled(!ssel.isEmpty());
	}

	/**
	 * 
	 */
	public ISelection getSelection() {
		return structuredViewer.getSelection();
	}

	/**
	 * 
	 */
	public void setSelection(ISelection selection) {
		structuredViewer.setSelection(selection);
	}

	/**
	 * refresh the display of this section.
	 */
	public void refresh() {
		super.refresh();
		structuredViewer.refresh();
		updateButtons();
	}

	/**
	 * 
	 */
	public void refreshAll() {
		// master section refresh all:
		setViewerInput(getInput());
		updateButtons();

		// detial sections refresh:
		// fire an event to the detail sections, let them
		// refresh.
		selectionChanged(null);
	}

	/**
	 * set the structuredViewer's input
	 * 
	 * @param input
	 */
	private void setViewerInput(Object input) {
		structuredViewer.setInput(input);
	}

	/**
	 * 
	 */
	protected void expansionStateChanged(boolean expanded) {
		if (expanded) {
			if (structuredViewer.getInput() == null) {
				refreshAll();
			}
			structuredViewer.refresh(true);
			structuredViewer.setSelection(structuredViewer.getSelection());
		} else {
			structuredViewer.setSelection(null);
		}
		super.expansionStateChanged(expanded);
	}

	/**
	 * 
	 */
	protected void expansionStateChanging(boolean expanding) {
		if (!expanding) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			this.getSection().setLayoutData(gd);
		} else {
			((FacesConfigMasterDetailPage) getPage()).closeOtherSections(this);

			GridData gd = new GridData(GridData.FILL_BOTH);
			this.getSection().setLayoutData(gd);
		}

		super.expansionStateChanging(expanding);
	}

	/**
	 * get the viewer.
	 * 
	 * @return the viewer
	 */
	public StructuredViewer getStructuredViewer() {
		return structuredViewer;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.jst.jsf.facesconfig.ui.section.AbstractFacesConfigSection#selectionChanged(org.eclipse.jface.viewers.SelectionChangedEvent)
	 */
	public void selectionChanged(SelectionChangedEvent event) {
		super.selectionChanged(event);
		updateButtons();
	}

    private void setRemoveButton(Button removeButton) {
        this.removeButton = removeButton;
    }

    /**
     * @return the remove button
     */
    protected Button getRemoveButton() {
        return removeButton;
    }
}
