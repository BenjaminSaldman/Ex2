package Ex2.src.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.*;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms {
    private DirectedWeightedGraph g;
    /**
     * Dijkstra Vertex node - contains the vertex,distance from the source, is visited and the id.
     */
    private class AlgoNode {
        NodeData n;
        double dist;
        int id;

        public AlgoNode(NodeData n) {
            this.n = n;
            this.dist = Double.MAX_VALUE;
            id = n.getKey();
        }
    }

    /**
     *
     * @param src
     * @param dest
     * @return map contains the nodes of the shortest path between src to dest, using Dijkstra algorithm.
     */
    private HashMap<Integer, AlgoNode> Dijkstra(AlgoNode src, AlgoNode dest) {
        HashMap<Integer, AlgoNode> ans = new HashMap<Integer, AlgoNode>(); //Shortest path from src to dest.
        HashMap<Integer, AlgoNode> dist = new HashMap<Integer, AlgoNode>(); //d[v] - all the sources map.
        //Priority queue that holds the min distance AlgoNode each time.
        PriorityQueue<AlgoNode> q = new PriorityQueue(new Comparator<AlgoNode>() {
            @Override
            public int compare(AlgoNode o1, AlgoNode o2) {
                return Double.compare(o1.dist, o2.dist);
            }
        });
        src.dist = 0; //Start point - distance =0.
        q.add(src);
        while (!(q.isEmpty())) {
            AlgoNode node = q.poll(); // Get the least distance node.
            node.n.setTag(1);   //Set visited to true.
            if (node.equals(dest)) { //Return the answer if the current node is the destination.
                return ans;
            }
            Iterator<EdgeData> e = this.g.edgeIter(node.id); //For every e-(u,v).
            while (e.hasNext()) {
                EdgeData edge = e.next();
                NodeData curr = this.g.getNode(edge.getDest()); //Check current destination.
                if (curr.getKey() == dest.id) { //If the current node is the id, check if he has shorter path to him.
                    double w = edge.getWeight() + node.dist;
                    if (w < dest.dist) {
                        dest.dist = w;
                        if (ans.containsKey(dest.id)) {
                            ans.replace(dest.id, node);
                        } else {
                            ans.put(dest.id, node);
                            q.add(dest);
                        }
                    }
                } else { //Else, we got other node.
                    AlgoNode algoNode;
                    if (!dist.containsKey(curr.getKey())) { //If we discovered a new node, add it to the sources.
                        algoNode = new AlgoNode(curr);
                        dist.put(algoNode.id, algoNode);
                    } else {
                        algoNode = dist.get(curr.getKey()); //else, get him from the map.
                    }
                    if (algoNode.n.getTag() == 0) { //If not visited the current node update his weight.
                        double w = edge.getWeight() + node.dist;
                        if (w < algoNode.dist) {
                            algoNode.dist = w;
                            if (ans.containsKey(algoNode.id)) {
                                ans.replace(algoNode.id, node);
                            } else {
                                ans.put(algoNode.id, node);
                                q.add(algoNode);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
    public GraphAlgo() {
        this.g = new DirectedGraph();
    }

    @Override
    public void init(DirectedWeightedGraph g) {
        this.g = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g1 = new DirectedGraph(this.g);
        return g1;
    }

    /**
     *
     * @param g
     * Initialize the nodes status to not visited.
     */
    private void InitNotVisited(DirectedWeightedGraph g) {
        Iterator<NodeData> e = g.nodeIter();
        while (e.hasNext()) {
            NodeData n = e.next();
            n.setTag(0);
        }
    }

    /**
     *
     * @return true if the graph is strongly connected.
     * Using BFS 2 times one on the original graph and the other to the transpose graph..
     */
    @Override
    public boolean isConnected() {
        if (this.g == null)
            return true;
        if (this.g.nodeSize() <= 1)
            return true;
        if (this.g.nodeSize() > this.g.edgeSize() + 1)
            return false;
        InitNotVisited(this.g);
        BFS(0, this.g);
        Iterator<NodeData> e = this.g.nodeIter();
        while (e.hasNext()) {
            NodeData n = e.next();
            if (n.getTag() == 0)
                return false;
        }
        DirectedWeightedGraph tr = transpose(this.g);
        InitNotVisited(tr);
        BFS(0, tr);
        e = tr.nodeIter();
        while (e.hasNext()) {
            NodeData n = e.next();
            if (n.getTag() == 0)
                return false;
        }
        return true;
    }

    /**
     *
     * @param start
     * @param g
     * BFS searches on given graph.
     */
    private void BFS(int start, DirectedWeightedGraph g) {
        Queue<NodeData> q = new LinkedList<NodeData>();
        NodeData n = g.getNode(start);
        q.add(n);
        n.setTag(1);
        while (!q.isEmpty()) {
            n = q.poll();
            Iterator<EdgeData> e = g.edgeIter(n.getKey());
            while (e.hasNext()) {
                EdgeData edge = e.next();
                if (g.getNode(edge.getDest()).getTag() != 1) {
                    g.getNode(edge.getDest()).setTag(1);
                    q.add(g.getNode(edge.getDest()));
                }
            }
        }
    }

    /**
     *
     * @param graph
     * @return the transpose graph.
     */
    private DirectedWeightedGraph transpose(DirectedWeightedGraph graph) {
        DirectedWeightedGraph tr = new DirectedGraph();
        Iterator<NodeData> e = graph.nodeIter();
        while (e.hasNext()) {
            tr.addNode(e.next());
        }
        e = tr.nodeIter();
        while (e.hasNext()) {
            NodeData n = e.next();
            Iterator<EdgeData> i = graph.edgeIter(n.getKey());
            while (i.hasNext()) {
                EdgeData ed = i.next();
                tr.connect(ed.getDest(), ed.getSrc(), ed.getWeight());
            }
        }
        return tr;
    }

    /**
     *
     * @param src - start node
     * @param dest - end (target) node
     * @return shortestPathDist between the source to the destination.
     */
    @Override
    public double shortestPathDist(int src, int dest) {
        if (src == dest)
            return 0;
        if (this.g.getNode(src) == null || this.g.getNode(dest) == null)
            return -1;
        InitNotVisited(this.g);
        AlgoNode a = new AlgoNode(g.getNode(src));
        AlgoNode b = new AlgoNode(g.getNode(dest));
        HashMap<Integer, AlgoNode> map = Dijkstra(a, b);
        if (map == null) {
            return -1;
        }

        return b.dist;
    }

    /**
     *
     * @param src - start node
     * @param dest - end (target) node
     * @return shortestPath between src to dest.
     */
    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        InitNotVisited(this.g);
        AlgoNode a = new AlgoNode(g.getNode(src));
        AlgoNode b = new AlgoNode(g.getNode(dest));
        HashMap<Integer, AlgoNode> map = Dijkstra(a, b);
        if (map == null) {
            return null;
        }
        AlgoNode aNode = b;
        NodeData node = aNode.n;
        LinkedList<NodeData> ans = new LinkedList<>();
        ans.add(node);
        while (node != this.g.getNode(src) && node != null) {
            node = map.get(aNode.id).n;
            ans.addFirst(node);
            aNode = map.get(aNode.id);
        }
        return ans;
    }

    /**
     *
     * @return the center of the graph.
     */
    @Override
    public NodeData center() {
        double ave = 0;
        double min = Double.MAX_VALUE;
        NodeData ans = null;
        Iterator<NodeData> e = this.g.nodeIter();
        double currMax=0;
        while (e.hasNext()) {
            NodeData curr = e.next();
            Iterator<NodeData> e2 = this.g.nodeIter();
            while (e2.hasNext()) {
                NodeData next = e2.next();
                ave = shortestPathDist(curr.getKey(), next.getKey());
                if(ave>currMax)
                {
                    currMax=ave;
                }
            }
            if (currMax < min) {
                min = currMax;
                ans = curr;
            }
            currMax=0;
        }
        return ans;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if (cities.isEmpty())
            return null;
        List<NodeData> ans = new LinkedList<NodeData>();
        for (int i = 0; i < cities.size() - 1; i++) {
            NodeData curr = cities.get(i);
            NodeData next = cities.get(i + 1);
            List<NodeData> l = shortestPath(curr.getKey(), next.getKey());
            for (NodeData n : l)
                ans.add(n);
        }

        return ans;
    }

    @Override
    public boolean save(String file) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(g);
        JsonObject finalOutput = JsonFiler.WriteToJson(g);
        try (FileWriter File = new FileWriter(file)) {
            File.write(gson.toJson(finalOutput));
            File.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean load(String file) {
        try {
            this.g = JsonFiler.ReadFromJson(file);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;

        }
    }


}
