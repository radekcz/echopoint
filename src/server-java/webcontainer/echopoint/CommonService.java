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

package echopoint;

import nextapp.echo.webcontainer.service.JavaScriptService;
import nextapp.echo.webcontainer.Service;
import nextapp.echo.webcontainer.WebContainerServlet;

/**
 * Register the Echopoint core library.
 *
 * @author Rakesh 2008-06-23
 * @version $Id$
 */
public class CommonService
{
  /** The service for the core echopoint namespace and components. */
  public static final Service ECHOPOINT_SERVICE =
      JavaScriptService.forResource( "echopoint.Boot", "resource/js/Echopoint.js" );

  /** Add the {@link #ECHOPOINT_SERVICE} to the service registry. */
  static
  {
    WebContainerServlet.getServiceRegistry().add( ECHOPOINT_SERVICE );
  }
}
