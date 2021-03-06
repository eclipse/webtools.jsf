/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.facesconfig.ui.section;


/**
 * Section interface to build relation between model and adapter
 * 
 * @author sfshi
 * 
 */
public interface IFacesConfigSection {
	/**
	 * set the input of this section
	 * 
	 * @param newInput
	 */
	void setInput(Object newInput);

	/**
	 * @return the input of this section
	 */
	Object getInput();
	
	/**
	 * initialize current section based on the input
	 * 
	 */
	void initialize();

	/**
	 * clear all section's contents.
	 * 
	 */
	void clearAll();

	/**
	 * refresh the needed parts of the section
	 * 
	 */
	void refresh();

	/**
	 * refresh all parts of the section
	 * 
	 */
	void refreshAll();

}
