///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             Spy.java
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
 * The Spy who should be found by the Game Player 
 *
 */
public class Spy {
    private GraphNode location;
    private int move;
    private int reveal;
    
    /**
     * @param g Graph of current game
     * @param reveal - number of steps after which the spy reveals himself
     */
    public Spy(SpyGraph g, int reveal){
        this.location = g.getRandomNode();
        this.reveal = reveal;
        this.move = 0;
    }

    /**
     * Prints the location of the spy if specified by reveal
     * Return false if spy has no moves to make
     * @param playerLoc - current location of the player
     * @return true if spy moves.
     */
    public boolean move(String playerLoc){

        // FIND POSSIBLE MOVES
        List<GraphNode> possibleMoves = new ArrayList<GraphNode>();
        List<Neighbor> neighbors = location.getNeighbors();
        for ( Neighbor n : neighbors ) {
            GraphNode gn = n.getNeighborNode();
            // NO SPYCAM AND NO PLAYER HERE
            if ( ! gn.getSpycam() && ! gn.getNodeName().equals(playerLoc)) {
                possibleMoves.add(gn);
                //System.out.println("added node "+gn.getNodeName()+" to possible moves for spy");
            }    else {
                //System.out.println("not a neighbor of " + gn.getNodeName());
            }
        }
        
        if ( possibleMoves.size() <= 0 ) 
            return false;
                
        //move spy to a random neighbor that does not have player or spycam
        GraphNode newLoc = possibleMoves.get(Game.RNG.nextInt(possibleMoves.size()));
        
        //if the spy should reveal location, print location
        move = (move+1)%reveal;

        if (move==0) System.out.println("Spy's location is " + newLoc.getNodeName());
        location = newLoc;
        return true;
        
    }
    
    /**
     * @return location of spy
     */
    public String revealLocation() { 
        return location.getNodeName();
    }


    /**
     * @param locationName name of potential node
     * @return true if Spy is at location specified by location name
     */
    public boolean isAt(String locationName) {
        return location.getNodeName().equals(locationName);
    }
    
    /** @return the name of the node that the spy is at */
    public String getLocationName() {
        return location.getNodeName();
    }
    
    
}
