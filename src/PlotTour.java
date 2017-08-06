import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.erichseifert.gral.data.DataSeries;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.graphics.Insets2D;
import de.erichseifert.gral.graphics.Label;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.axes.AxisRenderer;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.plots.points.DefaultPointRenderer2D;
import de.erichseifert.gral.plots.points.PointRenderer;
import de.erichseifert.gral.ui.InteractivePanel;


public class PlotTour extends Panel {
	
	/** Version id for serialization. */
	private static final long serialVersionUID = -5263057758564264676L;

	/** Instance to generate random data values. */
	private static final Random random = new Random();
	
	private List<LatLong> tour;
	
	@SuppressWarnings("unchecked")
	public PlotTour(List<LatLong> shortestTour) {
		this.tour = shortestTour;

	    //CREATES A DATA TABLE AND RUNS A LOOP TO INCREMENTALLY PLOT OUT POINTS OF A SINE CURVE ON THE STEP .25
	    DataTable tourData = new DataTable(Double.class, Double.class);
	    tourData.setName("cities");

	    //PLOT OUT DATA AND SET XYPLOT IN JFRAME, THEN CONNECT LINES AND CHANGE COLORS
	    XYPlot plot = new XYPlot(tourData);
	    
	    for (LatLong city: tour) {
	    	tourData.add(city.getLongitude(), city.getLatitude());
	    }
	    
	    plot.getLegend().clear();
	    
	    // Create different data series for each city point.
	    // This is useful for the legend and for assigning different colors.
	    List<DataSeries> points = new ArrayList<DataSeries>();
	    int iterations = 0;
	    for (LatLong city: tour) {
	    	DataTable tempData = new DataTable(Double.class, Double.class);
	    	tempData.add(city.getLongitude(), city.getLatitude());
	     	if (iterations < tour.size()-1) {
	 		    DataSeries ds = new DataSeries("City " + city.getId(), tempData);
//	 		    plot.add(ds);
	 		    points.add(ds);
	     	}
		    iterations++;
	    }
	    
	    // Sort city points data series by name.
	    points.sort(new DataSeriesComparator());
 		for (DataSeries ds: points) {
 			plot.add(ds);
 		}
	    
	    plot.setLegendVisible(true);
	    
	    // Format plot
 		plot.setInsets(new Insets2D.Double(20.0, 40.0, 40.0, 40.0));
 		plot.setBackground(Color.WHITE);
 		plot.getTitle().setText(getDescription());

 		// Format plot area
 		plot.getPlotArea().setBackground(new RadialGradientPaint(
 			new Point2D.Double(0.5, 0.5),
 			0.75f,
 			new float[] { 0.6f, 0.8f, 1.0f },
 			new Color[] { new Color(0, 0, 0, 0), new Color(0, 0, 0, 32), new Color(0, 0, 0, 128) }
 		));
 		plot.getPlotArea().setBorderStroke(null);

 		// Format axes
 		AxisRenderer axisRendererX = plot.getAxisRenderer(XYPlot.AXIS_X);
 		AxisRenderer axisRendererY = plot.getAxisRenderer(XYPlot.AXIS_Y);
 		axisRendererX.setLabel(new Label("Longitude"));
 		plot.setAxisRenderer(XYPlot.AXIS_X, axisRendererX);
 		
 		// Custom tick labels
// 		Map<Double, String> labels = new HashMap<Double, String>();
// 		labels.put(2.0, "Two");
// 		labels.put(1.5, "OnePointFive");
// 		axisRendererX.setCustomTicks(labels);
 		
 		// Custom stroke for the x-axis
 		BasicStroke stroke = new BasicStroke(2f);
 		axisRendererX.setShapeStroke(stroke);
 		Label linearAxisLabel = new Label("Latitude");
 		linearAxisLabel.setRotation(90);
 		axisRendererY.setLabel(linearAxisLabel);
 		
 		// Change intersection point of Y axis
// 		axisRendererY.setIntersection(1.0);
 		
 		// Change tick spacing
 		axisRendererX.setTickSpacing(5.0);
 		
 		
 		// Format data lines
 		LineRenderer lineRenderer = new DefaultLineRenderer2D();
 		lineRenderer.setColor(COLOR1);
 		lineRenderer.setStroke(new BasicStroke(
 				3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND,
 				10.0f, new float[] {3f, 6f}, 0.0f));
		plot.setLineRenderers(tourData, lineRenderer);

 		// Custom gaps for points
 		lineRenderer.setGap(2.0);
 		lineRenderer.setGapRounded(true);
 		
 		
 		// Format rendering of data points
 		for (DataSeries ds: points) {
 	 		PointRenderer defaultPointRenderer = new DefaultPointRenderer2D();
 	 		
 	 		// Java 'Color' class takes 3 floats, from 0 to 1.
 	 		float r = random.nextFloat();
 	 		float g = random.nextFloat();
 	 		float b = random.nextFloat();
			Color randomColor = new Color(r, g, b);
 	 		
 	 		defaultPointRenderer.setColor(randomColor);
 			plot.setPointRenderers(ds, defaultPointRenderer);
	    }
 		
 		
 		// Add plot to Swing component
 		add(new InteractivePanel(plot), BorderLayout.CENTER);
 		
 		// Change zoom level
 		plot.getNavigator().setZoom(0.6f);
 	}

 	@Override
 	public String getTitle() {
 		return "TSP tour plot";
 	}

 	@Override
 	public String getDescription() {
 		return "Travelling Salesman Problem Plot";
 	}

 	
}
