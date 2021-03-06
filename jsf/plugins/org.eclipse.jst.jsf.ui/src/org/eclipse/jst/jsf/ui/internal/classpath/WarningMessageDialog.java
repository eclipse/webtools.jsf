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
package org.eclipse.jst.jsf.ui.internal.classpath;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * Customized warning dialog for JSF Library Upgrade warnings.
 * 
 * @author cbateman
 * @deprecated
 */
class WarningMessageDialog extends MessageDialog 
{
    private Text    _messageLabel;
    
    /**
     * @param parent
     * @param title
     * @param message
     * @return true if the user hit OK
     * 
     * Overriden to set the default button to CANCEL and use this dialog.
     */
    public static boolean openConfirm(Shell parent, String title, String message) {
        MessageDialog dialog = new WarningMessageDialog(parent, title, null, // accept
                // the
                // default
                // window
                // icon
                message, QUESTION, new String[] { IDialogConstants.OK_LABEL,
                        IDialogConstants.CANCEL_LABEL }, 1); // CANCEL is the
        // default
        return dialog.open() == 0;
    }
    
    WarningMessageDialog(Shell parentShell, String dialogTitle,
            Image dialogTitleImage, String dialogMessage, int dialogImageType,
            String[] dialogButtonLabels, int defaultIndex) {
        super(parentShell, dialogTitle, dialogTitleImage, dialogMessage,
                dialogImageType, dialogButtonLabels, defaultIndex);
    }

    @Override
    protected Control createMessageArea(Composite composite) 
    {
        // create composite
        // create image
        Image image = getImage();
        if (image != null) {
            imageLabel = new Label(composite, SWT.NULL);
            image.setBackground(imageLabel.getBackground());
            imageLabel.setImage(image);
            addAccessibleListeners(imageLabel, image);
            GridDataFactory.fillDefaults().align(SWT.CENTER, SWT.BEGINNING)
                    .applyTo(imageLabel);
        }
        // create message
        if (message != null) {
            _messageLabel = new Text(composite, getMessageLabelStyle()|SWT.READ_ONLY);
            _messageLabel.setText(message);
            GridDataFactory
                    .fillDefaults()
                    .align(SWT.FILL, SWT.BEGINNING)
                    .grab(true, false)
                    .hint(
                            convertHorizontalDLUsToPixels(IDialogConstants.MINIMUM_MESSAGE_AREA_WIDTH),
                            SWT.DEFAULT).applyTo(_messageLabel);
        }
        return composite;   
    }
    
    /**
     * NOTE: copied from IconAndMessageDialog
     * Add an accessible listener to the label if it can be inferred from the
     * image.
     * 
     * @param label
     * @param image
     */
    private void addAccessibleListeners(Label label, final Image image) {
        label.getAccessible().addAccessibleListener(new AccessibleAdapter() {
            public void getName(AccessibleEvent event) {
                final String accessibleMessage = getAccessibleMessageFor(image);
                if (accessibleMessage == null) {
                    return;
                }
                event.result = accessibleMessage;
            }
        });
    }
    
    /**
     * NOTE: copied from IconAndMessageDialog
     * @param image
     * @return an accesible string
     */
    private String getAccessibleMessageFor(Image image) {
        if (image.equals(getErrorImage())) {
            return JFaceResources.getString("error");//$NON-NLS-1$
        }

        if (image.equals(getWarningImage())) {
            return JFaceResources.getString("warning");//$NON-NLS-1$
        }

        if (image.equals(getInfoImage())) {
            return JFaceResources.getString("info");//$NON-NLS-1$
        }

        if (image.equals(getQuestionImage())) {
            return JFaceResources.getString("question"); //$NON-NLS-1$
        }

        return null;
    }
}
