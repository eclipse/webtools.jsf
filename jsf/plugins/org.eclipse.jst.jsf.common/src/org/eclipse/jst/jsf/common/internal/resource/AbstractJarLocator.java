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
package org.eclipse.jst.jsf.common.internal.resource;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.common.internal.locator.AbstractLocator;
import org.eclipse.jst.jsf.common.internal.locator.ILocatorChangeListener;

/**
 * An abstract implementation of the JarLocator.
 * 
 * @author cbateman
 * 
 */
public abstract class AbstractJarLocator extends
        AbstractLocator<Collection<? extends ClasspathJarFile>, IProject, String>
        implements IJarLocator
{
    /**
     * @param id
     * @param displayName
     */
    public AbstractJarLocator(final String id, final String displayName)
    {
        super(id, displayName, Collections.EMPTY_LIST,
                new CopyOnWriteArrayList<ILocatorChangeListener>());
    }

    private final CopyOnWriteArrayList<JarChangeListener> _listeners = new CopyOnWriteArrayList<JarChangeListener>();

    public final Collection<? extends ClasspathJarFile> getJars(final IProject project)
    {
        return locate(project);
    }

    @Override
    protected abstract Collection<? extends ClasspathJarFile> doLocate(IProject context);

    public void addListener(final JarChangeListener listener)
    {
        _listeners.addIfAbsent(listener);
    }

    public void removeListener(final JarChangeListener listener)
    {
        _listeners.remove(listener);
    }

    /**
     * @param event
     */
    protected void fireChangeEvent(final JarChangeEvent event)
    {
        for (final JarChangeListener listener : _listeners)
        {
            listener.changed(event);
        }
    }

    public void dispose()
    {
        _listeners.clear();
    }

}
