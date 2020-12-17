package Day03;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day03 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static int numberOfTreesEncountered(List<char[]> charList, int Right, int Down){
        int index = 0;
        int trees = 0;
        int lengthOfPath = charList.get(0).length;
        for(int i = 0; i< charList.size(); i += Down){
            if(charList.get(i)[index] == '#'){
                trees++;
            }
            index = (index + Right) % lengthOfPath;
        }
        return trees;
    }

    public static void main(String[] args) {
        List<String> l = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day3\\text.txt");
        List<char[]> charList = new ArrayList<>(l.size());
        for(String line: l){
            charList.add(line.toCharArray());
        }
        long trees = 1;
        trees *= numberOfTreesEncountered(charList, 1, 1);
        System.out.println(trees);
        trees *= numberOfTreesEncountered(charList, 3, 1);
        System.out.println(trees);
        trees *= numberOfTreesEncountered(charList, 5, 1);
        System.out.println(trees);
        trees *= numberOfTreesEncountered(charList, 7, 1);
        System.out.println(trees);
        trees *= numberOfTreesEncountered(charList, 1, 2);
        System.out.println(trees);
    }
}
