import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;


public class FileManager {
	
	private List<LatLong> cities = new ArrayList<LatLong>();
	
	public void parseFile(String inputFile) {
	
		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			String line;
			int id = 1;
			while ((line = br.readLine()) != null) {
			
				if (line.startsWith("city")) {
					double latitude = Double.parseDouble(line.split("->")[1].split(",")[0].replace("latitude:", "").replaceAll("\\s+",""));
					double longitude = Double.parseDouble(line.split("->")[1].split(",")[1].replace("longitude:", "").replaceAll("\\s+",""));
					LatLong latlong = new LatLong(latitude, longitude, id);
					
					// check if latlong is unique
					if (!cities.contains(latlong)) {
						cities.add(latlong);
					} else {
						System.err.println("A city with the same latitude and longitude already exists!\n");
					}
					
				}
			
				id++;
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	public void recreateFile(String filename) {
	    try {
			if ( Files.exists(Paths.get(filename), LinkOption.NOFOLLOW_LINKS) ) {
				Files.delete(Paths.get(filename));
			}
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.flush();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeLineToFile(String outputFile, String line) {
		
		try {
		    Files.write(Paths.get(outputFile), (line + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<LatLong> getCities() {
		return cities;
	}
	
}
