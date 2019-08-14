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

    private double[][] adjacency_matrix;
    int size;

    public Graph(int size) {
        this.size = size;
        int x = size + 1;
        adjacency_matrix = new double[x][x];
        setAdjacency_matrix(adjacency_matrix);
    }

    public double[][] getAdjacency_matrix() {
        return adjacency_matrix;
    }

    public void setAdjacency_matrix(double[][] adjacency_matrix) {
        this.adjacency_matrix = adjacency_matrix;
    }

    public void addEdge(int to, int from, int edge) {
        try {
            adjacency_matrix[to][from] = edge;
            adjacency_matrix[from][to] = edge;
        } catch (ArrayIndexOutOfBoundsException index) {
            System.out.println("The vertices does not exists");
        }
    }

    public double getEdge(int to, int from) {
        try {
            return adjacency_matrix[to][from];
        } catch (ArrayIndexOutOfBoundsException index) {
            System.out.println("The vertices does not exists");
        }
        return -1;
    }
}
