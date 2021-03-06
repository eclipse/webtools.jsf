/*******************************************************************************
 * Copyright (c) 2001, 2007 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.actions.single;

import org.w3c.dom.Node;

/**
 * An edit part selection action that corresponds to a Node selection
 */
public abstract class SelectNodeAction extends SelectEditPartAction 
{
    private final Node  _node;

    /**
     * @param text
     * @param curNode
     */
    protected SelectNodeAction(String text, Node curNode) {
        super(text);
        _node = curNode;
    }

    /**
     * @return the current node
     */
    protected Node getNode() {
        return _node;
    }
}
