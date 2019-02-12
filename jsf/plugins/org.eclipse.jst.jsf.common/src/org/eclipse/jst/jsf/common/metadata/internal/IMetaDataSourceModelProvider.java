/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import org.eclipse.core.runtime.IAdaptable;


/**
 * Provides a source of metadata that can be transformed into a merged standard model
 * LIKELY TO CHANGE
 */
public interface IMetaDataSourceModelProvider extends IAdaptable{
	/**
	 * @return the source model
	 */
	public Object getSourceModel();
	/**
	 * @return the IMetaDataLocator instance that located this model provider instance
	 */
	public IMetaDataLocator getLocator();
	/**
	 * @param locator instance that located this model provider instance
	 */
	public void setLocator(IMetaDataLocator locator);
}
