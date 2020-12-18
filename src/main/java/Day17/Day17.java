package Day17;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day17 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static int activeNeighbours3D(Set<List<Integer>> activeCubes, List<Integer> coordinates){
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        int z = coordinates.get(2);
        int activeNeighbours = 0;
        for(int x1 = -1; x1 < 2; x1 ++){
            for(int y1 = -1; y1 < 2; y1 ++){
                for(int z1 = -1; z1 < 2; z1 ++){
                    if(!(x1 == 0 && y1 == 0 && z1 == 0)){
                        if(activeCubes.contains(List.of(x+x1,y+y1,z+z1))){
                            activeNeighbours++;
                        }
                    }
                }
            }
        }
        return activeNeighbours;
    }

    public static int conwayCubes3D(List<String> lines){
        Set<List<Integer>> activeCubes = new HashSet<>();
        for(int y = 0; y < lines.size(); y++){
            for(int x = 0; x < lines.get(0).length(); x++){
                if(lines.get(y).charAt(x) == '#'){
                    activeCubes.add(List.of(x,y,0));
                }
            }
        }
        int xLower = 0;
        int xUpper = lines.get(0).length() - 1;
        int yLower = 0;
        int yUpper = lines.size() - 1;
        int zLower = 0;
        int zUpper = 0;
        for(int rounds = 1; rounds <= 6; rounds ++){
            xLower--;
            xUpper++;
            yLower--;
            yUpper++;
            zLower--;
            zUpper++;
            Set<List<Integer>> newActiveCubes = new HashSet<>();
            for(int x = xLower; x <= xUpper; x++){
                for(int y = yLower; y <= yUpper; y++){
                    for(int z = zLower; z <= zUpper; z++){
                        int activeNeighbours = activeNeighbours3D(activeCubes,List.of(x,y,z));
                        if(activeCubes.contains(List.of(x,y,z))){
                            if(activeNeighbours == 2 || activeNeighbours == 3){
                                newActiveCubes.add(List.of(x,y,z));
                            }
                        }
                        else{
                            if(activeNeighbours == 3){
                                newActiveCubes.add(List.of(x,y,z));
                            }
                        }
                    }
                }
            }
            activeCubes = newActiveCubes;
        }
        return activeCubes.size();
    }

    public static int activeNeighbours4D(Set<List<Integer>> activeCubes, List<Integer> coordinates){
        int x = coordinates.get(0);
        int y = coordinates.get(1);
        int z = coordinates.get(2);
        int w = coordinates.get(3);
        int activeNeighbours = 0;
        for(int x1 = -1; x1 < 2; x1 ++){
            for(int y1 = -1; y1 < 2; y1 ++){
                for(int z1 = -1; z1 < 2; z1 ++){
                    for(int w1 = -1; w1 < 2; w1++){
                        if(!(x1 == 0 && y1 == 0 && z1 == 0 && w1 == 0)){
                            if(activeCubes.contains(List.of(x+x1,y+y1,z+z1,w+w1))){
                                activeNeighbours++;
                            }
                        }
                    }
                }
            }
        }
        return activeNeighbours;
    }

    public static int conwayCubes4D(List<String> lines){
        Set<List<Integer>> activeCubes = new HashSet<>();
        for(int y = 0; y < lines.size(); y++){
            for(int x = 0; x < lines.get(0).length(); x++){
                if(lines.get(y).charAt(x) == '#'){
                    activeCubes.add(List.of(x,y,0,0));
                }
            }
        }
        int xLower = 0;
        int xUpper = lines.get(0).length() - 1;
        int yLower = 0;
        int yUpper = lines.size() - 1;
        int zLower = 0;
        int zUpper = 0;
        int wLower = 0;
        int wUpper = 0;
        for(int rounds = 1; rounds <= 6; rounds ++){
            xLower--;
            xUpper++;
            yLower--;
            yUpper++;
            zLower--;
            zUpper++;
            wLower--;
            wUpper++;
            Set<List<Integer>> newActiveCubes = new HashSet<>();
            for(int x = xLower; x <= xUpper; x++){
                for(int y = yLower; y <= yUpper; y++){
                    for(int z = zLower; z <= zUpper; z++){
                        for(int w = wLower; w<= wUpper; w++){
                            int activeNeighbours = activeNeighbours4D(activeCubes,List.of(x,y,z,w));
                            if(activeCubes.contains(List.of(x,y,z,w))){
                                if(activeNeighbours == 2 || activeNeighbours == 3){
                                    newActiveCubes.add(List.of(x,y,z,w));
                                }
                            }
                            else{
                                if(activeNeighbours == 3){
                                    newActiveCubes.add(List.of(x,y,z,w));
                                }
                            }
                        }
                    }
                }
            }
            activeCubes = newActiveCubes;
        }
        return activeCubes.size();
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day17\\text.txt");
        System.out.println(conwayCubes3D(lines));
        System.out.println(conwayCubes4D(lines));
    }
}
