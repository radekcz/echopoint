/**
 * Root namespace for the Echopoint client-side test application.  This file
 * is used for the echopointclienttest.war file since the image paths are
 * different for the deployable client application.
 *
 * @author Rakesh 2008-07-20
 * @version $Id$
 */
echopoint.test = {};

/** The main application class implementation. */
echopoint.test.TestApp = Core.extend( Echo.Application,
{
  /** The {@link MainContent} for the application. */
  _mainContent: null,

  $construct: function()
  {
    Echo.Application.call( this );
    this.rootComponent.removeAll();
    this._mainContent = new echopoint.test.MainContent();
    this.rootComponent.add( this._mainContent );
  },

  /** Return {@link _mainContent}. */
  getMainContent: function() { return this._mainContent; }
});

/** The global application instance. */
var testApp = null;

/** Boostrapping code for the test application. */
function init()
{
  Core.Web.init();
  testApp = new echopoint.test.TestApp();
  var client = new Echo.FreeClient( testApp, document.getElementById( "rootArea" ) );
  client.addResourcePath( "Echo", "lib/echo/" );
  client.addResourcePath( "Extras", "lib/extras/" );
  client.addResourcePath( "echopoint", "resource/" );
  testApp.setStyleSheet( echopoint.test.TestApp.StyleSheet );
  client.init();
};

