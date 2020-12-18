package Day16;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day16 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static int ticketScanningErrorRate(List<String> lines){
        int sumOfErrors = 0;
        int lineCounter = 0;
        List<String> fields = new ArrayList<>();
        List<String> nearbyTickets = new ArrayList<>();
        for(int x = 0; x < lines.size(); x++){
            if(lines.get(x).isEmpty()){
                lineCounter = x;
                break;
            }
            else{
                fields.add(lines.get(x));
            }
        }
        for(int x = lineCounter + 5; x < lines.size(); x++){
            nearbyTickets.add(lines.get(x));
        }
        List<List<Integer>> ListOfRanges = new ArrayList<>();
        for(String field: fields){
            String[] fieldArray = field.split("[ -]+");
            int length = fieldArray.length;
            List<Integer> Ranges = List.of(Integer.parseInt(fieldArray[length - 5]),
                    Integer.parseInt(fieldArray[length - 4]),
                    Integer.parseInt(fieldArray[length - 2]),Integer.parseInt(fieldArray[length - 1]));
            ListOfRanges.add(Ranges);
        }
        for(String nearbyTicket: nearbyTickets){
            String[] numbersToCheck = nearbyTicket.split(",");
            for(String numberToCheck: numbersToCheck){
                boolean isValid = false;
                int number = Integer.parseInt(numberToCheck);
                for(List<Integer> Range: ListOfRanges){
                        if((number >= Range.get(0) && number <= Range.get(1))
                                || (number >= Range.get(2) && number <= Range.get(3))){
                            isValid = true;
                            break;
                        }
                }
                if(!isValid){
                    sumOfErrors += number;
                }
            }
        }
        return sumOfErrors;
    }

    public static List<String> validTickets(List<String> lines){
        List<String> validTickets = new ArrayList<>();
        int lineCounter = 0;
        List<String> fields = new ArrayList<>();
        List<String> nearbyTickets = new ArrayList<>();
        for(int x = 0; x < lines.size(); x++){
            if(lines.get(x).isEmpty()){
                lineCounter = x;
                break;
            }
            else{
                fields.add(lines.get(x));
            }
        }
        for(int x = lineCounter + 5; x < lines.size(); x++){
            nearbyTickets.add(lines.get(x));
        }
        List<List<Integer>> ListOfRanges = new ArrayList<>();
        for(String field: fields){
            String[] fieldArray = field.split("[ -]+");
            int length = fieldArray.length;
            List<Integer> Ranges = List.of(Integer.parseInt(fieldArray[length - 5]),
                    Integer.parseInt(fieldArray[length - 4]),
                    Integer.parseInt(fieldArray[length - 2]),Integer.parseInt(fieldArray[length - 1]));
            ListOfRanges.add(Ranges);
        }
        for(String nearbyTicket: nearbyTickets){
            boolean isTicketValid = true;
            String[] numbersToCheck = nearbyTicket.split(",");
            for(String numberToCheck: numbersToCheck){
                boolean isFieldValid = false;
                int number = Integer.parseInt(numberToCheck);
                for(List<Integer> Range: ListOfRanges){
                    if((number >= Range.get(0) && number <= Range.get(1))
                            || (number >= Range.get(2) && number <= Range.get(3))){
                        isFieldValid = true;
                        break;
                    }
                }
                if(!isFieldValid){
                    isTicketValid = false;
                    break;
                }
            }
            if(isTicketValid){
                validTickets.add(nearbyTicket);
            }
        }
        return validTickets;
    }
    public static boolean inRange(int x, int lowerBound, int upperBound){
        return x < lowerBound || x > upperBound;
    }

    public static long departureFieldsMultiplied(List<String> lines){
        List<String> validTickets = validTickets(lines);
        int lineCounter = 0;
        List<String> fields = new ArrayList<>();
        String yourTicket;
        List<String[]> nearbyTickets = new ArrayList<>();
        for(int x = 0; x < lines.size(); x++){
            if(lines.get(x).isEmpty()){
                lineCounter = x;
                break;
            }
            else{
                fields.add(lines.get(x));
            }
        }
        yourTicket = lines.get(lineCounter + 2);
        for(String validTicket: validTickets){
            nearbyTickets.add(validTicket.split(","));
        }
        Map<String,List<Integer>> possibleCorrectFields = new HashMap<>();
        // There are more than one matching slots for each field, therefore we need to create a list to store them all.
        for (String field: fields) {
            // for each field, find the corresponding matching slots
            List<Integer> correspondingFieldNumbers = new ArrayList<>();
            String[] fieldArray = field.split("[ -]+");
            int length = fieldArray.length;
            int lowerBound1 = Integer.parseInt(fieldArray[length - 5]);
            int upperBound1 = Integer.parseInt(fieldArray[length - 4]);
            int lowerBound2 = Integer.parseInt(fieldArray[length - 2]);
            int upperBound2 = Integer.parseInt(fieldArray[length - 1]);
            for (int x = 0; x < nearbyTickets.get(0).length; x++) {
                // loop through each slot
                boolean isCorrespondingField = true;
                for (String[] Ticket : nearbyTickets) {
                    // loop through each ticket
                    int fieldToTest = Integer.parseInt(Ticket[x]);
                    if ((inRange(fieldToTest, lowerBound1, upperBound1))
                            && (inRange(fieldToTest, lowerBound2, upperBound2))) {
                        isCorrespondingField = false;
                        break;
                    }
                }
                if (isCorrespondingField) {
                    correspondingFieldNumbers.add(x);
                }
            }
            String[] currentFieldArray = field.split(" ");
            String currentField;
            if (currentFieldArray.length == 5) {
                currentField = currentFieldArray[0] + " " + currentFieldArray[1];
            } else {
                currentField = currentFieldArray[0];
            }
            possibleCorrectFields.put(currentField, correspondingFieldNumbers);
        }
        Map<String,Integer> correctFields = new HashMap<>();
        int size = possibleCorrectFields.size();
        for(int x = 0; x < size; x++){
            int correctFieldNumber = -1;
            for(Map.Entry<String,List<Integer>> entry: possibleCorrectFields.entrySet()){
                if(entry.getValue().size() == 1){
                    // if the list only has one element, then it must be that slot
                    int y = entry.getValue().get(0);
                    correctFields.put(entry.getKey(),y);
                    correctFieldNumber = y;
                    break;
                }
            }
            Map<String,List<Integer>> newMap = new HashMap<>();
            /*
            I was unsure if you can directly edit the map you are iterating through, since you cannot do so for
            collections without using an iterator, therefore just to be safe I decided to create a new map everytime and
            replace the old map with the new on. Might be a bit more inefficient but at least I won't have to worry
            about random crashes.
            */
            for(Map.Entry<String,List<Integer>> entry: possibleCorrectFields.entrySet()){
                List<Integer> newList = new ArrayList<>(entry.getValue());
                if(entry.getValue().contains(correctFieldNumber)){
                    newList.remove(Integer.valueOf(correctFieldNumber));
                }
                newMap.put(entry.getKey(),newList);
            }
            possibleCorrectFields = newMap;
        }
        String[] yourTicketArray = yourTicket.split(",");
        long departureFieldsMultiplied = 1;
        for(Map.Entry<String,Integer> correctField: correctFields.entrySet()){
            if(correctField.getKey().contains("departure")){
                departureFieldsMultiplied *= Integer.parseInt(yourTicketArray[correctField.getValue()]);
            }
        }
        return departureFieldsMultiplied;
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day16\\text.txt");
        System.out.println(ticketScanningErrorRate(lines));
        System.out.println(departureFieldsMultiplied(lines));
    }
}
