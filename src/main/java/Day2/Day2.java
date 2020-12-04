package Day2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day2 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static void main(String[] args) {
        int validPasswords = 0;
        int validPasswordsv2 = 0;
        List<String> l = readFileInList("C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day2\\text.txt");
        for(String line: l){
            String[] splited = line.split("\\s+");
            String[] ints = splited[0].split("-");
            int lowerBound = Integer.parseInt(ints[0]);
            int upperBound = Integer.parseInt(ints[1]);
            char charToMatchTo = splited[1].charAt(0);
            long count = splited[2].chars().filter(ch -> ch == charToMatchTo).count();
            if (count >= lowerBound && count <= upperBound) {
                validPasswords++;
            }
            char[] chars = splited[2].toCharArray();
            if(chars[lowerBound-1] == charToMatchTo){
                if (chars[upperBound-1] != charToMatchTo){
                    validPasswordsv2++;
                }
            }
            else if (chars[upperBound-1] == charToMatchTo){
                validPasswordsv2++;
            }
        }
        System.out.println(validPasswords);
        System.out.println(validPasswordsv2);
    }
}