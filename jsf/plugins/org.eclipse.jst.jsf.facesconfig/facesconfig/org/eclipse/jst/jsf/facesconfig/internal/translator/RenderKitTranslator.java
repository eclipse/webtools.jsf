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
 *   Oracle Corporation - fixed getChildren()
 **************************************************************************************************/
package org.eclipse.jst.jsf.facesconfig.internal.translator;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigPackage;
import org.eclipse.wst.common.internal.emf.resource.Translator;

/**
 * @author xjiang, itrimble
 *
 */
public class RenderKitTranslator extends Translator {

	/**
	 * @param domNameAndPath
	 * @param aFeature
	 */
	public RenderKitTranslator(String domNameAndPath, EStructuralFeature aFeature) {
		super(domNameAndPath, aFeature);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.wst.common.internal.emf.resource.Translator#getChildren()
	 */
	public Translator[] getChildren() {
		
		FacesConfigPackage facesPackage = FacesConfigPackage.eINSTANCE;
		return new Translator[] {
				new DescriptionTranslator("description", facesPackage.getRenderKitType_Description()), //$NON-NLS-1$
				new DisplayNameTranslator("display-name", facesPackage.getRenderKitType_DisplayName()), //$NON-NLS-1$
				new IconTranslator("icon", facesPackage.getRenderKitType_Icon()), //$NON-NLS-1$
				new RenderKitIdTranslator("render-kit-id", facesPackage.getRenderKitType_RenderKitId()), //$NON-NLS-1$
				new RenderKitClassTranslator("render-kit-class", facesPackage.getRenderKitType_RenderKitClass()), //$NON-NLS-1$
				new RendererTranslator("renderer", facesPackage.getRenderKitType_Renderer()), //$NON-NLS-1$
				new ClientBehaviorRendererTranslator("client-behavior-renderer", facesPackage.getRenderKitType_ClientBehaviorRenderer()), //$NON-NLS-1$
                new RenderKitExtensionTranslator("render-kit-extension", facesPackage.getRenderKitType_RenderKitExtension()), //$NON-NLS-1$
				new Translator("id", facesPackage.getRenderKitType_Id(), DOM_ATTRIBUTE) //$NON-NLS-1$
		};
	}
}
