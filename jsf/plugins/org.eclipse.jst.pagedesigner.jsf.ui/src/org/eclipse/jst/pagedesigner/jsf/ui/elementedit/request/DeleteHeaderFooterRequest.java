/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.jsf.ui.elementedit.request;

import org.eclipse.gef.Request;

/**
 * @author mengbo
 * @version 1.5
 */
public class DeleteHeaderFooterRequest extends Request
{
    private boolean _isHeader;

    /**
     * @param type
     * @param isHeader
     */
    public DeleteHeaderFooterRequest(String type, boolean isHeader)
    {
        super(type);
        this._isHeader = isHeader;
    }

    /**
     * @return Returns the _isHeader.
     */
    public boolean isHeader()
    {
        return _isHeader;
    }

    /**
     * @param header The _isHeader to set.
     */
    public void setHeader(boolean header)
    {
        _isHeader = header;
    }
}
