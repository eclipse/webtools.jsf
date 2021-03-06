/*******************************************************************************
 * Copyright (c) 2010, 2011 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle Corporation - initial API and implementation and/or initial documentation
 *******************************************************************************/

package org.eclipse.jst.jsf.common.metadata.query.internal;

/**
 * Matches predicates
 *
 * @param <T>
 */
public interface IPredicateMatcher<T> {
	/**
	 * @param object
	 * @return MATCH
	 */
	public MATCH matches(T object);
	
	/**
	 * Reset the matcher
	 */
	public void reset();
	/**
	 * Enumeration of match results
	 *
	 */
	public enum MATCH {
		/**
		 * Does not match 
		 */
		NOT,
		/**
		 * Partial match thru the hierarchy... continue
		 */
		PARTIALLY,
		/**
		 * Full match - leaf most predicate satisfied
		 */
		FULLY;
	}
}
