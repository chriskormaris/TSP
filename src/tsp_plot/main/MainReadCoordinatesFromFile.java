package tsp_plot.main;

import graph.Edge;
import graph.Graph;
import graph.Vertex;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tsp.TSP;
import tsp_plot.FileManager;
import tsp_plot.LatLong;
import tsp_plot.PlotTour;
import tsp_plot.Utilities;


// TRAVELLING SALESMAN PROBLEM
// Made by Christos Kormaris
public class MainReadCoordinatesFromFile {

	static List<LatLong> cities;
	static List<LatLong> shortestTour;

	public static void main(String[] args) {
		cities = new ArrayList<>();

		/* get city coordinates from file */
		FileManager fileManager = new FileManager();
		fileManager.parseFile("tsp_coordinates/cities.txt");
		cities = fileManager.getCities();

		// calculate the distances between every city and print them
		/*
		for (LatLong city: cities) {
			System.out.println("City " + (cities.indexOf(city) + 1) + " coordinates -> " + city);
			for (LatLong other_city: cities) {
				if (city != other_city) {
					double distance = city.distanceFrom(other_city);
					System.out.println("City " + (cities.indexOf(city) + 1) + " distance from" + " City " + (cities.indexOf(other_city) + 1) + " -> " + distance + " meters");
				}
			}
			System.out.println();
		}
		*/

		/* RUN TRAVELLING SALESMAN ALGORITHM */

		// First construct the Graph
		Graph graph = new Graph(cities.size());
		graph.E = graph.V * (graph.V - 1);
		graph.edges = new Edge[graph.E];
		int counter = 0;
		Map<Vertex, LatLong> vertexCity = new HashMap<>();
		for (LatLong city : cities) {
			Vertex vertex = new Vertex(city.getId());
			vertexCity.put(vertex, city);
			graph.vertices[counter] = vertex;
			counter++;
		}

		counter = 0;
		for (Vertex u : graph.vertices) {
			for (Vertex v : graph.vertices) {
				if (u.number != v.number) {
					LatLong city = vertexCity.get(u);
					LatLong otherCity = vertexCity.get(v);
					double distance = city.distanceFrom(otherCity);
					Edge edge = new Edge(u, v, distance);
					graph.edges[counter] = edge;
					counter++;
				}
			}
		}

		// RUN the brute-force TSP algorithm
		TSP tsp = new TSP();
		Vertex[] tour = tsp.tsp(graph);
		System.out.println();

		// print the cities of the shortest tour
		shortestTour = new ArrayList<>();
		System.out.println("shortest tour: ");
		for (int i = 0; i < tour.length; i++) {
			for (LatLong city : cities) {

				if (tour[i].number == city.getId()) {
					shortestTour.add(city);
					System.out.println(city + " ");
				}

			}
		}
		System.out.println();

		// print the total distance of the shortest tour
		/*
		double totalDistance = 0;
		int cityCounter = 0;
		for (LatLong city: shortestTour) {
			if (cityCounter != 0) {
				double distance = city.distanceFrom(shortestTour.get(cityCounter-1));
				totalDistance += distance;
			}
			cityCounter++;
		}
		System.out.println("shortest tour distance: " + totalDistance);
		System.out.println();
		*/

		// find the tour's borders
		Utilities.printMapBorders(shortestTour);
		System.out.println();

		PlotTour myPlot = new PlotTour(shortestTour);
		myPlot.showInFrame();
	}

}
