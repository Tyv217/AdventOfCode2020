package Day09;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day09 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static boolean isSum(List<String> lines, long argument){
        boolean isSum = false;
        for(int x = 0; x < lines.size()-1;x++){
            for(int y = x+1; y < lines.size(); y++){
                if((Long.parseLong(lines.get(x)) + Long.parseLong(lines.get(y))) == argument){
                    isSum = true;
                }
            }
        }
        return isSum;
    }

    public static long firstWeakness(List<String> lines){
        long firstWeakness = -1;
        List<String> stringList = new ArrayList<>();
        for(int x = 0; x < 25; x++){
            stringList.add(lines.get(x));
        }
        for(int y = 25; y < lines.size()-1;y++){
            stringList.set((y%25),lines.get(y));
            if(!(isSum(stringList,Long.parseLong(lines.get(y+1))))){
                firstWeakness = Long.parseLong(lines.get(y+1));
            }
        }
        return firstWeakness;
    }
    public static long returnSubArray(List<String> lines, long sum){
        int lowerBound = 0;
        int upperBound = 0;
        for(int x = 0; x < lines.size(); x++){
            for(int y = 2; y < (lines.size() - x); y++){
                double tmpSum = 0;
                for(int z = 0; z < y; z++){
                    tmpSum += Long.parseLong(lines.get(x+z));
                }
                if(tmpSum == sum){
                    lowerBound = x;
                    upperBound = y;
                }
            }
        }
        long max = 0;
        long min = Long.parseLong(lines.get(lowerBound));
        for(int y1 = 0; y1 < upperBound; y1++){
            long currentLong = Long.parseLong(lines.get(lowerBound+y1));
            if(currentLong > max){
                max = currentLong;
            }
            if(currentLong < min){
                min = currentLong;
            }
        }
        return max + min;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day9\\text.txt");
        long firstWeakness = firstWeakness(lines);
        System.out.println(firstWeakness);
        System.out.println(returnSubArray(lines,firstWeakness));
    }
}
