//Geograhic Coordinates
public class LatLong {
	
	private double latitude; // y axis
	private double longitude; // x axis
	private int id;
	
	public LatLong(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	public LatLong(double latitude, double longitude, int id) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.id = id;
	}
	
	public LatLong() {

	}
	
	public LatLong(int id) {
		this.id = id;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	public LatLong getRandomLatLong(double maxLatitude, double minLatitude, double maxLongitude, double minLongitude) {
		LatLong latlong = new LatLong();
		
		double randomLatitude = (int) Math.round( Math.random() * (maxLatitude - minLatitude) ) + minLatitude;
		double randomLongitude = (int) Math.round( Math.random() * (maxLongitude - minLongitude) ) + minLongitude;
		
		latlong.setLatitude(randomLatitude);
		latlong.setLongitude(randomLongitude);
		
		return latlong;
	}
	
	public LatLong getRandomLatLong(double maxLatitude, double minLatitude, double maxLongitude, double minLongitude, int id) {
		LatLong latlong = new LatLong(id);
		
		double randomLatitude = (int) Math.round( Math.random() * (maxLatitude - minLatitude) ) + minLatitude;
		double randomLongitude = (int) Math.round( Math.random() * (maxLongitude - minLongitude) ) + minLongitude;
		
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
//		System.out.println("in hash code");
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) this.latitude;
		result = prime * result + (int) this.longitude;
		result = prime * result + (int) this.id;
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
		if (getId() != other.getId())
			return false;
		
		return true;
	}

	// calculate the euclidean distance between two nodes
	public double distanceFrom(LatLong latlong2) {
	//public synchronized double distanceFrom(Node node2) {
		double distance = 0;
				
		double latitude1 = getLatitude();
		double latitude2 = latlong2.getLatitude();
				
		double longitude1 = getLongitude();
		double longitude2 = latlong2.getLongitude();
				
		distance = Math.sqrt( Math.pow(longitude1 - longitude2, 2) + Math.pow(latitude1 - latitude2, 2) );
				
		return distance;
	}

}
