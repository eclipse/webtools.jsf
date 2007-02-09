/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.pagedesigner.css2.property;

import org.eclipse.jst.pagedesigner.css2.ICSSStyle;
import org.w3c.dom.Element;
import org.w3c.dom.css.CSSValue;

/**
 * @author mengbo
 */
public interface ICSSPropertyMeta {
	public static final int PERCENTAGE_NONE = 0;

	public static final int PERCENTAGE_BOXSIZE = 1;

	public static final int PERCENTAGE_HEIGHT_CONTAININGBLOCK = 2;

	public static final int PERCENTAGE_FONT = 3;

	public static final int PERCENTAGE_WIDTH_CONTAININGBLOCK = 4;

	public static final Object NOT_SPECIFIED = "NOT_SPECIFIED";

	/**
	 * whether default inherit.
	 * 
	 * @return
	 */
	public boolean isInherited();

	/**
	 * initial value
	 * 
	 * @return
	 */
	public Object getInitialValue(String propertyName, ICSSStyle style);

	public Object getHTMLElementInitialValue(Element element, String htmltag,
			String propertyName);

	/**
	 * what's percentage value based on.
	 * 
	 * @return
	 */
	public int getPercentageType();

	/**
	 * for many CSS property, there is corresponding HTML attribute can also
	 * specify value for them. This method should check whether the element has
	 * corresponding HTML attribute provide value.
	 * 
	 * @param element
	 *            the element
	 * @param htmltag
	 *            the html tag name. This is for in case element is jsp/jsf
	 *            element, and have non html tag name.
	 * @param propertyName
	 *            the property name.
	 * @param style
	 *            the style
	 * @return null if no attribute override.
	 */
	public Object calculateHTMLAttributeOverride(Element element,
			String htmltag, String propertyName, ICSSStyle style);

	/**
	 * @param value
	 * @param propertyName
	 * @param style
	 * @return
	 */
	public Object calculateCSSValueResult(CSSValue value, String propertyName,
			ICSSStyle style);

}