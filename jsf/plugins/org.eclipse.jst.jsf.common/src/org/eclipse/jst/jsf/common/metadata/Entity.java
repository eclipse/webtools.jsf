/*******************************************************************************
 * Copyright (c) 2007, 2019 IBM Corporation and others.
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
 * $Id$
 */
package org.eclipse.jst.jsf.common.metadata;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Entity</b></em>'.
 * <p><b>Provisional API - subject to change</b></p>
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Entity#getChildEntities <em>Child Entities</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Entity#getTraits <em>Traits</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Entity#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Entity#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.Entity#getIncludeGroups <em>Include Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getEntity()
 * @model extendedMetaData="kind='element' name='entity'"
 * @generated
 */
public interface Entity extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * Returns the value of the '<em><b>Child Entities</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.Entity}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child Entities</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child Entities</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getEntity_ChildEntities()
	 * @model containment="true" keys="id type"
	 *        extendedMetaData="kind='element' name='entity'"
	 * @generated
	 */
	EList<Entity> getChildEntities();

	/**
	 * Returns the value of the '<em><b>Traits</b></em>' containment reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.Trait}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Traits</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Traits</em>' containment reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getEntity_Traits()
	 * @model containment="true" keys="id"
	 *        extendedMetaData="kind='element' name='trait'"
	 * @generated
	 */
	EList<Trait> getTraits();

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
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getEntity_Id()
	 * @model id="true" required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.Entity#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getEntity_Type()
	 * @model
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link org.eclipse.jst.jsf.common.metadata.Entity#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Include Groups</b></em>' reference list.
	 * The list contents are of type {@link org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Include Groups</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Include Groups</em>' reference list.
	 * @see org.eclipse.jst.jsf.common.metadata.MetadataPackage#getEntity_IncludeGroups()
	 * @model extendedMetaData="kind='element' name='include-entity-group'"
	 * @generated
	 */
	EList<IncludeEntityGroup> getIncludeGroups();

	/**
	 * <!-- begin-user-doc -->
	 * @param visitor 	 
	 * <!-- end-user-doc -->
	 * @model visitorDataType="org.eclipse.jst.jsf.common.metadata.IEntityVisitor"
	 * @generated
	 */
	void accept(IEntityVisitor visitor);

	/**
	 * <!-- begin-user-doc -->
	 * @return Model 
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	Model getModel();

} // Entity
