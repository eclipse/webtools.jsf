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
/**
 * <copyright>
 * </copyright>
 *
 * $Id: FaceletTaglibFactoryImpl.java,v 1.1 2010/03/18 06:24:28 cbateman Exp $
 */
package org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ComponentTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ConverterTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletLibraryClassTagLib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibFactory;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletTaglibPackage;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FaceletXMLDefnTaglib;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.FunctionDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.HandlerTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.SourceTagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.TagDefn;
import org.eclipse.jst.jsf.facelet.core.internal.registry.taglib.faceletTaglib_1_0.ValidatorTagDefn;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class FaceletTaglibFactoryImpl extends EFactoryImpl implements FaceletTaglibFactory
{
    /**
     * Creates the default factory implementation.
     * <!-- begin-user-doc -->
     * @return the factory
     * <!-- end-user-doc -->
     * @generated
     */
    public static FaceletTaglibFactory init()
    {
        try
        {
            FaceletTaglibFactory theFaceletTaglibFactory = (FaceletTaglibFactory)EPackage.Registry.INSTANCE.getEFactory("http://org.eclipse.jst.jsf.facelet.core/faceletTaglib.ecore");  //$NON-NLS-1$
            if (theFaceletTaglibFactory != null)
            {
                return theFaceletTaglibFactory;
            }
        }
        catch (Exception exception)
        {
            EcorePlugin.INSTANCE.log(exception);
        }
        return new FaceletTaglibFactoryImpl();
    }

    /**
     * Creates an instance of the factory.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibFactoryImpl()
    {
        super();
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    @Override
    public EObject create(EClass eClass)
    {
        switch (eClass.getClassifierID())
        {
            case FaceletTaglibPackage.FACELET_LIBRARY_CLASS_TAG_LIB: return createFaceletLibraryClassTagLib();
            case FaceletTaglibPackage.FACELET_XML_DEFN_TAGLIB: return createFaceletXMLDefnTaglib();
            case FaceletTaglibPackage.FACELET_TAGLIB_DEFN: return createFaceletTaglibDefn();
            case FaceletTaglibPackage.COMPONENT_TAG_DEFN: return createComponentTagDefn();
            case FaceletTaglibPackage.VALIDATOR_TAG_DEFN: return createValidatorTagDefn();
            case FaceletTaglibPackage.CONVERTER_TAG_DEFN: return createConverterTagDefn();
            case FaceletTaglibPackage.HANDLER_TAG_DEFN: return createHandlerTagDefn();
            case FaceletTaglibPackage.SOURCE_TAG_DEFN: return createSourceTagDefn();
            case FaceletTaglibPackage.TAG_DEFN: return createTagDefn();
            case FaceletTaglibPackage.FUNCTION_DEFN: return createFunctionDefn();
            default:
                throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletLibraryClassTagLib createFaceletLibraryClassTagLib()
    {
        FaceletLibraryClassTagLibImpl faceletLibraryClassTagLib = new FaceletLibraryClassTagLibImpl();
        return faceletLibraryClassTagLib;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletXMLDefnTaglib createFaceletXMLDefnTaglib()
    {
        FaceletXMLDefnTaglibImpl faceletXMLDefnTaglib = new FaceletXMLDefnTaglibImpl();
        return faceletXMLDefnTaglib;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibDefn createFaceletTaglibDefn()
    {
        FaceletTaglibDefnImpl faceletTaglibDefn = new FaceletTaglibDefnImpl();
        return faceletTaglibDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ComponentTagDefn createComponentTagDefn()
    {
        ComponentTagDefnImpl componentTagDefn = new ComponentTagDefnImpl();
        return componentTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ValidatorTagDefn createValidatorTagDefn()
    {
        ValidatorTagDefnImpl validatorTagDefn = new ValidatorTagDefnImpl();
        return validatorTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public ConverterTagDefn createConverterTagDefn()
    {
        ConverterTagDefnImpl converterTagDefn = new ConverterTagDefnImpl();
        return converterTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public HandlerTagDefn createHandlerTagDefn()
    {
        HandlerTagDefnImpl handlerTagDefn = new HandlerTagDefnImpl();
        return handlerTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public SourceTagDefn createSourceTagDefn()
    {
        SourceTagDefnImpl sourceTagDefn = new SourceTagDefnImpl();
        return sourceTagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public TagDefn createTagDefn()
    {
        TagDefnImpl tagDefn = new TagDefnImpl();
        return tagDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FunctionDefn createFunctionDefn()
    {
        FunctionDefnImpl functionDefn = new FunctionDefnImpl();
        return functionDefn;
    }

    /**
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public FaceletTaglibPackage getFaceletTaglibPackage()
    {
        return (FaceletTaglibPackage)getEPackage();
    }

    /**
     * <!-- begin-user-doc -->
     * @return the package
     * <!-- end-user-doc -->
     * @deprecated
     * @generated
     */
    @Deprecated
    public static FaceletTaglibPackage getPackage()
    {
        return FaceletTaglibPackage.eINSTANCE;
    }

} //FaceletTaglibFactoryImpl
