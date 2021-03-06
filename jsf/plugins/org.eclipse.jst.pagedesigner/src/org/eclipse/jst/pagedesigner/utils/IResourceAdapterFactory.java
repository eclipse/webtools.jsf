/*******************************************************************************
 * Copyright (c) 2011 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Ian Trimble - initial API and implementation
 *******************************************************************************/ 
package org.eclipse.jst.pagedesigner.utils;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.jst.pagedesigner.parts.NodeEditPart;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;

/**
 * IAdapterFactory capable of adapting to IResource instances.
 * 
 * @author ian.trimble@oracle.com
 */
public class IResourceAdapterFactory implements IAdapterFactory {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapter(java.lang.Object, java.lang.Class)
	 */
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		IResource adapter = null;
		if (adapterType.equals(IResource.class)) {
			if (adaptableObject instanceof NodeEditPart) {
				final IDOMNode node = ((NodeEditPart)adaptableObject).getIDOMNode();
				if (node != null) {
					final IDOMModel model = node.getModel();
					if (model != null) {
						adapter = StructuredModelUtil.getFileFor(model);
					}
				}
			}
		}
		return adapter;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.core.runtime.IAdapterFactory#getAdapterList()
	 */
	public Class[] getAdapterList() {
		return new Class[]{IResource.class};
	}

}
