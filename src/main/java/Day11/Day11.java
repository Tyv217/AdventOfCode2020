package Day11;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day11 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }
    public static int numberOfOccupiedSeatsAdjacent(List<String> lines, int row, int column){
        int occupiedNeighbours = 0;
        for(int x = 0; x < 3; x++){
            try{
                if(lines.get(row-1).charAt(column-1+x) == '#'){
                    occupiedNeighbours++;
                }
            }
            catch(IndexOutOfBoundsException ignored){}
            try{
                if(lines.get(row+1).charAt(column-1+x) == '#'){
                    occupiedNeighbours++;
                }
            }
            catch(IndexOutOfBoundsException ignored){}
        }
        try{
            if(lines.get(row).charAt(column-1) == '#'){
                occupiedNeighbours++;
            }
        }
        catch(IndexOutOfBoundsException ignored){}
        try{
            if(lines.get(row).charAt(column+1) == '#'){
                occupiedNeighbours++;
            }
        }
        catch(IndexOutOfBoundsException ignored){}
        return occupiedNeighbours;
    }

    public static List<String> returnNewLayout(List<String> lines){
        List<String> newLayout = new ArrayList<>();
        for(int row = 0; row < lines.size(); row++){
            char[] rowLayout = new char[lines.get(0).length()];
            for(int col = 0; col < lines.get(0).length(); col++){
                switch(lines.get(row).charAt(col)){
                    case '.':
                        rowLayout[col] = '.';
                        break;
                    case 'L':
                        if(numberOfOccupiedSeatsAdjacent(lines,row,col) == 0){
                            rowLayout[col] = '#';
                        }
                        else{
                            rowLayout[col] = 'L';
                        }
                        break;
                    case '#':
                        if(numberOfOccupiedSeatsAdjacent(lines,row,col) >= 4){
                            rowLayout[col] = 'L';
                        }
                        else{
                            rowLayout[col] = '#';
                        }
                        break;
                }
            }
            String string = String.valueOf(rowLayout);
            newLayout.add(string);
        }
        return newLayout;
    }
    public static List<String> loopUntilSameLayout(List<String> lines){
        while(!lines.equals(returnNewLayout(lines))){
            lines = returnNewLayout(lines);
        }
        return returnNewLayout(lines);
    }

    public static int numberOfOccupiedSeatsAdjacentNewRules(List<String> lines, int row, int col){
        int occupiedNeighbours = 0;
        int height = lines.size() - 1;
        int width = lines.get(0).length() - 1;
        if(!(row == 0 || col == 0)){
            for(int x = 1; x <= Math.min(row, col); x++){
                if(lines.get(row-x).charAt(col - x) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row-x).charAt(col - x) == 'L') {
                    break;
                }
            }
        }
        if(!(row == 0)){
            for(int x = 1; x <= row; x++){
                if(lines.get(row-x).charAt(col) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row-x).charAt(col) == 'L') {
                    break;
                }
            }
        }
        if(!(row == 0 || col == width)){
            for(int x = 1; x <= Math.min(row,width - col); x++){
                if(lines.get(row-x).charAt(col+x) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row-x).charAt(col+x) == 'L') {
                    break;
                }
            }
        }
        if(!(col == width)){
            for(int x = 1; x <= width - col; x++){
                if(lines.get(row).charAt(col + x) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row).charAt(col + x) == 'L') {
                    break;
                }
            }
        }
        if(!(row == height || col == width)){
            for(int x = 1; x <= Math.min(height - row,width - col); x++){
                if(lines.get(row+x).charAt(col+x) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row+x).charAt(col+x) == 'L') {
                    break;
                }
            }
        }
        if(!(row == height)){
            for(int x = 1; x <= height - row; x++){
                if(lines.get(row+x).charAt(col) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row+x).charAt(col) == 'L') {
                    break;
                }
            }
        }
        if(!(row == height || col == 0)){
            for(int x = 1; x <= Math.min(height - row,col); x++){
                if(lines.get(row+x).charAt(col-x) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row+x).charAt(col-x) == 'L') {
                    break;
                }
            }
        }
        if(!(col == 0)){
            for(int x = 1; x <= col; x++){
                if(lines.get(row).charAt(col-x) == '#'){
                    occupiedNeighbours++;
                    break;
                }
                else if (lines.get(row).charAt(col-x) == 'L') {
                    break;
                }
            }
        }
        return occupiedNeighbours;
    }
    public static List<String> returnNewLayoutNewRules(List<String> lines){
        List<String> newLayout = new ArrayList<>();
        for(int row = 0; row < lines.size(); row++){
            char[] rowLayout = new char[lines.get(0).length()];
            for(int col = 0; col < lines.get(0).length(); col++){
                switch(lines.get(row).charAt(col)){
                    case '.':
                        rowLayout[col] = '.';
                        break;
                    case 'L':
                        if(numberOfOccupiedSeatsAdjacentNewRules(lines,row,col) == 0){
                            rowLayout[col] = '#';
                        }
                        else{
                            rowLayout[col] = 'L';
                        }
                        break;
                    case '#':
                        if(numberOfOccupiedSeatsAdjacentNewRules(lines,row,col) >= 5){
                            rowLayout[col] = 'L';
                        }
                        else{
                            rowLayout[col] = '#';
                        }
                        break;
                }
            }
            String string = String.valueOf(rowLayout);
            newLayout.add(string);
        }
        return newLayout;
    }
    public static List<String> loopUntilSameLayoutNewRules(List<String> lines){
        while(!lines.equals(returnNewLayoutNewRules(lines))){
            lines = returnNewLayoutNewRules(lines);
        }
        return returnNewLayoutNewRules(lines);
    }

    public static int countOccupiedSeats(List<String> Layout){
        int occupiedSeats = 0;
        for(String s: Layout){
            for(int x = 0; x < s.length(); x++){
                if(s.charAt(x) == '#'){
                    occupiedSeats++;
                }
            }
        }
        return occupiedSeats;
    }



    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day11\\text.txt");
        List<String> finalizedLayout = loopUntilSameLayout(lines);
        System.out.println(countOccupiedSeats(finalizedLayout));
        List<String> finalizedLayout1 = loopUntilSameLayoutNewRules(lines);
        System.out.println(countOccupiedSeats(finalizedLayout1));
    }
}
