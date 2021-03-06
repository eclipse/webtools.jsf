/*******************************************************************************
 * Copyright (c) 2001, 2008 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.designtime.tests.views.model.jsp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.jst.jsf.common.internal.policy.IdentifierOrderedIteratorPolicy;
import org.eclipse.jst.jsf.common.runtime.internal.view.model.common.ITagElement;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.internal.tld.ITLDConstants;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.CompositeTagResolvingStrategy;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.DefaultJSPTagResolver;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TLDNamespace;
import org.eclipse.jst.jsf.designtime.internal.view.model.jsp.TagIntrospectingStrategy;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDDocument;
import org.eclipse.jst.jsp.core.internal.contentmodel.tld.provisional.TLDElementDeclaration;

public class TestTLDNamespace extends BaseTestClass
{
    private TLDNamespace _coreNS;
    private TLDNamespace _htmlNS;

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();

        final List<String> policies = new ArrayList<String>();
        policies.add(DefaultJSPTagResolver.ID);
        policies.add(TagIntrospectingStrategy.ID);
        final IdentifierOrderedIteratorPolicy<String>  idOrderedPolicy =
            new IdentifierOrderedIteratorPolicy<String>(policies);

        final CompositeTagResolvingStrategy<TLDElementDeclaration> compStrategy =
            new CompositeTagResolvingStrategy<TLDElementDeclaration>(idOrderedPolicy);

        compStrategy.addStrategy(new TagIntrospectingStrategy(
                _webProjectTestEnv.getTestProject()));
        compStrategy.addStrategy(new DefaultJSPTagResolver(_webProjectTestEnv
                .getTestProject()));

        final TLDDocument coreDoc = TestUtil.getDocument(_tagRecords
                .get(ITLDConstants.URI_JSF_CORE));
        _coreNS = new TLDNamespace(coreDoc, compStrategy);

        final TLDDocument htmlDoc = TestUtil.getDocument(_tagRecords
                .get(ITLDConstants.URI_JSF_HTML));
        _htmlNS = new TLDNamespace(htmlDoc, compStrategy);
    }

    @Override
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetNSUri()
    {
        assertEquals(ITLDConstants.URI_JSF_CORE, _coreNS.getNSUri());
        assertEquals(ITLDConstants.URI_JSF_HTML, _htmlNS.getNSUri());
    }

    public void testGetViewElements()
    {
        final Map<String, ITagElement> coreElements = TestUtil
        .constructTagElements(_coreNS.getViewElements());

        if (_jsfVersion == JSFVersion.V1_2)
        {
            VerifyRegistryUtil.runVerifiers(VerifyRegistryUtil.CORE_VERIFIERS_12, coreElements);
        }
        else
        {
            VerifyRegistryUtil.runVerifiers(VerifyRegistryUtil.CORE_VERIFIERS_11, coreElements);
        }

        final Map<String, ITagElement> htmlElements = TestUtil
        .constructTagElements(_htmlNS.getViewElements());
        VerifyRegistryUtil.runVerifiers(VerifyRegistryUtil.HTML_VERIFIERS,
                htmlElements);
    }

}
