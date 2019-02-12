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
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib;

import org.eclipse.core.resources.IProject;

/**
 * Implemented by factories that create new IProjectTaglibDescriptor objects.
 * 
 * @author cbateman
 *
 */
public interface IProjectTaglibDescriptorFactory
{
    /**
     * @param project
     * @param factory
     * @param jarProvider
     * @param webAppProvider
     * @param vcQuery
     * @return a new taglib descriptor.
     */
    IProjectTaglibDescriptor create(final IProject project, final TagRecordFactory factory);
}
