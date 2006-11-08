/*******************************************************************************
 * Copyright (c) 2004, 2006 Sybase, Inc. and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Sybase, Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.facesconfig.ui.test;

import org.eclipse.jface.util.Assert;
import org.eclipse.jst.jsf.facesconfig.emf.FacesConfigType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationCaseType;
import org.eclipse.jst.jsf.facesconfig.emf.NavigationRuleType;
import org.eclipse.jst.jsf.facesconfig.emf.ToViewIdType;
import org.eclipse.jst.jsf.facesconfig.ui.FacesConfigEditor;
import org.eclipse.jst.jsf.facesconfig.ui.page.IntroductionPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.PageflowEditor;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.AddConnectionCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.AddNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DeleteConnectionCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.DeleteNodeCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.ReconnectConnectionCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.Pageflow;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowPage;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.synchronization.TransformUtil;
import org.eclipse.ui.actions.ActionFactory;

/**
 * @author hmeng
 */

public class PageflowEditorTest extends FacesConfigEditorTest {
	private static final String LIST_JSP = "/list.jsp";

	private static final String INDEX_JSP = "/index.jsp";

	private static final String INDEX1_JSP = "/index1.jsp";

	public PageflowEditorTest() {
		// TODO Auto-generated constructor stub
	}

	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
	}

	public void testAddElements() {
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
		Pageflow pageflow = getPageflow();
		PageflowPage source = createPage(INDEX_JSP);
		Assert.isTrue(pageflow.getNodes().contains(source));

		PageflowPage target = createPage(LIST_JSP);
		Assert.isTrue(pageflow.getNodes().contains(target));

		PageflowLink link = createLink(source, target);

		Assert.isTrue(pageflow.getLinks().contains(link));
		FacesConfigType facesConfig = getFacesConfig();
		NavigationRuleType rule = (NavigationRuleType) facesConfig
				.getNavigationRule().get(0);
		NavigationCaseType caseType = (NavigationCaseType) rule
				.getNavigationCase().get(0);
		Assert.isTrue(rule.getFromViewId().getTextContent().equals(INDEX_JSP));
		Assert.isTrue(caseType.getToViewId().getTextContent().equals(LIST_JSP));
	}

	private PageflowLink createLink(PageflowPage source, PageflowPage target) {
		AddConnectionCommand connectionCommand = new AddConnectionCommand();
		connectionCommand.setSource(source);
		connectionCommand.setTarget(target);
		PageflowLink link = PageflowFactory.eINSTANCE.createPFLink();
		connectionCommand.setPFLink(link);
		editor.getDelegatingCommandStack().execute(
				connectionCommand);
		return link;
	}

	private PageflowPage createPage(String sourcePath) {
		PageflowPage source = PageflowFactory.eINSTANCE.createPFPage();
		source.setPath(sourcePath);
		AddNodeCommand command = new AddNodeCommand();
		command.setParent(getPageflow());
		command.setChild(source);
		editor.getDelegatingCommandStack().execute(
				command);
		return source;
	}

	private FacesConfigType getFacesConfig() {
		return editor.getFacesConfig();
	}

	public void testDeleteNode() {
		testAddElements();
		Pageflow pageflow = getPageflow();
		DeleteNodeCommand command = new DeleteNodeCommand(pageflow);
		command.setParent(pageflow);
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, pageflow);
		command.setChild(page);
		command.execute();
		Assert.isTrue(!pageflow.getNodes().contains(page));
		Assert.isTrue(pageflow.getLinks().size() == 0);
		Assert.isTrue(getFacesConfig().getNavigationRule().size() == 0);
	}

	public void testDeleteLink() {
		testAddElements();
		Pageflow pageflow = getPageflow();
		DeleteConnectionCommand command = new DeleteConnectionCommand();
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, pageflow);
		PageflowLink link = (PageflowLink) page.getOutlinks().get(0);
		command.setSource(link.getSource());
		command.setTarget(link.getTarget());
		command.setPFLink(link);
		command.execute();
		Assert.isTrue(link.getFCElements().isEmpty());
		Assert.isTrue(link.eAdapters().size() == 0);
		Assert.isTrue(!pageflow.getLinks().contains(link));
		Assert.isTrue(pageflow.getLinks().size() == 0);
		Assert.isTrue(getFacesConfig().getNavigationRule().size() == 0);
	}

	public void testAddNavigationCase() {
		testAddElements();
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		rule.getNavigationCase().remove(0);
		Assert.isTrue(getPageflow().getLinks().size() == 0);
	}

	public void testSetPFProperty() {
		testAddElements();
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, getPageflow());
		page.setPath(INDEX1_JSP);
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		Assert.isTrue(getFacesConfig().getNavigationRule().size() == 1);
		Assert.isTrue(rule.getFromViewId().getTextContent().equals(INDEX1_JSP));
	}

	public void testSetFCProperty() {
		testAddElements();
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		rule.getFromViewId().setTextContent(INDEX1_JSP);
		Assert.isTrue(getPageflow().getLinks().size() == 1);
		Assert.isTrue(((PageflowPage) ((PageflowLink) getPageflow().getLinks()
				.get(0)).getSource()).getPath().equals(INDEX1_JSP));
	}

	private Pageflow getPageflow() {
		return editor.getPageflowPage().getPageflow();
	}

	public void testChangeLinkTarget() {
		testAddElements();
		ReconnectConnectionCommand command = new ReconnectConnectionCommand();
		PageflowPage page = createPage(INDEX1_JSP);
		command.setSource(page);
		PageflowLink link = (PageflowLink) getPageflow().getLinks().get(0);
		command.setPFLink(link);
		command.execute();
		NavigationRuleType rule = (NavigationRuleType) getFacesConfig()
				.getNavigationRule().get(0);
		Object element1 = link.getSource().getFCElements().getData().get(0);
		Object element2 = link.getTarget().getFCElements().getData().get(0);
		Assert.isTrue(link.getSource().getFCElements().getData().size() == 1);
		Assert.isTrue(link.getTarget().getFCElements().getData().size() == 1);
		Assert.isTrue(element1 == rule.getFromViewId());

		Assert
				.isTrue(((ToViewIdType) element2).eContainer().eContainer() == rule);
		Assert.isTrue(getFacesConfig().getNavigationRule().size() == 1);
		Assert.isTrue(rule.getFromViewId().getTextContent().equals(INDEX1_JSP));
	}

	public void testUndo() {
		testAddElements();
		Pageflow pageflow = getPageflow();
		DeleteConnectionCommand command = new DeleteConnectionCommand();
		PageflowPage page = TransformUtil.findPage(INDEX_JSP, pageflow);
		PageflowLink link = (PageflowLink) page.getOutlinks().get(0);
		command.setPFLink(link);
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
		editor.getDelegatingCommandStack().execute(command);
		Assert.isTrue(getFacesConfig().getNavigationRule().size() == 0);
		Assert.isTrue(getPageflow().getLinks().size() == 0);
		editor.getDelegatingCommandStack().undo();
		Assert.isTrue(getFacesConfig().getNavigationRule().size() == 1);
		Assert.isTrue(getPageflow().getLinks().size() == 1);
	}

	public void testRedo() {
		testUndo();
		editor.getDelegatingCommandStack().redo();
		Assert.isTrue(getFacesConfig().getNavigationRule().size() == 0);
		Assert.isTrue(getPageflow().getLinks().size() == 0);
	}

	public void testEditorSwitch() throws Exception {
		editor.setFocus();
		editor.setActivePage(IntroductionPage.class.getName());
		Assert.isTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.UNDO.getId()) == null);
		Assert.isTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.REDO.getId()) == null);
		testAddElements();
		editor.setActivePage(PageflowEditor.PAGE_ID);
		Assert
				.isTrue(editor.getActionBarContributor().getActionBars()
						.getGlobalActionHandler(ActionFactory.UNDO.getId())
						.isEnabled());

		editor.setActivePage(IntroductionPage.class.getName());
		Assert.isTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.UNDO.getId()) == null);
		Assert.isTrue(editor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.REDO.getId()) == null);
		FacesConfigEditor anotherEditor = (FacesConfigEditor) openWithEditor("WebContent/WEB-INF/faces-config1.xml");
		anotherEditor.setFocus();
		Assert.isTrue(anotherEditor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.UNDO.getId()) == null);
		Assert.isTrue(anotherEditor.getActionBarContributor().getActionBars()
				.getGlobalActionHandler(ActionFactory.REDO.getId()) == null);
		editor.setFocus();
		editor.setActiveEditorPage(PageflowEditor.PAGE_ID);
		Assert
				.isTrue(editor.getActionBarContributor().getActionBars()
						.getGlobalActionHandler(ActionFactory.UNDO.getId())
						.isEnabled());
	}
}
