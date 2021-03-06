/*******************************************************************************
 * Copyright (c) 2005, 2007 Oracle Corporation.
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
package org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal;

import org.eclipse.jst.pagedesigner.dtmanager.converter.operations.AbstractTransformOperation;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;

/**
 * ITransformOperation implementation that copies all attributes from the
 * source Element instance to the current Element instance.
 * 
 * @author Ian Trimble - Oracle
 */
public class CopyAllAttributesOperation extends AbstractTransformOperation {

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jst.pagedesigner.dtmanager.converter.operations.internal.provisional.AbstractTransformOperation#transform(org.w3c.dom.Element, org.w3c.dom.Element)
	 */
	public Element transform(Element srcElement, Element curElement) {
		if (srcElement != null && curElement != null) {
			NamedNodeMap attributes = srcElement.getAttributes();
			for (int i = 0; i < attributes.getLength(); i++) {
				Attr attribute = (Attr)attributes.item(i);
				curElement.setAttribute(attribute.getName(), attribute.getValue());
			}
		}
		return curElement;
	}

}
