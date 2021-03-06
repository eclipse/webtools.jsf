/*******************************************************************************
 * Copyright (c) 2004, 2011 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.test;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jst.jsf.facesconfig.ui.util.WebrootUtil;

/**
 * @author sfshi
 * 
 */
public class WebrootUtilTest extends FacesConfigEditorTest {

	private static final String WEB_CONTENT_PATH = "/emptyjsfproject/WebContent";

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#getWebPath(org.eclipse.core.runtime.IPath)}.
	 */
	public void testGetWebPathIPath() {
        // TODO:
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#isUnderWebContentFolder(org.eclipse.core.resources.IResource)}.
	 * 
	 * @throws CoreException
	 */
	public void testIsUnderWebContentFolder() throws CoreException {
		IResource resource = project.getFile(new Path("WebContent/web.xml"));
		assertTrue(WebrootUtil.isUnderWebContentFolder(resource));

		IContainer container = WebrootUtil.getWebContentContainer(project);
		IFile page1 = container.getFile(new Path("page1.jsp"));
		page1.create(null, true, new NullProgressMonitor());

		assertTrue(WebrootUtil.isUnderWebContentFolder(page1));

		IFile file1 = project.getFile("file1.txt");
		file1.create(null, true, new NullProgressMonitor());

		assertFalse(WebrootUtil.isUnderWebContentFolder(file1));
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#getWebContentPath(org.eclipse.core.resources.IProject)}.
	 */
	public void testGetWebContentPath() {

		IPath path = WebrootUtil.getWebContentPath(project);
		assertNotNull(path);
		assertEquals(path.toString(), WEB_CONTENT_PATH);
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#getWebContentContainer(org.eclipse.core.resources.IProject)}.
	 */
	public void testGetWebContentContainer() {
		IContainer container = WebrootUtil.getWebContentContainer(project);
		assertNotNull(container);
		assertTrue(container.exists());
		assertEquals(container.getFullPath().toString(), WEB_CONTENT_PATH);
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#getWebContentFolderDepth(org.eclipse.core.resources.IProject)}.
	 */
	public void testGetWebContentFolderDepth() {
		assertEquals(2, WebrootUtil.getWebContentFolderDepth(project));
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#isValidWebFile(org.eclipse.core.runtime.IPath)}.
	 * @throws CoreException 
	 */
	public void testIsValidWebFile() throws CoreException {
		
		IContainer container = WebrootUtil.getWebContentContainer(project);
		IFile page1 = container.getFile(new Path("page1.jsp"));
		page1.create(null, true, new NullProgressMonitor());

		assertTrue(WebrootUtil.isValidWebFile(page1.getFullPath()));

		IFile page2 = container.getFile(new Path("page2.jsv"));
		page2.create(null, true, new NullProgressMonitor());
		assertTrue(WebrootUtil.isValidWebFile(page2.getFullPath()));

		IFile file1 = project.getFile("file1.txt");
		file1.create(null, true, new NullProgressMonitor());

		assertFalse(WebrootUtil.isValidWebFile(file1.getFullPath()));

	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#getWebPath(java.lang.String)}.
	 */
	public void testGetWebPathString() {
        // TODO:
	}

	/**
	 * Test method for
	 * {@link org.eclipse.jst.jsf.common.ui.internal.utils.WebrootUtil#getPageNameFromWebPath(java.lang.String)}.
	 */
	public void testGetPageNameFromWebPath() {
	    // TODO:
	}

}
