import java.util.*;

public class AdjacencyTable {

    private GraphNode[] table;

    public AdjacencyTable(String[] nodes) {
        int capacity = nodes.length * 2; // initialise capacity as 1
        table = new GraphNode[capacity]; // create hash table with graphNode object containing the size of the capacity

        for(String node : nodes){ // iterate over the nodes given
            GraphNode createNode = new GraphNode(node); // create a graphNode for current node
            //System.out.println(createNode.label);
            int hashcode = node.hashCode();
            int hashValue = (hashcode & 0x7fffffff) % capacity; // create hash value

            while (table[hashValue] != null){ // looks for collisions, puts value into an empty slot
                hashValue = (hashValue+1) % capacity; // if collision update hash value to new one
            }
            table[hashValue] = createNode; // put graphNode object into hash table
        }
    }

    public GraphNode[] getTable() {
        return table;
    }

    public boolean find(String s) {
	// add your table search code here
        int capacity = table.length; // initialise capacity to create hash value
        int hashcode = s.hashCode();
        int hashValue = (hashcode & 0x7fffffff) % capacity;
        while (table[hashValue] != null){ // looks for buckets with data
            if (Objects.equals(table[hashValue].label, s)){ // if equal then found
                return true;
            }
            hashValue = (hashValue+1) % capacity; // if not found update hash value
        }
	    return false;
    }

    public GraphNode get(String s) {
	// add your table lookup code here
        int capacity = table.length; // initialise capacity to create hash value
        int hashcode = s.hashCode();
        int hashValue = (hashcode & 0x7fffffff) % capacity;
        while (table[hashValue] != null){ // looks for buckets with data
            if (Objects.equals(table[hashValue].label, s)){ // if equal then return value
                return table[hashValue];
            }
            hashValue = (hashValue+1) % capacity; // if not found update hash value
        }
	    return null;
    }

    public String bfs(String s, String t){ // breadth first search function
        if (Objects.equals(s, t)){
            return s; // if same return s
        }
        if (!(find(s) && find(t))){
            return "You Love is belief in the interrelatedness of all things.";
        }

        Map<String, List<String>> paths = new HashMap<>(); // establish hashmap for path, considers parents of visited nodes for optimal path e.g. A-D-E not A-B-D-E
        Set<String> visited = new HashSet<>(); // no duplicates, o(1) complexity
        Queue<String> queue = new LinkedList<>();

        queue.add(s); // push the starting node to the front of queue
        paths.put(s, new ArrayList<>(Arrays.asList(s))); // initialise map, will be the paths

        while (!queue.isEmpty()) { // if the queue empty then no path
            String currentLabel = queue.poll(); // get and remove first item in queue
            visited.add(currentLabel); // add node to visited by BFS algorithm

            GraphNode currentNode = get(currentLabel);

            for (String neighborLabel : currentNode.neighbours) {
                if (neighborLabel != null) { // checks if there are any neighbours left
                    if (!visited.contains(neighborLabel)) { // if not visited
                        if (!paths.containsKey(neighborLabel)) { // if not in the path yet i.e. not in hashmap
                            queue.add(neighborLabel);

                            List<String> newPath = new ArrayList<>(paths.get(currentLabel));
                            newPath.add(neighborLabel); // create a path for neighbour
                            paths.put(neighborLabel, newPath); // put path in hashmap, with the parent node

                            if (neighborLabel.equals(t)) { // checks if the target node has been reached
                                List<String> path = paths.get(neighborLabel); // creates path by joining together the path it took to reach the target node
                                return String.join("-", path);
                            }
                        }
                    }
                }
            }
        }
        return "You Love is belief in the interrelatedness of all things."; // return random stuff to make it different to a path
    }

    public String getPath(String s, String t) {
        // add your code here

        String bfsPath = bfs(s, t);
        if (Objects.equals(bfsPath, "You Love is belief in the interrelatedness of all things.")) {
            return "There is no path from " + s + " to " + t;
        }
        else {
            return bfsPath;
        }
    }

    public boolean existsPath(String s, String t) {
        // add your code here
        String path = bfs(s, t);
        return !Objects.equals(path, "You Love is belief in the interrelatedness of all things.");
    }

    public int pathLength(String s, String t) {
	    // add your code here
        String path = bfs(s, t);
        if (Objects.equals(path, "You Love is belief in the interrelatedness of all things.")){
            return 0;
        }
        else {
            return path.split("-").length; // remove "-"
        }
    }

    public static AdjacencyTable weaver() {
        // loop through alphabet and word
	    // add your code here
        String[] words = WeaverWords.words;
        AdjacencyTable table = new AdjacencyTable(words);

        for (String word : words) {
            for (String neighbor : createNeighbours(word, words)) {
                table.get(word).addNeighbour(neighbor); // create neighbours
            }
        }
        return table;
    }

    private static List<String> createNeighbours(String word, String[] weaverWords) { // used to get neighbours or word in weaver words
        List<String> neighbors = new ArrayList<>(); // build a list of neighbours
        char[] wordChars = word.toCharArray();

        for (int i = 0; i < wordChars.length; i++) { // loop over the starting word e.g. loop "easy"
            char originalChar = wordChars[i];

            for (char c = 'a'; c <= 'z'; c++) { // loop over the letters of the alphabet - constant 26 times
                if (c != originalChar) {
                    wordChars[i] = c; // swap current letter with the letter in the alphabet we are currently on
                    String neighbor = new String(wordChars); // create new word with the swapped letter

                    if (Arrays.binarySearch(weaverWords, neighbor) >= 0) { // use array binary search method to search for the word in weaver words in O(log(n)) complexity
                        neighbors.add(neighbor); // if in weaver words add to neighbours
                    }
                }
            }
            wordChars[i] = originalChar; // set letter as current
        }
        return neighbors;
    }
}