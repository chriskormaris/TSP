import java.util.List;
import java.util.ArrayList;

// A class to represent a vertex in a graph
public class Vertex {
	
    public int number;
    public String name;
    
    public List<Vertex> inNeighbors;
    public List<Vertex> outNeighbors;
    public List<Edge> inEdges;
    public List<Edge> outEdges;
    
    // copy constructor
    public Vertex(Vertex otherVertex) {
    	this.number = otherVertex.number;
    	this.name = otherVertex.name;
    	
    	this.inNeighbors = new ArrayList<Vertex>();
    	this.outNeighbors = new ArrayList<Vertex>();
    	this.inEdges = new ArrayList<Edge>();
    	this.outEdges = new ArrayList<Edge>();
    	
    	for (int i=0; i<otherVertex.inNeighbors.size(); ++i) {
    		this.inNeighbors.add(otherVertex.inNeighbors.get(i));
    	}
    	
    	for (int i=0; i<otherVertex.outNeighbors.size(); ++i) {
    		this.outNeighbors.add(otherVertex.outNeighbors.get(i));
    	}
    	
    	for (int i=0; i<otherVertex.inEdges.size(); ++i) {
    		this.inEdges.add(otherVertex.inEdges.get(i));
    	}
    	
    	for (int i=0; i<otherVertex.outEdges.size(); ++i) {
    		this.outEdges.add(otherVertex.outEdges.get(i));
    	}
    	
    }
    
    public Vertex() {
    	this.number = 0;
    	this.inNeighbors = new ArrayList<Vertex>();
    	this.outNeighbors = new ArrayList<Vertex>();
    	this.inEdges = new ArrayList<Edge>();
    	this.outEdges = new ArrayList<Edge>();
    	
    	this.name = "0";
    }
    
    public Vertex(int number) {
    	this.number = number;
    	this.inNeighbors = new ArrayList<Vertex>();
    	this.outNeighbors = new ArrayList<Vertex>();
    	this.inEdges = new ArrayList<Edge>();
    	this.outEdges = new ArrayList<Edge>();
    	
    	this.name = String.valueOf(this.number);
    }
    
    public Vertex(int number, String name) {
    	this.number = number;
    	this.inNeighbors = new ArrayList<Vertex>();
    	this.outNeighbors = new ArrayList<Vertex>();
    	this.inEdges = new ArrayList<Edge>();
    	this.outEdges = new ArrayList<Edge>();
    	
    	this.name = name;
    }
    
    @Override
    public String toString() {
    	return name;
    }
    
}
    