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
package echopoint.command;

import nextapp.echo.app.Command;

/**
 * <code>CloseBrowser</code> can be used to <code>close</code> the browser or tab.
 * It works with FireFox if you set the variable dom.allow_scripts_to_close_windows=true in your config (about:config)
 * <p>
 * @author perxi
 * @version $Id$
 */
public class CloseBrowser implements Command
{
  public CloseBrowser() {}
}
