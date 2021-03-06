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
import org.eclipse.jst.jsf.common.internal.types.TypeConstants;
import org.eclipse.jst.jsf.core.JSFVersion;
import org.eclipse.jst.jsf.core.tests.validation.MockValidationReporter.ReportedProblem;
import org.eclipse.jst.jsf.validation.el.tests.base.ELAssert;
import org.eclipse.jst.jsf.validation.el.tests.base.SingleJSPTestCase;
import org.eclipse.jst.jsf.validation.internal.el.diagnostics.DiagnosticFactory;


/**
 * Test suite for arithimetic divide (/, div)
 * @author cbateman
 *
 */
public class ArithmeticDivideTestCase extends SingleJSPTestCase
{
    public ArithmeticDivideTestCase()
    {
        super("/testdata/jsps/arithmeticDivide.jsp.data", "/WEB-INF/arithmeticDivide.jsp", JSFVersion.V1_1, FACES_CONFIG_FILE_NAME_1_1);
    }

    @Override
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    @Override
    public void testSanity()
    {
        super.testSanity();
        assertEquals("myBean.integerProperty / 3", ELAssert.getELText(_structuredDocument,828));
        assertEquals("myBean.integerProperty div 3", ELAssert.getELText(_structuredDocument,887));
        assertEquals("myBean.stringProperty / 3", ELAssert.getELText(_structuredDocument,948));
        assertEquals("myBean.stringProperty div 3", ELAssert.getELText(_structuredDocument,1006));
        assertEquals("myBean.integerProperty / myBean.integerProperty", ELAssert.getELText(_structuredDocument,1066));
        assertEquals("myBean.integerProperty div myBean.integerProperty", ELAssert.getELText(_structuredDocument,1146));
        assertEquals("myBean.bigIntegerProperty / 4", ELAssert.getELText(_structuredDocument,1228));
        assertEquals("myBean.bigIntegerProperty / 5.5", ELAssert.getELText(_structuredDocument,1290));
        assertEquals("myBean.bigDoubleProperty / 5.5", ELAssert.getELText(_structuredDocument,1354));
        assertEquals("myBean.doubleProperty / 5", ELAssert.getELText(_structuredDocument,1417));

        assertEquals("5 / 3", ELAssert.getELText(_structuredDocument,1505));
        assertEquals("5 div 3", ELAssert.getELText(_structuredDocument,1540));
        assertEquals("5.5 / 4", ELAssert.getELText(_structuredDocument,1577));
        assertEquals("5.5 div 4", ELAssert.getELText(_structuredDocument,1614));
        assertEquals("'5' / '4'", ELAssert.getELText(_structuredDocument,1656));
        assertEquals("'5' div '4'", ELAssert.getELText(_structuredDocument,1698));
        assertEquals("null / null", ELAssert.getELText(_structuredDocument,1742));
        assertEquals("null div null", ELAssert.getELText(_structuredDocument,1786));
        assertEquals("5.5 / 3.5", ELAssert.getELText(_structuredDocument,1832));

        assertEquals("5 / true", ELAssert.getELText(_structuredDocument,1897));
        assertEquals("5 div true", ELAssert.getELText(_structuredDocument,1935));
        assertEquals("myBean.integerProperty / myBean.booleanProperty", ELAssert.getELText(_structuredDocument,1975));
        assertEquals("myBean.integerProperty div myBean.booleanProperty", ELAssert.getELText(_structuredDocument,2052));
        assertEquals("myBean.stringArrayProperty / myBean.booleanProperty", ELAssert.getELText(_structuredDocument,2131));
        assertEquals("myBean.integerProperty div myBean.booleanProperty", ELAssert.getELText(_structuredDocument,2212));
        assertEquals("myBean.integerProperty / true ", ELAssert.getELText(_structuredDocument,2294));
        assertEquals("myBean.integerProperty div true ", ELAssert.getELText(_structuredDocument,2357));
        assertEquals("'a' / 'b'", ELAssert.getELText(_structuredDocument,2422));
        assertEquals("'a' div 'b'", ELAssert.getELText(_structuredDocument,2464));
        assertEquals("5.5 / null", ELAssert.getELText(_structuredDocument,2505));
        assertEquals("5.5 div null", ELAssert.getELText(_structuredDocument,2545));
        assertEquals("5/0", ELAssert.getELText(_structuredDocument,2587));
        assertEquals("5 div 0", ELAssert.getELText(_structuredDocument,2620));
        assertEquals("myBean.bigIntegerProperty + true", ELAssert.getELText(_structuredDocument,2657));
        assertEquals("myBean.bigDoubleProperty / null", ELAssert.getELText(_structuredDocument,2719));
        assertEquals("myBean.bigDoubleProperty div true", ELAssert.getELText(_structuredDocument,2780));
    }

    @Override
    public void testNoErrorExprs()
    {
        assertNoError(828, Signature.SIG_DOUBLE);
        assertNoError(887, Signature.SIG_DOUBLE);
        assertNoError(948, Signature.SIG_DOUBLE);
        assertNoError(1006, Signature.SIG_DOUBLE);
        assertNoError(1066, Signature.SIG_DOUBLE);
        assertNoError(1146, Signature.SIG_DOUBLE);
        assertNoError(1228, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1290, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1354, TypeConstants.TYPE_BIG_DOUBLE);
        assertNoError(1417, Signature.SIG_DOUBLE);
    }

    @Override
    public void testWarningExprs()
    {
        List<ReportedProblem> list = assertSemanticWarning(1505, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1540, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1577, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1614, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1656, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1698, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);

        list = assertSemanticWarning(1742, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);

        list = assertSemanticWarning(1786, Signature.SIG_LONG, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_BOTH_OPERANDS_NULL_ID);

        list = assertSemanticWarning(1832, Signature.SIG_DOUBLE, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_CONSTANT_EXPRESSION_ALWAYS_EVAL_SAME_ID);
    }

    @Override
    public void testErrorExprs()
    {
        List<ReportedProblem> list = assertSemanticError(1897, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1935, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(1975, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2052, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2131, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2212, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2294, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2357, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2422, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2464, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2505, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2545, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2587, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2620, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2657, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);

        list = assertSemanticError(2719, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_POSSIBLE_DIVISION_BY_ZERO_ID);

        list = assertSemanticError(2780, null, 1);
        ELAssert.assertContainsProblem(list, DiagnosticFactory.BINARY_OP_COULD_NOT_MAKE_NUMERIC_COERCION_ID);
    }
}
