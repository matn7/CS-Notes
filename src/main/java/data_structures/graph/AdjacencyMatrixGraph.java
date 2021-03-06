package data_structures.graph;

import data_structures.queue.Queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AdjacencyMatrixGraph implements Graph {

    // Set up a V X V Matrix to hold the vertices and Edges relationship
    private int[][] adjacencyMatrix;

    // This can be directed or undirected graph
    private GraphType graphType = GraphType.DIRECTED;

    int numVertices = 0;

    public AdjacencyMatrixGraph(int numVertices, GraphType graphType) {
        this.numVertices = numVertices;
        this.graphType = graphType;
        adjacencyMatrix = new int[numVertices][numVertices];

        for (int i = 0; i < numVertices; i++) {
            for (int j = 0; j < numVertices; j++) {
                adjacencyMatrix[i][j] = 0; // initialize matrix
            }
        }
    }

    public int getNumVertices() {
        return numVertices;
    }

    @Override
    // V1 - source vertex V2 destination vertex
    public void addEdge(int v1, int v2) {
        if (v1 >= numVertices || v1 < 0 || v2 >= numVertices || v2 < 0) {
            throw new IllegalArgumentException("Vertex number not valid");
        }
        adjacencyMatrix[v1][v2] = 1; // set the cell at row v1 and column v2 to 1
        if (graphType == GraphType.UNDIRECTED) {
            // If the graph is undirected then the connection goes both ways - set row v2 and column v1 as well
            adjacencyMatrix[v2][v1] = 1;
        }
    }

    @Override
    public List<Integer> getAdjacentVertices(int v) {
        if (v >= numVertices || v < 0) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }

        // Setup the list to hold the adjacent vertices
        List<Integer> adjacentVerticesList = new ArrayList<>();

        for (int i = 0; i < numVertices; i++) {
            // If 1 is present in the cell it means that the vertex V is directly connected to another vertex
            if (adjacencyMatrix[v][i] == 1) {
                adjacentVerticesList.add(i);
            }
        }

        // Always return the vertices in ascending order
        Collections.sort(adjacentVerticesList);
        // Sort the vertices in Ascending order so return Values are consistent
        return adjacentVerticesList;
    }

    // Graph DEPTH FIRST TRAVERSAL
    public static void depthFirstTraversal(Graph graph, int[] visited, int currectVertex) {
        if (visited[currectVertex] == 1) {
            // If the current index has already been visited just return
            return;
        }

        // Set the current vertex as visited
        visited[currectVertex] = 1;

        List<Integer> list = graph.getAdjacentVertices(currectVertex);
        for (int vertex : list) {
            // For all Adjacency Vertices - Perform the Depth First Traversal (DFS)
            depthFirstTraversal(graph, visited, vertex);
        }
        System.out.println(currectVertex + "->");

        // Iterate through all Nodes and starts the DFS at every Node to ensure that even unconnected node are covered
        /*for (int i = 0; i < N; i++) {
            depthFirstTraversal(graph, visited, i);
        }*/
    }

    // GRAPH - BREADTH FIRST TRAVERSAL
    public static void breadthFirstTraversal(Graph graph, int[] visited, int currentVertex)
        throws Queue.QueueOverflowException, Queue.QueueUnderflowException {
        // A Queue to Add the children in breadth first order
        Queue<Integer> queue = new Queue<>(Integer.class);
        queue.enqueue(currentVertex);
        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            if (visited[vertex] == 1) {
                // Check if the node has ben seen before - if yes continue
                continue;
            }

            System.out.println(vertex + "->");
            // Process and visit the Node
            visited[vertex] = 1;

            // For all Adjacent vertices - Add it to the Queue to visit in BFS Before
            List<Integer> list = graph.getAdjacentVertices(vertex);

            for (int v : list) {
                if (visited[v] != 1) {
                    queue.enqueue(v);
                }
            }
        }
        // UNCONNECTED GRAPH
        // Iterate through all nodes and start the BFS at every node to ensure that even unconnected nodes are covered
        /*for (int i = 0; i < N; i++) {
            breadthFirstTraversal(graph, visited, i);
        }*/
    }

    // Indegree in Adjacency Matrix
    public int getIndegree(int v) {
        if (v < 0 || v >= numVertices) {
            throw new IllegalArgumentException("Vertex number is not valid");
        }
        int indegree = 0;
        for (int i = 0; i < getNumVertices(); i++) {
            if (adjacencyMatrix[i][v] != 0) {
                // If the current vertex is present as an adjacency vertex for any
                // other vertex then increment the indegree count for the curreny vertex
                indegree++;
            }
        }
        return indegree;
    }
}
