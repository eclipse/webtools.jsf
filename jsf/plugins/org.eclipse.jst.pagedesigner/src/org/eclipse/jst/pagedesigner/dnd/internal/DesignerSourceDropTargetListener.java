/*******************************************************************************
 * Copyright (c) 2006, 2020 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.dnd.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jst.pagedesigner.commands.PaletteDropInsertCommand;
import org.eclipse.jst.pagedesigner.editors.pagedesigner.PageDesignerResources;
import org.eclipse.jst.pagedesigner.editors.palette.IDropSourceData;
import org.eclipse.jst.pagedesigner.editors.palette.ITagDropSourceData;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.texteditor.ITextEditorDropTargetListener;
import org.eclipse.wst.sse.ui.StructuredTextEditor;
import org.eclipse.wst.sse.ui.internal.ExtendedEditorDropTargetAdapter;

/**
 * @author mengbo
 */
public class DesignerSourceDropTargetListener extends
		ExtendedEditorDropTargetAdapter implements
		ITextEditorDropTargetListener {
	private int _location;

	private StructuredTextEditor _textEditor;

	/**
	 * @param textEditor
	 */
	public DesignerSourceDropTargetListener(StructuredTextEditor textEditor) {
		super(false);
		_textEditor = textEditor;
		setTextViewer(_textEditor.getTextViewer());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.texteditor.ITextEditorDropTargetListener#getTransfers()
	 */
	public Transfer[] getTransfers() {
		return new Transfer[] { TemplateTransfer.getInstance(),
				TextTransfer.getInstance(), ResourceTransfer.getInstance() };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.DropTargetListener#dragOperationChanged(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void dragOperationChanged(DropTargetEvent event) {
		super.dragOperationChanged(event);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.DropTargetListener#dragOver(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void dragOver(DropTargetEvent event) {
		StyledText text = null;
		if (_textEditor.getTextViewer() != null) {
			text = _textEditor.getTextViewer().getTextWidget();
			if (TemplateTransfer.getInstance().isSupportedType(
					event.currentDataType) ||
					ResourceTransfer.getInstance().isSupportedType(
							event.currentDataType)) {
				if (_textEditor.getTextViewer() != null) {
						Point p = new Point(event.x, event.y);
						SourceViewerDragDropHelper.getInstance().updateCaret(
								_textEditor, p);
						_location = text.getCaretOffset();
						if (TemplateTransfer.getInstance().isSupportedType(
								event.currentDataType) ||
								ResourceTransfer.getInstance().isSupportedType(
										event.currentDataType)) {
							_location = SourceViewerDragDropHelper.getInstance()
									.getValidLocation(_textEditor, _location);
						}
						SourceViewerDragDropHelper.getInstance().showCaret(
								_textEditor, _location);
				    }
		
			} else if (TextTransfer.getInstance().isSupportedType(
					event.currentDataType)) {
				super.dragOver(event);
				_location = text.getCaretOffset();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.dnd.DropTargetListener#drop(org.eclipse.swt.dnd.DropTargetEvent)
	 */
	public void drop(DropTargetEvent event) {
		StyledText text = null;
		if (_textEditor.getTextViewer() != null) {
			text = _textEditor.getTextViewer().getTextWidget();
		}
		text.setCaretOffset(_location);
		Command command = getCommand(event);
		if (command != null) {
			command.execute();
		}

		// For a resource transfer, we don't want the source drag listener
		// to do cleanup and actually remove the source file. See the
		// Javadoc note in the Navigator view's CommonDropAdapterAssistant
		// handleDrop() method.
		if (event != null) {
			if (event.detail != DND.DROP_NONE) {
				event.detail = DND.DROP_COPY;
			}
		}
	}

	private Command getCommand(DropTargetEvent event) {
		if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
			Object data = event.data;
			if (data instanceof String) {
				SourceViewLocalDropCommand command = new SourceViewLocalDropCommand(
						_textEditor, data, _location);
				return command;
			}
		} else if (TemplateTransfer.getInstance().isSupportedType(
				event.currentDataType)) {
			Object data = event.data;
			PaletteDropInsertCommand command = null;
			if (data instanceof IDropSourceData) {
			    final IDropSourceData dropSourceData = (IDropSourceData) data;
					// "Create new item"
				command = new PaletteDropInsertCommand(
						PageDesignerResources
								.getInstance()
								.getString(
										"DesignerSourceDropTargetListener.InserCommandLabel"), _textEditor, dropSourceData, _location); //$NON-NLS-1$
		    
			}
			return command;
		} else if (ResourceTransfer.getInstance().isSupportedType(event.currentDataType)) {
			Object data = event.data;
			PaletteDropInsertCommand command = null;
			if (data instanceof IResource[]) {
				IResource[] resources = (IResource[]) data;
				if (resources.length > 0 && (resources[0] instanceof IFile)) {
					IFile file = (IFile) resources[0];
					ITagDropSourceData dropSource = file
							.getAdapter(ITagDropSourceData.class);
					if (dropSource != null) {
						command = new PaletteDropInsertCommand(
								PageDesignerResources
										.getInstance()
										.getString(
												"DesignerSourceDropTargetListener.InserCommandLabel"), //$NON-NLS-1$
								_textEditor, dropSource, _location);
					}
				}
			}
			return command;
        }
		return null;
	}

}
