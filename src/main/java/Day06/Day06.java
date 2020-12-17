package Day06;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day06 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static List<String> combineStrings(List<String> lines){
        List<String> allStrings = new ArrayList<>();
        lines.add("");
        StringBuilder tmp = new StringBuilder();
        for(String l: lines){
            if(l.isEmpty()){
                allStrings.add(tmp.toString());
                tmp = new StringBuilder();
            }
            else{
                if(tmp.length() == 0){
                    tmp = new StringBuilder(l);
                }
                else{
                    tmp.append(l);
                }
            }
        }
        return allStrings;
    }

    public static int countDistinctQuestions(List<String> allStrings){
        int totalAnswers = 0;
        for(String entries: allStrings){
            Set<Character> setOfChars = new HashSet<>();
            for(int i = 0; i < entries.length();i++){
                setOfChars.add(entries.charAt(i));
            }
            totalAnswers += setOfChars.size();
        }
        return totalAnswers;
    }

    public static List<Map.Entry<String, Integer>> combineStringsAndNumberOfStrings(List<String> lines){
        List<Map.Entry<String, Integer>> allStrings = new ArrayList<>();
        StringBuilder tmp = new StringBuilder();
        int count = 0;
        for(String l: lines){
            if(l.isEmpty()){
                allStrings.add(Map.entry(tmp.toString(),count));
                tmp = new StringBuilder();
                count = 0;
            }
            else{
                if(tmp.length() == 0){
                    tmp = new StringBuilder(l);
                }
                else{
                    tmp.append(l);
                }
                count++;
            }
        }
        return allStrings;
    }

    public static int countQuestionsEveryoneAnsweredYes(List<Map.Entry<String, Integer>> allStrings){
        int totalAnswers = 0;
        for(Map.Entry<String, Integer> entries: allStrings){
            Map<Character, Integer> setOfChars = new HashMap<>();
            String entryKey = entries.getKey();
            for(int i = 0; i < entryKey.length();i++){
                setOfChars.put(entryKey.charAt(i),setOfChars.getOrDefault(entryKey.charAt(i), 0) + 1);
            }
            for(Map.Entry<Character, Integer> characters: setOfChars.entrySet()){
                if(characters.getValue().equals(entries.getValue())){
                    totalAnswers++;
                }
            }
        }
        return totalAnswers;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day6\\text.txt");
        List<String> allStrings = combineStrings(lines);
        int totalAnswers = countDistinctQuestions(allStrings);
        System.out.println(totalAnswers);
        List<Map.Entry<String, Integer>> allStrings1 = combineStringsAndNumberOfStrings(lines);
        int totalAnswers1 = countQuestionsEveryoneAnsweredYes(allStrings1);
        System.out.println(totalAnswers1);
    }
}
