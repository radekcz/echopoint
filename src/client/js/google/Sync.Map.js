/**
 * Component rendering peer: Map
 *
 * @author Rakesh 2008-08-25
 * @version: $Id$
 */
echopoint.google.MapSync = Core.extend(
    echopoint.google.internal.AbstractChartSync,
{
  $static:
  {
    DEFAULT_HEIGHT: "220",
    DEFAULT_WIDTH: "440"
  },

  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.MAP, this );
  },

  /** Over-ridden to return the appropriate chart type. */
  getChartType: function()
  {
    return echopoint.google.Map.CHART_TYPE;
  },

  /**
   * Over-ridden to configure the map regions and colours.
   *
   * @see #setGeographicalArea
   * @see #setColors
   */
  setAdditionalParameters: function( url )
  {
    url = this.setGeographicalArea( url );
    url = this.setColors( url );
    url = this.setCodes( url );
    return url;
  },

  /** Set the geographical region for the map. */
  setGeographicalArea: function( url )
  {
    var region = this.component.render( echopoint.google.Map.GEOGRAPHICAL_AREA );
    url += "&chtm=" + ( ( region ) ? region : echopoint.google.Map.WORLD );
    return url;
  },

  /** Over-ridden to set the colour and colour gradients for the map. */
  setColors: function( url )
  {
    var colors = this.component.render( echopoint.google.Map.COLORS );
    if ( colors )
    {
      url += "&chco=" + colors;
    }

    return url;
  },

  /**
   * Set the codes of the countries/states that are to be coloured.
   */
  setCodes: function( url )
  {
    var codes = this.component.render( echopoint.google.Map.CODES );
    if ( codes )
    {
      url += "&chld=" + codes;
    }

    return url;
  },

  /**
   * Over-ridden to specify the default height for a map.
   */
  getDefaultHeight: function()
  {
    return echopoint.google.MapSync.DEFAULT_HEIGHT;
  },

  /**
   * Over-ridden to specify the default width for a map.
   */
  getDefaultWidth: function()
  {
    return echopoint.google.MapSync.DEFAULT_WIDTH;
  }
});
