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
package org.eclipse.jst.pagedesigner.css2.list;

/**
 * @author mengbo
 */
public class IncrementObject {
	private String _counterName;

	private Integer _increment;

	/**
	 * @param name
	 * @param increment
	 */
	public IncrementObject(String name, Integer increment) {
		_counterName = name;
		_increment = increment;
	}

	/**
	 * @return Returns the _counterName.
	 */
	public String getCounterName() {
		return _counterName;
	}

	/**
	 * @param name
	 *            The _counterName to set.
	 */
	public void setCounterName(String name) {
		_counterName = name;
	}

	/**
	 * @return Returns the _increment.
	 */
	public Integer getIncrement() {
		return _increment;
	}

	/**
	 * @param _increment
	 *            The _increment to set.
	 */
	public void setIncrement(Integer _increment) {
		this._increment = _increment;
	}
}
