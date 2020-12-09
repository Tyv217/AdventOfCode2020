package Day7;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day7 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static class Vertex{
        String label;
        public Vertex(String label){
            this.label = label;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return Objects.equals(label, vertex.label);
        }

        @Override
        public int hashCode() {
            return Objects.hash(label);
        }
    }

    public static class Graph{
        public Map<Vertex,List<Vertex>> adjVertices;
        public Graph(){
            adjVertices  = new HashMap<>();

        }

        public void addVertex(String label){
            Vertex vertex = new Vertex(label);
            adjVertices.putIfAbsent(vertex, new ArrayList<>());
        }

        public void addEdge(String label1, String label2){
            Vertex v1 = new Vertex(label1);
            Vertex v2 = new Vertex(label2);
            adjVertices.get(v1).add(v2);
        }

        public List<Vertex> getVertices(String label){
            Vertex vertex = new Vertex(label);
            return adjVertices.get(vertex);
        }

        public Set<String> Traverse(Graph graph, String root){
            Set<String> visited = new LinkedHashSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.add(root);
            visited.add(root);
            while(!queue.isEmpty()){
                String vertex = queue.remove();
                for(Vertex v: graph.getVertices(vertex)){
                    if(!visited.contains(v.label)){
                        visited.add(v.label);
                        queue.add(v.label);
                    }
                }
            }
            return visited;
        }
    }

    public static int returnDistinctColoursThatContainShinyGoldBag(List<String> allStrings) {
        Graph graph = new Graph();
        for (String string : allStrings) {
            String[] stringArray = string.split(" ");
            if (stringArray.length == 7) {
                String colour = stringArray[0] + " " + stringArray[1];
                graph.addVertex(colour);
            } else {
                int numberOfColours = (stringArray.length - 4) / 4;
                String outerColour = stringArray[0] + " " + stringArray[1];
                graph.addVertex(outerColour);
                for (int x = 0; x < numberOfColours; x++) {
                    String innerColour = stringArray[5 + 4 * x] + " " + stringArray[6 + 4 * x];
                    graph.addVertex(innerColour);
                    graph.addEdge(innerColour, outerColour);
                }
            }
        }
        return (graph.Traverse(graph, "shiny gold")).size() - 1;
    }

    public static class recursive{
        public static List<String> stringList;

        public recursive(){
            stringList = new ArrayList<String>();
        }

        public static void recursiveIterate(List<String> allStrings, String source){
            String[] sourceStringArray = source.split(" ");
            String source1 = sourceStringArray[1] + " " + sourceStringArray[2];
            int weight = Integer.parseInt(sourceStringArray[0]);
            stringList.add(source);
            for(String colourString: allStrings){
                String[] stringArray = colourString.split(" ");
                if((stringArray[0] + " " + stringArray[1]).equals(source1)) {
                    if (!(stringArray.length == 7)){
                        int numberOfColours = (stringArray.length - 4) / 4;
                        for (int x = 0; x < numberOfColours; x++) {
                            String destColour = stringArray[5 + 4 * x] + " " + stringArray[6 + 4 * x];
                            Integer destWeight = Integer.parseInt(stringArray[4+4*x]) * weight;
                            recursiveIterate(allStrings, destWeight + " " + destColour);
                        }
                    }
                }
            }
        }

        public static int sumUpBags(){
            int total = 0;
            for(String string: stringList){
                String[] stringArray = string.split(" ");
                total += Integer.parseInt(stringArray[0]);
            }
            return total - 1;
        }
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day7\\text.txt");
        int totalColours = returnDistinctColoursThatContainShinyGoldBag(lines);
        System.out.println(totalColours);
        recursive recur = new recursive();
        recur.recursiveIterate(lines, "1 shiny gold");
        System.out.println(recur.sumUpBags());
    }
}