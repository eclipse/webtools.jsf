/*******************************************************************************
 * Copyright (c) 2006, 2007 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.jsp.core.pagevar.adapter;

import java.util.List;

import org.eclipse.jst.pagedesigner.jsp.core.pagevar.IVariableInfo;
import org.eclipse.wst.sse.core.internal.provisional.INodeAdapter;
import org.w3c.dom.Element;

/**
 * A IPageVariableAdapter normally will be adapted to an JSP tag.
 * 
 * @author mengbo
 * @version 1.5
 */
public interface IPageVariableAdapter extends INodeAdapter {
	/**
	 * most JSP tags will only support a single variable.
	 * @param element 
	 * 
	 * @return true if the tag supports multiple variables
	 */
	public boolean supportMultipleVariable(Element element);

	/**
	 * If only support single variable, this method will return the variable
	 * info. This method is provided for better performance.
	 * 
	 * @param element 
	 * @return null means no variable info provided.
	 */
	public IVariableInfo getVariableInfo(Element element);

	/**
	 * should return a list of IVariableInfo
	 * @param element 
	 * 
	 * @return the list of variable infos
	 */
	public List getVariableInfos(Element element);
}
