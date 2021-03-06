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
package org.eclipse.jst.jsf.facelet.ui.internal.contentassist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory2;
import org.eclipse.jst.jsf.facelet.core.internal.cm.FaceletDocumentFactory;
import org.eclipse.jst.jsf.facelet.core.internal.util.ViewUtil;
import org.eclipse.jst.jsf.facelet.core.internal.util.ViewUtil.PrefixEntry;
import org.eclipse.wst.xml.core.internal.contentmodel.CMDocument;
import org.eclipse.wst.xml.core.internal.contentmodel.CMNode;
import org.eclipse.wst.xml.core.internal.contentmodel.modelquery.extension.ModelQueryExtension;
import org.w3c.dom.Element;

/**
 * A model query extension used to help support content assist in html files.
 * 
 * @author cbateman
 *
 */
public class MyModelQueryExtension extends ModelQueryExtension
{

    @Override
    public String[] getAttributeValues(Element ownerElement, String namespace,
            String name)
    {
        // TODO Auto-generated method stub
        return super.getAttributeValues(ownerElement, namespace, name);
    }

    @Override
    public CMNode[] getAvailableElementContent(Element parentElement,
            String namespace, int includeOptions)
    {
        final IStructuredDocumentContext context = IStructuredDocumentContextFactory2.INSTANCE
                .getContext(parentElement);
        if (context != null)
        {
            final IWorkspaceContextResolver resolver = IStructuredDocumentContextResolverFactory.INSTANCE
                    .getWorkspaceContextResolver(context);
            
            if (resolver != null)
            {
                final IProject project = resolver.getProject();
                final FaceletDocumentFactory factory = 
                    new FaceletDocumentFactory(project);
                final Map<String, PrefixEntry> map = 
                    ViewUtil.getDocumentNamespaces(parentElement.getOwnerDocument());
                String prefix = null;
                for (final Map.Entry<String, PrefixEntry> entry : map.entrySet())
                {
                    if (entry.getValue().getUri().equals(namespace))
                    {
                        prefix = entry.getValue().getPrefix();
                        break;
                    }
                }
                
                if (prefix != null)
                {
                    CMDocument document = 
                        factory.createCMDocumentForContext(namespace, prefix);
                    if (document != null)
                    {
                        List<CMNode>  nodes = new ArrayList<CMNode>();
                        for (final Iterator<?> it = document.getElements().iterator(); it.hasNext();)
                        {
                            nodes.add((CMNode)it.next());
                        }
                        return nodes.toArray(new CMNode[0]);
                    }
                }
            }
        }
        return new CMNode[0];
    }
}
