/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * http:// https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 * Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.jsfhtml;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.IJSFCoreSupport;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.JSFAddActionGroup;
import org.eclipse.jst.pagedesigner.jsf.ui.actions.JSFNavigationGroup;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMElement;
import org.w3c.dom.Element;


/**
 * @author mengbo
 * @version 1.5
 */
public class DefaultJSFHTMLElementEdit extends AbstractElementEdit
{
    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.elementedit.AbstractElementEdit#fillContextMenu(org.eclipse.jface.action.IMenuManager, org.w3c.dom.Element)
     */
    public void fillContextMenu(IMenuManager contextMenu, Element ele)
    {
        super.fillContextMenu(contextMenu, ele);

        // next are JSF HTML special support.
        new JSFAddActionGroup().fillContextMenu(contextMenu, (IDOMElement) ele, new JSFCoreSupport(ele.getLocalName()));
        new JSFNavigationGroup().fillContextMenu(contextMenu, (IDOMElement) ele, new JSFCoreSupport(ele.getLocalName()));
    }

    static class JSFCoreSupport implements IJSFCoreSupport
    {
        String _localName;

        /**
         * @param localtag
         */
        public JSFCoreSupport(String localtag)
        {
            _localName = localtag;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jst.pagedesigner.jsf.actions.IJSFCoreSupport#isActionSource()
         */
        public boolean isActionSource()
        {
            // UICommand.
            return _localName.startsWith("command"); //$NON-NLS-1$
        }

        /* (non-Javadoc)
         * @see org.eclipse.jst.pagedesigner.jsf.actions.IJSFCoreSupport#isUIComponent()
         */
        public boolean isUIComponent()
        {
            return true;
        }

        /* (non-Javadoc)
         * @see org.eclipse.jst.pagedesigner.jsf.actions.IJSFCoreSupport#isValueHolder()
         */
        public boolean isValueHolder()
        {
            // UIOutput
            return _localName.startsWith("output") || _localName.startsWith("input") //$NON-NLS-1$ //$NON-NLS-2$
                || _localName.startsWith("select"); //$NON-NLS-1$
        }

        /* (non-Javadoc)
         * @see org.eclipse.jst.pagedesigner.jsf.actions.IJSFCoreSupport#isEditableValueHolder()
         */
        public boolean isEditableValueHolder()
        {
            // UIInput
            return _localName.startsWith("input") || _localName.startsWith("select"); //$NON-NLS-1$ //$NON-NLS-2$
        }

        /* (non-Javadoc)
         * @see org.eclipse.jst.pagedesigner.jsf.actions.IJSFCoreSupport#supportSelectItems()
         */
        public boolean supportSelectItems()
        {
            return _localName.startsWith("select"); //$NON-NLS-1$
        }

    }
}
