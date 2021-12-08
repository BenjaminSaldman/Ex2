package Ex2.src.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class GraphAlgo implements DirectedWeightedGraphAlgorithms{
    private DirectedWeightedGraph g;
    public GraphAlgo(){
        this.g=new DirectedGraph();
    }
    @Override
    public void init(DirectedWeightedGraph g) {
        this.g=g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return this.g;
    }

    @Override
    public DirectedWeightedGraph copy() {
        DirectedWeightedGraph g1=new DirectedGraph(this.g);
        return g1;
    }
    private void InitNotVisited()
    {
        Iterator<NodeData>e=this.g.nodeIter();
        while (e.hasNext())
        {
            NodeData n=e.next();
            n.setTag(0);
        }
    }

    @Override
    public boolean isConnected() {
        if(this.g==null)
            return true;
        if(g.nodeSize()<=1)
            return true;
        if(g.nodeSize()>g.edgeSize())
            return false;
    return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        double ave=0;
        int n=this.g.nodeSize();
        double min=Double.MAX_VALUE;
        NodeData ans=null;
        Iterator<NodeData>e=this.g.nodeIter();

        while (e.hasNext())
        {
            NodeData curr=e.next();
            Iterator<NodeData>e2=this.g.nodeIter();
            while(e2.hasNext())
            {
                NodeData next=e2.next();
                ave+=(shortestPathDist(curr.getKey(),next.getKey())/n);
            }
            if(ave<min)
            {
                min=ave;
                ans=curr;
            }

        }
        return ans;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        if(cities.isEmpty())
            return null;
        List<NodeData>ans =new LinkedList<NodeData>();
        double min=Double.MAX_VALUE;
        List<NodeData>l=new LinkedList<NodeData>();
        NodeData start= cities.get(0);
        cities.remove(0);
        int curr=0;
        ans.add(start);
        while(!cities.isEmpty())
        {
            for(int i=0;i<cities.size();i++)
            {
                if(shortestPathDist(start.getKey(), cities.get(i).getKey())<min){
                    min=shortestPathDist(start.getKey(), cities.get(i).getKey());
                    l=shortestPath(start.getKey(),cities.get(i).getKey());
                    curr=i;
                }
            }
            start=cities.get(curr);
            min=Double.MAX_VALUE;
            for (int i=0;i<l.size();i++)
            {
                ans.add(l.get(i));
                cities.remove(l.get(i));
            }

        }
        return ans;
    }

    @Override
    public boolean save(String file) {
        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(g);
        JsonObject finalOutput=JsonFiler.WriteToJson(g);
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
        try{
            this.g= JsonFiler.ReadFromJson(file);
            return true;
        }catch (IOException e){
            e.printStackTrace();
            return false;

        }
    }

}
