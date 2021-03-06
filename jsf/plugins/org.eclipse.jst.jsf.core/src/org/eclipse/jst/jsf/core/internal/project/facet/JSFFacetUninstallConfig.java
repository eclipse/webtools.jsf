/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
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
package org.eclipse.jst.jsf.core.internal.project.facet;

import org.eclipse.jst.common.project.facet.core.libprov.LibraryUninstallDelegate;
import org.eclipse.wst.common.project.facet.core.ActionConfig;
import org.eclipse.wst.common.project.facet.core.IActionConfigFactory;
import org.eclipse.wst.common.project.facet.core.IFacetedProjectWorkingCopy;
import org.eclipse.wst.common.project.facet.core.IProjectFacetVersion;

/**
 * Configuration for JSF facet uninstall
 *
 */
public class JSFFacetUninstallConfig

    extends ActionConfig
    
{
    private LibraryUninstallDelegate librariesUninstallDelegate = null;
    
    /**
     * @return the library uninstall delegate
     */
    public LibraryUninstallDelegate getLibrariesUninstallDelegate()
    {
        return this.librariesUninstallDelegate;
    }

    @Override
    public void setFacetedProjectWorkingCopy( final IFacetedProjectWorkingCopy fpjwc )
    {
        super.setFacetedProjectWorkingCopy( fpjwc );
        init();
    }

    @Override
    public void setProjectFacetVersion( final IProjectFacetVersion fv )
    {
        super.setProjectFacetVersion( fv );
        init();
    }
    
    private void init()
    {
        final IFacetedProjectWorkingCopy fpjwc = getFacetedProjectWorkingCopy();
        final IProjectFacetVersion fv = getProjectFacetVersion();
        
        if( this.librariesUninstallDelegate == null && fpjwc != null && fv != null )
        {
            this.librariesUninstallDelegate = new LibraryUninstallDelegate( fpjwc, fv );
        }
    }
    
    /**
     * The action configuration factory for the JSF facet uninstall config
     *
     */
    public static final class Factory
        
        implements IActionConfigFactory
        
    {
        public Object create()
        {
            return new JSFFacetUninstallConfig();
        }
    }
    
}
