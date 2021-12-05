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
        JsonArray nodesjson =jsonObj.get("Nodes").getAsJsonArray();
        JsonArray edgejson = jsonObj.get("Edges").getAsJsonArray();
        for (int i=0;i<nodesjson.size();i++) {
            JsonElement val=nodesjson.get(i).getAsJsonObject();
            int key =val.getAsJsonObject().get("id").getAsInt();
            //JsonObject Loc=val.getAsJsonObject().get("pos").getAsJsonObject();
            String[] pos=val.getAsJsonObject().get("pos").getAsString().split(",");
            double x =Double.parseDouble(pos[0]);
            double y =Double.parseDouble(pos[1]);
            double z =Double.parseDouble(pos[2]);
            GeoLocation location =new Location(x,y,z);
            NodeData n =new Node(location.x(), location.y(), location.z(), key);
            g.addNode(n);
        }
        for (int i=0;i<edgejson.size();i++) {
            JsonElement edge=edgejson.get(i).getAsJsonObject();
            int src =edge.getAsJsonObject().get("src").getAsInt();
            int dest =edge.getAsJsonObject().get("dest").getAsInt();
            double weight =edge.getAsJsonObject().get("w").getAsDouble();
            g.connect(src,dest,weight);


        }
        return g;

    }
}
