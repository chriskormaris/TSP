import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// a brute-force approach
// link -> http://www.quickperm.org/
public class TSP {
	
	static List<Vertex[]> tours = new ArrayList<Vertex[]>();
	static Map<Vertex[], Double> tourSums = new HashMap<Vertex[], Double>();
	static Vertex[] minTour;
	static double minDistance = 1000000;
	
	// find all permutations for a Hamiltonian (TSP) cycle or tour
	// complexity: O(n!)
	public static void findAllTours(Graph G) {
		
		Vertex[] path = G.vertices;
		int N = path.length;
		
		minTour = new Vertex[G.V];

		
		int[] p = new int[N+1];
		for (int i=0; i<N+1; i++) {
			p[i] = i;
		}
		int i=0;
		int j;
		int iteration=1;
		
		while (i<N) {
			p[i]--;
			if (i%2 == 1) {
				j = p[i];
			} else {
				j = 0;
			}
			
			swap(path, i, j);
			Vertex[] tour = new Vertex[N+1];
			System.arraycopy(path, 0, tour, 0, N);
			tour[N] = path[0];
			tours.add(tour);
			
			// calculate the tour total distance and add it to a map
			double tourDistance = calculateTourDistance(tour);
			printNoLN(tour, iteration);
			System.out.println("|| distance: " + tourDistance);
			iteration++;

			i = 1;
			while (p[i] == 0) {
				p[i] = i;
				i++;
			} // end while (p[i] is equal to 0)
		} // end while (i < N)
		   
	}
	
	private static void print(Vertex[] a) {
		System.out.print("tour: ");
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
	
	private static void printNoLN(Vertex[] a, int iteration) {
		System.out.print("tour " + iteration + ": ");
		for(int i=0; i<a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

	private static void swap(Vertex[] a, int i, int j) {
		Vertex temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	// calculates a tours total distance and adds it to a map
	private static double calculateTourDistance(Vertex[] tour) {
		double tourDistance = 0;
		for (int i=0; i<tour.length; i++) {
			for (Edge edge: tour[i].outEdges) {
				if (i<tour.length-1) {
					if (tour[i+1].number == edge.v.number) {
						tourDistance += edge.weight;
					}
				}
			}
		}
		
		// find the tour of minimum distance
		if (tourDistance <= minDistance) {
			minDistance = tourDistance;
			minTour = tour;
		}

		tourSums.put(tour, tourDistance);
		
		return tourDistance;
	}

	public Vertex[] tsp(Graph G) {
		findAllTours(G);
		System.out.println();
		System.out.println("TSP tour of minimum weight: " + minDistance);
		print(minTour);
		return minTour;
	}
	
	public static void main(String[] args) {
		Vertex A = new Vertex(0, "A"); // SOURCE VERTEX
		Vertex B = new Vertex(1, "B");
		Vertex C = new Vertex(2, "C");
		Vertex D = new Vertex(3, "D");
		Vertex E = new Vertex(4, "E");
//		Vertex F = new Vertex(5, "F");
//		Vertex G = new Vertex(6, "G"); // SINK VERTEX
//		Vertex A2 = new Vertex(7, "A2"); // ADD A SECOND SOURCE VERTEX TO THE GRAPH
		
		Vertex[] vertices = {A, B, C, D, E/*, F, G, A2*/};
		
		Graph graph = new Graph(vertices);
		graph.completeDigraph(); // complete digraph of random weight edges
		
		TSP tsp = new TSP();
		tsp.tsp(graph);
	}

}
