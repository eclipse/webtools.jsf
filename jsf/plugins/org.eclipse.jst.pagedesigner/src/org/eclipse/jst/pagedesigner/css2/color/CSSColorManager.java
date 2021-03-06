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
package org.eclipse.jst.pagedesigner.css2.color;

/**
 * @author mengbo
 */
public class CSSColorManager {
	private static CSSColorManager _instance;

	private CSSColorManager() {
        // no external instantiation
	}

	/**
	 * @return the single instance
	 */
	public static CSSColorManager getInstance() {
		if (_instance == null) {
			_instance = new CSSColorManager();
		}
		return _instance;
	}

	/**
	 * return Color or RGB. If return color, then the returned color is system
	 * color, caller should NOT dispose the returned color
	 * 
	 * @param cssText
	 * @return the color object for cssText
	 */
	public Object getColor(String cssText) {
		return CSSColorConverter.getInstantce().getCSSColor(cssText);
	}
}
