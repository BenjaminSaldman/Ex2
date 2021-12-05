package Ex2.src.api;
import Ex2.src.api.NodeData;

public class Edge implements Ex2.src.api.EdgeData {
    private int src,dst;
    private double weight;
    private String info;
    public Edge(int src, int dst, double weight)
    {
        this.src=src;
        this.dst=dst;
        this.weight=weight;
        info="Src: "+src +"Dest: "+dst+" "+"Weight: "+weight;
    }
    @Override
    public int getSrc() {
        return src;
    }

    @Override
    public int getDest() {
        return dst;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getInfo() {
        return info ;
    }

    @Override
    public void setInfo(String s) {
        info=s;
    }

    @Override
    public int getTag() {
        return 0;
    }

    @Override
    public void setTag(int t) {

    }
}
