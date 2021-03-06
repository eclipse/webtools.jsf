/*******************************************************************************
 * Copyright (c) 2004, 2007 Sybase, Inc. and others.
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

package org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpolicy;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jst.jsf.facesconfig.ui.EditorPlugin;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.action.OpenEditorAction;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.ConnectionCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.ConnectionCommandFactory;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.command.OpenEditorCommand;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.editpart.PageflowNodeEditPart;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.figure.PageflowNodeFigure;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowLink;
import org.eclipse.jst.jsf.facesconfig.ui.pageflow.model.PageflowNode;
import org.eclipse.jst.jsf.facesconfig.ui.preference.GEMPreferences;

/**
 * Customizes conntection policies
 *
 */
public class PageflowNodeEditPolicy extends GraphicalNodeEditPolicy {
	/*
	 * (non-Javadoc)
	 * 
	 * @see GraphicalNodeEditPolicy#createDummyConnection()
	 */
	protected Connection createDummyConnection(Request req) {
		PolylineConnection conn = new PolylineConnection();
		conn.setLineWidth(getLineWidth());
		conn.setForegroundColor(ColorConstants.black);

		return conn;
	}

	/**
	 * get the connection line width from preference.
	 */
	private int getLineWidth() {
		IPreferenceStore store = EditorPlugin.getDefault().getPreferenceStore();
		int lineWidth = store.getInt(GEMPreferences.LINE_WIDTH);
		return lineWidth;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GraphicalNodeEditPolicy#getConnectionCompleteCommand()
	 */
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		ConnectionCommand command = (ConnectionCommand) request
				.getStartCommand();
		// ConnectionAnchor anchor =
		// getPageflowNodeEditPart().getTargetConnectionAnchor(request);

		command.setTarget(getPageflowNode());

		return command;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GraphicalNodeEditPolicy#getConnectionCreateCommand()
	 */
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		ConnectionCommand command = ConnectionCommandFactory
				.createCommand(request);
		command.setPFLink((PageflowLink) request.getNewObject());
		PageflowNode flowObject = getPageflowNode();
		command.setSource(flowObject);
		request.setStartCommand(command);
		return command;
	}

	/**
	 * get the edit part of pageflow node
	 * 
	 * @return - the edit part of pageflow node
	 */
	protected PageflowNodeEditPart getPageflowNodeEditPart() {
		return (PageflowNodeEditPart) getHost();
	}

	/**
	 * get the pageflow node
	 * 
	 * @return - the pageflow node
	 */
	protected PageflowNode getPageflowNode() {
		return (PageflowNode) getHost().getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GraphicalNodeEditPolicy#getReconnectTargetCommand()
	 */
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		ConnectionCommand cmd = ConnectionCommandFactory.createCommand(request);
		cmd
				.setPFLink((PageflowLink) request.getConnectionEditPart()
						.getModel());
		cmd.setTarget(getPageflowNode());
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GraphicalNodeEditPolicy#getReconnectSourceCommand()
	 */
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		ConnectionCommand cmd = ConnectionCommandFactory.createCommand(request);
		cmd
				.setPFLink((PageflowLink) request.getConnectionEditPart()
						.getModel());
		cmd.setSource(getPageflowNode());
		return cmd;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see GraphicalNodeEditPolicy#getCommand()
	 */
	public Command getCommand(Request request) {
		if (OpenEditorAction.OPEN_EDITOR_REQUEST.equals(request.getType())) {
			return new OpenEditorCommand(getHost());
		}

		return super.getCommand(request);
	}

	/**
	 * return the pageflow node figure
	 * 
	 * @return - the pageflow node's figure
	 */
	protected PageflowNodeFigure getPageflowNodeFigure() {
		return (PageflowNodeFigure) ((GraphicalEditPart) getHost()).getFigure();
	}
}
