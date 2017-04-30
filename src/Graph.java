
// A class to represent a connected, directed and weighted graph
public class Graph {
			
	public int V, E;
    public Edge[] edges;
    public Vertex[] vertices;
    
    public Graph() {
    	
    }
    
    // copy constructor
    public Graph(Graph otherGraph) {
    	this.V = otherGraph.V;
    	this.E = otherGraph.E;
    	this.vertices = new Vertex[V];
    	this.edges = new Edge[E];
    	for (int i=0; i<E; ++i)
        	this.edges[i] = otherGraph.edges[i];
        for (int i=0; i<V; ++i)
        	this.vertices[i] = otherGraph.vertices[i];
    }
    
    // Creates a graph with V vertices and E edges
    public Graph(int v, int e) {
    	this.V = v;
        this.E = e;
        this.edges = new Edge[e];
        this.vertices = new Vertex[v];
        for (int i=0; i<e; ++i)
        	this.edges[i] = new Edge();
        for (int i=0; i<v; ++i)
        	this.vertices[i] = new Vertex();
    }
    
    public Graph(Vertex[] vertices, Edge[] edges) {
    	this.V = vertices.length;
    	this.E = edges.length;
        this.vertices = vertices;
        this.edges = edges;
    }
    
    public Graph(Vertex[] vertices) {
    	this.V = vertices.length;
        this.vertices = vertices;
    }
    
    public Graph(int V) {
    	this.V = V;
        this.vertices = new Vertex[V];
    }
    
    public void completeDigraph() {
    	this.E = this.V * (this.V - 1); 
    	this.edges = new Edge[E];

    	int counter = 0;
    	for (Vertex u: vertices) {
        	for (Vertex v: vertices) {
        		if (u.number != v.number) {
        			int weight = 1 + (int) Math.round( (10-1) * Math.random() );
            		Edge e = new Edge(u, v, weight);
            		edges[counter] = e;
            		System.out.println(e);
            		counter++;
        		}
        	}
    	}
		System.out.println();
    	
    }
	
}
