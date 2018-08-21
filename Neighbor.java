///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Neighbor.java
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Neighbor class represents an edge between two nodes and the associated cost.
 */

public class Neighbor implements Comparable<Neighbor> {
	private int cost;
	private GraphNode neighbor;

	/** 
	 * A neighbor is added to an existing GraphNode by creating an instance 
	 * of Neighbor that stores the neighbor and the cost to reach that 
	 * neighbor.
	 * 
	 * @param cost of the edge
	 * @param neighbor GraphNode
	 */
	public Neighbor(int cost, GraphNode neighbor) {
		this.cost = cost;
		this.neighbor = neighbor;
	}

	/** 
	 * Returns the cost of the edge. 
	 *
	 * @return the cost of this edge.
	 */
	public int getCost() {
		return cost;
	}

	/** 
	 * Returns the neigbor on the other end of the edge. 
	 *
	 * @return the neighbor
	 */
	public GraphNode getNeighborNode() {
		return neighbor;
	}

	/** 
	 * Return the results of comparing this node's neighbor name to the other 
	 * node's neighbor name. Allows List of Neighbors to be sorted. 
	 * Hint: Read the java docs for String class carefully 
	 *
	 * @param otherNode Neighbor instance whose Graphnode needs to be compared.
	 * @return negative value or 0 or positive value
	 */
	public int compareTo(Neighbor otherNode) {
		// Store otherNode's GraphNode's name field 
		String otherNodeString = otherNode.getNeighborNode().toString();

		// Compare current node's name to otherNode's name
		return neighbor.toString().compareTo(otherNodeString);
	}

	/**
	 * Returns a String representation of this Neighbor.
	 * The String that is returned shows an arrow (with the cost in the middle) 
	 * and then the Neighbor node's name. 
	 *
	 * Example:  
	 * " --1--> b"
	 * indicates a cost of 1 to get to node b
	 * Note: Quotes are given here for clarification, do not print the quotes.
	 *
	 * @return String representation
	 */
	public String toString() {
		String nodeString = (" --" + cost + "--> " + neighbor.getNodeName());
		return nodeString;
	}
}
