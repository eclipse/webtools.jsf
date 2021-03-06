/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.commands.range;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.IHTMLConstants;
import org.eclipse.jst.pagedesigner.dom.DOMPosition;
import org.eclipse.jst.pagedesigner.dom.DOMRefPosition;
import org.eclipse.jst.pagedesigner.dom.EditModelQuery;
import org.eclipse.jst.pagedesigner.dom.IDOMPosition;
import org.w3c.dom.Node;

/**
 * @author mengbo
 */
public class BlockNodeFinder {
	private String[] _additionalTags;

	private IDOMPosition _position;

	/**
	 * @param position
	 * @param additionalTags
	 */
	public BlockNodeFinder(IDOMPosition position, String[] additionalTags) {
		_position = position;
		_additionalTags = additionalTags;
	}

	private Node findInlineSiblings(IDOMPosition position, List result,
			boolean forward) {
		Node container = EditModelQuery.getInstance().getSibling(position,
				forward);
		if (!forward) {
			while (container != null) {
				if (EditModelQuery.isInline(container)) {
					result.add(container);
				} else {
					return container;
				}
				container = container.getPreviousSibling();
			}
		} else {
			while (container != null) {
				if (EditModelQuery.isInline(container)) {
					result.add(container);
				} else {
					return container;
				}
				container = container.getNextSibling();
			}
		}
		// the result will be non-zero length.
		return null;
	}

	private Node getParagraphNodes(IDOMPosition position, List result,
			boolean forward) {
		Node sResult = findInlineSiblings(position, result, forward);
		Node container = position.getContainerNode();
		container = position.isText() ? container.getParentNode() : container;
		while (sResult == null) {
			// stop at block, special container and H style nodes.
			if (EditModelQuery.isBlockNode(container)
					|| EditModelQuery.isDocument(container)
					|| (container.getLocalName() != null && (container
							.getLocalName().equals(IJSFConstants.TAG_VIEW) || container
							.getLocalName().equalsIgnoreCase(
									IHTMLConstants.TAG_HTML))) || //
					(_additionalTags != null
							&& Arrays.asList(_additionalTags).contains(
									getTagName()) && Arrays.asList(
							_additionalTags).contains(container))) {
				return container;
			}
			position = new DOMRefPosition(container, forward);
			sResult = findInlineSiblings(position, result, forward);
			container = container.getParentNode();
		}
		return sResult;
	}

	/**
	 * Search for an area between two block nodes or within a block node, search
	 * will stop before or under a node which has block display-type, or
	 * particular container like "html", jsf "view", .etc, two positions (left
	 * and right) are returned in result.
	 * 
	 * The searcher will search parent's directly children, if no block node is
	 * found, then go up the node tree to search again.
	 * 
	 * @param position
	 * @param result
	 */
	public void getParagraphNodes(IDOMPosition position, List result) {
		List tempResult = new ArrayList();
		Node r1 = getParagraphNodes(position, tempResult, true);
		if (EditModelQuery.isChild(r1, position.getContainerNode())) {
			result.add(new DOMPosition(r1, r1.getChildNodes().getLength()));
		} else {
			result.add(new DOMRefPosition(r1, false));
		}

		Node r2 = getParagraphNodes(position, tempResult, false);
		if (EditModelQuery.isChild(r2, position.getContainerNode())) {
			result.add(new DOMPosition(r2, 0));
		} else {
			result.add(new DOMRefPosition(r2, true));
		}
	}

	private String getTagName() {
		String name = _position.getContainerNode().getNodeName();
		name = name == null ? "" : name.toLowerCase(); //$NON-NLS-1$
		return name;
	}
}
