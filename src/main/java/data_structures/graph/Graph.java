package data_structures.graph;

import java.util.List;

public interface Graph {

    enum GraphType {
        DIRECTED,
        UNDIRECTED
    }

    // An edge lies between two vertices
    // vertices are represented by numbers
    void addEdge(int v1, int v2);

    List<Integer> getAdjacentVertices(int v);

}
