/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.commands.range;

import org.eclipse.jst.pagedesigner.commands.CommandResources;
import org.eclipse.jst.pagedesigner.dom.DOMRange;
import org.eclipse.jst.pagedesigner.viewer.IHTMLGraphicalViewer;

/**
 * @author mengbo
 */
public class PasteCommand extends RangeModeCommand {

	/**
	 * @param viewer
	 */
	public PasteCommand(IHTMLGraphicalViewer viewer) {
		super(CommandResources.getString("PasteCommand.Label.Paste"), viewer); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.RangeModeCommand#doRangeExecute(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected DOMRange doRangeExecute(DOMRange selection) {
		InsertEdit edit = new InsertEdit(selection, getViewer(),
				new ClipboardData(getViewer().getControl()));
		if (edit.operate()) {
			return new DOMRange(edit.getOperationPosition(), edit
					.getOperationPosition());
		}
        return selection;
	}

}
