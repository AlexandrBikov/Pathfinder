package by.bikov;

import java.util.*;

public class Graph {

    private final int VERTEX_MAX = 100;
    private Vertex[] vertexList;
    private int vertexCount;
    private int[][] matrix;

    public Graph() {
        matrix = new int[VERTEX_MAX][VERTEX_MAX];
        for(int i = 0; i < VERTEX_MAX; i++)
            for(int j = 0; j < VERTEX_MAX; j++)
                matrix[i][j] = 0;
        vertexCount = 0;
        vertexList = new Vertex[VERTEX_MAX];

    }

    public void addVertex(int label) {
        vertexList[vertexCount++] = new Vertex(label);
    }

    public void addEdge(int begin, int end) {
        matrix[begin][end] = 1;
    }

    public void addEdge(int[] edge) {
        matrix[edge[0]][edge[1]] = 1;
    }

    private Stack<Integer> makeList(Vertex vertex){
        Stack<Integer> stack = new Stack<Integer>();

        while(vertex!=null) {
            stack.push(vertex.getValue()-1);
            vertex = vertex.getPrevious();
        }
        return stack;
    }

    private int getSuccessor (int v) {
        for(int j = 0; j < vertexCount; j++)
            if(matrix[v][j] == 1 && !vertexList[j].isVisited())
                return j;
        return -1;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public Vertex[] getVertexList() {
        return vertexList;
    }

    public Stack<Integer> bfs(int start, int end) {
        ArrayDeque<Integer> queue = new ArrayDeque<Integer>();
        vertexList[start].setVisited(true);
        queue.push(start);
        int vertex;

        System.out.println("Start: " + vertexList[start].getValue() + "\nEnd: " + vertexList[end].getValue());

        while(!queue.isEmpty()) {
            int current = queue.pop();
            while((vertex = getSuccessor(current)) != -1) {
                vertexList[vertex].setVisited(true);
                vertexList[vertex].setPrevious(vertexList[current]);
                if(vertex == end){
                    return makeList(vertexList[vertex]);
                }
                queue.add(vertex);
            }
        }
        return null;
    }

    public Stack<Integer> dfs(int start, int end) {
        Stack<Integer> stack = new Stack<Integer>();

        vertexList[start].setVisited(true);
        stack.push(start);

        System.out.println("Start: " + vertexList[start].getValue() + "\nEnd: " + vertexList[end].getValue());

        while(!stack.isEmpty()) {
            int current = stack.peek();
            int vertex = getSuccessor(current);
            if (vertex == -1){
                stack.pop();
            } else if(vertex == end) {
                stack.push(vertex);
                Collections.reverse(stack);
                return stack;
            } else {
                vertexList[vertex].setVisited(true);
                stack.push(vertex);
            }
        }
        return null;
    }
}
