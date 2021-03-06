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
 * $Id: TraitImpl.java,v 1.10 2011/03/16 21:14:13 gkessler Exp $
 */
package org.eclipse.jst.jsf.common.metadata.internal.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.jst.jsf.common.metadata.MetadataPackage;
import org.eclipse.jst.jsf.common.metadata.Trait;
import org.eclipse.jst.jsf.common.metadata.internal.IMetaDataSourceModelProvider;
import org.eclipse.jst.jsf.common.metadata.query.ITraitVisitor;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Trait</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.jst.jsf.common.metadata.internal.impl.TraitImpl#getSourceModelProvider <em>Source Model Provider</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TraitImpl extends EObjectImpl implements Trait {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "Copyright (c) 2007 Oracle Corporation"; //$NON-NLS-1$

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
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected EObject value;

	/**
	 * The default value of the '{@link #getSourceModelProvider() <em>Source Model Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelProvider()
	 * @generated
	 * @ordered
	 */
	protected static final IMetaDataSourceModelProvider SOURCE_MODEL_PROVIDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSourceModelProvider() <em>Source Model Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSourceModelProvider()
	 * @generated
	 * @ordered
	 */
	protected IMetaDataSourceModelProvider sourceModelProvider = SOURCE_MODEL_PROVIDER_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TraitImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MetadataPackage.Literals.TRAIT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param newValue 
	 * @param msgs 
	 * @return NotificationChain
	 * @generated
	 */
	public NotificationChain basicSetValue(EObject newValue, NotificationChain msgs) {
		EObject oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MetadataPackage.TRAIT__VALUE, oldValue, newValue);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(EObject newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject)value).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MetadataPackage.TRAIT__VALUE, null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject)newValue).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MetadataPackage.TRAIT__VALUE, null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.TRAIT__VALUE, newValue, newValue));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IMetaDataSourceModelProvider getSourceModelProvider() {
		return sourceModelProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceModelProvider(IMetaDataSourceModelProvider newSourceModelProvider) {
		IMetaDataSourceModelProvider oldSourceModelProvider = sourceModelProvider;
		sourceModelProvider = newSourceModelProvider;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.TRAIT__SOURCE_MODEL_PROVIDER, oldSourceModelProvider, sourceModelProvider));
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
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.TRAIT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void accept(ITraitVisitor visitor) {
		visitor.visit(this);
		visitor.visitCompleted(this);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case MetadataPackage.TRAIT__VALUE:
				return basicSetValue(null, msgs);
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
			case MetadataPackage.TRAIT__ID:
				return getId();
			case MetadataPackage.TRAIT__VALUE:
				return getValue();
			case MetadataPackage.TRAIT__SOURCE_MODEL_PROVIDER:
				return getSourceModelProvider();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MetadataPackage.TRAIT__ID:
				setId((String)newValue);
				return;
			case MetadataPackage.TRAIT__VALUE:
				setValue((EObject)newValue);
				return;
			case MetadataPackage.TRAIT__SOURCE_MODEL_PROVIDER:
				setSourceModelProvider((IMetaDataSourceModelProvider)newValue);
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
			case MetadataPackage.TRAIT__ID:
				setId(ID_EDEFAULT);
				return;
			case MetadataPackage.TRAIT__VALUE:
				setValue((EObject)null);
				return;
			case MetadataPackage.TRAIT__SOURCE_MODEL_PROVIDER:
				setSourceModelProvider(SOURCE_MODEL_PROVIDER_EDEFAULT);
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
			case MetadataPackage.TRAIT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case MetadataPackage.TRAIT__VALUE:
				return value != null;
			case MetadataPackage.TRAIT__SOURCE_MODEL_PROVIDER:
				return SOURCE_MODEL_PROVIDER_EDEFAULT == null ? sourceModelProvider != null : !SOURCE_MODEL_PROVIDER_EDEFAULT.equals(sourceModelProvider);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: "); //$NON-NLS-1$
		result.append(id);
		result.append(", sourceModelProvider: "); //$NON-NLS-1$
		result.append(sourceModelProvider);
		result.append(')');
		return result.toString();
	}

	public boolean equals(Object value_){
		if (!(value_ instanceof String))
			return super.equals(value_);
		
		String key = (String)value_;
		if (key.equals(getId()))
			return true;
		
		return false;
	}

    @Override
    public int hashCode() 
    {
        // try to match convention x.equals(y) => x.hashCode() == y.hashCode()
        return getId() != null ? getId().hashCode() : 0xDEADBEEF;
    }
} //TraitImpl