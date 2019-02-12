/*******************************************************************************
 * Copyright (c) 2006 Sybase, Inc. and others.
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
package org.eclipse.jst.jsf.common.ui.internal.utils;

import java.util.HashMap;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * @author mengbo
 */
public class PluginImageHelper {
	private static PluginImageHelper _instance = null;

	/**
	 * Gets the instance.
	 * 
	 * @return Returns a TaglibPluginImageHelper
	 */
	public synchronized static PluginImageHelper getInstance() {
		if (_instance == null) {
			_instance = new PluginImageHelper();
		}
		return _instance;
	}

	// save a descriptor for each image
	private HashMap _imageDescRegistry = null;

	private final String PLUGIN_SEPARATOR = "^"; //$NON-NLS-1$

	/**
	 * Creates an image from the given resource and adds the image to the image
	 * registry.
	 * 
	 * @param resource
	 * @param pluginId
	 * @return Image
	 */
	private Image createImage(String resource, String pluginId) {
		ImageDescriptor desc = getImageDescriptor(resource, pluginId);
		Image image = null;

		if (desc != null) {
			image = desc.createImage();

			// dont add the missing image descriptor image to the image
			// registry
			if (!desc.equals(ImageDescriptor.getMissingImageDescriptor())) {
				String thePluginId = pluginId;
				if (thePluginId == null) {
					return null;
				}
				String key = thePluginId + PLUGIN_SEPARATOR + resource;
				getImageRegistry().put(key, image);
			}
		}
		return image;
	}

	/**
	 * Creates an image descriptor from the given imageFilePath in the given
	 * pluginId and adds the image descriptor to the image descriptor registry.
	 * If an image descriptor could not be created, the default "missing" image
	 * descriptor is returned but not added to the image descriptor registry.
	 * 
	 * @param imageFilePath
	 * @param pluginId
	 *            if null, look in this plugin
	 * @return ImageDescriptor image descriptor for imageFilePath or default
	 *         "missing" image descriptor if resource could not be found
	 */
	private ImageDescriptor createImageDescriptor(String imageFilePath,
			String pluginId) {
		String thePluginId = pluginId;
		if (thePluginId == null) {
			return null;
		}

		ImageDescriptor imageDescriptor = AbstractUIPlugin
				.imageDescriptorFromPlugin(thePluginId, imageFilePath);
		if (imageDescriptor != null) {
			String key = thePluginId + PLUGIN_SEPARATOR + imageFilePath;
			getImageDescriptorRegistry().put(key, imageDescriptor);
		} else {
			imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
		}

		return imageDescriptor;
	}

	/**
	 * Retrieves the image associated with resource from the image registry. If
	 * the image cannot be retrieved, attempt to find and load the image at the
	 * location specified in resource.
	 * 
	 * @param resource
	 *            the image to retrieve
	 * @return Image the image associated with resource or null if one could not
	 *         be found
	 */
	public Image getImage(String resource) {
		return getImage(resource, null);
	}

	/**
	 * Retrieves the image associated with resource from the image registry. If
	 * the image cannot be retrieved, attempt to find and load the image at the
	 * location specified in resource.
	 * 
	 * @param resource
	 *            the image to retrieve
	 * @param pluginId
	 * @return Image the image associated with resource or null if one could not
	 *         be found
	 */
	public Image getImage(String resource, String pluginId) {
		String thePluginId = pluginId;
		if (thePluginId == null) {
			return null;
		}

		String key = thePluginId + PLUGIN_SEPARATOR + resource;

		Image image = getImageRegistry().get(key);
		if (image == null) {
			// create an image
			image = createImage(resource, pluginId);
		}
		return image;
	}

	/**
	 * Retrieves the image descriptor associated with resource from the image
	 * descriptor registry. If the image descriptor cannot be retrieved, attempt
	 * to find and load the image descriptor at the location specified in
	 * resource.
	 * 
	 * @param resource
	 *            the image descriptor to retrieve
	 * @return ImageDescriptor the image descriptor assocated with resource or
	 *         the default "missing" image descriptor if one could not be found
	 */
	public ImageDescriptor getImageDescriptor(String resource) {
		return getImageDescriptor(resource, null);
	}

	/**
	 * Retrieves the image descriptor associated with resource in pluginId from
	 * the image descriptor registry. If the image descriptor cannot be
	 * retrieved, attempt to find and load the image descriptor at the location
	 * specified in resource in pluginId.
	 * 
	 * @param resource
	 *            the image descriptor to retrieve
	 * @param pluginId
	 *            the plugin the resource is located. if null, use look in this
	 *            plugin
	 * @return ImageDescriptor the image descriptor assocated with resource or
	 *         the default "missing" image descriptor if one could not be found
	 */
	public ImageDescriptor getImageDescriptor(String resource, String pluginId) {
		ImageDescriptor imageDescriptor = null;
		String thePluginId = pluginId;
		if (thePluginId == null) {
			return null;
		}

		String key = thePluginId + PLUGIN_SEPARATOR + resource;
		Object o = getImageDescriptorRegistry().get(key);
		if (o == null) {
			// create a descriptor
			imageDescriptor = createImageDescriptor(resource, pluginId);
		} else {
			imageDescriptor = (ImageDescriptor) o;
		}
		return imageDescriptor;
	}

	/**
	 * Returns the image descriptor registry for this plugin.
	 * 
	 * @return HashMap - image descriptor registry for this plugin
	 */
	private HashMap getImageDescriptorRegistry() {
		if (_imageDescRegistry == null) {
			_imageDescRegistry = new HashMap();
		}
		return _imageDescRegistry;
	}

	/**
	 * Returns the image registry for this plugin.
	 * 
	 * @return ImageRegistry - image registry for this plugin
	 */
	private ImageRegistry getImageRegistry() {
		return JFaceResources.getImageRegistry();
	}
}
