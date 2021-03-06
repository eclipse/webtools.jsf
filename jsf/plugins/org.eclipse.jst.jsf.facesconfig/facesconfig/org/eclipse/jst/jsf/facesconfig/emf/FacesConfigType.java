/***************************************************************************************************
 * Copyright (c) 2005, 2010 IBM Corporation and others. 
 * All rights reserved. This program and the accompanying materials 
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors: 
 *   IBM Corporation - initial API and implementation
 *   Oracle Corporation - revision
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.emf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getApplication <em>Application</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getOrdering <em>Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getAbsoluteOrdering <em>Absolute Ordering</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getFactory <em>Factory</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getComponent <em>Component</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getConverter <em>Converter</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getManagedBean <em>Managed Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getNavigationRule <em>Navigation Rule</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getReferencedBean <em>Referenced Bean</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getRenderKit <em>Render Kit</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getLifecycle <em>Lifecycle</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getValidator <em>Validator</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getBehavior <em>Behavior</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getFacesConfigExtension <em>Faces Config Extension</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getXmlns <em>Xmlns</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#isMetadataComplete <em>Metadata Complete</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType()
 * @model extendedMetaData="name='faces-config_._type' kind='elementOnly'"
 * @generated
 */
public interface FacesConfigType extends EObject {
    /**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2005, 2006 IBM Corporation and others"; //$NON-NLS-1$

    /**
	 * Returns the value of the '<em><b>Application</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ApplicationType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Application</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Application</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Application()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ApplicationType" containment="true"
	 *        extendedMetaData="kind='element' name='application' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getApplication();

    /**
	 * Returns the value of the '<em><b>Ordering</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.OrderingType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordering</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordering</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Ordering()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.OrderingType" containment="true"
	 *        extendedMetaData="kind='element' name='ordering' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getOrdering();

				/**
	 * Returns the value of the '<em><b>Absolute Ordering</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Absolute Ordering</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Absolute Ordering</em>' containment reference.
	 * @see #setAbsoluteOrdering(AbsoluteOrderingType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_AbsoluteOrdering()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='absolute-ordering' namespace='##targetNamespace'"
	 * @generated
	 */
	AbsoluteOrderingType getAbsoluteOrdering();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getAbsoluteOrdering <em>Absolute Ordering</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Absolute Ordering</em>' containment reference.
	 * @see #getAbsoluteOrdering()
	 * @generated
	 */
	void setAbsoluteOrdering(AbsoluteOrderingType value);

				/**
	 * Returns the value of the '<em><b>Factory</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.FactoryType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Factory</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Factory</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Factory()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.FactoryType" containment="true"
	 *        extendedMetaData="kind='element' name='factory' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getFactory();

    /**
	 * Returns the value of the '<em><b>Component</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ComponentType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Component</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Component</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Component()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ComponentType" containment="true"
	 *        extendedMetaData="kind='element' name='component' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getComponent();

    /**
	 * Returns the value of the '<em><b>Converter</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ConverterType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Converter</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Converter</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Converter()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ConverterType" containment="true"
	 *        extendedMetaData="kind='element' name='converter' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getConverter();

    /**
	 * Returns the value of the '<em><b>Managed Bean</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Managed Bean</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Managed Bean</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_ManagedBean()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ManagedBeanType" containment="true"
	 *        extendedMetaData="kind='element' name='managed-bean' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getManagedBean();

    /**
	 * Returns the value of the '<em><b>Name</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' containment reference.
	 * @see #setName(NameType)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Name()
	 * @model containment="true"
	 *        extendedMetaData="kind='element' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	NameType getName();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getName <em>Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' containment reference.
	 * @see #getName()
	 * @generated
	 */
	void setName(NameType value);

				/**
	 * Returns the value of the '<em><b>Navigation Rule</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Navigation Rule</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Navigation Rule</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_NavigationRule()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType" containment="true"
	 *        extendedMetaData="kind='element' name='navigation-rule' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getNavigationRule();

    /**
	 * Returns the value of the '<em><b>Referenced Bean</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Referenced Bean</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Referenced Bean</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_ReferencedBean()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ReferencedBeanType" containment="true"
	 *        extendedMetaData="kind='element' name='referenced-bean' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getReferencedBean();

    /**
	 * Returns the value of the '<em><b>Render Kit</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.RenderKitType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Render Kit</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Render Kit</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_RenderKit()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.RenderKitType" containment="true"
	 *        extendedMetaData="kind='element' name='render-kit' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getRenderKit();

    /**
	 * Returns the value of the '<em><b>Lifecycle</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.LifecycleType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Lifecycle</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Lifecycle</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Lifecycle()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.LifecycleType" containment="true"
	 *        extendedMetaData="kind='element' name='lifecycle' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getLifecycle();

    /**
	 * Returns the value of the '<em><b>Validator</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.ValidatorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Validator</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Validator</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Validator()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.ValidatorType" containment="true"
	 *        extendedMetaData="kind='element' name='validator' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getValidator();

    /**
	 * Returns the value of the '<em><b>Behavior</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.BehaviorType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Behavior</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Behavior</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Behavior()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.BehaviorType" containment="true"
	 *        extendedMetaData="kind='element' name='behavior' namespace='##targetNamespace'"
	 * @generated
	 */
	EList getBehavior();

				/**
	 * Returns the value of the '<em><b>Faces Config Extension</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType}.
	 * <!-- begin-user-doc -->
     * <p>
     * If the meaning of the '<em>Faces Config Extension</em>' reference list isn't clear,
     * there really should be more of a description here...
     * </p>
     * <!-- end-user-doc -->
	 * @return the value of the '<em>Faces Config Extension</em>' reference list.
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_FacesConfigExtension()
	 * @model type="org.eclipse.jst.jsf.facesconfig.emf.FacesConfigExtensionType"
	 *        extendedMetaData="kind='element' name='faces-config-extension' namespace='##targetNamespace'"
	 * @generated
	 */
    EList getFacesConfigExtension();

    /**
	 * Returns the value of the '<em><b>Xmlns</b></em>' attribute.
	 * The default value is <code>"http://java.sun.com/JSF/Configuration"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xmlns</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xmlns</em>' attribute.
	 * @see #isSetXmlns()
	 * @see #unsetXmlns()
	 * @see #setXmlns(String)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Xmlns()
	 * @model default="http://java.sun.com/JSF/Configuration" unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.String"
	 *        extendedMetaData="kind='attribute' name='xmlns' namespace='##targetNamespace'"
	 * @generated
	 */
	String getXmlns();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getXmlns <em>Xmlns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xmlns</em>' attribute.
	 * @see #isSetXmlns()
	 * @see #unsetXmlns()
	 * @see #getXmlns()
	 * @generated
	 */
	void setXmlns(String value);

    /**
	 * Unsets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getXmlns <em>Xmlns</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSetXmlns()
	 * @see #getXmlns()
	 * @see #setXmlns(String)
	 * @generated
	 */
	void unsetXmlns();

    /**
	 * Returns whether the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getXmlns <em>Xmlns</em>}' attribute is set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return whether the value of the '<em>Xmlns</em>' attribute is set.
	 * @see #unsetXmlns()
	 * @see #getXmlns()
	 * @see #setXmlns(String)
	 * @generated
	 */
	boolean isSetXmlns();

    /**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_Id()
	 * @model unique="false" id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

    /**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

				/**
	 * Returns the value of the '<em><b>Metadata Complete</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Metadata Complete</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Metadata Complete</em>' attribute.
	 * @see #setMetadataComplete(boolean)
	 * @see org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage#getFacesConfigType_MetadataComplete()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Boolean"
	 *        extendedMetaData="kind='attribute' name='metadata-complete' namespace='##targetNamespace'"
	 * @generated
	 */
	boolean isMetadataComplete();

				/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType#isMetadataComplete <em>Metadata Complete</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Metadata Complete</em>' attribute.
	 * @see #isMetadataComplete()
	 * @generated
	 */
	void setMetadataComplete(boolean value);

} // FacesConfigType
