/*******************************************************************************
 * Copyright (c) 2007 Oracle Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Oracle - initial API and implementation
 *    
 ********************************************************************************/
package org.eclipse.jst.jsf.common.metadata.internal;

import java.util.Iterator;

import org.eclipse.jst.jsf.common.metadata.internal.provisional.Entity;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Model;
import org.eclipse.jst.jsf.common.metadata.internal.provisional.Trait;


public class StandardAnnotationFilesTranslator implements IMetaDataTranslator {

	public void translate(IMetaDataModelMergeAssistant assistant) {//TODO: throw proper errors
		//null translate - sourceModel object are already Entities and traits
		//traverse the tree and add to model
		
		//temp - throw proper errors 
		//assert assistant.getSourceModel() instanceof ModelKeyDescriptor;
		
		MetaDataModel mm = assistant.getMergedModel();
		if (mm.getRoot() == null)
			mm.setRoot((Model)assistant.getSourceModel());
		
		else {
			//for each entity and trait call "add".   assistant will handle merge.
			Model mk = (Model)assistant.getSourceModel();
			traverseAndAdd(assistant, mk);
		}			
	}
	
	protected void traverseAndAdd(IMetaDataModelMergeAssistant assistant, final Entity entity){
		assistant.addEntity(entity);
		
		for (final Iterator/*<Trait>*/ it=entity.getTraits().iterator();it.hasNext();){
			Trait trait = (Trait)it.next();
			assistant.addTrait(entity, trait);
		}
		
		for (final Iterator/*<EntityKey>*/ it=entity.getChildEntities().iterator();it.hasNext();){
			Entity e = (Entity)it.next();
			traverseAndAdd(assistant, e);
		}
	}
}
