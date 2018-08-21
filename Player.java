///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Player.java
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
 * Represents a player in the Game.
 */
public class Player {
	String playerName;
	int budget;
	int numSpycams;
	GraphNode currNode;
	List<GraphNode> spycams;

	/**
	 * Constructs an instance of Player to track location and other information
	 * for the player.
	 */
	public Player(String name, int budget, int spycams, GraphNode startnode) {

		playerName = name;
		this.budget = budget;
		numSpycams = spycams;
		currNode = startnode;
		this.spycams = new ArrayList<GraphNode>();

	}

	/**
	 * Subtract the decrease amount from the budget.
	 * 
	 * @param dec amount to decrease the budget by
	 */
	public void decreaseBudget(int dec) {
		budget = budget - dec;
	}

	/**
	 * If there are no remaining spy cams to drop, display "Not enough spycams" 
	 * and return false. Otherwise:
	 *
	 * If there is not a spy cam at the player's current location:
	 *  display "Dropped a Spy cam at D" where D is the node name
	 *  drop a spy cam (here) D
	 *  decrement the remaining spy cam count if there was not already a spycam
	 *
	 * Note: Make sure to set the spycam on the GraphNode as well.
	 * 
	 * @return true if spycam is dropped successfully, false otherwise.
	 */
	public boolean dropSpycam() {

		if (numSpycams>0) { // Executes if there are enough spycams to drop.

			if (!getLocation().getSpycam()) { // Checks for existing spycam.

				System.out.println("Dropped a Spy cam at "+ getLocationName());
				// Declaring presence of spycam at current node.
				getLocation().setSpycam(true);
				spycams.add(currNode);

				numSpycams--;

				return true;
			}

			else { // Executes if spycam is present at current node.

				System.out.println("Already a Spy cam there");
				return true;
			}
		}
		else // Executes if numSpycams=0.
			System.out.println("Not enough spycams");
		return false;
	}

	/**
	 * Return the amount remaining in this player's budget.
	 *
	 * @return budget remaining
	 */
	public int getBudget() {
		return budget;
	}

	/**
	 * Returns the node where the player is currently located.
	 *
	 * @return currNode player's current node 
	 */
	public GraphNode getLocation() {
		return currNode;
	}

	/**
	 * Returns the name of the node where the player is currently located.
	 *
	 * @return node label for the current location of the player
	 */
	public String getLocationName() {
		return currNode.getNodeName();
	}

	/**
	 * Return the name of the player.
	 *
	 * @return name of player
	 */
	public String getName() {
		return playerName;
	}

	/**
	 * If pickupSpyCam is true, increment the number of spy cams remaining.
	 * Note: Make sure to unset the spycam on the GraphNode as well.
	 *
	 * @param: pickupSpyCam true if a spy cam was picked up. 
	 *         False means there was no spy cam
	 */
	public void getSpycamBack(boolean pickupSpyCam) {

		if(pickupSpyCam) {
			numSpycams++;
		}
	}

	/**
	 * Returns the number of spy cams available to drop.
	 *
	 * @return number of spycams remaining
	 */
	public int getSpycams() {
		return numSpycams;
	}

	/**
	 * Change the location of the player to the specified node. Moves to nodes 
	 * with a cost of one are not counted. (ie. it is "free" to "walk" to a 
	 * node). 
	 * If attempt to move to a node that is not a neighbor (nn) is made,
	 * displays the message: "<nn> is not a neighbor of your current location"; 
	 * and returns false.
	 * If there is no sufficient budget, then display 
	 * "Not enough money cost is <COST> budget is <BUDGET>" 
	 * Note: Quotes are given here for clarification, do not print the quotes.
	 * If the cost to neighbor is greater than 1, decrement budget by that 
	 * amount.         
	 *                 
	 * Note: Write a try-catch block for NotNeighborException, but you should
	 * do necessary checks such that this exception will never be thrown from
	 * the GraphNode functions that you are invoking.
	 * Hint: Carefully consider which function to call first to ensure that 
	 * an exception will never be thrown.
	 *                 
	 * @param name of the neighboring to move to
	 * @return true if the player successfully moves to this node
	 */                


    public boolean move(String name) {
    	
    	try{
    	
    	// Gets a list of the current node's neighbors and creates iterator.
    	Iterator<Neighbor> itr = currNode.getNeighbors().iterator();
    	
    	// Iterate through list of the current node's neighbors.
    	while(itr.hasNext()){
    		
    		// Store neighbor from list. 
    		Neighbor tmp = itr.next();
    	
    		// Check name of current node against param name. 
    		if(tmp.getNeighborNode().getNodeName().equals(name)){
    		
    			// Check for sufficient funds.
    			if(tmp.getCost() > budget){
    				System.out.println("Not enough money cost is "
    			 + tmp.getCost() + " budget is " + budget );
    				return false;
    			}
    			
    			/* If the cost to the node is 1 do not decrement 'moves' budget
    			    and update currNode. */
    			else if (tmp.getCost() == 1){
    				currNode = tmp.getNeighborNode();
    				return true;
    			}
    			
    			/* Case where cost to the node is less than budget,
    			   decrement budget and update currNode. */
    			else {
    				currNode = tmp.getNeighborNode();
    				budget = budget - tmp.getCost();
    				return true;
    			}
    		}
    	}
    	
    	/* No methods GraphNode methods used can throw exception, 
    	   so throw new exception. */
    	throw new NotNeighborException();
    	
    	/* Case where the param name is not a name of one of 
    	    the current GraphNode's neighbors. */
    	} catch(NotNeighborException excpt){
    		System.out.println(name + " is not a neighbor of your current location");
    		return false;
    	}
    }


	/**
	 * Check the node to see if there is a spy cam. If there is a spy cam at 
	 * that node, remove the spy cam from that node. Also, remove the spy cam 
	 * name from the Player's list of spy cam names. Otherwise, return false.
	 * Note: Make sure to unset the spycam on the GraphNode as well.
	 *
	 * @param node the player asked to remove a spy cam from.
	 * @return true if a spycam is retrieved
	 */
	public boolean pickupSpycam(GraphNode node) {

		if(node.getSpycam()) // Checks if @param node has spycam or not.
			for(int i=0; i<spycams.size(); i++) { 

				// Finding and removing @param node in 'spycams' list. 
				if(spycams.get(i).getNodeName().equals(node.getNodeName())) {
					spycams.remove(i);

					node.setSpycam(false);
					getSpycamBack(true); // Increments numSpycams.
					return true;
				}
			}
		// Executes if @param node does not have spycam.
		return false;

	}

	/**
	 * Display the names of the locations where Spy Cams were dropped (and are 
	 * still there). If there are spy cams at nodes named a, f, and g, the 
	 * display would show:
	 * "Spy cam at a"
	 * "Spy cam at f"
	 * "Spy cam at g"
	 * Note: Quotes are given here for clarification, do not print the quotes.
	 */
	public void printSpyCamLocations() {

		Iterator<GraphNode> itr = spycams.iterator();

		// Iterate through list of spycams
		while(itr.hasNext()){
			System.out.println("Spy cam at " + itr.next().getNodeName());
		}
	}

}
