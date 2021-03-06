/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
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
package org.eclipse.jst.jsf.validation.el.tests.jsp;

import java.util.List;

import org.eclipse.jdt.core.Signature;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;

/**
 * Test cases for logical not equals (ne, !=)
 * 
 * @author cbateman
 */
public class LogicalNotEqualsTestCase extends SingleJSPTestCase
{
    public LogicalNotEqualsTestCase()
    {
        super("/testdata/jsps/logicalNotEquals.jsp.data", "/logicalNotEquals.jsp", JSFVersion.V1_1,FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        assertEquals("myBean.stringProperty != '3'", ELAssert.getELText(_structuredDocument,828));
        assertEquals("myBean.stringProperty ne '3'", ELAssert.getELText(_structuredDocument,889));
        assertEquals("myBean.integerProperty != 3", ELAssert.getELText(_structuredDocument,950));
        assertEquals("myBean.integerProperty ne 3", ELAssert.getELText(_structuredDocument,1010));
        assertEquals("myBean.booleanProperty != true", ELAssert.getELText(_structuredDocument,1070));
        assertEquals("myBean.booleanProperty ne true", ELAssert.getELText(_structuredDocument,1133));
        assertEquals("myBean.integerProperty != '4' ", ELAssert.getELText(_structuredDocument,1196));
        assertEquals("myBean.integerProperty ne '4' ", ELAssert.getELText(_structuredDocument,1259));
        assertEquals("myBean.bigDoubleProperty != 4.5", ELAssert.getELText(_structuredDocument,1322));
        assertEquals("myBean.doubleProperty ne 67", ELAssert.getELText(_structuredDocument,1386));
        assertEquals("myBean.bigIntegerProperty != 500", ELAssert.getELText(_structuredDocument,1446));
        assertEquals("myBean.stringArrayProperty != '3'", ELAssert.getELText(_structuredDocument,1511));
        assertEquals("myBean.stringArrayProperty ne myBean.listProperty", ELAssert.getELText(_structuredDocument,1577));
        assertEquals("myBean.coins != 'dime'", ELAssert.getELText(_structuredDocument,1655));
        assertEquals("myBean.coins ne 'dime'", ELAssert.getELText(_structuredDocument,1707));
        assertEquals("myBean.colors != 'red'", ELAssert.getELText(_structuredDocument,1759));
        assertEquals("myBean.colors ne 'red'", ELAssert.getELText(_structuredDocument,1810));
        assertEquals("myBean.coins != myBean.stringProperty", ELAssert.getELText(_structuredDocument,1861));
        assertEquals("myBean.coins ne myBean.stringProperty", ELAssert.getELText(_structuredDocument,1927));
        assertEquals("myBean.rawEnum != 'red'", ELAssert.getELText(_structuredDocument,1993));
        assertEquals("myBean.coinEnum ne myBean.coins", ELAssert.getELText(_structuredDocument,2045));

        assertEquals("5 != 3", ELAssert.getELText(_structuredDocument,2128));
        assertEquals("5 ne 3", ELAssert.getELText(_structuredDocument,2164));
        assertEquals("true != false", ELAssert.getELText(_structuredDocument,2200));
        assertEquals("true ne false", ELAssert.getELText(_structuredDocument,2243));
        assertEquals("'4' != '34'", ELAssert.getELText(_structuredDocument,2286));
        assertEquals("'4' ne '34'", ELAssert.getELText(_structuredDocument,2327));
        assertEquals("'34' != '34'", ELAssert.getELText(_structuredDocument,2368));
        assertEquals("'34' ne '34'", ELAssert.getELText(_structuredDocument,2410));
        assertEquals("myBean.integerProperty != null", ELAssert.getELText(_structuredDocument,2452));
        assertEquals("null ne myBean.integerProperty", ELAssert.getELText(_structuredDocument,2512));
        assertEquals("5.4 != 4.3", ELAssert.getELText(_structuredDocument,2572));
        assertEquals("true != true", ELAssert.getELText(_structuredDocument,2612));
        assertEquals("myBean.coins != 'notAValue'", ELAssert.getELText(_structuredDocument,2654));
        assertEquals("myBean.coins ne 'notAValue'", ELAssert.getELText(_structuredDocument,2711));
        assertEquals("myBean.coins != 'notAValue' || myBean.coins != 'dime'", ELAssert.getELText(_structuredDocument,2768));
        assertEquals("myBean.coins ne 'notAValue' || myBean.coins ne 'dime'", ELAssert.getELText(_structuredDocument,2851));
        assertEquals("myBean.coins != myBean.colors", ELAssert.getELText(_structuredDocument,2934));

        assertEquals("5 != true", ELAssert.getELText(_structuredDocument,3012));
        assertEquals("5 ne true", ELAssert.getELText(_structuredDocument,3051));
        assertEquals("myBean.integerProperty != myBean.booleanProperty", ELAssert.getELText(_structuredDocument,3090));
        assertEquals("myBean.integerProperty ne myBean.booleanProperty", ELAssert.getELText(_structuredDocument,3168));
        assertEquals("myBean.stringArrayProperty != myBean.booleanProperty", ELAssert.getELText(_structuredDocument,3246));
        assertEquals("myBean.booleanProperty ne myBean.stringArrayProperty", ELAssert.getELText(_structuredDocument,3328));
        assertEquals("myBean.integerProperty != true ", ELAssert.getELText(_structuredDocument,3413));
        assertEquals("myBean.integerProperty ne true ", ELAssert.getELText(_structuredDocument,3477));
        assertEquals("false != myBean.integerProperty", ELAssert.getELText(_structuredDocument,3541));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_BOOLEAN);
        assertNoError(889, Signature.SIG_BOOLEAN);
        assertNoError(950, Signature.SIG_BOOLEAN);
        assertNoError(1010, Signature.SIG_BOOLEAN);
        assertNoError(1070, Signature.SIG_BOOLEAN);
        assertNoError(1133, Signature.SIG_BOOLEAN);
        assertNoError(1196, Signature.SIG_BOOLEAN);
        assertNoError(1259, Signature.SIG_BOOLEAN);
        assertNoError(1322, Signature.SIG_BOOLEAN);
        assertNoError(1386, Signature.SIG_BOOLEAN);
        assertNoError(1446, Signature.SIG_BOOLEAN);
        assertNoError(1511, Signature.SIG_BOOLEAN);
        assertNoError(1577, Signature.SIG_BOOLEAN);
        assertNoError(1655, Signature.SIG_BOOLEAN);
        assertNoError(1707, Signature.SIG_BOOLEAN);
        assertNoError(1759, Signature.SIG_BOOLEAN);
        assertNoError(1810, Signature.SIG_BOOLEAN);
        assertNoError(1861, Signature.SIG_BOOLEAN);
        assertNoError(1927, Signature.SIG_BOOLEAN);
        assertNoError(1993, Signature.SIG_BOOLEAN);
        assertNoError(2045, Signature.SIG_BOOLEAN);
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=235637
        // can only call comparison null always same if can verify both
        // sides of the expression are null
        assertNoError(2452, Signature.SIG_BOOLEAN);
        assertNoError(2512, Signature.SIG_BOOLEAN);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(2128, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2164, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2200, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2243, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2286, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2327, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2368, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2410, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2572, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2612, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(2654, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);

        list = assertSemanticWarning(2711, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);

        list = assertSemanticWarning(2768, Signature.SIG_BOOLEAN, 2);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(2851, Signature.SIG_BOOLEAN, 2);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_FIRST_ARGUMENT_SHORT_CIRCUITS_ID);

        list = assertSemanticWarning(2934, Signature.SIG_BOOLEAN, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_COMPARISON_WITH_ENUM_ALWAYS_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(3012, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3051, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3090, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3168, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3246, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(3328, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CANNOT_COERCE_ARGUMENT_TO_BOOLEAN_ID);

        list = assertSemanticError(3413, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3477, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(3541, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
