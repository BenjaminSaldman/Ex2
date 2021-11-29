package Ex2;

import Ex2.src.api.DirectedWeightedGraph;
import Ex2.src.api.EdgeData;
import Ex2.src.api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

public class DirectedGraph implements DirectedWeightedGraph {
    private HashMap<Integer,HashMap<Integer,NodeData>> edges;
    static int count=0;
    private int self;
    public DirectedGraph()
    {
//        edges=new HashMap<Integer,new HashMap()>();
        self=count;
    }
    @Override
    public NodeData getNode(int key) {
        return edges.get(key).get(key);
    }

    @Override
    public EdgeData getEdge(int src, int dest) {
        return null;
    }

    @Override
    public void addNode(NodeData n) {
        nodes.put(n.getKey(),n);
    }

    @Override
    public void connect(int src, int dest, double w) {
        edges.put(count,new Edge(nodes.get(src),nodes.get(dest),w));
        self=count;
        count++;
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        return null;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        return null;
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
