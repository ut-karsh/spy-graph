///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             GraphNode.java
// Semester:         CS 367 Summer 2017
//
// Author:           Utkarsh Maheshwari umaheshwari@wisc.edu
// CS Login:         maheshwari
// Lecturer's Name:  Meenakshi Syamkumar
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Jared Akers
// Email:            jakers@wisc.edu
// CS Login:         akers
// Lecturer's Name:  Meenakshi Syamkumar
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
import java.util.*;

/**
 * GraphNode class maintains a vertex name and a list of adjacent vertices which
 * stores the neighbors in a sorted list.
 */

public class GraphNode implements Comparable<GraphNode> {
    private String nodeName;
    private boolean spycam;
    private List<Neighbor> neighbors;
    private boolean visited;
    
    /** 
     * Constructs a GraphNode with the vertex name and empty neighbors list.
     * 
     * @param name of the vertex, which needs to be unique
     */
    public GraphNode(String name) {
    	nodeName = name;
    	spycam = false;
    	neighbors = new ArrayList<Neighbor>();
    	visited = false;
    }

    /** 
     * Returns the name of the vertex. 
     *
     * @return the name of this GraphNode
     */
    public String getNodeName() {
    	return nodeName;
    }

    /** 
     * Returns the neigbors of the vertex. 
     *
     * @return the neighbors of this GraphNode
     */
    public List<Neighbor> getNeighbors() {
    	return neighbors;
    }

    /**
     * Sets the visited flag of this vertex.
     *
     * @param flagVal boolean value used to set the flag
     */
    public void setVisited(boolean flagVal) {
    	visited = flagVal;
    }
    
    /**
     * Gets the visited flag of this vertex.
     *
     * @return visited boolean value 
     */
    public boolean getVisited() {
    	return visited;
    }

    /** 
     * Return the results of comparing this node's name to the other node's 
     * name. 
     * 
     * @param otherNode GraphNode instance whose vertex name is required for 
     * comparison
     * @return negative value or 0 or positive value
     */
    public int compareTo(GraphNode otherNode) {
    	
    	return nodeName.compareTo(otherNode.getNodeName());
    }


    /** 
     * Adds a new neighbor and maintains sorted order of neighbors by neighbor 
     * name.
     *
     * @param neighbor an adjacent node 
     * @param cost to move to that node (from this node)
     */
    public void addNeighbor(GraphNode neighbor, int cost) {
    	
    	Neighbor newNeighbor = new Neighbor(cost, neighbor);
    	neighbors.add(newNeighbor);
    	neighbors.sort(null);
    }

    /** 
     * Prints a list of neighbors of this GraphNode and the cost of the edge to 
     * them. 
     * Example:
     * "1 b"
     * "4 c"
     * Note: Quotes are given here for clarification, do not print the quotes.
     * @throws NotNeighborException 
     */
    public void displayCostToEachNeighbor() { //TODO remove throws and see what to do
    	
    	Iterator<Neighbor> neighborItr = neighbors.iterator();
    	
    	while (neighborItr.hasNext()) {
    		Neighbor neighbor = neighborItr.next();
    		String neighborName = neighbor.getNeighborNode().getNodeName();
    		int cost = neighbor.getCost();
        	
    		System.out.println(neighborName +" "+ cost);
    	}
    }

    /** 
     * Returns cost to reach the neighbor.
     *
     * @param neighborName name of neighbor
     * @return cost to neighborName
     * @throws NotNeighborException if neighborName is not a neighbor
     */
    public int getCostTo(String neighborName) throws NotNeighborException {
    		
    	Iterator<Neighbor> itr = neighbors.iterator();
    	
    	while(itr.hasNext()){
    		Neighbor neighbor = itr.next();
    		GraphNode tmp = neighbor.getNeighborNode();
    		if (tmp.toString().equals(neighborName)){
    			return neighbor.getCost();
    		}
    	}
    	throw new NotNeighborException();
    	}
    
    /** 
     * Returns the GraphNode associated with name that is a neighbor of the 
     * current node.
     *
     * @param neighborName name of neighbor
     * @return the GraphNode associated with name that is neighbor of this node
     * @throws NotNeighborException if neighborName is not a neighbor
     */
    public GraphNode getNeighbor(String neighborName) throws NotNeighborException {
    	Iterator<Neighbor> itr = neighbors.iterator();
    	
    	while(itr.hasNext()){
    		GraphNode tmp = itr.next().getNeighborNode();
    		if (tmp.toString().equals(neighborName)){
    			return tmp;
    		}
    	}
    	throw new NotNeighborException();
    }

    /** 
     * Returns an iterator that can be used to find neighbor names
     * of this GraphNode.
     *
     * @return iterator of String node labels
     */
    public Iterator<String> getNeighborNames() {
    	
    	List<String> neighNames = new ArrayList<String>();
    	Iterator<Neighbor> itr = neighbors.iterator();
    	
    	while (itr.hasNext()){
    		neighNames.add(itr.next().toString());
    	}
    	
    	return neighNames.iterator();
    }

    /** 
     * Sets/unsets spycam at this node.
     *
     * @param cam indicates whether the node now has a spycam
     */
    public void setSpycam(boolean cam) {
    	spycam = cam;
    }

    /** 
     * Returns information about spycam presense in this node.
     *
     * @return true if the GraphNode has a spycam
     */
    public boolean getSpycam() {
    	return spycam;
    }

    /** 
     * Returns true if this node name is a neighbor of current node.
     *
     * @param neighborName name of neighbor
     * @return true if the node is an adjacent neighbor
     */
    public boolean isNeighbor(String neighborName) {
    	
    	Iterator<String> itr = getNeighborNames();
    	
    	while(itr.hasNext()){
    		
    		if(itr.next().equals(neighborName)){
    			return true;
    		}
    	}
    	return false;
    }

    /** 
     * Returns the name of this node.
     *
     * @return name of node
     */
    public String toString() {
    	return nodeName;
    }
    
}
