/*******************************************************************************
 * Copyright (c) 2001, 2010 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.test.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;



/**
 * Represents a piece of java code (usually a full compilation unit) that is loaded
 * from a static test file somewhere.  
 * 
 * @author cbateman
 *
 */
public class TestFileResource extends LoadableResource
{
    private ByteArrayOutputStream   _buffer = new ByteArrayOutputStream();
    
    /**
     * @return the contents
     */
    public String toString()
    {
        return _buffer.toString();
    }

    public String toString(final String encoding) throws UnsupportedEncodingException
    {
        return _buffer.toString(encoding);

    }
    /**
     * @return the contents as a byte array
     */
    public byte[] toBytes()
    {
        return _buffer.toByteArray();
    }
    
    protected void bufferLoaded(byte[] buffer, int numBytes) 
    {
        _buffer.write(buffer, 0, numBytes);
    }
}
