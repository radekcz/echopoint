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
package echopoint.tucana;

import echopoint.ProgressBar;
import echopoint.internal.AbstractContainerPeer;
import nextapp.echo.app.Component;
import nextapp.echo.app.util.Context;
import nextapp.echo.webcontainer.AbstractComponentSynchronizePeer;
import nextapp.echo.webcontainer.ContentType;
import nextapp.echo.webcontainer.ResourceRegistry;
import nextapp.echo.webcontainer.ServerMessage;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.UserInstance;
import nextapp.echo.webcontainer.WebContainerServlet;
import nextapp.echo.webcontainer.service.JavaScriptService;

/**
 * Rendering peer for the {@link echopoint.tucana.FileUploadSelector}
 * component.
 *
 * @author Rakesh Vidyadharan 2008-11-3
 * @version $Id$
 */
public class FileUploadSelectorPeer extends AbstractContainerPeer
{
  /** The name of the component for which this class is a peer. */
  private static final String COMPONENT_NAME = FileUploadSelector.class.getName();

  /** The JS service files to load. */
  private static final String[] SERVICE_FILES =
      {
          "resource/js/tucana/Application.FileUploadSelector.js",
          "resource/js/tucana/Sync.FileUploadSelector.js"
      };

  /** The service for the client side peer for this component. */
  private static final Service COMPONENT_SERVICE =
      JavaScriptService.forResources( COMPONENT_NAME, SERVICE_FILES );

  /** The property used to keep track of repetitive uploads. */
  private static final String PROPERTY_UPLOAD_INDEX = "uploadIndex";

  /** Register the services */
  static
  {
    UploadProgressService.install();
    UploadReceiverService.install();
    WebContainerServlet.getServiceRegistry().add( COMPONENT_SERVICE );
    final ResourceRegistry resources = WebContainerServlet.getResourceRegistry();
    resources.add( "echopoint", "images/upload.png", ContentType.IMAGE_PNG );
    resources.add( "echopoint", "images/cancel.png", ContentType.IMAGE_PNG );
    resources.add( "echopoint", "images/wait.png", ContentType.IMAGE_PNG );
  }

  /** Default constructor.  Add output properties. */
  public FileUploadSelectorPeer()
  {
    addOutputProperty( PROPERTY_UPLOAD_INDEX );
    addRequiredComponentClass( ProgressBar.class );

    addEvent( new AbstractComponentSynchronizePeer.EventPeer(
        FileUploadSelector.COMPLETE_ACTION,
        FileUploadSelector.ACTION_LISTENERS_CHANGED_PROPERTY )
    {
      @Override
      public boolean hasListeners( Context context, Component component )
      {
        return ( (FileUploadSelector) component ).hasActionListeners();
      }
    });
  }

  /** {@inheritDoc} */
  @Override
  public void init( final Context context, final Component component )
  {
    super.init( context, component );
    final ServerMessage serverMessage =
        (ServerMessage) context.get( ServerMessage.class );
    serverMessage.addLibrary( COMPONENT_NAME );
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getComponentClass
   */
  @Override
  public Class getComponentClass()
  {
    return FileUploadSelector.class;
  }

  /**
   * {@inheritDoc}
   * @see nextapp.echo.webcontainer.AbstractComponentSynchronizePeer#getClientComponentType
   */
  public String getClientComponentType( final boolean shortType )
  {
    return COMPONENT_NAME;
  }

  /**
   * Over-ridden to handle requests for the <code>uploadIndex</code> property.
   *
   * @see nextapp.echo.webcontainer.ComponentSynchronizePeer#getOutputProperty(Context,
   *      Component, String, int)
   */
  @Override
  public Object getOutputProperty( final Context context,
      final Component component, final String propertyName, final int propertyIndex )
  {
    if ( PROPERTY_UPLOAD_INDEX.equals( propertyName ) )
    {
      final FileUploadSelector uploadSelect = (FileUploadSelector) component;
      final UserInstance userInstance = (UserInstance) context.get( UserInstance.class );
      final UploadRenderState renderState = getRenderState( uploadSelect, userInstance );
      return renderState.getMaxUploadIndex();
    }

    if ( FileUploadSelector.PROPERTY_BUTTON_DISPLAY.equals( propertyName ) ||
        FileUploadSelector.PROPERTY_BUTTON_MODE.equals( propertyName ) )
    {
      return component.get( propertyName ).toString();
    }

    return super.getOutputProperty( context, component, propertyName, propertyIndex );
  }

  /**
   * Gets the render state for the given component. Synchronization is handled
   * internally.
   *
   * @param uploadSelect The file upload component instance.
   * @param userInstance The user instance for the session.
   * @return the render state, never <code>null</code>.
   */
  public static UploadRenderState getRenderState(
      final FileUploadSelector uploadSelect, final UserInstance userInstance )
  {
    UploadRenderState renderState;
    synchronized ( uploadSelect )
    {
      renderState = (UploadRenderState) userInstance.getRenderState( uploadSelect );
      if ( renderState == null )
      {
        renderState = new UploadRenderState();
        userInstance.setRenderState( uploadSelect, renderState );
      }
    }
    return renderState;
  }
}