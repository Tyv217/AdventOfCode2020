package Day1;
import java.util.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.io.*;
public class Find2020Sum {
    public static List<Integer> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Integer> intList = new ArrayList<>();
        for(String s : lines) intList.add(Integer.valueOf(s));
        return intList;
    }

    public static void main(String[] args) {
        List<Integer> l = readFileInList("C:\\Users\\thoma\\OneDrive\\Desktop\\test.txt");
        Collections.sort(l);
        label:
        for(int i = 0; i < l.size()-1; i++){
            for(int j = i+1; j < l.size(); j++){
                if(l.get(j)+l.get(i) == 2020){
                    System.out.printf("%d, %d, %d\n",l.get(i),l.get(j),l.get(j)*l.get(i));
                }
                else if (l.get(j)+l.get(i) > 2020){
                    continue label;
                }
            }
        }
        for(int i = 0; i < l.size()-2; i++){
            label1:
            for(int j = i+1; j < l.size()-1; j++){
                for(int k = j+1; k < l.size(); k++){
                    if(l.get(j)+l.get(i)+l.get(k) == 2020){
                        System.out.printf("%d, %d, %d, %d\n",l.get(i),l.get(j),l.get(k),l.get(j)*l.get(i)*l.get(k));
                    }
                    else if (l.get(j)+l.get(i)+l.get(k) > 2020){
                        continue label1;
                    }

                }
            }
        }
    }

}
