/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author kamini
 */
public class Graph {

    private int[][] adjacency_matrix;

    public int[][] getAdjacency_matrix() {
        return adjacency_matrix;
    }

    public void setAdjacency_matrix(int[][] adjacency_matrix) {
        this.adjacency_matrix = adjacency_matrix;
    }

    public Graph() {

        adjacency_matrix = new int[11][11];
        setAdjacency_matrix(adjacency_matrix);
    }

    public void addEdge(int to, int from, int edge) {
        try {
            adjacency_matrix[to][from] = edge;
            adjacency_matrix[from][to] = edge;
        } catch (ArrayIndexOutOfBoundsException index) {
            System.out.println("The vertices does not exists");
        }
    }

    public int getEdge(int to, int from) {
        try {
            return adjacency_matrix[to][from];
        } catch (ArrayIndexOutOfBoundsException index) {
            System.out.println("The vertices does not exists");
        }
        return -1;
    }
}
