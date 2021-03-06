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
package org.eclipse.jst.jsf.facelet.core.internal.tagmodel;

import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorTypeInfo;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.IAttributeAdvisor;
import org.eclipse.jst.jsf.facelet.core.internal.cm.FaceletDocumentFactory;


/**
 * 
 *
 */
public class ValidatorTag extends FaceletTag
{
    /**
     * 
     */
    private static final long serialVersionUID = 3898280066837027347L;
    private final ValidatorTypeInfo _validatorTypeInfo;

    /**
     * @param name
     * @param uri
     * @param validatorTypeInfo 
     * @param handlerClass 
     * @param factory 
     * @param advisor 
     */
    public ValidatorTag(final String uri, final String name, final ValidatorTypeInfo validatorTypeInfo, final String handlerClass, final FaceletDocumentFactory factory, 
            final IAttributeAdvisor advisor)
    {
        super(uri, name, TagType.VALIDATOR, handlerClass, factory, advisor);
        _validatorTypeInfo = validatorTypeInfo;
    }
    /**
     * @return the validator id
     */
    public ValidatorTypeInfo getValidatorId()
    {
        return _validatorTypeInfo;
    }
    @Override
    public String toString()
    {
        String toString = super.toString();
        toString += "Validator Id: "+getValidatorId()+"\n"; //$NON-NLS-1$ //$NON-NLS-2$

        if (getTagHandlerClassName() != null)
        {
            toString += "Handler Class: "+getTagHandlerClassName()+"\n"; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return toString;
    }
}
