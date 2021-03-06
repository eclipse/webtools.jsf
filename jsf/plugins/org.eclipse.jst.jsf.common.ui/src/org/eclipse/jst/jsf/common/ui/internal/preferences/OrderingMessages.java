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
package org.eclipse.jst.jsf.common.ui.internal.preferences;

import org.eclipse.osgi.util.NLS;

/**
 * @author cbateman
 *
 */
public class OrderingMessages extends NLS
{
    private static final String BUNDLE_NAME= "org.eclipse.jst.jsf.common.ui.internal.preferences.OrderingMessages";//$NON-NLS-1$

    private OrderingMessages() {
        // Do not instantiate
    }

    /**
     * see OrderMessages.properties
     */
    public static String Ordering_Up;
    /**
     * see OrderMessages.properties
     */
    public static String Ordering_Down;
    
    static {
        NLS.initializeMessages(BUNDLE_NAME, OrderingMessages.class);
    }

}
