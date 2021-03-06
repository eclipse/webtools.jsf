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
package org.eclipse.jst.jsf.common.runtime.tests.model.behavioural;

import junit.framework.TestCase;

import org.eclipse.jst.jsf.common.runtime.internal.model.behavioural.ValueHolderInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentFactory;
import org.eclipse.jst.jsf.common.runtime.internal.model.component.ComponentInfo;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterDecorator;
import org.eclipse.jst.jsf.common.runtime.internal.model.decorator.ConverterTypeInfo;
import org.eclipse.jst.jsf.common.runtime.tests.model.RuntimeTestUtil;

public class TestValueHolderInfo extends TestCase {

    private ComponentInfo _componentInfo;
    private ValueHolderInfo _valueHolder;
    private ConverterTypeInfo  _converterTypeInfo;
    private ConverterDecorator _converterDecorator;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        _converterTypeInfo = ConverterTypeInfo.UNKNOWN;
        _converterDecorator = new ConverterDecorator(_componentInfo, _converterTypeInfo);
        _componentInfo = ComponentFactory.createComponentInfo("id", null, RuntimeTestUtil
                .createComponentTypeInfo(), true);
        _valueHolder = new ValueHolderInfo(_converterDecorator, "value", "value");
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testGetValue() {
        assertEquals("value", _valueHolder.getValue());
    }

    public void testGetLocalValue() {
        assertEquals("value", _valueHolder.getLocalValue());
    }

    public void testGetConverter() {
        RuntimeTestUtil.verifySame(_converterDecorator, _valueHolder.getConverter());
        RuntimeTestUtil.verifySame(_converterTypeInfo, _valueHolder.getConverter().getTypeInfo());
    }

    public void testSerializable() throws Exception
    {
        final ValueHolderInfo   deserialized = 
            RuntimeTestUtil.serializeDeserialize(_valueHolder);

        RuntimeTestUtil.verifySame(_valueHolder, deserialized);
    }
}
