package Ex2.src;

import Ex2.src.api.DirectedWeightedGraph;
import Ex2.src.api.DirectedWeightedGraphAlgorithms;
import Ex2.src.api.GUI_graph;
import Ex2.src.api.GraphAlgo;

/**
 * This class is the main class for Ex2 - your implementation will be tested using this class.
 */
public class Ex2 {
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraph getGrapg(String json_file) {
        DirectedWeightedGraphAlgorithms g=new GraphAlgo();
        g.load(json_file);
        DirectedWeightedGraph ans =g.getGraph() ;
        // ****** Add your code here ******
        //
        // ********************************
        return ans;
    }
    /**
     * This static function will be used to test your implementation
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     * @return
     */
    public static DirectedWeightedGraphAlgorithms getGrapgAlgo(String json_file) {
        DirectedWeightedGraphAlgorithms ans = new GraphAlgo();
        ans.load(json_file);
        return ans;
    }
    /**
     * This static function will run your GUI using the json fime.
     * @param json_file - a json file (e.g., G1.json - G3.gson)
     *
     */
    public static void runGUI(String json_file) {
        DirectedWeightedGraphAlgorithms alg = getGrapgAlgo(json_file);
        new GUI_graph(alg);
    }
    public static void main(String[] args)
    {
        runGUI(args[0]);
    }
}