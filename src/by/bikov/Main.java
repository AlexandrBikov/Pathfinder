package by.bikov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

    private static Graph graph = new Graph();
    private static Stack<Integer> result;
    private static ArrayList<int[]> fileData = new ArrayList<int[]>();

    public static void main(String[] args) {
        getData(args[1]);
        convertData();
        getResult(args[0]);
        printPath();
    }

    private static void getData(String fileName){
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String temp, tempArray[];

            while((temp = br.readLine()) != null){
                tempArray = temp.split(" ");
                int[] intArray = new int[tempArray.length];
                for(int i = 0; i < tempArray.length; i++){
                    intArray[i] = Integer.parseInt(tempArray[i]);
                }
                fileData.add(intArray);
            }
            br.close();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    private static void getResult(String algorithm){
        if(algorithm.equals("dfs")){
            result = graph.dfs(fileData.get(fileData.size()-1)[0]-1, fileData.get(fileData.size()-1)[1]-1);
        } else {
            result = graph.bfs(fileData.get(fileData.size()-1)[0]-1, fileData.get(fileData.size()-1)[1]-1);
        }
    }

    private static void printPath() {
        if (result == null) {
            System.out.println("Path not found");
        } else {
            while (!result.empty()) {
                System.out.print(graph.getVertexList()[result.pop()].getValue() + " ");
            }
        }
    }

    private static void convertData(){
        for(int i=0; i<fileData.get(1).length; i++){
            graph.addVertex(fileData.get(1)[i]);
        }

        if(fileData.get(0)[0] == 0) {
           for(int i=0; i<graph.getVertexCount(); i++){
               for(int j : fileData.get(i+2)){
                   graph.addEdge(i, j-1);
               }
           }
        } else {
            for(int i=0; i<fileData.get(2)[0]; i++){
                graph.addEdge(fileData.get(i+3));
            }
        }
    }

}
