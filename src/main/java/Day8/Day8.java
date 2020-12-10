package Day8;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day8 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public static int executeInstructions(List<String> lines){
        int currentInstruction = 0;
        int accumulator = 0;
        Map<Integer, Integer> mapCheckDuplicateInstruction = new HashMap<>();
        while(!(mapCheckDuplicateInstruction.containsKey(currentInstruction))){
            mapCheckDuplicateInstruction.put(currentInstruction,0);
            String[] instruction = lines.get(currentInstruction).split(" ");
            switch (instruction[0]) {
                case "nop" -> currentInstruction++;
                case "acc" -> {
                    accumulator += Integer.parseInt(instruction[1]);
                    currentInstruction++;
                }
                case "jmp" -> currentInstruction += Integer.parseInt(instruction[1]);
            }
        }
        return accumulator;
    }
    public static List<Integer> testInstructionsForLoop(List<String> lines){
        int currentInstruction = 0;
        int accumulator = 0;
        int trueFalse = 0;
        Map<Integer, Integer> mapCheckDuplicateInstruction = new HashMap<>();
        while(currentInstruction < lines.size()){
            if(mapCheckDuplicateInstruction.containsKey(currentInstruction)){
                trueFalse = 1;
                break;
            }
            mapCheckDuplicateInstruction.put(currentInstruction,0);
            String[] instruction = lines.get(currentInstruction).split(" ");
            switch (instruction[0]) {
                case "nop" -> currentInstruction++;
                case "acc" -> {
                    accumulator += Integer.parseInt(instruction[1]);
                    currentInstruction++;
                }
                case "jmp" -> currentInstruction += Integer.parseInt(instruction[1]);
            }
        }
        List<Integer> intList = new ArrayList<>();
        intList.add(0,trueFalse);
        intList.add(1,accumulator);
        return intList;
    }
    public static int checkValidInstructions(List<String> lines){
        int returnValue = -1;
        for(int x = 0; x < lines.size(); x++){
            List<String> modifiedLines = new ArrayList<>(lines);
            String instruction = modifiedLines.get(x);
            switch(instruction.substring(0,3)){
                case "acc":
                    break;
                case "nop":
                    instruction = "jmp" + instruction.substring(3);
                    modifiedLines.set(x,instruction);
                    break;
                case "jmp":
                    instruction = "nop" + instruction.substring(3);
                    modifiedLines.set(x,instruction);
                    break;
            }
            List<Integer> intList = testInstructionsForLoop(modifiedLines);
            if(intList.get(0) == 0){
                returnValue = intList.get(1);
                break;
            }
        }
        return returnValue;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day8\\text.txt");
        System.out.println(executeInstructions(lines));
        System.out.println(checkValidInstructions(lines));
    }
}
