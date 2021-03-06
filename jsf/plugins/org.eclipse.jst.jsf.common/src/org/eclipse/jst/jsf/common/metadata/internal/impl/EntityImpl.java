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
 * $Id: EntityImpl.java,v 1.9 2011/03/16 21:14:13 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.jst.jsf.common.metadata.Entity;
import org.eclipse.jst.jsf.common.metadata.IncludeEntityGroup;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Model;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.query.IEntityVisitor;
import org.eclipse.jst.jsf.common.metadata.query.internal.IHierarchicalEntityVisitor;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Entity</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl#getChildEntities <em>Child Entities</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl#getTraits <em>Traits</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.EntityImpl#getIncludeGroups <em>Include Groups</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EntityImpl extends EObjectImpl implements Entity {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

	/**
	 * The cached value of the '{@link #getChildEntities() <em>Child Entities</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildEntities()
	 * @generated
	 * @ordered
	 */
	protected EList<Entity> childEntities;

	/**
	 * The cached value of the '{@link #getTraits() <em>Traits</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTraits()
	 * @generated
	 * @ordered
	 */
	protected EList<Trait> traits;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getIncludeGroups() <em>Include Groups</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludeGroups()
	 * @generated
	 * @ordered
	 */
	protected EList<IncludeEntityGroup> includeGroups;

	/**
	 * The cached value of the Model
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private Model _model;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EntityImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.ENTITY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Entity> getChildEntities() {
		if (childEntities == null) {
			childEntities = new EObjectContainmentEList<Entity>(Entity.class, this, MetadataPackage.ENTITY__CHILD_ENTITIES);
		}
		return childEntities;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Trait> getTraits() {
		if (traits == null) {
			traits = new EObjectContainmentEList<Trait>(Trait.class, this, MetadataPackage.ENTITY__TRAITS);
		}
		return traits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<IncludeEntityGroup> getIncludeGroups() {
		if (includeGroups == null) {
			includeGroups = new EObjectResolvingEList<IncludeEntityGroup>(IncludeEntityGroup.class, this, MetadataPackage.ENTITY__INCLUDE_GROUPS);
		}
		return includeGroups;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.ENTITY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.ENTITY__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private boolean accept(final IHierarchicalEntityVisitor visitor) {		
		if (visitor.visitEnter( this )) {
			acceptChildren(visitor);
		}			
		return visitor.visitLeave(this);
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void accept(final IEntityVisitor visitor) {
		if (visitor instanceof IHierarchicalEntityVisitor) {			
			accept((IHierarchicalEntityVisitor)visitor);
		}
		else {
			if (visitor.stopVisiting())
				return;
			
			visitor.visit(this);
			if (visitor.stopVisiting())
				return;
			
			acceptChildren(visitor);
			
			visitor.visitCompleted(this);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	private void acceptChildren(final IEntityVisitor visitor) {
		if (!getChildEntities().isEmpty()){
			for (final Iterator/*<Entity>*/ it = getChildEntities().iterator(); it.hasNext();){
				final Entity k = (Entity)it.next();
				k.accept(visitor);
				if (visitor.stopVisiting())
					return;
			}
		}
	}
	
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public Model getModel() {
		if (_model == null){
			EObject parent = this.eContainer();
			while (true){				
				if (parent instanceof Model){
					_model = (Model)parent;
					break;
				}
				if (parent != null)
					parent = parent.eContainer();
				else
					break;
			}
		}
		return _model;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.ENTITY__CHILD_ENTITIES:
				return ((InternalEList<?>)getChildEntities()).basicRemove(otherEnd, msgs);
			case MetadataPackage.ENTITY__TRAITS:
				return ((InternalEList<?>)getTraits()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MetadataPackage.ENTITY__CHILD_ENTITIES:
				return getChildEntities();
			case MetadataPackage.ENTITY__TRAITS:
				return getTraits();
			case MetadataPackage.ENTITY__ID:
				return getId();
			case MetadataPackage.ENTITY__TYPE:
				return getType();
			case MetadataPackage.ENTITY__INCLUDE_GROUPS:
				return getIncludeGroups();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
		@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetadataPackage.ENTITY__CHILD_ENTITIES:
				getChildEntities().clear();
				getChildEntities().addAll((Collection<? extends Entity>)newValue);
				return;
			case MetadataPackage.ENTITY__TRAITS:
				getTraits().clear();
				getTraits().addAll((Collection<? extends Trait>)newValue);
				return;
			case MetadataPackage.ENTITY__ID:
				setId((String)newValue);
				return;
			case MetadataPackage.ENTITY__TYPE:
				setType((String)newValue);
				return;
			case MetadataPackage.ENTITY__INCLUDE_GROUPS:
				getIncludeGroups().clear();
				getIncludeGroups().addAll((Collection<? extends IncludeEntityGroup>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case MetadataPackage.ENTITY__CHILD_ENTITIES:
				getChildEntities().clear();
				return;
			case MetadataPackage.ENTITY__TRAITS:
				getTraits().clear();
				return;
			case MetadataPackage.ENTITY__ID:
				setId(ID_EDEFAULT);
				return;
			case MetadataPackage.ENTITY__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case MetadataPackage.ENTITY__INCLUDE_GROUPS:
				getIncludeGroups().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case MetadataPackage.ENTITY__CHILD_ENTITIES:
				return childEntities != null && !childEntities.isEmpty();
			case MetadataPackage.ENTITY__TRAITS:
				return traits != null && !traits.isEmpty();
			case MetadataPackage.ENTITY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MetadataPackage.ENTITY__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case MetadataPackage.ENTITY__INCLUDE_GROUPS:
				return includeGroups != null && !includeGroups.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", type: "); //$NON-NLS-1$
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //EntityImpl
