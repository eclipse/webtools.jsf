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
package org.eclipse.jst.pagedesigner.figurehandler;

import org.eclipse.jst.pagedesigner.css2.layout.CSSBrFlowLayout;
import org.eclipse.jst.pagedesigner.css2.layout.CSSFigure;
import org.eclipse.jst.pagedesigner.css2.layout.CSSLayout;
import org.w3c.dom.Element;

/**
 * @author mengbo
 */
public class BRFigureHandler extends AbstractFigureHandler {
	/**
	 * @param figure
	 * @return the CSS layout for fixed layout
	 */
	protected CSSLayout getFixedCSSLayout(CSSFigure figure) {
		return new CSSBrFlowLayout(figure);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jst.pagedesigner.figurehandler.IFigureHandler#isWidget()
	 */
	public boolean isWidget() {
		return false;
	}

	public void updateFigure(Element node, CSSFigure oldFigure) {
		oldFigure.setCSSStyle(getCSSStyle(node));
		oldFigure.setFixedLayoutManager(getFixedCSSLayout(oldFigure));
	}
}
