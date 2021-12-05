package Ex2.src.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Test {
    public static void main(String[] args)
    {
        DirectedWeightedGraph g=new DirectedGraph();
        try {
            GsonBuilder Gbuilde = new GsonBuilder();
            Gbuilde.registerTypeAdapter(DirectedGraph.class, new fromJson());
            Gson gson = Gbuilde.create();
            FileReader fr = new FileReader("G2.json");
            g=gson.fromJson(fr,DirectedGraph.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }
}
