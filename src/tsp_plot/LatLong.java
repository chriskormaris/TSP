package tsp_plot;


// Geographic Coordinates
public class LatLong {

	private double longitude;  // x axis
	private double latitude;  // y axis
	private int id;

	public LatLong() {

	}

	public LatLong(int id) {
		this.id = id;
	}

	public LatLong(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public LatLong(double longitude, double latitude, int id) {
		this.longitude = longitude;
		this.latitude = latitude;
		this.id = id;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LatLong getRandomLatLong(double maxLongitude, double minLongitude, double maxLatitude, double minLatitude) {
		return getRandomLatLong(maxLongitude, minLongitude, maxLatitude, minLatitude, 0);
	}

	public LatLong getRandomLatLong(
		double maxLongitude,
		double minLongitude,
		double maxLatitude,
		double minLatitude,
		int id
	) {
		LatLong latlong = new LatLong(id);

		double randomLatitude = Math.random() * (maxLatitude - minLatitude) + minLatitude;
		double randomLongitude = Math.random() * (maxLongitude - minLongitude) + minLongitude;

		latlong.setLatitude(randomLatitude);
		latlong.setLongitude(randomLongitude);

		return latlong;
	}

	@Override
	public String toString() {
		if (this.id >= 0) {
			return "id: " + id + ", latitude: " + this.getLatitude() + ", longitude: " + this.getLongitude();
		} else {
			return "latitude: " + this.getLatitude() + ", longitude: " + this.getLongitude();
		}
	}

	@Override
	public int hashCode() {
		// System.out.println("in hash code");
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) this.latitude;
		result = prime * result + (int) this.longitude;
		result = prime * result + this.id;
		return result;
	}

	// equals Implementation
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		LatLong other = (LatLong) obj;
		if (getLatitude() != other.getLatitude())
			return false;
		if (getLongitude() != other.getLongitude())
			return false;
		return getId() == other.getId();
	}

	// calculate the euclidean distance between two nodes
	public double distanceFrom(LatLong other) {
	// public synchronized double distanceFrom(LatLong other) {
		double latitude1 = getLatitude();
		double latitude2 = other.getLatitude();

		double longitude1 = getLongitude();
		double longitude2 = other.getLongitude();

        return Math.sqrt(Math.pow(longitude1 - longitude2, 2) + Math.pow(latitude1 - latitude2, 2));
	}

}
