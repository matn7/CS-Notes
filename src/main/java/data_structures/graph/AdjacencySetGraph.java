package data_structures.graph;

import java.util.*;

/**
 * Created by Mati on 01.08.2017.
 */
public class AdjacencySetGraph implements Graph {

    // Setup a list of vertex Nodes, each node holds a set of adjacent vertex
    private List<Node> vertexList = new ArrayList<>();

    // Directed or undirected graph
    private GraphType graphType = GraphType.DIRECTED;
    private int numVertices = 0;

    public AdjacencySetGraph(int numVertices, GraphType graphType) {
        this.numVertices = numVertices;
        for (int i = 0; i < numVertices; i++) {
            // Initialize the Vertex List, and other information in the Constructor
            vertexList.add(new Node(i));
        }
        this.graphType = graphType;
    }

    @Override
    public void addEdge(int v1, int v2) {
        // Specify the vertices the edge connects - v1 is the source and v2 is the destination vertex
        if (v1 >= numVertices || v1 < 0 || v2 >= numVertices || v2 < 0) {
            throw new IllegalArgumentException("Vertex number is not valid: " + v1 + ", " + v2);
        }
        // Add v2 ato the SET of Node v1
        vertexList.get(v1).addEdge(v2);
        if (graphType == GraphType.UNDIRECTED) {
            // If the Graph is UNDIRECTED then the connection goes both ways-set v1 to the set of Node v2
            vertexList.get(v2).addEdge(v1);
        }
    }

    @Override
    public List<Integer> getAdjacentVertices(int v) {
        if (v >= numVertices || v < 0) {
            throw new IllegalArgumentException("Vertex number is not valid: " + v);
        }
        // Just return the adjacent vertices from the Node class
        return vertexList.get(v).getAdjacentVertices();
    }

    // INDEGREE
    public int getIndegree(int v) {
        if (v < 0 || v >= numVertices) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }
        int indegree = 0;

        for (int i = 0; i < numVertices; i++) {
            if (getAdjacentVertices(i).contains(v)) {
                // If the current vertex is present as an adjacent vertex for any
                // othervertex then increment the indegree count for the current index
                indegree++;
            }
        }
        return indegree;
    }

    // Class which represnet a vertex +---+
    //                                | A |
    //                                +---+
    public static class Node {
        private int vertexNumber;

        // Each Node holds a set of Adjacent Vertices
        private Set<Integer> adjaencySet = new HashSet<>();

        public Node(int vertexNumber) {
            // Each vertex has an index or unique number associated with it
            this.vertexNumber = vertexNumber;
        }

        public int getVertexNumber() {
            return vertexNumber;
        }

        // Helper method to add an edge with this node as the source
        public void addEdge(int vertexNumber) {
            adjaencySet.add(vertexNumber);
        }

        // Get the Adjacent Vertices for this Node
        public List<Integer> getAdjacentVertices() {
            List<Integer> sortedList = new ArrayList<>(adjaencySet);
            Collections.sort(sortedList);
            return sortedList;
        }
    }

}
