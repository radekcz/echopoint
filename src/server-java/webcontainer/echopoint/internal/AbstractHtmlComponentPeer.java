/*
 * This file is part of the Echo Point Project.  This project is a
 * collection of Components that have extended the Echo Web Application
 * Framework Version 3.
 *
 * Version: MPL 1.1
 *
 * The contents of this file are subject to the Mozilla Public License Version
 * 1.1 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.mozilla.org/MPL/
 *
 * Software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTY OF ANY KIND, either express or implied. See the License
 * for the specific language governing rights and limitations under the
 * License.
 */

package echopoint.internal;

import echopoint.CommonResources;
import echopoint.CommonService;
import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractComponentSynchronizePeer;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * Rendering peer for the {@link echopoint.internal.AbstractHtmlComponent} class.
 *
 * @author Rakesh 2008-03-22
 * @version $Id$
 */
public class AbstractHtmlComponentPeer
  extends AbstractComponentSynchronizePeer
{
  private static final String COMPONENT_NAME = AbstractHtmlComponent.class.getName();

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
  {
    "resource/js/Application.HtmlComponent.js",
    "resource/js/Sync.AbstractHtmlComponent.js"
  };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
    JavaScriptService.forResources( COMPONENT_NAME, SERVICE_FILES );

  /** Register the services */
  static
  {
    CommonResources.install();
    WebContainerServlet.getServiceRegistry().add( COMPONENT_SERVICE );
  }

  /**
   * @inheritDoc
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#init
   */
  @Override
  public void init( final Context context, final  Component component )
  {
    super.init( context, component );
    ServerMessage serverMessage =
      (ServerMessage) context.get( ServerMessage.class );
    serverMessage.addLibrary( CommonService.ECHOPOINT_SERVICE.getId() );
    serverMessage.addLibrary(  COMPONENT_NAME );
  }

  /**
   * @inheritDoc
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getComponentClass
   */
  public Class getComponentClass()
  {
    return AbstractHtmlComponent.class;
  }

  /**
   * @inheritDoc
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getClientComponentType
   */
  public String getClientComponentType( final boolean shortType )
  {
    return COMPONENT_NAME;
  }
}