package Day14;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day14 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static long bitmaskSum(List<String> lines) {
        char[] mask = new char[36];
        Map<Long, Long> intMap = new HashMap<>();
        for (String instruction : lines) {
            String[] instructionArray = instruction.split(" ");
            if (instruction.charAt(1) == 'a') {
                mask = instructionArray[2].toCharArray();
            } else {
                long storedValue = Long.parseLong(instructionArray[2]);
                long storedIndex = Long.parseLong(instructionArray[0].substring(4, instructionArray[0].length() - 1));
                for (int x = 1; x <= mask.length; x++) {
                    char bit = mask[x - 1];
                    if (bit == '0') {
                        storedValue = storedValue & ((long) (Math.pow(2, 37)) - 1 - (long) (Math.pow(2, 36 - x)));
                    } else if (bit == '1') {
                        storedValue = storedValue | ((long) (Math.pow(2, 36 - x)));
                    }
                }
                intMap.put(storedIndex, storedValue);
            }
        }
        long counter = 0;
        for (Map.Entry<Long, Long> x : intMap.entrySet()) {
            counter += x.getValue();
        }
        return counter;
    }

    public static List<String> returnMaskCombinations(String mask) {
        List<String> maskCombinations = new ArrayList<>();
        char[] maskArray = mask.toCharArray();
        List<Integer> floatingPositions = new ArrayList<>();
        for (int x = 0; x < 36; x++) {
            if (maskArray[x] == 'X') {
                floatingPositions.add(x);
                maskArray[x] = '0';
            }
        }
        int numberOfPositions = floatingPositions.size();
        for (int y = 0; y < Math.pow(2, numberOfPositions); y++) {
            for (int z = 0; z < numberOfPositions; z++) {
                if (y % Math.pow(2, z) == 0) {
                    switch (maskArray[floatingPositions.get(z)]) {
                        case '0' -> maskArray[floatingPositions.get(z)] = '1';
                        case '1' -> maskArray[floatingPositions.get(z)] = '0';
                    }
                }
            }
            maskCombinations.add(String.valueOf(maskArray));
        }
        return maskCombinations;
    }

    public static long memoryAddressDecoder(List<String> lines) {
        Map<Long, Long> intMap = new HashMap<>();
        char[] mask = new char[36];
        for (String instruction : lines) {
            String[] instructionArray = instruction.split(" ");
            if (instruction.charAt(1) == 'a') {
                mask = instructionArray[2].toCharArray();
            } else {
                long index = Long.parseLong(instructionArray[0].substring(4, instructionArray[0].length() - 1));
                String address = Long.toBinaryString(index);
                int addressSize = address.length();
                char[] addressArray = new char[36];
                for (int x = addressSize - 1; x >= 0; x--) {
                    addressArray[35 - x] = address.charAt(addressSize - x - 1);
                }
                try {
                    for (int x = 0; x < 36 - addressSize; x++) {
                        addressArray[x] = '0';
                    }
                } catch (IndexOutOfBoundsException ignored) {
                }
                for (int x = 0; x < 36; x++) {
                    switch (mask[x]) {
                        case '0':
                            break;
                        case '1':
                            addressArray[x] = '1';
                            break;
                        case 'X':
                            addressArray[x] = 'X';
                            break;
                    }
                }
                List<String> addresses = returnMaskCombinations(String.valueOf(addressArray));
                for (String memoryAddress : addresses) {
                    long binaryAddress = Long.parseLong(memoryAddress,2);
                    long value = Long.parseLong(instructionArray[2]);
                    intMap.put(binaryAddress,value);
                }
            }
        }
        long counter = 0;
        for (Map.Entry<Long, Long> x : intMap.entrySet()) {
            counter += x.getValue();
        }
        return counter;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day14\\text.txt");
        System.out.println(bitmaskSum(lines));
        System.out.println(memoryAddressDecoder(lines));
    }
}