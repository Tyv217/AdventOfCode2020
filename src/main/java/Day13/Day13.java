package Day13;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Day13 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public static int earliestBus(List<String> lines){
        int departureTime = Integer.parseInt(lines.get(0));
        String[] buses = lines.get(1).split("[,x]+");
        int earliestBus = -1;
        int waitingTime = 0;
        label:
        for(int x = 0; x < Integer.parseInt(buses[0]); x++){
            for(String bus: buses){
                int currentBus = Integer.parseInt(bus);
                if((departureTime+x) % (currentBus) == 0){
                    earliestBus = currentBus;
                    waitingTime = x;
                    break label;
                }
            }
        }
        return earliestBus * waitingTime;
    }

    public static long modularInverse(long number, int modulus){
        long inverse = 0;
        for(int x = 1; x < modulus; x++){
            if((x * number) % modulus == 1){
                inverse = x;
            }
        }
        return inverse;
    }

    public static long busCRT(List<String> lines){
        //implement the Chinese Remainder Theorem
        long departureTime = 0L;
        String[] buses = lines.get(1).split("[,]");
        Map<Integer,Integer> busToTimeMap = new HashMap<>();
        long totalProduct = 1L;
        for(int x = 0; x < buses.length; x++){
            if(!buses[x].equals("x")){
                int bus = Integer.parseInt(buses[x]);
                busToTimeMap.put(bus,x);
                totalProduct *= bus;
            }
        }
        for(Map.Entry<Integer,Integer> busToTime: busToTimeMap.entrySet()){
            long N_i = totalProduct / busToTime.getKey();
            departureTime += (busToTime.getKey() - busToTime.getValue()) * N_i * modularInverse(N_i,busToTime.getKey());
        }
        departureTime %= totalProduct;
        return departureTime;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day13\\text.txt");
        System.out.println(earliestBus(lines));
        System.out.println(busCRT(lines));
    }
}
