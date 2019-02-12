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
package org.eclipse.jst.pagedesigner.requests;

import org.eclipse.gef.requests.LocationRequest;

/**
 * This is a LocationRequest plus keyboard modified support.
 * 
 * @author mengbo
 * @version 1.5
 */
public class LocationModifierRequest extends LocationRequest {

	private boolean _controlKeyDown;

	/**
	 * 
	 */
	public LocationModifierRequest() {
		super();
	}

	/**
	 * @param type
	 */
	public LocationModifierRequest(Object type) {
		super(type);
	}

	/**
	 * @return true if the control key was pressed
	 */
	public boolean isControlKeyPressed() {
		return _controlKeyDown;
	}

	/**
	 * @param b
	 */
	public void setControlKeyPressed(boolean b) {
		this._controlKeyDown = b;
	}
}
