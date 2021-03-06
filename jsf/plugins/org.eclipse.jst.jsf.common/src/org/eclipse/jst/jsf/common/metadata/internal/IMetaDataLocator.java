/*******************************************************************************
 * Copyright (c) 2007, 2010 Oracle Corporation.
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

import java.util.List;

/**
 * Locates instances of metadata of a specific source model type
 */
public interface IMetaDataLocator {
	/**
	 * @param uri
	 * @return a list of <code>IMetaDataSourceModelProvider</code>s for the uri located by this instance 
	 */
	public List <IMetaDataSourceModelProvider> locateMetaDataModelProviders(String uri);
	
	/**
	 * Opportunity for service to start (add listeners, etc.). 
	 * Framework calls this immediately after construction and all setup should occur at this time.
	 */
	public void startLocating();
	/**
	 * Stop looking for instances of metadata model sources.  An opportunity to cleanup. 
	 */
	public void stopLocating();

	/**
	 * @param observer add a {@link IMetaDataObserver} of this locator 
	 */
	public void addObserver(IMetaDataObserver observer);
	/**
	 * @param observer remove a {@link IMetaDataObserver} of this locator 
	 */
	public void removeObserver(IMetaDataObserver observer);
	
	/**
	 * @return IDomainSourceModelType instance that created this locator
	 */
	public IDomainSourceModelType getDomainSourceModelType();
	/**
	 * @param domainSourceModelType set the domainSourceModelType instance that created this locator
	 */
	public void setDomainSourceModelType(IDomainSourceModelType domainSourceModelType);

}
