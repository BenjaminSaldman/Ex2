package Ex2.src.api;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.Map;

import static Ex2.src.api.Node.tag;
import static java.awt.SystemColor.info;

public class fromJson implements JsonDeserializer<DirectedWeightedGraph>{
    @Override
    public DirectedWeightedGraph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObj= jsonElement.getAsJsonObject();
        DirectedWeightedGraph g=new DirectedGraph();
        JsonObject nodesjson =jsonObj.get("Nodes").getAsJsonObject();
        JsonObject edgejson = jsonObj.get("Edges").getAsJsonObject();
        for (Map.Entry<String,JsonElement> s: nodesjson.entrySet()) {
            JsonElement val=s.getValue();
            int key =val.getAsJsonObject().get("id").getAsInt();
            JsonObject Loc=val.getAsJsonObject().get("pos").getAsJsonObject();
            String[] pos=Loc.getAsJsonObject().get("pos").getAsString().split(",");
            double x =Double.parseDouble(pos[0]);
            double y =Double.parseDouble(pos[1]);
            double z =Double.parseDouble(pos[2]);
            GeoLocation location =new Location(x,y,z);
            NodeData n =new Node(location.x(), location.y(), location.z(), key);
            g.addNode(n);
        }
        for (Map.Entry<String,JsonElement> s: edgejson.entrySet()) {
            JsonObject val_edges=s.getValue().getAsJsonObject();
            for (Map.Entry<String,JsonElement> set: val_edges.entrySet()) {
                JsonElement edge=set.getValue();
                int src =edge.getAsJsonObject().get("src").getAsInt();
                int dest =edge.getAsJsonObject().get("dest").getAsInt();
                double weight =edge.getAsJsonObject().get("w").getAsDouble();
                g.connect(src,dest,weight);
            }

        }
        return g;

    }
}
