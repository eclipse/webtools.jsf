/*******************************************************************************
 * Copyright (c) 2010 Oracle Corporation and others.
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

package org.eclipse.jst.jsf.common.metadata.query.internal.taglib;

import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataDomainContext;
import org.eclipse.jst.jsf.common.metadata.query.internal.AbstractMetaDataDomainQueryFactory;
import org.eclipse.jst.jsf.common.metadata.query.internal.IMetaDataQuery;

/**
 * Factory for producing TagLibraryDomain {@link IMetaDataQuery}s.  
 * ({@link ITaglibDomainMetaDataQuery} specifically)
 */
public class TaglibDomainMetaDataQueryFactory 
	extends
		AbstractMetaDataDomainQueryFactory {

	/**
	 * Constructor
	 */
	public TaglibDomainMetaDataQueryFactory() {
		super("TagLibraryDomain"); //$NON-NLS-1$
	}

	public ITaglibDomainMetaDataQuery createQuery(final IMetaDataDomainContext context) {
		return new TaglibDomainMetaDataQuery(getManager(context), context);
	}

}
