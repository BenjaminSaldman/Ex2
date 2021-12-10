# Ex2 Direceted Graphs Algorithms
This program represents a directed graph using data structures and enables graphical display to the user.
### The capabilities that the program supports are:
* Create a directed graph.
* Run algorithms on directed graph.
* Save a graph into json file.
* Load a graph from json file.
* Graphical display of graph and algorithms.
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
### GraphAlgo
This class implement the methods of DirectedWeightedGraphAlgorithms.
##### Class Objects:
* DirectedWeightedGraph g - The graph we run the methods on.
##### Methods: 
 * private void Dijkstra(int src) - updating nodes weight and parent by Dijkstra algorithm from source.
 * private void InitNotVisited(DirectedWeightedGraph g) - Initialize all graph vertices tag to 0 ("Not Visited") - For BFS.
 * private void BFS(int start, DirectedWeightedGraph g) - Updating the graph vertices by BFS order.
 * private DirectedWeightedGraph transpose(DirectedWeightedGraph graph) - Creating the transpose graph of given graph.
 * public boolean load(String file) - Load a graph from json file.
 * public boolean save(String file) - Save a graph into json file.
 * public List<NodeData> tsp(List<NodeData> cities) - return list that contains the best way to get to all the cities.
 * public NodeData center() - Return the node that has the minimal distance from all the other vertices.
 * public List<NodeData> shortestPath(int src, int dest) - Return the shortest path between 2 nodes.
 * public double shortestPathDist(int src, int dest) - Return the distance of the shortest paths.
 * public boolean isConnected() - Return true if the graph is strongly connected.
##### Tests:
  All the methods will be tested by the same way: we will create a directed graph that is known to us if he is connected, what is shortest path between 2 vertices
  and what is the center of the graph, after that we will run the algorithms on this graph and check if the results we got match ours.
  Testing Save/Load: Those methods can be tested simply: Save: run the save algorithm on the given graph and check if the json file is valid. Load: run the load function and       check if the graph it loaded is the real graph that the file represent.
### JsonFiler
  This class is responsible to read from json file and save a graph into json file, it containd 2 methods.
##### Methods: 
  * public static JsonObject WriteToJson(DirectedWeightedGraph graph) - This method gets a graph and return JsonObject that builds the object into json.
  * public static DirectedWeightedGraph ReadFromJson(String filename) - This method reads a graph from a json file.
##### Tests:
  Implemented in GraphAlgo tests.
### GUI_graph
 This class is running the GUI and responsible on the visualization of the algorithms and the graph.
##### Methods:
  * public GUI_graph(DirectedWeightedGraphAlgorithms AlgoGraph) - constructor - gets a graph and shows the graph on the screen.
  * public void actionPerformed(ActionEvent e) - listener to buttons.
##### Inner classes:
  * public class GraphP extends JPanel - shows the scaled graph on the screen.
## Libraries
  ### Project Libraries
  * gson-2.8.6 (1) (1).jar - Gson library.
  * json-simple-4.0.0.jar - Json simple library.
  
## Instructions
  ### Running the project on intellij
  Make sure to add the libraries - right click on the library -> add as library.
  Make sure to not change the location of the files.
  ### Running the GUI
  Open a folder, enter to this folder the graphs you want to load and the file 'Ex2.jar'.
  In the cmd run script: java -jar Ex2.jar <Your file name>.json.
  ### How to work with the GUI:
  After running the script a window will be opened.
  In the opened window, you will see the printed graph and menu.
  *To load a new graph into the window, enter the location of the json file on your computer into the text field 
  and press the button "Load".
  * To save the graph into a json file enter the file name you want into the text field and press the button "Save".
  * To see what is the shortest path between two nodes, enter the nodes ID into the text field by this format: ID1,ID2 and click on the "Paths" button.
  * To see what is the tsp, enter to the text field the ID's of the cities by this format: ID1,ID2,ID3... and click on "TSP" button.
  * To remove a node from the graph, enter the node id to the text field and click on "Remove Node ID".
  * To add a new node to the graph, enter the node details by this format: x,y,z,ID and click on "new node x,y,z,id".
  * To see what is the shortest distance between 2 nodes, enter the Id's of the nodes and click on "Dist".
  * To see the center of the graph, click on "center" menu and click on "center" again.
  * To see if the graph is connected, click on "connected" menu and select "connected" again.
  * To undo the changes on the graph, click on "Back" menu and select "back".
  
  
  


  
  
