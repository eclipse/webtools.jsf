/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.context.symbol.internal.impl;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/*package*/ class Messages
{
    private static final String         BUNDLE_NAME     = "org.eclipse.jst.jsf.context.symbol.internal.impl.messages"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
                                                                .getBundle(BUNDLE_NAME);

    private Messages()
    {
        // no external instantiation
    }

    public static String getString(String key)
    {
        try
        {
            return RESOURCE_BUNDLE.getString(key);
        }
        catch (MissingResourceException e)
        {
            return '!' + key + '!';
        }
    }
}
