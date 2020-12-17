package Day15;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day15 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.asList(lines.get(0).split(",").clone());
    }

    public static int elvesGame(List<String> lines, int turns){
        Map<Integer,Integer> numberToAge = new HashMap<>();
        int turnNumber = 1;
        int currentNumber = 0;
        int newNumber = 0;
        while(turnNumber <= turns){
            if(turnNumber <= lines.size()){
                currentNumber = Integer.parseInt(lines.get(turnNumber-1));
            }
            else {
                currentNumber = newNumber;
            }
            newNumber = numberToAge.getOrDefault(currentNumber,0);
            numberToAge.put(currentNumber,0);
            numberToAge.replaceAll((k, v) -> v + 1);
            turnNumber++;
        }
        return currentNumber;
    }

    public static int optimizedElvesGame(List<String> lines, int turns){
        int[] lastOccurrenceArray = new int[turns];
        int turnNumber = lines.size() + 1;
        int lastNumberSpoken = Integer.parseInt(lines.get(lines.size() - 1));
        for(int x = 0; x < lines.size() - 1; x++){
            lastOccurrenceArray[Integer.parseInt(lines.get(x))] = x+1;
        }
        while(turnNumber <= turns){
            int lastOccurrence = lastOccurrenceArray[lastNumberSpoken];
            lastOccurrenceArray[lastNumberSpoken] = turnNumber - 1;
            if(lastOccurrence == 0){
                lastNumberSpoken = 0;
            }
            else{
                lastNumberSpoken = turnNumber - lastOccurrence - 1;
            }
            turnNumber++;
        }
        return lastNumberSpoken;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day15\\text.txt");
        System.out.println(elvesGame(lines, 2020));
        System.out.println(optimizedElvesGame(lines,30000000));
    }
}
