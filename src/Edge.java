
// A class to represent a weighted edge in graph
public class Edge {
	
	public String name;
	public int src, dest;
	public double weight;
    public Vertex u;
    public Vertex v;
    
    // copy constructor
    public Edge(Edge otherEdge) {
    	this.src = otherEdge.src;
    	this.dest = otherEdge.dest;
    	this.weight = otherEdge.weight;
    	this.u = otherEdge.u;
        this.v = otherEdge.v;
        
        this.name = otherEdge.u.name + "->" + otherEdge.v.name;
    }

    public Edge() {
    	this.src = 0;
    	this.dest = 0;
    	this.weight = 0;
        this.u = new Vertex(src);
        this.v = new Vertex(dest);
        
        this.name = this.u.name + "->" + this.v.name;
        
        this.u.outNeighbors.add(v);
        this.v.inNeighbors.add(u);
        
        this.u.outEdges.add(this); // add the new edge in the out edges of the source vertex
        this.v.inEdges.add(this); // add the new edge in the in edges of the destination vertex
    }
    
    public Edge(Vertex u, Vertex v, double weight, String name) {
    	this.src = u.number;
        this.dest = v.number;
        this.u = u;
        this.v = v;
        this.weight = weight;
        
        this.name = this.u.name + "->" + this.v.name;
        
        this.u.outNeighbors.add(v);
        this.v.inNeighbors.add(u);
        
        this.u.outEdges.add(this); // add the new edge in the out edges of the source vertex
        this.v.inEdges.add(this); // add the new edge in the in edges of the destination vertex
    }
    
    public Edge(Vertex u, Vertex v, double weight) {
    	this.src = u.number;
        this.dest = v.number;
        this.u = u;
        this.v = v;
        this.weight = weight;
        
        this.name = this.u.name + "->" + this.v.name;
        
        this.u.outNeighbors.add(v);
        this.v.inNeighbors.add(u);
        
        this.u.outEdges.add(this); // add the new edge in the out edges of the source vertex
        this.v.inEdges.add(this); // add the new edge in the in edges of the destination vertex
    }
    
    public void reverseEdge() {
    	Vertex temp = this.u;
    	this.u = this.v;
    	this.v = temp;
    }
    
    @Override
    public String toString() {
    	return name + ", weight: " + weight;
    }
    
};
