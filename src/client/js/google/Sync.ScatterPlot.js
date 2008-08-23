/**
 * Component rendering peer: ScatterPlot
 *
 * @author Rakesh 2008-08-22
 * @version: $Id$
 */
echopoint.google.ScatterPlotSync = Core.extend(
    echopoint.google.internal.AdvancedChartSync,
{
  $load: function()
  {
    Echo.Render.registerPeer( echopoint.constants.SCATTER_PLOT, this );
  },

  /** Over-ridden to return the appropriate chart type. */
  getChartType: function()
  {
    return echopoint.google.ScatterPlot.CHART_TYPE;
  },

  /**
   * Encode the special <code>size</code> array of values for scatter plot
   * point sizes.
   */
  encodeSize: function( data, chartData )
  {
    if ( data.size )
    {
      chartData.push( "," );
      var max = data.getSizeMax();

      for ( var i = 0; i < data.size.length; i++ )
      {
        var currentValue = data.size[i];

        if ( !isNaN( currentValue ) && currentValue >= 0 )
        {
          chartData.push( this._simpleEncoding.charAt(
              Math.round( ( this._simpleEncoding.length - 1 ) *
                          currentValue / max ) ) );
        }
        else
        {
          chartData.push( "_" );
        }
      }
    }
  }
});
