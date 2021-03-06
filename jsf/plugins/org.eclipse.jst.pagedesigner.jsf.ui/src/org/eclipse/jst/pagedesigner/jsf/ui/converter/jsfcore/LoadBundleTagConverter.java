/*******************************************************************************
 * Copyright (c) 2006, 2008 Sybase, Inc. and others.
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
package org.eclipse.jst.pagedesigner.jsf.ui.converter.jsfcore;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.PropertyResourceBundle;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jst.jsf.common.ui.IFileFolderConstants;
import org.eclipse.jst.jsf.common.ui.internal.logging.Logger;
import org.eclipse.jst.jsf.common.ui.internal.utils.ResourceUtils;
import org.eclipse.jst.jsf.core.internal.tld.IJSFConstants;
import org.eclipse.jst.pagedesigner.PDPlugin;
import org.eclipse.jst.pagedesigner.converter.HiddenTagConverter;
import org.eclipse.jst.pagedesigner.utils.PreviewUtil;
import org.eclipse.jst.pagedesigner.utils.StructuredModelUtil;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMModel;
import org.eclipse.wst.xml.core.internal.provisional.document.IDOMNode;
import org.w3c.dom.Element;

/**
 * @author mengbo
 * @deprecated Use DTTagConverter meta-data instead
 */
public class LoadBundleTagConverter extends HiddenTagConverter
{
    private static Logger _log = PDPlugin.getLogger(LoadBundleTagConverter.class);

    /**
     * @param host
     * @param labelProvider 
     */
    public LoadBundleTagConverter(Element host, ILabelProvider labelProvider)
    {
        super(host, labelProvider);
    }

    /* (non-Javadoc)
     * @see org.eclipse.jst.pagedesigner.converter.ITagConverter#convertRefresh(java.lang.Object)
     */
    public void convertRefresh(Object context)
    {
        Element hostEle = getHostElement();
        String varString = hostEle.getAttribute(IJSFConstants.ATTR_VAR);
        String baseName = hostEle.getAttribute(IJSFConstants.ATTR_BASENAME);
        if (baseName == null || varString == null)
        {
            return;
        }
        baseName = baseName.replace('.', '/') + IFileFolderConstants.DOT + IFileFolderConstants.EXT_PROPERTIES;
        IProject project = null;
        
        if (hostEle instanceof IDOMNode)
        {
            IDOMModel model = ((IDOMNode) hostEle).getModel();
            if (model != null)
            {
                project = StructuredModelUtil.getProjectFor(model);
            }
        }
        if (project == null)
        {
            return;
        }
        String prop = (new StringBuffer("webroot") //$NON-NLS-1$
            .append(IFileFolderConstants.PATH_SEPARATOR).append(IFileFolderConstants.FOLDER_WEBINF).append(
            IFileFolderConstants.PATH_SEPARATOR).append(IFileFolderConstants.FOLDER_CLASS).append(
            IFileFolderConstants.PATH_SEPARATOR).append(baseName)).toString();
        IResource res = project.findMember(prop);
        if (res == null)
        {
            prop = IFileFolderConstants.FOLDER_SOURCE + IFileFolderConstants.PATH_SEPARATOR + baseName;
            res = project.findMember(prop);
            if (res == null)
            {
                return;
            }
        }
        InputStream ins = null;
        try
        {
            File f = new File(res.getLocation().toString());
            ins = new FileInputStream(f);
            if (ins != null)
            {
                ins = new BufferedInputStream(ins);
                PropertyResourceBundle bundle = new PropertyResourceBundle(ins);
                if (bundle != null)
                {
                    if (PreviewUtil.getBUNDLE_MAP() == null)
                    {
                        PreviewUtil.setBUNDLE_MAP(new HashMap());
                    }
                    else
                    {
                        PreviewUtil.getBUNDLE_MAP().clear();
                    }
                    PreviewUtil.getBUNDLE_MAP().put(varString, bundle);
                    PreviewUtil.setBUNDLE(bundle);
                    PreviewUtil.setVAR(varString);
                }
            }
        }
        catch (MalformedURLException e)
        {
            _log.error("LoadBundleTagConverter.convertRefresh.MalformedURLException", e); //$NON-NLS-1$
            return;
        }
        catch (IOException e)
        {
            _log.error("LoadBundleTagConverter.convertRefresh.IOException", e); //$NON-NLS-1$
            return;
        }
        finally
        {
            ResourceUtils.ensureClosed(ins);
        }

        return;
    }
}
