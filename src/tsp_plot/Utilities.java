package tsp_plot;

import java.util.List;

public class Utilities {

	public static void printMapBorders(List<LatLong> map) {
		double minLongitude = 180;
		double maxLongitude = -180;
		double minLatitude = 90;
		double maxLatitude = -90;
		for (LatLong city: map) {
		    double longitude = city.getLongitude();
		    double latitude = city.getLatitude();
		    if (longitude < minLongitude) {
		    	minLongitude = longitude;
		    }
		    if (longitude > maxLongitude) {
		    	maxLongitude = longitude;
		    }
		    if (latitude < minLatitude) {
		    	minLatitude = latitude;
		    }
		    if (latitude > maxLatitude) {
		    	maxLatitude = latitude;
		    }
		}
		System.out.println("minLong: " + minLongitude);
		System.out.println("maxLong: " + maxLongitude);
		System.out.println("minLat: " + minLatitude);
		System.out.println("maxLat: " + maxLatitude);
	}
	
}
