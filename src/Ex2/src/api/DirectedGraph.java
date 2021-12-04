package Ex2.src.api;
import Ex2.src.api.DirectedWeightedGraph;
import Ex2.src.api.EdgeData;
import Ex2.src.api.NodeData;

import java.util.HashMap;
import java.util.Iterator;

public class DirectedGraph implements DirectedWeightedGraph {
    private HashMap<Integer, NodeData> nodes;
    private HashMap<Integer, HashMap<Integer, EdgeData>> edges;
    private int count ;
    private int MC;

    public DirectedGraph() {
        edges = new HashMap<Integer, HashMap<Integer, EdgeData>>();
        nodes = new HashMap<Integer, NodeData>();
        count=0;
        MC = 0;
    }
    public DirectedGraph(DirectedWeightedGraph graph){
        Iterator<NodeData>e=graph.nodeIter();
        while(e.hasNext())
        {
            NodeData temp=e.next();
            NodeData n=new Node(temp);
            this.addNode(n);
        }
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
        NodeData n=nodes.get(key);
        if(n!=null)
        {
           Iterator<NodeData> k=nodeIter();
           while(k.hasNext())
           {
               NodeData temp=k.next();
               if(edges.get(temp.getKey()).get(key)!=null)
                   this.removeEdge(temp.getKey(),key);
           }
           Iterator<EdgeData> e=edgeIter(key);
           while(e.hasNext())
           {
               EdgeData temp=e.next();
               this.removeEdge(key, temp.getDest());
               e=edgeIter(key);
           }
           edges.remove(key,edges.get(key));
           nodes.remove(key,n);
            MC++;
        }
        return n;
    }

    @Override
    public EdgeData removeEdge(int src, int dest) {
        EdgeData e=this.getEdge(src,dest);
        if(e!=null)
        {
            edges.get(src).remove(dest,e);
            count--;
            MC++;
        }
        return e;
    }

    @Override
    public int nodeSize() {
        return nodes.size();
    }

    @Override
    public int edgeSize() {
        return count;
    }

    @Override
    public int getMC() {
        return MC;
    }
}
