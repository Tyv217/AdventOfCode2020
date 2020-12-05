package Day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day4 {
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
                    tmp.append(" ").append(l);
                }
            }
        }
        return allStrings;
    }
    public static int countValidPassports(List<String> allStrings){
        int count = 0;
        for(String passport: allStrings){
            if (passport.contains("byr") && passport.contains("iyr") && passport.contains("eyr") &&
                    passport.contains("hgt") && passport.contains("hcl") && passport.contains("ecl") &&
                    passport.contains("pid")) {
                count++;
            }
        }
        return count;
    }
    public static int countValidPassportsV2(List<String> allStrings){
        int count = 0;
        for(String passport: allStrings){
            if (passport.contains("byr") && passport.contains("iyr") && passport.contains("eyr") &&
                    passport.contains("hgt") && passport.contains("hcl") && passport.contains("ecl") &&
                    passport.contains("pid")) {
                String[] arrayOfFields = passport.split(" ");
                Map<String, String> mapOfFields = new HashMap<>();
                for(String fields: arrayOfFields){
                    mapOfFields.put(fields.split(":")[0],fields.split(":")[1]);
                }
                boolean byr = Integer.parseInt(mapOfFields.get("byr")) >= 1920
                        && Integer.parseInt(mapOfFields.get("byr")) <= 2002;
                boolean iyr = Integer.parseInt(mapOfFields.get("iyr")) >= 2010
                        && Integer.parseInt(mapOfFields.get("iyr")) <= 2020;
                boolean eyr = Integer.parseInt(mapOfFields.get("eyr")) >= 2020
                        && Integer.parseInt(mapOfFields.get("eyr")) <= 2030;
                boolean hgt = false;
                String hgtString = mapOfFields.get("hgt");
                int hgtLength = hgtString.length();
                if(hgtString.startsWith("cm", hgtLength-2)){
                    int tmp = Integer.parseInt(hgtString.replace("cm",""));
                    if(tmp >= 150 && tmp <= 193){
                        hgt = true;
                    }
                }
                else if (hgtString.startsWith("in", hgtLength-2)){
                    int tmp = Integer.parseInt(hgtString.replace("in",""));
                    if(tmp >= 59 && tmp <= 76){
                        hgt = true;
                    }
                }
                boolean hcl = false;
                String hclString = mapOfFields.get("hcl");
                int hclLength = hclString.length();
                if(hclString.charAt(0) == '#'){
                    if(hclLength == 7 && hclString.substring(1,6).matches("[a-f0-9]+")){
                        hcl = true;
                    }
                }
                boolean ecl = false;
                String eclString = mapOfFields.get("ecl");
                List<String> eclMatchingList = List.of("amb", "blu", "brn", "gry", "grn", "hzl", "oth");
                for(String matchingString: eclMatchingList){
                    if(eclString.equals(matchingString)){
                        ecl = true;
                        break;
                    }
                }
                boolean pid = false;
                String pidString = mapOfFields.get("pid");
                if(pidString.length() == 9){
                    if(pidString.matches("[0-9]+")){
                        pid = true;
                    }
                }
                if(byr && iyr && eyr && hgt && hcl && ecl && pid){
                    count++;
                }
            }
        }
        return count;
    }
    public static void main(String[] args) {
        List<String> l = readFileInList("C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day4\\text.txt");
        List<String> allStrings = combineStrings(l);
        System.out.println(countValidPassports(allStrings));
        System.out.println(countValidPassportsV2(allStrings));
    }
}
