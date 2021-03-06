/*******************************************************************************
 * Copyright (c) 2006, 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman/Oracle - initial API and implementation
 *    
 ********************************************************************************/

package org.eclipse.jst.jsf.common.internal.types;

import java.math.BigDecimal;
import java.math.BigInteger;

import org.eclipse.jdt.core.Signature;

/**
 * Represents a IntegerLiteral as defined by JSP.2.9
 * @author cbateman
 *
 */
public class IntegerLiteralType extends NumericTypeLiteral
{
    /**
     * A singleton for zero literals
     */
    public final static IntegerLiteralType      ZERO = new IntegerLiteralType(0);
    
    private final long   _literalValue;
    
    /**
     * @param literalValue
     */
    public IntegerLiteralType(long literalValue)
    {
        // according to the notes to JSP.2.9, bullet 4, integer literals are longs
        super(Signature.SIG_LONG);
        _literalValue = literalValue;
    }
    
    protected Number getBoxedValue() 
    {
        return Long.valueOf(_literalValue);
    }

    /**
     * Per JSP.2.8.3, step 5
     * @see org.eclipse.jst.jsf.common.internal.types.LiteralType#coerceToNumber(java.lang.Class)
     */
    public Number coerceToNumber(Class T) throws TypeCoercionException 
    {        
        if (T == BigInteger.class)
        {
            return BigInteger.valueOf(_literalValue);
        }
        else if (T == BigDecimal.class)
        {
            return BigDecimal.valueOf(_literalValue);
        }
        
        Number commonCoercion = super.coerceToNumber(T);

        if (commonCoercion == null)
        {
            throw new IllegalArgumentException("Not a target numeric type: "+T); //$NON-NLS-1$
        }

        return commonCoercion;
    }
}
