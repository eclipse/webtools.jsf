/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.List;

/**
 * Locates instances of metadata of a specific source model type
 */
public interface IMetaDataLocator {
	/**
	 * @param uri
	 * @return a list of <code>IMetaDataModelProvider</code>s for the uri located by this instance 
	 */
	public List/*<IMetaDataModelProvider>*/ locateMetaDataModelProviders(String uri);
	/**
	 * Stop looking for instances of metadata model sources.  An opporttunity to cleanup. 
	 */
	public void stopLocating();

	/**
	 * @param add a {@link IMetaDataObserver} of this locator 
	 */
	public void addObserver(IMetaDataObserver observer);
	/**
	 * @param remove a {@link IMetaDataObserver} of this locator 
	 */
	public void removeObserver(IMetaDataObserver observer);
	
	/**
	 * @return IDomainSourceModelType instance that created this locator
	 */
	public IDomainSourceModelType getDomainSourceModelType();
	/**
	 * @param set the domainSourceModelType instance that created this locator
	 */
	public void setDomainSourceModelType(IDomainSourceModelType domainSourceModelType);

}
