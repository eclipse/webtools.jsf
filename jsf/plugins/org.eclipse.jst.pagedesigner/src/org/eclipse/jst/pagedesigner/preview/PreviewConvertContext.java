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
package org.eclipse.jst.pagedesigner.preview;

import java.util.List;

import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.converter.ConvertPosition;
import org.eclipse.jst.pagedesigner.converter.IConverterFactory;
import org.eclipse.jst.pagedesigner.converter.ITagConverter;
import org.eclipse.jst.pagedesigner.dtmanager.DTManager;
import org.eclipse.wst.xml.core.internal.document.InvalidCharacterException;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMDocument;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMText;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * @author mengbo
 * @version 1.5
 */
public class PreviewConvertContext {
	private Logger _log = PDPlugin.getLogger(PreviewConvertContext.class);

	private IDOMDocument _destDocument;

	/**
	 * Instantiates an instance for the specified IDOMDocument.
	 * 
	 * @param destDocument IDOMDocument instance.
	 */
	public PreviewConvertContext(IDOMDocument destDocument) {
		this._destDocument = destDocument;
	}

	/**
	 * Converts specified Node for preview.
	 * 
	 * @param ele Node instance to convert.
	 * @return Converted Node instance.
	 */
	public Node previewConvert(Node ele) {
		if (ele instanceof Element) {
			return previewConvertElement((Element) ele);
		} else if (ele instanceof Text) {
			return createText((Text) ele);
		} else {
			// XXX: we'll support other node like doctype etc in the future.
			// so they should also be rendered into preview.

			return null;
		}
	}

	/**
	 * @param text
	 * @return
	 */
	private Node createText(Text text) {
		Text previewText = this._destDocument.createTextNode(text.getData());
		try {
			((IDOMText) previewText).setSource(((IDOMText) text).getSource());
		} catch (InvalidCharacterException e) {
			// "Error"
			_log.info("PreviewConvertContext.Error.0", e); //$NON-NLS-1$
		}
		return previewText;
	}

	/**
	 * Converts specified Element instance for preview.
	 * 
	 * @param ele Element instance to be converted.
	 * @return Converted Node instance.
	 */
	protected Node previewConvertElement(Element ele) {
		ITagConverter converter = createTagConverter(ele);
		if (!converter.isVisualByHTML()) {
			return null;
		}
		converter.convertRefresh(null);
		Element result = converter.getResultElement();
		List children = converter.getChildModeList();
		if (children != null) {
			for (int i = 0, size = children.size(); i < size; i++) {
				Node child = (Node) children.get(i);
				if (child != null) {
					Node childPreview = previewConvert(child);
					if (childPreview != null) {
						ConvertPosition position = converter
								.getChildVisualPosition(child);
						if (position != null) {
							/* FIX for bug #179403
							// FIXME: not using index here, need fix.
							position.getParentNode().appendChild(childPreview);
							*/
							NodeList childNodes = position.getParentNode().getChildNodes();
							if (childNodes.getLength() > position.getIndex()) {
								Node item = childNodes.item(position.getIndex());
								position.getParentNode().insertBefore(childPreview, item);
							} else {
								position.getParentNode().appendChild(childPreview);
							}
						}
					}
				}
			}
		}
		return result;
	}

	/**
	 * @param ele
	 * @return
	 */
	private ITagConverter createTagConverter(Element ele) {
		return DTManager.getInstance().getTagConverter(ele,
				IConverterFactory.MODE_PREVIEW, _destDocument);
	}
}
