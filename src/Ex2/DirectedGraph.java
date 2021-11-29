package Ex2;

import Ex2.src.api.DirectedWeightedGraph;
import Ex2.src.api.EdgeData;
import Ex2.src.api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

public class DirectedGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    static int count = 0;
    private int MC;
    private int self;

    public DirectedGraph() {
        edges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        nodes = new HashMap<Integer, NodeData>();
        self = count;
        MC = 0;
    }

    @Override
    public NodeData getNode(int key) {
        return nodes.get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return edges.get(src).get(dest);
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(), n);
        HashMap<Integer, EdgeData> neigbhors = new HashMap<Integer, EdgeData>();
        edges.put(n.getKey(), neigbhors);
    }

    @Override
    public void connect(int src, int dest, double w) {
        if (src != dest) {
            if (this.getNode(src) != null && this.getNode(dest) != null) {
                if (edges.get(src).get(dest) == null) {
                    EdgeData e = new Edge(src, dest, w);
                    edges.get(src).put(dest, e);
                    count++;
                    this.MC++;
                } else if (edges.get(src).get(dest).getWeight() != w) {
                    EdgeData e = new Edge(src, dest, w);
                    edges.get(src).replace(dest, e);
                    MC++;
                }
            }
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return this.nodes.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        HashMap<Integer,EdgeData>k= (HashMap<Integer, EdgeData>) edges.values();
        return k.values().iterator();
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {

        return edges.get(node_id).values().iterator();
    }

    @Override
    public NodeData removeNode(int key) {
        return null;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        return null;
    }

    @Override
    public int nodeSize() {
        return 0;
    }

    @Override
    public int edgeSize() {
        return 0;
    }

    @Override
    public int getMC() {
        return 0;
    }
}
