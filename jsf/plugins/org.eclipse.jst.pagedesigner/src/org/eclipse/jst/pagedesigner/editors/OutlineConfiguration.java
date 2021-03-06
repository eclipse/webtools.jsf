/*******************************************************************************
 * Copyright (c) 2006, 2012 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.editors;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jst.jsp.ui.views.contentoutline.JSPContentOutlineConfiguration;
import org.eclipse.wst.sse.core.internal.provisional.IStructuredModel;
import org.eclipse.wst.sse.core.internal.provisional.IndexedRegion;

/**
 * the HTML editor's outline view configuration
 *
 */
public class OutlineConfiguration extends JSPContentOutlineConfiguration {
	private Object[] _selections = new Object[0];

	public ISelection getSelection(TreeViewer viewer, ISelection selection) {
		if ((viewer.getInput() instanceof IStructuredModel)
				&& (selection instanceof ITextSelection)) {
			_selections = getSelectedObjects((IStructuredModel) viewer
					.getInput(), (ITextSelection) selection);
			if (_selections != null) {
				return super.getSelection(viewer, new StructuredSelection(
						_selections));
			}
		}
		else if (selection instanceof IStructuredSelection)
		{
		    return super.getSelection(viewer, selection);
		}
		return super.getSelection(viewer, new StructuredSelection(_selections));
	}
	
	private Object[] getSelectedObjects(IStructuredModel model,
			ITextSelection selection) {
		Object[] selectedStructures = null;
		if (model != null) {
			IndexedRegion region = model
					.getIndexedRegion(selection.getOffset());
			int end = selection.getOffset() + selection.getLength();
			if (region != null) {
				if (end <= region.getEndOffset()) {
					// single selection
					selectedStructures = new Object[1];
					selectedStructures[0] = region;
				} else {
					// multiple selection
					int maxLength = model.getStructuredDocument().getLength();
					List structures = new ArrayList(2);
					while (region != null && region.getEndOffset() <= end
							&& region.getEndOffset() < maxLength) {
						structures.add(region);
						region = model
								.getIndexedRegion(region.getEndOffset() + 1);
					}
					selectedStructures = structures.toArray();
				}
			}
		}
		if (selectedStructures == null) {
			selectedStructures = new Object[0];
		}
		return selectedStructures;
	}

    @Override
    public TransferDropTargetListener[] getTransferDropTargetListeners(
            TreeViewer treeViewer)
    {
        TransferDropTargetListener[] originalListeners =  
                super.getTransferDropTargetListeners(treeViewer);
        
        List<TransferDropTargetListener> configuredListeners = 
                getConfiguredTransferDropTargetListeners();
        
        TransferDropTargetListener[] consolidated = 
                new TransferDropTargetListener[originalListeners.length + configuredListeners.size()];
        
        int i = 0;
        // Put the configured listeners ahead of the original,
        // which just allows reordering of the nodes/tags;
        // otherwise, the LocalSelectionTransfer (for reordering)
        // takes precedence over a ResourceTransfer.
        for (TransferDropTargetListener configured : configuredListeners)
        {
            consolidated[i++] = configured;
        }
        for (TransferDropTargetListener original : originalListeners)
        {
            consolidated[i++] = original;
        }
        
        return consolidated;
    }
	
    private List<TransferDropTargetListener> getConfiguredTransferDropTargetListeners()
    {
        return OutlineTargetListenerReader.getListeners();
    }
}
