package tsp_plot;


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

	private List<LatLong> cities;

	public void parseFile(String inputFile) {
		try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
			cities = new ArrayList<>();
			String line;
			int id = 1;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("city")) {
					double longitude = Double.parseDouble(line.split("->")[1].split(",")[0]
															  .replace("longitude:", "").trim());
					double latitude = Double.parseDouble(line.split("->")[1].split(",")[1]
															 .replace("latitude:", "").trim());
					LatLong latLong = new LatLong(longitude, latitude, id);

					// check if latLong is unique
					if (!cities.contains(latLong)) {
						cities.add(latLong);
					} else {
						System.err.println("A city with the same latitude and longitude already exists!\n");
					}
				}
				id++;
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void recreateFile(String filename) {
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
			if (Files.exists(Paths.get(filename), LinkOption.NOFOLLOW_LINKS)) {
				Files.delete(Paths.get(filename));
			}
			bw.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public void writeLineToFile(String outputFile, String line) {
		try {
			Files.write(Paths.get(outputFile), (line + "\n").getBytes(), StandardOpenOption.APPEND);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public List<LatLong> getCities() {
		return this.cities;
	}

}
