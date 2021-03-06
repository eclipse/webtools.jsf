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
public class CopyCommand extends RangeModeCommand {

	/**
	 * @param viewer
	 */
	public CopyCommand(IHTMLGraphicalViewer viewer) {
		super(CommandResources.getString("CopyCommand.Label.Copy"), viewer); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.commands.range.RangeModeCommand#doRangeExecute(org.eclipse.jst.pagedesigner.dom.DOMRange)
	 */
	protected DOMRange doRangeExecute(DOMRange selection) {
		DesignEdit edit = new CopyEdit(selection, getViewer());
		edit.operate();
		return selection;
	}

}
