package Day12;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Day12 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public static long manhattanDistance(List<String> lines){
        double x = 0;
        double y = 0;
        double angle = 0;
        for(String instruction: lines){
            int inputValue = Integer.parseInt(instruction.substring(1));
            switch (instruction.charAt(0)) {
                case 'N' -> y += inputValue;
                case 'S' -> y -= inputValue;
                case 'E' -> x += inputValue;
                case 'W' -> x -= inputValue;
                case 'L' -> angle += inputValue;
                case 'R' -> angle -= inputValue;
                case 'F' -> {
                    x += Math.cos(angle * Math.PI / 180) * inputValue;
                    y += Math.sin(angle * Math.PI / 180) * inputValue;
                }
            }
        }
        return Math.round(Math.abs(x) + Math.abs(y));
    }

    public static long manhattanDistanceNewInstructions(List<String> lines){
        double xShip = 0;
        double yShip = 0;
        double xWayPoint = 10;
        double yWayPoint = 1;
        for(String instruction: lines){
            int inputValue = Integer.parseInt(instruction.substring(1));
            switch (instruction.charAt(0)) {
                case 'N' -> yWayPoint += inputValue;
                case 'S' -> yWayPoint -= inputValue;
                case 'E' -> xWayPoint += inputValue;
                case 'W' -> xWayPoint -= inputValue;
                case 'L' -> {
                    double xTemp = xWayPoint - xShip;
                    double yTemp = yWayPoint - yShip;
                    double sin = Math.sin(inputValue*Math.PI/180);
                    double cos = Math.cos(inputValue*Math.PI/180);
                    xWayPoint = xTemp*(cos) - yTemp*(sin) + xShip;
                    yWayPoint = xTemp*(sin) + yTemp*(cos) + yShip;
                }
                case 'R' -> {
                    double xTemp = xWayPoint - xShip;
                    double yTemp = yWayPoint - yShip;
                    double sin = Math.sin(-inputValue*Math.PI/180);
                    double cos = Math.cos(-inputValue*Math.PI/180);
                    xWayPoint = xTemp*(cos) - yTemp*(sin) + xShip;
                    yWayPoint = xTemp*(sin) + yTemp*(cos) + yShip;
                }
                case 'F' -> {
                    double xTemp = inputValue * (xWayPoint - xShip);
                    double yTemp = inputValue * (yWayPoint - yShip);
                    xShip += xTemp;
                    yShip += yTemp;
                    xWayPoint += xTemp;
                    yWayPoint += yTemp;
                }
            }
        }
        return Math.round(Math.abs(xShip) + Math.abs(yShip));
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day12\\text.txt");
        System.out.println(manhattanDistance(lines));
        System.out.println(manhattanDistanceNewInstructions(lines));
    }
}
