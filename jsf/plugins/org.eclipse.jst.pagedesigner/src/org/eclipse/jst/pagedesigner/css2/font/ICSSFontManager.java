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
package org.eclipse.jst.pagedesigner.css2.font;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;

/**
 * @author mengbo
 */
public interface ICSSFontManager {
	/**
	 * @param style
	 * @return ??
	 */
	public ICSSFont createFont(ICSSStyle style);

	/**
	 * FIXME: purpose?
	 */
	public void dispose();
}
