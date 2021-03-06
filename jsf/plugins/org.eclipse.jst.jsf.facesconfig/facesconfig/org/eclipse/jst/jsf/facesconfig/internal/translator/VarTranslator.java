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
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * Translator for the var
 *
 */
public class VarTranslator extends Translator {
    /**
     * @param domNameAndPath
     * @param feature
     */
    public VarTranslator(String domNameAndPath, EStructuralFeature feature) {
        super(domNameAndPath, feature, END_TAG_NO_INDENT);
    }

    public Translator[] getChildren()
    {
        FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;

        return new Translator[] {
            new Translator(TEXT_ATTRIBUTE_VALUE, facesPackage.getVarType_TextContent()),
            new Translator("id", facesPackage.getVarType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
        };
    }
}
