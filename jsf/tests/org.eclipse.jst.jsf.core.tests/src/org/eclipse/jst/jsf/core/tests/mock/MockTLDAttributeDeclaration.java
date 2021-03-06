/*******************************************************************************
 * Copyright (c) 2010, 2019 IBM Corporation and others.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.core.tests.mock;

import org.eclipse.jst.jsp.core.internal.contentmodel.tld.CMDataTypeImpl;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDAttributeDeclaration;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDataType;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;

public class MockTLDAttributeDeclaration extends MockCMAttributeDeclaration
        implements TLDAttributeDeclaration
{

    private final String _description;
    private final String _id;
    private final boolean _required;

    public MockTLDAttributeDeclaration(String nodeName, CMDataType cmType, String description, String id, boolean required)
    {
        super(nodeName, cmType);
        _description = description;
        _id = id;
        _required = required;
    }

    public MockTLDAttributeDeclaration(final String nodeName, String description, String id, boolean required)
    {
        this(nodeName, new CMDataTypeImpl("foo data type name"), description, id, required);
    }
    public String getDescription()
    {
        return _description;
    }

    public String getId()
    {
        return _id;
    }

    public CMDocument getOwnerDocument()
    {
        throw new UnsupportedOperationException();
    }

    public String getRtexprvalue()
    {
        throw new UnsupportedOperationException();
    }

    public String getType()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isFragment()
    {
        throw new UnsupportedOperationException();
    }

    public boolean isRequired()
    {
        return _required;
    }

}
