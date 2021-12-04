package Ex2.src.api;

import Ex2.src.api.GeoLocation;

public class Node implements Ex2.src.api.NodeData{
    static int tag=0;
    private int personalTag;
    private int ID;
    private double weight;
    private GeoLocation location;
    private String Info;
    public Node(double x, double y, double z, int ID)
    {
        location=new Location(x,y,z);
        this.ID=ID;
        personalTag=tag;
        weight=0;
        tag++;
        Info="ID: "+ID+" The Location of this node is in: "+location.toString()+ "Personal tag: "+personalTag;
    }
    public Node (NodeData n)
    {
        this.personalTag=n.getTag();
        this.ID=n.getKey();
        this.weight=n.getWeight();
        this.Info=n.getInfo();
        if(n.getLocation()!=null)
            this.location=new Location(n.getLocation());
        else
            this.location=null;

    }

    @Override
    public int getKey() {
        return ID;
    }

    @Override
    public GeoLocation getLocation() {
        return new Location(location.x(),location.y(),location.z());
    }

    @Override
    public void setLocation(GeoLocation p) {
        this.location=new Location(p.x(),p.y(),p.z());
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public void setWeight(double w) {
        weight=w;
    }

    @Override
    public String getInfo() {
        return Info;
    }

    @Override
    public void setInfo(String s) {
        Info=s;
    }

    @Override
    public int getTag() {
        return tag;
    }

    @Override
    public void setTag(int t) {
        tag=t;
    }
}