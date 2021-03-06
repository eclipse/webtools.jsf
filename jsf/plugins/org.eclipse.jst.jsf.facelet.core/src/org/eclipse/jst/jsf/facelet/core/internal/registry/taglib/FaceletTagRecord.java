/*******************************************************************************
 * Copyright (c) 2008, 2010 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Cameron Bateman - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib.FaceletTaglibTag;

/**
 * Super of all facelet tag records.
 * 
 * @author cbateman
 *
 */
public abstract class FaceletTagRecord implements IFaceletTagRecord
{
    private final CopyOnWriteArrayList<ITagRecordChangeListener> _listeners;
    private final TagRecordDescriptor _descriptor;

    /**
     * @param descriptor 
     * 
     */
    public FaceletTagRecord(final TagRecordDescriptor descriptor)
    {
        _listeners = new CopyOnWriteArrayList<ITagRecordChangeListener>();
        _descriptor = descriptor;
    }

    
    public TagRecordDescriptor getDescriptor()
    {
        return _descriptor;
    }


    public void addListener(final ITagRecordChangeListener listener)
    {
        _listeners.addIfAbsent(listener);
    }

    public void removeListener(final ITagRecordChangeListener listener)
    {
        _listeners.remove(listener);
    }

    /**
     * @param event
     */
    protected void fireEvent(final TagRecordChangeEvent event)
    {
        for (final ITagRecordChangeListener listener : _listeners)
        {
            listener.changed(event);
        }
    }

    /**
     * 
     */
    private static final long serialVersionUID = -4606745577562951499L;

    public abstract String getURI();

    public abstract FaceletTaglibTag getTag(final String name);

    public abstract Collection<? extends FaceletTaglibTag> getTags();
    
}
