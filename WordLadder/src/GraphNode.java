import java.util.Arrays;

public class GraphNode {

    // A node holds a String label, and an array of its neighbours
    public String label;
    public String[] neighbours; // 6 neighbours to start with
    public int currentSize; // used to track size for resizing

    // You may add further fields and methods. 
     
    public GraphNode(String label) {
      this.label = label;
      this.neighbours = new String[6];
      this.currentSize = 0;
    }

    public void addNeighbour(String s) {
        if (neighbours.length - 1 == currentSize){
            int doubleSize = neighbours.length * 2;
            String[] largerNeighbours = new String[doubleSize];
            System.arraycopy(neighbours, 0, largerNeighbours,0, neighbours.length-1);
            neighbours = largerNeighbours;
        }
        neighbours[currentSize] = s;
        currentSize++;
   }
}
