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
package org.eclipse.jst.jsf.core.tests.set;

import org.eclipse.jst.jsf.core.tests.tagmatcher.BaseTagMatcherTestCase;

public class TestElementToTagIdentifierMapping extends BaseTagMatcherTestCase
{
    protected void setUp() throws Exception 
    {
        _srcFileName = "/testfiles/jsps/testdata1.jsp.data";
        _destFileName = "/testdata1.jsp";
        super.setUp();
    }

    public void testElementToIdMapping()
    {
        // TODO:
    }
}
