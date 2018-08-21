///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  Game.java
// File:             SpyGraph.java
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
 * Stores all vertexes as a list of GraphNodes.  Provides necessary graph operations as
 * need by the SpyGame class.
 */


public class SpyGraph implements Iterable<GraphNode> {

	private List<GraphNode> vlist;
	private List<Neighbor> path;

	/**
	 * Initializes an empty list of GraphNode objects
	 */
	public SpyGraph(){
		vlist = new ArrayList<GraphNode>();
		path = new ArrayList<Neighbor>();
	}

	/**
	 * Add a vertex with this label to the list of vertexes.
	 * No duplicate vertex names are allowed.
	 * @param name The name of the new GraphNode to create and add to the list.
	 */
	public void addGraphNode(String name){

		for (int i=0; i<vlist.size(); i++)
			if(vlist.get(i).getNodeName().equals(name))
				return; // Does not proceed if node already exists on graph.

		GraphNode newVertex = new GraphNode(name);
		vlist.add(newVertex);
	}

	/**
	 * Adds v2 as a neighbor of v1 and adds v1 as a neighbor of v2.
	 * Also sets the cost for each neighbor pair.
	 *   
	 * @param v1name The name of the first vertex of this edge
	 * @param v2name The name of second vertex of this edge
	 * @param cost The cost of traveling to this edge
	 * @throws IllegalArgumentException if the names are the same
	 */
	public void addEdge(String v1name, String v2name, int cost) 
			throws IllegalArgumentException {

		if(v1name.equals(v2name))
			throw new IllegalArgumentException();

		// Adding v2 as neighbor of v1
		GraphNode v2 = getNodeFromName(v2name);
		GraphNode v1 = getNodeFromName(v1name);
		v1.addNeighbor(v2, cost);

		// Adding v1 as neighbor of v2
		v2.addNeighbor(v1, cost);
	}

	/**
	 * Return an iterator through all nodes in the SpyGraph
	 * @return iterator through all nodes in alphabetical order.
	 */
	public Iterator<GraphNode> iterator() {
		return vlist.iterator();
	}

	/**
	 * Return Breadth First Search list of nodes on path 
	 * from one Node to another.
	 * @param start First node in BFS traversal
	 * @param end Last node (match node) in BFS traversal
	 * @return The BFS traversal from start to end node.
	 * @throws NotNeighborException 
	 */

	public List<Neighbor> BFS( String start, String end ) {

		/* path: variable which eventually stores path from 'start' to 'end'. */ 
		path = new ArrayList<Neighbor>();
		/* List of Neighbor elements used to keep track of __ vertices of path*/  
		List<Neighbor> parents = new ArrayList<Neighbor>();

		// Setting all GraphNodes to unvisited.
		for(int i=0; i<vlist.size(); i++)
			vlist.get(i).setVisited(false);

		// Getting Neighbor instance associated with @param 'start'.
		List<Neighbor> startNeighbors = getNodeFromName(start).getNeighbors();

		Iterator<Neighbor> itr = startNeighbors.iterator();

		while(itr.hasNext()){
			Iterator<Neighbor> parIter = itr.next().getNeighborNode().getNeighbors().iterator();
			while(parIter.hasNext() && parents.isEmpty()){
				Neighbor tmp = parIter.next();
				if(tmp.getNeighborNode().getNodeName().equals(start)){
					parents.add(tmp);
				}
			}
		}

		// Calling helper method.
		BFS(parents, getNodeFromName(end));

		return path;

	}
	/** Does a Breadth First Search of @param end in the SpyGraph and if found,
	 * returns the path from the beginning node in @param parents to end,
	 * found during the Breadth First Search traversal. 
	 * 
	 * @param parents: Initially contains the Neighbor instance of beginning node.
	 * @param end: Destination node, the route to which is to be found.
	 * @return path from beginning node to end node.
	 */
	private int BFS (List<Neighbor> parents, GraphNode end) {
		/** List of successors (Neighbors) of 'parents', used to keep track of
		 node of respective parent node and hence helps keeps track of path**/
		List<Neighbor> children = new ArrayList<Neighbor>();

		//Runs till all nodes in the same level are visited.
		for(int i=0; i<parents.size(); i++) {

			// Stores GraphNode of Neighbor instance at pos i.  
			GraphNode nextParent = parents.get(i).getNeighborNode();

			// If statement prevents redundancy, improves efficiency.
			if (! nextParent.getVisited()) {

				nextParent.setVisited(true);

				/** Below block of code checks Neighbor list of nextParent to
				 * see if end node is found. If not, it adds the Neighbors to
				 * list 'children' to be used as next set of parents (in the
				 * next recursive call).
				 */
				for(int j=0; j<nextParent.getNeighbors().size(); j++) {

					// Gets Neighbor instance stored at pos j.
					Neighbor neighbor = nextParent.getNeighbors().get(j);

					// Prevents parent from being added as child.
					if(! neighbor.getNeighborNode().getVisited()) {

						/* If destination node is found, then its Neighbor
						 * instance is added to 'path' and the index of its
						 * parent is returned to previous recursive call.
						 */
						if(neighbor.getNeighborNode().equals(end)) {

							path.add(0, neighbor);

							return i;
						}

						/* If end node is not found, the neighbors are added
						 * to children list, which becomes parents list for
						 * the next recursive call.
						 */
						else
							//	if(!children.contains(childNeighbor))
							children.add(neighbor);
					}
				}
			}
		}

		if(! children.isEmpty()) {
			// index is the index of parent of previous node in path. 
			int index = BFS(children, end);

			// Adding node belonging to returned index, thus building path.
			// 'children' because it is the parent list of next recursive call. 
			if (index>=0) {
				path.add(0, children.get(index));


				// Below code gets index of parent of latest node added to path.
				for(int i=0; i<parents.size(); i++) {

					GraphNode parent = parents.get(i).getNeighborNode();

					for(int j=0; j<parent.getNeighbors().size(); j++) {

						Neighbor childNeighbor = parent.getNeighbors().get(j);

						if(childNeighbor.equals(path.get(0))) {
							return i;
						}
					}
				}
			}
		}
		return -1;
	}

	/**
	 * @param name Name corresponding to node to be returned
	 * @return GraphNode associated with name, null if no such node exists
	 */
	public GraphNode getNodeFromName(String name){
		for ( GraphNode n : vlist ) {
			if (n.getNodeName().equalsIgnoreCase(name))
				return n;
		}
		return null;
	}


	/**
	 * Return Depth First Search list of nodes on path 
	 * from one Node to another.
	 * @param start First node in DFS traversal
	 * @param end Last node (match node) in DFS traversal
	 * @return The DFS traversal from start to end node.
	 */
	public List<Neighbor> DFS(String start, String end) {

		// Turn the given Strings into their corresponding nodes
		GraphNode startNode = getNodeFromName(start);
		GraphNode endNode = getNodeFromName(end);

		// Reset the pathNode's list 
		path = new ArrayList<Neighbor>();

		Iterator<GraphNode> itr = vlist.iterator();

		// Set all nodes' visited field to false
		while(itr.hasNext()){
			itr.next().setVisited(false);
		}

		// Call recursive helper method
		if(startNode != null && endNode != null) {
			// Helper method will update path
			DFS(endNode, startNode);
		}

		return path;
	}

	/**
	 * Helper method
	 * Method overloads other DfS method and uses recursion to plot a path to
	 * the endNode using a depth-first search.
	 * @param endNode Node to traverse to
	 * @param node Node that will be checked  to see if it is endNode
	 * @return Return true if endNode equals the current node "node" 
	 */
	private boolean DFS(GraphNode endNode, GraphNode node){

		// Set node to visited, prevents multiple calls to the same node
		node.setVisited(true);

		Iterator<Neighbor> itr = node.getNeighbors().iterator();

		// Set node to visited, prevents multiple calls to the same node
		while(itr.hasNext()){
			Neighbor successor = itr.next();

			// If the node was visited skip it 
			if(! successor.getNeighborNode().getVisited()){

				// If node is the "endNode": add to list and end recursion
				if(successor.getNeighborNode().equals(endNode)){
					path.add(successor);
					return true;
				}

				// Continue recursion and add node to list
				if(DFS(endNode, successor.getNeighborNode())){
					path.add(0,successor);
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * OPTIONAL: Students are not required to implement Dijkstra's ALGORITHM
	 *
	 * Return Dijkstra's shortest path list of nodes on path 
	 * from one Node to another.
	 * @param start First node in path
	 * @param end Last node (match node) in path
	 * @return The shortest cost path from start to end node.
	 */
	public List<Neighbor> Dijkstra(String start, String end){

		// TODO: implement Dijkstra's shortest path algorithm
		// may need and create a companion method

		return new ArrayList<Neighbor>();
	}


	/**
	 * DO NOT EDIT THIS METHOD 
	 * @return a random node from this graph
	 */
	public GraphNode getRandomNode() {
		if (vlist.size() <= 0 ) {
			System.out.println("Must have nodes in the graph before randomly choosing one.");
			return null;
		}
		int randomNodeIndex = Game.RNG.nextInt(vlist.size());
		return vlist.get(randomNodeIndex);
	}

}
