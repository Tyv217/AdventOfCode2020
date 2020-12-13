package Day10;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day10 {
    public static List<Integer> readFileInListToInt(String fileName) {
        List<String> stringLines = Collections.emptyList();
        try {
            stringLines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> intLines = new ArrayList<>();
        for(String numbers: stringLines){
            intLines.add(Integer.parseInt(numbers));
        }
        return intLines;
    }
    public static int oneJoltsTimesThreeJolts(List<Integer> intLines){
        intLines.add(0);
        Collections.sort(intLines);
        int oneJolts = 0;
        int threeJolts = 1;
        for(int x = 0; x < intLines.size() - 1; x++){
            if((intLines.get(x+1)-intLines.get(x) == 1)){
                oneJolts++;
            }
            else if((intLines.get(x+1)-intLines.get(x) == 3)){
                threeJolts++;
            }
        }
        return oneJolts * threeJolts;
    }
    public static long combinations(List<Integer> intLines){
        Collections.sort(intLines);
        intLines.add(intLines.get(intLines.size()-1)+3);
        System.out.println(intLines);
        long combinations = 1L;
        int counter = 0;
        for(int x = 0; x < intLines.size() - 1; x++){
            counter++;
            if((intLines.get(x+1)-intLines.get(x) == 3)){
                System.out.println(counter);
                switch(counter){
                    case 0:
                    case 1:
                    case 2:
                        break;
                    case 3:
                        combinations *= 2;
                        break;
                    case 4:
                        combinations *= 4;
                        break;
                    case 5:
                        combinations *= 7;
                        break;
                    case 6:
                        combinations *= 13;
                }
                counter = 0;
            }
        }
        return combinations;
    }

    public static void main(String[] args) {
        List<Integer> intLines = readFileInListToInt(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day10\\text.txt");
        System.out.println(oneJoltsTimesThreeJolts(intLines));
        System.out.println(combinations(intLines));
    }
}
