/*******************************************************************************
 * Copyright (c) 2008, 2019 IBM Corporation and others.
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
package org.eclipse.jst.pagedesigner.itemcreation.customizer;

import java.util.HashMap;
import java.util.Map;

/**
 * Class containing all the data that pertains to the customization
 * of a tag's attributes. 
 * 
 * @author prusev
 * @author Debajit Adhikary
 *
 */
public class AttributeData {

	private Map<String, String> attrs = new HashMap<String, String>();

	
	/**
	 * Returns a map of the attributes (Name-Value pairs)
	 * 
	 * @return Map of attribute names and values
	 * 
	 */
	public Map<String, String> getAttributes()
	{
		return attrs;
	}

	
	/**
	 * Sets the attribute data to the map of attribute names and values
	 * provided.
	 * 
	 * @param attribs
	 *            Map of attribute names and values
	 * 
	 */
	public void setAttributes(Map<String, String> attribs)
	{
	    attrs.clear();
	    attrs.putAll(attribs);
	}

	
	/**
	 * Adds an attribute.
	 * 
	 * @param attr
	 *            Attribute name
	 * @param userVal
	 *            Attribute value
	 * 
	 */
	public void addAttribute(String attr, String userVal)
	{
		attrs.put(attr, userVal);
	}

	/**
	 * Returns the value for a given attribute name.
	 * 
	 * @param attr
	 *            Attribute name whose value is to be found
	 * @return Attribute value for the given attribute name
	 * 
	 */
	public String getValForAttrib(String attr)
	{
		return attrs.get(attr);
	}
}
