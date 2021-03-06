/*******************************************************************************
 * Copyright (c) 2006, 2013 Oracle Corporation.
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

package org.eclipse.jst.jsf.context.resolver.structureddocument.internal.impl;


import java.util.Iterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jst.jsf.context.AbstractDelegatingFactory;
import org.eclipse.jst.jsf.context.IModelContext;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IDOMContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IMetadataContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory;
import org.eclipse.jst.jsf.context.resolver.structureddocument.ITaglibContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.IWorkspaceContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IStructuredDocumentContextResolverFactory2;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.ITextRegionContextResolver;
import org.eclipse.jst.jsf.context.resolver.structureddocument.internal.IXMLNodeContextResolver;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.wst.sse.core.internal.provisional.text.IStructuredDocument;

/**
 * Implements a factory for creating context resolvers for structured document
 * contexts.
 * 
 * @author cbateman
 * 
 */
public class StructuredDocumentContextResolverFactory extends
AbstractDelegatingFactory implements
IStructuredDocumentContextResolverFactory, IStructuredDocumentContextResolverFactory2
{
    /* static attributes */
    private static StructuredDocumentContextResolverFactory INSTANCE;

    /**
     * @return an instance (possibly shared) of the this factory
     */
    public synchronized static StructuredDocumentContextResolverFactory getInstance()
    {
        if (INSTANCE == null)
        {
            INSTANCE = new StructuredDocumentContextResolverFactory();
        }

        return INSTANCE;
    }

    /**
     * Constructor
     */
    protected StructuredDocumentContextResolverFactory()
    {
        super(new Class[]
                        { IStructuredDocumentContextResolverFactory.class });
    }

    /**
     * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory#getDOMContextResolver(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
     */
    public IDOMContextResolver getDOMContextResolver(
            final IStructuredDocumentContext context)
    {
        IDOMContextResolver resolver = internalGetDOMResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetDOMResolver(context);
        }

        return resolver;
    }

    private IDOMContextResolver internalGetDOMResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new DOMContextResolver(context);
        }

        return null;
    }

    private IDOMContextResolver delegateGetDOMResolver(
            final IStructuredDocumentContext context)
    {
        Iterator<IAdaptable> it = getDelegatesIterator();
        while (it.hasNext())
        {
            IAdaptable adapter = it.next();

            final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) adapter
            .getAdapter(IStructuredDocumentContextResolverFactory.class);

            if (delegateFactory != null)
            {
                final IDOMContextResolver contextResolver = delegateFactory
                .getDOMContextResolver(context);

                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }
        }

        return null;
    }

    /**
     * @see org.eclipse.jst.jsf.context.resolver.structureddocument.IStructuredDocumentContextResolverFactory#getTextRegionResolver(org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext)
     */
    public ITextRegionContextResolver getTextRegionResolver(
            final IStructuredDocumentContext context)
    {
        ITextRegionContextResolver resolver = internalGetTextRegionResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetTextRegionResolver(context);
        }

        return resolver;
    }

    private ITextRegionContextResolver internalGetTextRegionResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new TextRegionContextResolver(context);
        }

        return null;
    }

    private ITextRegionContextResolver delegateGetTextRegionResolver(
            final IStructuredDocumentContext context)
    {
        Iterator<IAdaptable> it = getDelegatesIterator();
        while (it.hasNext())
        {
            IAdaptable adapter = it.next();
            final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) (adapter)
            .getAdapter(IStructuredDocumentContextResolverFactory.class);
            final ITextRegionContextResolver contextResolver = delegateFactory
            .getTextRegionResolver(context);

            if (contextResolver != null)
            {
                return contextResolver;
            }
        }

        return null;
    }

    public IWorkspaceContextResolver getWorkspaceContextResolver(
            final IStructuredDocumentContext context)
    {
        IWorkspaceContextResolver resolver = internalGetWorkspaceContextResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetWorkspaceContextResolver(context);
        }

        return resolver;

    }
    
    public IWorkspaceContextResolver getWorkspaceContextResolver2(IModelContext context)
    {
        IWorkspaceContextResolver resolver = delegateGetWorkspaceContextResolver2(context);
        
        if (resolver != null)
        {
            return resolver;
        }

        if (context instanceof IStructuredDocumentContext)
        {
            return getWorkspaceContextResolver((IStructuredDocumentContext) context);
        }
        
        return null;
    }

    private IWorkspaceContextResolver delegateGetWorkspaceContextResolver2(IModelContext context)
    {
        Iterator<IAdaptable> it = getDelegatesIterator();
        while (it.hasNext())
        {
            IAdaptable adapter = it.next();
            final IStructuredDocumentContextResolverFactory2 delegateFactory = (IStructuredDocumentContextResolverFactory2) (adapter)
                    .getAdapter(IStructuredDocumentContextResolverFactory2.class);
            final IWorkspaceContextResolver contextResolver = delegateFactory.getWorkspaceContextResolver2(context);

            if (contextResolver != null) { return contextResolver; }
        }

        return null;
    }

    private IWorkspaceContextResolver internalGetWorkspaceContextResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new WorkspaceContextResolver(context);
        }

        return null;
    }

    private IWorkspaceContextResolver delegateGetWorkspaceContextResolver(
            final IStructuredDocumentContext context)
    {
        Iterator<IAdaptable> it = getDelegatesIterator();
        while (it.hasNext())
        {
            IAdaptable adapter = it.next();
            final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) (adapter)
            .getAdapter(IStructuredDocumentContextResolverFactory.class);
            final IWorkspaceContextResolver contextResolver = delegateFactory
            .getWorkspaceContextResolver(context);

            if (contextResolver != null)
            {
                return contextResolver;
            }
        }

        return null;
    }

    public ITaglibContextResolver getTaglibContextResolver(
            final IStructuredDocumentContext context)
    {
        // check the delegats first
        ITaglibContextResolver resolver = internalGetTaglibContextResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetTaglibContextResolver(context);
        }

        return resolver;
    }

    public ITaglibContextResolver getTaglibContextResolverFromDelegates(
            final IStructuredDocumentContext context)
    {
        // check the delegats first
        ITaglibContextResolver resolver = delegateGetTaglibContextResolver(context);

        if (resolver == null)
        {
            resolver = internalGetTaglibContextResolver(context);
        }

        return resolver;
    }

    private ITaglibContextResolver internalGetTaglibContextResolver(
            final IStructuredDocumentContext context)
    {
        // always delegate.  No defaults.
        return null;
    }

    private ITaglibContextResolver delegateGetTaglibContextResolver(
            final IStructuredDocumentContext context)
    {
        Iterator<IAdaptable> it = getDelegatesIterator();
        while (it.hasNext())
        {
            IAdaptable adapter = it.next();
            final IStructuredDocumentContextResolverFactory delegateFactory =
                (IStructuredDocumentContextResolverFactory) adapter
                .getAdapter(IStructuredDocumentContextResolverFactory.class);

            if (delegateFactory != null)
            {
                final ITaglibContextResolver contextResolver = delegateFactory
                        .getTaglibContextResolver(context);

                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }
        }

        return null;
    }

    public IMetadataContextResolver getMetadataContextResolver(
            final IStructuredDocumentContext context)
    {
        IMetadataContextResolver resolver = internalGetMetadataContextResolver(context);

        if (resolver == null)
        {
            resolver = delegateGetMetadataContextResolver(context);
        }

        return resolver;
    }

    private IMetadataContextResolver internalGetMetadataContextResolver(
            final IStructuredDocumentContext context)
    {
        if (context.getStructuredDocument() instanceof IStructuredDocument)
        {
            return new MetadataContextResolver(this, context);
        }

        return null;
    }

    private IMetadataContextResolver delegateGetMetadataContextResolver(
            final IStructuredDocumentContext context)
    {
        Iterator<IAdaptable> it = getDelegatesIterator();
        while (it.hasNext())
        {
            IAdaptable adapter = it.next();
            final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) (adapter)
            .getAdapter(IStructuredDocumentContextResolverFactory.class);
            final IMetadataContextResolver contextResolver = delegateFactory
            .getMetadataContextResolver(context);

            if (contextResolver != null)
            {
                return contextResolver;
            }
        }

        return null;
    }

	public <T> T getResolver(final IStructuredDocumentContext context, final Class<T> clazz) {

	    {
	        Iterator<IAdaptable> it = getDelegatesIterator();
	        while (it.hasNext())
            {
	            IAdaptable adapter = it.next();
                final IStructuredDocumentContextResolverFactory delegateFactory =
                    (IStructuredDocumentContextResolverFactory) adapter
                    .getAdapter(IStructuredDocumentContextResolverFactory.class);

                if (delegateFactory != null 
                		&& delegateFactory instanceof IStructuredDocumentContextResolverFactory2)
                {
                    final IStructuredDocumentContextResolverFactory2 contextResolverFactory = (IStructuredDocumentContextResolverFactory2)delegateFactory;                            
                    final T contextResolver = contextResolverFactory.getResolver(context, clazz);
                    if (contextResolver != null)
                    {
                        return contextResolver;
                    }
                }
            }

            return null;
        }
	}

    public IXMLNodeContextResolver getXMLNodeContextResolver(IModelContext context)
    {
        IXMLNodeContextResolver delegateGetXMLNodeResolver = delegateGetXMLNodeResolver(context);
        if (delegateGetXMLNodeResolver != null)
        {
            return delegateGetXMLNodeResolver;
        }

        if (context instanceof IStructuredDocumentContext)
        {
           IDOMContextResolver domContextResolver = getDOMContextResolver((IStructuredDocumentContext) context);
           return new DOMBasedXMLNodeContextResolver(domContextResolver);
        }
        
        return null;
    }
    
    private IXMLNodeContextResolver delegateGetXMLNodeResolver(
            final IModelContext context)
    {
        Iterator<IAdaptable> it = getDelegatesIterator();
        while (it.hasNext())
        {
            IAdaptable adapter = it.next();

            final IStructuredDocumentContextResolverFactory delegateFactory = (IStructuredDocumentContextResolverFactory) adapter
            .getAdapter(IStructuredDocumentContextResolverFactory.class);

            if (delegateFactory instanceof IStructuredDocumentContextResolverFactory2)
            {
                final IXMLNodeContextResolver contextResolver = ((IStructuredDocumentContextResolverFactory2)delegateFactory)
                .getXMLNodeContextResolver(context);

                if (contextResolver != null)
                {
                    return contextResolver;
                }
            }
        }

        return null;
    }
}
