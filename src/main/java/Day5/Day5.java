package Day5;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static int findMaxSeatID(List<String> lines){
        int max = 0;
        for(String seatPartitions: lines){
            int lowerBound = 0;
            int upperBound = 127;
            int seatID = 0;
            for(int i = 0; i < 7; i++){
                if(seatPartitions.charAt(i) == 'F'){
                    int difference = upperBound - lowerBound;
                    upperBound -= (difference+1)/2;
                }
                else if(seatPartitions.charAt(i) == 'B'){
                    int difference = upperBound - lowerBound;
                    lowerBound += (difference+1)/2;
                }
            }
            if(lowerBound == upperBound){
                seatID = lowerBound*8;
            }
            int lowerBound1 = 0;
            int upperBound1 = 7;
            for(int i = 7; i < 9; i++){
                if(seatPartitions.charAt(i) == 'L'){
                    int difference = upperBound1 - lowerBound1;
                    upperBound1 -= (difference+1)/2;
                }
                else if(seatPartitions.charAt(i) == 'R'){
                    int difference = upperBound1 - lowerBound1;
                    lowerBound1 += (difference+1)/2;
                }
            }
            if(seatPartitions.charAt(9) == 'L'){
                seatID += lowerBound1;
            }
            else if(seatPartitions.charAt(9) == 'R'){
                seatID += upperBound1;
            }
            if (seatID > max){
                max = seatID;
            }
        }
        return max;
    }

    public static int findMySeatID(List<String> lines) {
        List<Integer> seatIdList = new ArrayList<>(lines.size());
        for (String seatPartitions : lines) {
            int lowerBound = 0;
            int upperBound = 127;
            int seatID = 0;
            for (int i = 0; i < 7; i++) {
                if (seatPartitions.charAt(i) == 'F') {
                    int difference = upperBound - lowerBound;
                    upperBound -= (difference + 1) / 2;
                } else if (seatPartitions.charAt(i) == 'B') {
                    int difference = upperBound - lowerBound;
                    lowerBound += (difference + 1) / 2;
                }
            }
            if (lowerBound == upperBound) {
                seatID = lowerBound * 8;
            }
            int lowerBound1 = 0;
            int upperBound1 = 7;
            for (int i = 7; i < 9; i++) {
                if (seatPartitions.charAt(i) == 'L') {
                    int difference = upperBound1 - lowerBound1;
                    upperBound1 -= (difference + 1) / 2;
                } else if (seatPartitions.charAt(i) == 'R') {
                    int difference = upperBound1 - lowerBound1;
                    lowerBound1 += (difference + 1) / 2;
                }
            }
            if (seatPartitions.charAt(9) == 'L') {
                seatID += lowerBound1;
            } else if (seatPartitions.charAt(9) == 'R') {
                seatID += upperBound1;
            }
            seatIdList.add(seatID);
        }
        Collections.sort(seatIdList);
        int returnSeatID = 0;
        for(int i = 0; i < seatIdList.size() - 1; i++){
            if (seatIdList.get(i+1) == (seatIdList.get(i)+2)){
                returnSeatID = seatIdList.get(i) + 1;
            }
        }
        return returnSeatID;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day5\\text.txt");
        System.out.println(findMaxSeatID(lines));
        System.out.println(findMySeatID(lines));
    }
}
