/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.ui.internal.jspeditor;

import org.eclipse.jdt.core.IJavaElement;

/**
 * Test-only interface.  Not for normal use.
 *
 * @author cbateman
 *
 */
public interface ITestHyperlink {
	/**
	 * @return the java element for symbol2 or null if none.
	 */
	public IJavaElement determineJavaElement();
}
