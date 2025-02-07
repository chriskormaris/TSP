package tsp_plot;

import java.util.List;

public class Utilities {

	private Utilities() {
	}

	public static void printMapBorders(List<LatLong> map) {
		double minLongitude = 180;
		double maxLongitude = -180;
		double minLatitude = 90;
		double maxLatitude = -90;
		for (LatLong city : map) {
			double longitude = city.getLongitude();
			double latitude = city.getLatitude();
			minLongitude = Math.min(minLongitude, longitude);
			maxLongitude = Math.max(maxLongitude, longitude);
			minLatitude = Math.min(minLatitude, latitude);
			maxLatitude = Math.max(maxLatitude, latitude);
		}
		System.out.println("minLongitude: " + minLongitude);
		System.out.println("maxLongitude: " + maxLongitude);
		System.out.println("minLatitude: " + minLatitude);
		System.out.println("maxLatitude: " + maxLatitude);
	}

}
