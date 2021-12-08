package Ex2.src.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.*;
import java.util.Iterator;

public class Test {
    public static void main(String[] args)
    {
        DirectedWeightedGraph g=new DirectedGraph();
        try {
            GsonBuilder Gbuilde = new GsonBuilder();
            Gbuilde.registerTypeAdapter(DirectedWeightedGraph.class, new fromJson());
            Gson gson = Gbuilde.create();
            FileReader fr = new FileReader("C:\\Users\\binya\\IdeaProjects\\Ex2\\src\\Ex2\\src\\api\\G1.json");
            g=gson.fromJson(fr,DirectedWeightedGraph.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        System.out.println(g.toString());
        try {
            g=JsonFiler.ReadFromJson("C:\\Users\\binya\\IdeaProjects\\Ex2\\src\\Ex2\\src\\api\\G1.json");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Iterator<EdgeData>e1=g.edgeIter();
        while (e1.hasNext())
            System.out.println(e1.next().toString());

        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(g);
        JsonObject finalOutput=JsonFiler.WriteToJson(g);
        try (FileWriter file = new FileWriter("T.json")) {
            file.write(gson.toJson(finalOutput));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

