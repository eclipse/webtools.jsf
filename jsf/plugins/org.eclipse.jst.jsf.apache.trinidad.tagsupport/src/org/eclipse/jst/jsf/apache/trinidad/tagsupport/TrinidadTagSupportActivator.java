/*******************************************************************************
 * Copyright (c) 2001, 2009 Oracle Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Contributors:
 *     Oracle Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.jsf.apache.trinidad.tagsupport;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jst.jsf.apache.trinidad.tagsupport.dtresourceprovider.TrinidadDTResourceProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class TrinidadTagSupportActivator extends AbstractUIPlugin {

	/**
	 * the plugin id
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.apache.trinidad.tagsupport"; //$NON-NLS-1$

	// The shared instance
	private static TrinidadTagSupportActivator plugin;
	
	/**
	 * The constructor
	 */
	public TrinidadTagSupportActivator() {
        // do nothing
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		TrinidadDTResourceProvider.init();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static TrinidadTagSupportActivator getDefault() {
		return plugin;
	}

	/**
	 * Writes an informational message to this plug-in's log.
	 * 
	 * @param message Informational message to be written.
	 */
	public static void logInfo(String message) {
		ILog log = getDefault().getLog();
		log.log(new Status(IStatus.INFO, PLUGIN_ID, message));
	}

	/**
	 * Writes an error message to this plug-in's log.
	 * 
	 * @param message Error message to be written.
	 * @param ex Throwable instance.
	 */
	public static void logError(String message, Throwable ex) {
		ILog log = getDefault().getLog();
		IStatus status;
		if (ex != null) {
			status = new Status(IStatus.ERROR, PLUGIN_ID, message, ex);
		} else {
			status = new Status(IStatus.ERROR, PLUGIN_ID, message);
		}
		log.log(status);
	}

}
