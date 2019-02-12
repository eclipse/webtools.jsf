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
package org.eclipse.jst.pagedesigner.jsf.ui.attributegroup;

import org.eclipse.jst.jsf.common.ui.internal.dialogfield.DialogFieldGroupPage;
import org.eclipse.ui.internal.dialogs.NewWizard;

/**
 * @author mengbo
 * @version 1.5
 */
public class CommonDialogFieldWizard extends NewWizard
{
    DialogFieldGroupPage mainPage;
    /**
     * Constructg a new wizard using page as the wizard mage
     * 
     * @param page
     */
    public CommonDialogFieldWizard(DialogFieldGroupPage page)
    {
        this.mainPage = page;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#performFinish()
     */
    public boolean performFinish()
    {
        return true;
    }

    /* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages()
    {
        addPage(mainPage);        
    }
}
