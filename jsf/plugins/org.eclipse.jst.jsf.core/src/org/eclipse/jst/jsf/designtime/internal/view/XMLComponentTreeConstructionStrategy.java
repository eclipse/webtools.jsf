/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.designtime.internal.view;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jst.jsf.common.runtime.internal.model.ViewObject;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.Decorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ValidatorDecorator;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContext;
import org.eclipse.jst.jsf.context.structureddocument.IStructuredDocumentContextFactory;
import org.eclipse.jst.jsf.designtime.context.DTFacesContext;
import org.eclipse.jst.jsf.designtime.internal.view.XMLViewObjectMappingService.ElementData;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A component tree construction strategy based on XML view definition
 * 
 * @author cbateman
 * 
 */
public class XMLComponentTreeConstructionStrategy extends
        ComponentTreeConstructionStrategy<Node, IDocument>
{
    private final XMLViewDefnAdapter                _adapter;
    private final XMLViewObjectConstructionStrategy _objectConstructionStrategy;
    private XMLViewObjectMappingService             _tagMappingService;

    /**
     * @param adapter
     * @param project
     */
    public XMLComponentTreeConstructionStrategy(
            final XMLViewDefnAdapter adapter, final IProject project)
    {
        _adapter = adapter;
        _objectConstructionStrategy = new XMLViewObjectConstructionStrategy(
                adapter, new ComponentConstructionData(0, null, project));
    }

    @Override
    public ComponentInfo createComponentTree(final DTFacesContext context,
            final DTUIViewRoot viewRoot)
    {
        final IDocument container = _adapter.getContainer(context, viewRoot
                .getViewId());
        final List<Node> roots = _adapter.getViewDefnRoots(container);

        if (roots.size() > 0)
        {
            _tagMappingService = (XMLViewObjectMappingService) viewRoot
                    .getServices()
                    .getAdapter(XMLViewObjectMappingService.class);
            _objectConstructionStrategy.getConstructionData().setIdCounter(0);
            // can only handle a single root for XML; should be the DOM root
            return buildComponentTree(roots.get(0), viewRoot, container);
        }

        return viewRoot;
    }

    private ComponentInfo buildComponentTree(final Node root,
            final DTUIViewRoot viewRoot, final IDocument document)
    {
        final ComponentInfo dummyRoot = ComponentFactory.createComponentInfo(
                null, null, null, true);
        // populate the dummy root
        recurseDOMModel(root, dummyRoot, document);

        // try to extract the view defined root from the dummyRoot and update
        // 'root' with its children.
        populateViewRoot(viewRoot, dummyRoot.getChildren());
        return viewRoot;
    }

    /**
     * Tries to find the view defined view root in children and use it populate
     * viewRoot. Children may sub-class to a different algorithm or, in some
     * cases create an implicit (i.e. Facelets does this) view root if one is
     * not explicitly created by the view definition.
     * 
     * Regardless of the strategy, the following post-conditions must be true
     * 
     * To the extend that children represent the top-level objects in the view
     * under the presumed root, viewRoot must be populated with them either
     * directly if the creation of a view root is implicit (i.e. Facelets) or
     * through a valid view root declaration found in the view definition (i.e.
     * f:view in JSP) found the children list.
     * 
     * The default behaviour assumes the JSP case.
     * 
     * TODO: add validation cases for missing view roots in JSP.
     * 
     * @param viewRoot
     * @param children
     */
    protected void populateViewRoot(final DTUIViewRoot viewRoot,
            final List children)
    {
        ComponentInfo foundRoot = null;
        // TODO: additional cases:
        // 1) Valid case: view is a fragment and has one or more non-view root
        // children
        // 2) Invalid case: not a fragment and has no view root
        // 3) Invalid case: any definition and has more than one view root
        // 4) Invalid case: any definition and has component siblings to the
        // view root
        FIND_VIEWROOT: for (final Iterator it = children.iterator(); it
                .hasNext();)
        {
            final ComponentInfo topLevelChild = (ComponentInfo) it.next();

            if ("javax.faces.ViewRoot".equals(topLevelChild
                    .getComponentTypeInfo().getComponentType()))
            {
                foundRoot = topLevelChild;
                break FIND_VIEWROOT;
            }
        }

        if (foundRoot != null)
        {
            for (final Iterator it = foundRoot.getChildren().iterator(); it
                    .hasNext();)
            {
                final ComponentInfo child = (ComponentInfo) it.next();
                final String facetName = foundRoot.getFacetName(child);

                // if not a facet, add as a normal child
                if (facetName == null)
                {
                    viewRoot.addChild(child);
                }
                // if it is a facet, add as a facet
                else
                {
                    viewRoot.addFacet(facetName, child);
                }
            }
        }
    }

    private void recurseDOMModel(final Node node, final ComponentInfo parent,
            final IDocument document)
    {
        ViewObject mappedObject = null;

        _objectConstructionStrategy.getConstructionData().setParent(parent);

        mappedObject = _adapter.mapToViewObject(node,
                _objectConstructionStrategy, document);

        ComponentInfo newParent = parent;

        if (node instanceof Element)
        {
            maybeAddMapping(mappedObject, (Element) node, document);
        }

        if (mappedObject instanceof ComponentInfo)
        {
            parent.addChild((ComponentInfo) mappedObject);
            newParent = (ComponentInfo) mappedObject;
        }
        else if (mappedObject instanceof ConverterDecorator)
        {
            // TODO: validate for parent is not a ValueHolder
            parent.addDecorator((Decorator) mappedObject,
                    ComponentFactory.CONVERTER);
        }
        else if (mappedObject instanceof ValidatorDecorator)
        {
            // TODO: validate for parent is a not an EditableValueHolder
            parent.addDecorator((Decorator) mappedObject,
                    ComponentFactory.VALIDATOR);
        }

        final NodeList children = node.getChildNodes();
        final int numChildren = children.getLength();

        for (int i = 0; i < numChildren; i++)
        {
            recurseDOMModel(children.item(i), newParent, document);
        }
    }

    private void maybeAddMapping(ViewObject mappedObject, Element node,
            IDocument document)
    {
        if (mappedObject != null && _tagMappingService != null)
        {
            final String uri = _adapter.getNamespace(node, document);
            final IStructuredDocumentContext context = IStructuredDocumentContextFactory.INSTANCE
                    .getContext(document, node);
            final ElementData elementData = XMLViewObjectMappingService
                    .createElementData(uri, node.getLocalName(), context);
            _tagMappingService.createMapping(elementData, mappedObject);
        }
    }
}
