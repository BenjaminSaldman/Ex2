# Ex2 Direceted Graphs Algorithms
This program represents a directed graph using data structures and enables graphical display to the user.
### The capabilities that the program supports are:
*Create a directed graph.
*Run algorithms on directed graph.
*Save a graph into json file.
*Load a graph from json file.
*Graphical display of graph and algorithms.
## Interfaces
* DirectedWeightedGraph 
* DirectedWeightedGraphAlgorithms
* EdgeData
* NodeData
* GeoLocation
## The classes of the project
### DirectedGraph
This class represent a directed graph and implement the methods of DirectedWeightedGraph
##### Class Objects:
* HashMap<Integer, NodeData> nodes - graph vertices
* HashMap<Integer, HashMap<Integer, EdgeData>> edges - graph edges
*  private int count - Edges counter
*  private int MC - graph changes counter.
##### Tests:
* Provided with the other tests classes, simple tests that check the validity of each function.
### Node
This class represent a vertice of graph and implement the methods of NodeData.
##### Class Objects:
* int tag; - node tag, used in BFS, Dijkstra.
* int ID; - personal node id.
* double weight; 
* GeoLocation location; - node location in the space
* String Info; - info string of the node.
##### Tests:
* Provided with the other tests classes, simple tests that check the validity of each function.
### EdgeData
This class represent an edge in directed graph and implement the methods of EdgeData.
##### Class Objects:
* int src,dst - Src and dst node (id's).
* double weight - Weight of the node.
* String info -Representative String.
##### Tests:
* Provided with the other tests classes, simple tests that check the validity of each function.
### Location
This class represent a location in the space (x,y,z) and implement the methods of GeoLocation.
##### Class Objects:
* double x - x coordinate of the object.
* double y - y coordinate of the object.
* double z - z coordinate of the object.
##### Tests:
* Provided with the other tests classes, simple tests that check the validity of each function.
