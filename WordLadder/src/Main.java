import java.util.Arrays;

public class Main {
    //static String[] words = {"aahs","aals","abas","abba","abbe","abed","abet","able", "aahs","aals","abas","abba","abbe","abed","abet","able", "aahs","aals","abas","abba","abbe","abed","abet","able"};
    public static void main(String[] args) {
        GraphNode n = new GraphNode("test");
        n.addNeighbour("First");
        n.addNeighbour("Second");
        for (int i= 0; i < n.neighbours.length; i++) {
            System.out.println(n.neighbours[i]);
        }
        n.addNeighbour("Third");
        n.addNeighbour("Fourth");
        for (String s: n.neighbours) {
            System.out.println(s);
        }
        System.out.println(Arrays.toString(n.label.toCharArray()));
        System.out.println(Arrays.toString(n.neighbours));

        String[] nodes = {"A","B","C","D","E"};
        AdjacencyTable t = new AdjacencyTable(nodes);
        GraphNode m = t.get("A");
        m.addNeighbour("B");
        m.addNeighbour("D");
        m = t.get("B");
        m.addNeighbour("C");
        m.addNeighbour("D");
        m = t.get("D");
        m.addNeighbour("E");
        System.out.println(t.getPath("A", "E"));
        System.out.println(t.getPath("A", "A"));
        System.out.println(t.getPath("E", "A"));

        long start1 = System.nanoTime();
        AdjacencyTable table = AdjacencyTable.weaver();
        System.out.println(table.getPath("node","edge"));
        System.out.println(table.getPath("abdw","sdfk"));
        System.out.println(table.pathLength("fvdfg","msdfsdfeow"));
        System.out.println(table.existsPath("jiff","meow"));
        System.out.println(table.getPath("easy","easy"));
        System.out.println(table.getPath("ceer","sout"));
        long end1 = System.nanoTime();
        System.out.println("Elapsed Time in ms: "+ ((end1-start1)/1000000));

        //String[] nodes = {"A","B","C","D","E"};
        //AdjacencyTable t = new AdjacencyTable(nodes);
        //GraphNode m = t.get("A");
        //m.addNeighbour("B");
        //System.out.println(t.existsPath("T", "C"));
    }
}
