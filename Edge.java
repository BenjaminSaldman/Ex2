package Ex2;

import Ex2.src.api.NodeData;

public class Edge implements Ex2.src.api.EdgeData {
    private NodeData src,dst;
    private double weight;
    public Edge(NodeData src, NodeData dst, double weight)
    {
        this.src=src;
        this.dst=dst;
        this.weight=weight;
    }
    @Override
    public int getSrc() {
        return 0;
    }

    @Override
    public int getDest() {
        return 0;
    }

    @Override
    public double getWeight() {
        return 0;
    }

    @Override
    public String getInfo() {
        return null;
    }

    @Override
    public void setInfo(String s) {

    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
