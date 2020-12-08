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
        public Graph(Map<Vertex,List<Vertex>> adjVertices){
            this.adjVertices = adjVertices;
        }
        public Map<Vertex,List<Vertex>> get(){
            return adjVertices;
        }
        public void set(Map<Vertex,List<Vertex>> adjVertices){
            this.adjVertices = adjVertices;
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
            Set<String> visited = new LinkedHashSet<String>();
            Queue<String> queue = new LinkedList<String>();
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
        public int TraverseWithCount(Graph graph, String root, String end, Map<String,List<String>> stringMap){
            int count = 0;
            for(Vertex v: graph.getVertices(root)){
                int rootToV = 1;
                if(root.equals("shiny gold")){
                    List<String> rootList = stringMap.get(root);
                    rootToV = Integer.parseInt(rootList.get(1));
                }
                //System.out.println(v.label);
                List<String> stringList = stringMap.get(v.label);
                //System.out.println(v.label);
                if (v.label.equals(end)){
                    return rootToV;
                }
                else if(stringList.isEmpty()){
                    return rootToV;
                }
                else{
                    int number = Integer.parseInt(stringList.get(1));
                    //System.out.println(number);
                    count += (rootToV * number * TraverseWithCount(graph,v.label,end,stringMap));
                    //System.out.println(stringList.get(0));
                    //System.out.printf("%s: %d\n", v.label, number);
                }
            }
            //System.out.println(count);
            return count;

        }
    }
    public static class WeightedVertex{
        String label;
        private Set<WeightedEdge> edges;
        public WeightedVertex(String label) {
            this.label = label;
            edges = new HashSet<>();
        }
        public WeightedVertex(String label, Set<WeightedEdge> edges) {
            this.label = label;
            this.edges = edges;
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
    public static class WeightedEdge{
        private WeightedVertex source;
        private WeightedVertex destination;
        private double weight;
    }
    public static class WeightedGraph {
        public Map<Vertex, Set<Vertex>> adjVertices;

        public WeightedGraph() {
            Map<Vertex, Set<Vertex>> adjVertices = new HashMap<>();
            this.adjVertices = adjVertices;
        }

        public WeightedGraph(Map<Vertex, Set<Vertex>> adjVertices) {
            this.adjVertices = adjVertices;
        }

        public Map<Vertex, Set<Vertex>> get() {
            return adjVertices;
        }

        public void set(Map<Vertex, Set<Vertex>> adjVertices) {
            this.adjVertices = adjVertices;
        }

        public void addVertex(String label) {
            Vertex vertex = new Vertex(label);
            adjVertices.putIfAbsent(vertex, new HashSet<>());
        }

        public void addEdge(String label1, String label2, double weight) {
            Vertex v1 = new Vertex(label1);
            Vertex v2 = new Vertex(label2);
            adjVertices.get(v1).add(v2);
        }

        public Set<Vertex> getVertices(String label) {
            Vertex vertex = new Vertex(label);
            return adjVertices.get(vertex);
        }

        public Set<String> Traverse(Graph graph, String root) {
            Set<String> visited = new LinkedHashSet<String>();
            Queue<String> queue = new LinkedList<String>();
            queue.add(root);
            visited.add(root);
            while (!queue.isEmpty()) {
                String vertex = queue.remove();
                for (Vertex v : graph.getVertices(vertex)) {
                    if (!visited.contains(v.label)) {
                        visited.add(v.label);
                        queue.add(v.label);
                    }
                }
            }
            return visited;
        }

        public int TraverseWithCount(Graph graph, String root, String end, Map<String, List<String>> stringMap) {
            int count = 0;
            for (Vertex v : graph.getVertices(root)) {
                int rootToV = 1;
                if (root.equals("shiny gold")) {
                    List<String> rootList = stringMap.get(root);
                    rootToV = Integer.parseInt(rootList.get(1));
                }
                //System.out.println(v.label);
                List<String> stringList = stringMap.get(v.label);
                //System.out.println(v.label);
                if (v.label.equals(end)) {
                    return rootToV;
                } else if (stringList.isEmpty()) {
                    return rootToV;
                } else {
                    int number = Integer.parseInt(stringList.get(1));
                    //System.out.println(number);
                    count += (rootToV * number * TraverseWithCount(graph, v.label, end, stringMap));
                    //System.out.println(stringList.get(0));
                    //System.out.printf("%s: %d\n", v.label, number);
                }
            }
            //System.out.println(count);
            return count;

        }
    }
    public static int returnDistinctColoursThatContainShinyGoldBag(List<String> allStrings){
        Graph graph = new Graph();
        for(String string: allStrings){
            String[] stringArray = string.split(" ");
            if(stringArray.length == 7){
                String colour = stringArray[0] + " " + stringArray[1];
                graph.addVertex(colour);
            }
            else{
                int numberOfColours = (stringArray.length - 4) / 4;
                String outerColour = stringArray[0] + " " + stringArray[1];
                graph.addVertex(outerColour);
                for(int x = 0; x < numberOfColours; x++){
                    String innerColour = stringArray[5+4*x] + " " + stringArray[6+4*x];
                    graph.addVertex(innerColour);
                    graph.addEdge(innerColour,outerColour);

                    }
                }
            }
        return (graph.Traverse(graph, "shiny gold")).size() - 1;
    }
    public static int returnNumberOfBags(List<String> allStrings){
        Graph graph = new Graph();
        Map<String,List<String>> stringMap = new HashMap<>();
        for(String string: allStrings){
            String[] stringArray = string.split(" ");
            if(stringArray.length == 7){
                String colour = stringArray[0] + " " + stringArray[1];
                graph.addVertex(colour);
                stringMap.put(colour,new ArrayList<String>());
            }
            else{
                int numberOfColours = (stringArray.length - 4) / 4;
                String innerColour = stringArray[0] + " " + stringArray[1];
                graph.addVertex(innerColour);
                for(int x = 0; x < numberOfColours; x++){
                    String outerColour = stringArray[5+4*x] + " " + stringArray[6+4*x];
                    graph.addVertex(outerColour);
                    graph.addEdge(innerColour,outerColour);
                    List<String> colourAndNumber = List.of(outerColour,stringArray[4+4*x]);
                    //System.out.println(colourAndNumber);
                    stringMap.put(outerColour,colourAndNumber);
                    if(innerColour.equals("shiny gold")) {
                        System.out.printf("%s,%s\n",innerColour,outerColour);
                    }
                }
            }
        }
        //for(Map.Entry<Vertex,List<Vertex>> entry: graph.adjVertices.entrySet()) {
            //System.out.println("new one");
            //System.out.println(entry.getKey().label);
            //for(Vertex v: entry.getValue()){
                //System.out.println(v.label);
            //}
        //}
        int count = 0;
        for(String visited: graph.Traverse(graph,"shiny gold")){
            if(!visited.equals("shiny gold")){
                count += graph.TraverseWithCount(graph, "shiny gold", visited, stringMap);
                System.out.println(visited);
                //System.out.println(count);
                //System.out.println(count);
            }
        }
        return count;
    }
    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day7\\text.txt");
        int totalColours = returnDistinctColoursThatContainShinyGoldBag(lines);
        System.out.println(totalColours);
        int totalColours1 = returnNumberOfBags(lines);
        System.out.println(totalColours1);
    }
}
