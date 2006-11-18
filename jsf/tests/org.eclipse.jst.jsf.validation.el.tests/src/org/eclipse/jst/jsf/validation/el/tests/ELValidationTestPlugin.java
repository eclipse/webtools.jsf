package org.eclipse.jst.jsf.validation.el.tests;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

/**
 * The activator class controls the plug-in life cycle
 */
public class ELValidationTestPlugin extends AbstractUIPlugin 
{
	/**
	 * The plug-in ID
	 */
	public static final String PLUGIN_ID = "org.eclipse.jst.jsf.validation.el.tests";

	// The shared instance
	private static ELValidationTestPlugin plugin;
	
	/**
	 * The constructor
	 */
	public ELValidationTestPlugin() {
		plugin = this;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
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
	public static ELValidationTestPlugin getDefault() {
		return plugin;
	}

}
