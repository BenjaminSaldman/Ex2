package Ex2.src.api;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * This Class Handles the GUI part of the project.
 */
public class GUI_graph extends JFrame implements ActionListener {

    DirectedWeightedGraph graph; //The given graph from json file.
    DirectedWeightedGraphAlgorithms AlgoGraph; //Given algorithm graph.
    double WIDTH = Toolkit.getDefaultToolkit().getScreenSize().getWidth(); //Dimension of screen.
    double HEIGHT = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    /**
     * Menu Bar and items for the center algorithm and restart the graph.
     */
    JMenuBar menuBar;
    JMenu Center;
    JMenu Back;
    JMenuItem center;
    JMenuItem back;

    GraphP g; //JPanel inner object.
    JTextField textField; //Input text to read.
    JButton save, load, Paths, TSP; //GUI features buttons: save and load graphs, paint the shortest paths and TSP.
    JLabel text; //GUI instructions.

    public GUI_graph(DirectedWeightedGraphAlgorithms AlgoGraph) {

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Initialize X to close operation.
        this.setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight()); //Set screen size.
        //Initialization of menu buttons.
        menuBar = new JMenuBar();
        Back = new JMenu("Back");
        Center = new JMenu("Center");
        center = new JMenuItem("center");
        back = new JMenuItem("back");
        center.addActionListener(this);
        back.addActionListener(this);
        Center.add(center);
        Back.add(back);
        menuBar.add(Back);
        menuBar.add(Center);
        this.setJMenuBar(menuBar);
        ////////////////////////////////////////////////////////////////////////
        //Initialization of Buttons and texts.
        textField = new JTextField();
        textField.setBounds((int) WIDTH - 250, (int) HEIGHT / 2, 222, 20);
        text = new JLabel();
        text.setBounds((int) WIDTH - 250, (int) (HEIGHT / 2) - 50, 240, 20);
        text.setText("Enter filename to save/load or <src,dst>");
        save = new JButton("Save");
        load = new JButton("Load");
        Paths = new JButton("Paths");
        TSP = new JButton("TSP-Insert Node ID's");
        save.setBounds((int) WIDTH - 250, (int) (HEIGHT / 2) + 50, 222, 20);
        load.setBounds((int) WIDTH - 250, (int) (HEIGHT / 2) + 100, 222, 20);
        Paths.setBounds((int) WIDTH - 250, (int) (HEIGHT / 2) + 150, 222, 20);
        TSP.setBounds((int) WIDTH - 250, (int) (HEIGHT / 2) + 200, 222, 20);
        save.addActionListener(this);
        load.addActionListener(this);
        Paths.addActionListener(this);
        TSP.addActionListener(this);
        add(save);
        add(load);
        add(Paths);
        add(text);
        add(textField);
        add(TSP);
        ///////////////////////////////////////////////////////////////////////////////////
        //Creating new Graph and adding the JPanel.
        this.AlgoGraph = AlgoGraph;
        this.graph = AlgoGraph.getGraph();
        g = new GraphP(graph);
        this.add(g);
        this.setVisible(true);


    }

    /**
     * @param e Listen and react to Events: Button's click or menu click.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Print the TSP algorithm nodes on the graph.
        if (e.getSource() == TSP) {
            String[] nodes = textField.getText().split(",");
            try {
                LinkedList<NodeData> tsp = new LinkedList<>();
                for (int i = 0; i < nodes.length; i++) {
                    tsp.add(this.graph.getNode(Integer.parseInt(nodes[i])));
                }
                List<NodeData> l = this.AlgoGraph.tsp(tsp);
                this.g.setExternal(l);
                this.g.repaint();

            } catch (Exception k) {
                k.printStackTrace();
            }

        }
        //Print the center Node on the screen.
        if (e.getSource() == center) {
            NodeData n = this.AlgoGraph.center();
            this.g.setCenter(n);
            this.g.repaint();

        }
        //Bring back to the main graph.
        if (e.getSource() == back) {
            this.g.extra = false;
            this.g.cent = false;
            this.g.repaint();
        }
        //Save the current graph into a json file.
        if (e.getSource() == save) {
            try {
                AlgoGraph.save(textField.getText());
            } catch (Exception k) {
                k.printStackTrace();
            }
        }
        //Load a new graph into the GUI.
        if (e.getSource() == load) {

            try {
                this.AlgoGraph.load(textField.getText());
                this.graph = AlgoGraph.getGraph();
                g.change(graph);
                this.g.repaint();
            } catch (Exception k) {
                k.printStackTrace();
            }
        }

    }

    /**
     * Inner class, Extends JPanel.
     */
    public class GraphP extends JPanel {
        LinkedList<Point2D> points; //Points to print into the screen (nodes).
        Point2D center; //Center point (result of center algorithm).
        ArrayList<Integer> Id; //Nodes ID.
        DirectedWeightedGraph g; //The graph that the algorithm works on.
        List<NodeData> external; //External algorithms nodes to paint.
        boolean extra;
        boolean cent;

        public GraphP(DirectedWeightedGraph g) {
            this.g = g;
            this.cent = false;
            this.extra = false;
            center = new Point2D.Double();
            points = new LinkedList<Point2D>();
            external = new LinkedList<NodeData>();
            Iterator<NodeData> n = graph.nodeIter();
            Id = new ArrayList<Integer>();
            while (n.hasNext()) {
                NodeData next = n.next();
                this.points.add(new Point2D.Double(next.getLocation().x(), next.getLocation().y()));
                Id.add(next.getKey());


            }
        }

        /**
         * @param g set a new graph into the JPanel.
         */
        void change(DirectedWeightedGraph g) {
            this.g = g;
            this.points.removeAll(points);
            Id.removeAll(Id);
            Iterator<NodeData> n = graph.nodeIter();
            while (n.hasNext()) {
                NodeData next = n.next();
                this.points.add(new Point2D.Double(next.getLocation().x(), next.getLocation().y()));
                Id.add(next.getKey());


            }
        }

        /**
         * @param n set the graph center.
         */
        void setCenter(NodeData n) {
            this.center.setLocation(n.getLocation().x(), n.getLocation().y());
            cent = true;
        }

        /**
         *
         * @param n set the external nodes needed to bo added.
         */
        void setExternal(List<NodeData> n) {
            this.external = n;
            this.extra = true;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            int prevID = 0; //ID of current node.
            int k = 0; //ID counter.
            int m=0; //ID counter.
            for (Point2D p : points) {
                //Print all the nodes in black color.
                g.setColor(Color.BLACK);
                g.fillOval((int) (scale(p.getX(), minMax()[0], minMax()[1], 0, GUI_graph.this.WIDTH / 5 + GUI_graph.this.WIDTH / 2) - 5), (int) (scale(p.getY(), minMax()[2], minMax()[3], 0, GUI_graph.this.HEIGHT / 5 + GUI_graph.this.HEIGHT / 2) - 5), 10, 10);
                //Print the node ID
                String ID = Id.get(k++) + "";
                g.drawString(ID, (int) (scale(p.getX(), minMax()[0], minMax()[1], 0, GUI_graph.this.WIDTH / 5 + GUI_graph.this.WIDTH / 2)) + 10, (int) (scale(p.getY(), minMax()[2], minMax()[3], 0, GUI_graph.this.HEIGHT / 5 + GUI_graph.this.HEIGHT / 2) + 5));
                for (Point2D l:points) {
                    //Run from current node to all other nodes and check if there is an edge between the nodes.
                    if(l!=p) {
                        if ((graph.getEdge(Id.get(prevID), Id.get(m)) != null) || graph.getEdge(Id.get(m), Id.get(prevID)) != null) {
                           // Double dist = p.distance(prev);
                           // g.drawLine((int) p.getX(), (int) p.getY(), (int) l.getX(), (int) l.getY());
                            g.drawLine((int) (scale(p.getX(), minMax()[0], minMax()[1], 0, GUI_graph.this.WIDTH / 5 + GUI_graph.this.WIDTH / 2)), (int) (scale(p.getY(), minMax()[2], minMax()[3], 0, GUI_graph.this.HEIGHT / 5 + GUI_graph.this.HEIGHT / 2)),
                                    (int) (scale(l.getX(), minMax()[0], minMax()[1], 0, GUI_graph.this.WIDTH / 5 + GUI_graph.this.WIDTH / 2)), (int) (scale(l.getY(), minMax()[2], minMax()[3], 0, GUI_graph.this.HEIGHT / 5 + GUI_graph.this.HEIGHT / 2)));
                        }
                    }
                    m++;
                }
                m=0; //Node ID counter=0;
                prevID=k; //Current node for iteration.

            }
            //Print the center in Red.
            if (cent) {
                g.setColor(Color.RED);
                g.fillOval((int) (scale(center.getX(), minMax()[0], minMax()[1], 0, GUI_graph.this.WIDTH / 5 + GUI_graph.this.WIDTH / 2) - 5), (int) (scale(center.getY(), minMax()[2], minMax()[3], 0, GUI_graph.this.HEIGHT / 5 + GUI_graph.this.HEIGHT / 2) - 5), 10, 10);
            }
            //Print TSP, The Shortest paths in Blue.
            if (extra) {
                g.setColor(Color.BLUE);
                for (NodeData n : this.external) {
                    Point2D p = new Point2D.Double(n.getLocation().x(), n.getLocation().y());
                    g.fillOval((int) (scale(p.getX(), minMax()[0], minMax()[1], 0, GUI_graph.this.WIDTH / 5 + GUI_graph.this.WIDTH / 2) - 5), (int) (scale(p.getY(), minMax()[2], minMax()[3], 0, GUI_graph.this.HEIGHT / 5 + GUI_graph.this.HEIGHT / 2) - 5), 10, 10);
                }
            }

        }

        /**
         *
         * @param data
         * @param r_min
         * @param r_max
         * @param t_min
         * @param t_max
         * @return scaled location.
         */
        private double scale(double data, double r_min, double r_max,
                             double t_min, double t_max) {
            double res = ((data - r_min) / (r_max - r_min)) * (t_max - t_min) + t_min;

            if (res == 0)
                return res + 10;
            return res;


        }

        /**
         *
         * @return array that contains the minimum X,Y and maximum X,Y among the nodes.
         */
        private double[] minMax() {
            double[] ans = new double[4];
            double xMax = 0, xMin = Double.MAX_VALUE, yMax = 0, yMin = Double.MAX_VALUE;
            for (Point2D p : points) {
                if (p.getX() < xMin) {
                    xMin = p.getX();
                    ans[0] = p.getX();
                }
                if (p.getX() > xMax) {
                    xMax = p.getX();
                    ans[1] = p.getX();
                }
                if (p.getY() < yMin) {
                    yMin = p.getY();
                    ans[2] = p.getY();
                }
                if (p.getY() > yMax) {
                    yMax = p.getY();
                    ans[3] = p.getY();
                }
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        DirectedWeightedGraphAlgorithms gr = new GraphAlgo();
        String s = "C:\\Users\\binya\\IdeaProjects\\Ex2\\src\\Ex2\\src\\api\\G3.json";
        gr.load(s);
        new GUI_graph(gr);


    }


}
