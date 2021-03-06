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
package org.eclipse.jst.jsf.common.internal.strategy;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.QualifiedName;


/**
 * Copy of {@link TestableProjectFactoryStrategy} except that this can accept IResources
 * 
 * An abstract class that, when given a project resource and a project session key, 
 * will provide the instance of OUTPUT to use, or,
 * no result will be returned if the resource is not a project.
 * <p>
 * Users need only set the project session property with the key and OUTPUT instance
 * @param <OUTPUT>
 */
public abstract class TestableResourceFactoryStrategy<OUTPUT> implements ISimpleStrategy<IResource, OUTPUT> {
	private QualifiedName _key;

	/**
	 * @param testableFactorySessionKey - project property session key for property value holding testable instance 
	 */
	public TestableResourceFactoryStrategy(final QualifiedName testableFactorySessionKey) {
		_key = testableFactorySessionKey;
	}
	
	public OUTPUT perform(final IResource resource) throws Exception {
		if (_key != null && resource != null) {
			if (resource instanceof IProject) {
				final Object factory = ((IProject)resource).getSessionProperties().get(_key);
				if (factory != null)
					return (OUTPUT)factory;
			} 
		}			
		return getNoResult();
	}

	public OUTPUT getNoResult() {
		return null;
	}
		
}
