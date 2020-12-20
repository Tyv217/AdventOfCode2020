package Day18;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class Day18 {
    public static List<String> readFileInList(String fileName) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    public static long applyOperation(char operator, long a, long b){
        long returnValue = 0;
        switch(operator){
            case '+' -> returnValue =  a + b;
            case '*' -> returnValue = a * b;
        }
        return returnValue;
    }

    public static long evalSum(String expression, int precedence) {
        char[] tokens = expression.toCharArray();
        Stack<Long> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            char token = tokens[i];
            if (token == '(') {
                operators.push(token);
            }
            else if (token == ')') {
                while (operators.peek() != '(') {
                    char operator = operators.pop();
                    long a = values.pop();
                    long b = values.pop();
                    values.push(applyOperation(operator, a, b));
                }
                //remove ')'
                operators.pop();
            }
            else if (token == '+' || token == '*') {
                if(precedence == 1){
                    while (!operators.isEmpty() && operators.peek() != ')' && operators.peek() !='(') {
                        char thisOp = operators.pop();
                        long a = values.pop();
                        long b = values.pop();
                        values.push(applyOperation(thisOp, a, b));
                    }
                }
                else if(precedence == 2){
                    while (!operators.isEmpty() && precedenceOfOperators(token,operators.peek())){
                        char thisOp = operators.pop();
                        long a = values.pop();
                        long b = values.pop();
                        values.push(applyOperation(thisOp, a, b));
                    }
                }
                operators.push(token);
            }
            else if (token >= '0' && token <= '9'){
                StringBuilder stringBuilder = new StringBuilder();
                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9') {
                    stringBuilder.append(tokens[i++]);
                }
                values.push(Long.parseLong(stringBuilder.toString()));
                i--;
            }
        }
        while (!operators.isEmpty()) {
            char thisOp = operators.pop();
            long a = values.pop();
            long b = values.pop();
            values.push(applyOperation(thisOp, a, b));
        }
        return values.pop();
    }

    public static long evalAllLines(List<String> lines, int precedence){
        long total = 0;
        for(String expression: lines){
            total += evalSum(expression, precedence);
        }
        return total;
    }

    public static boolean precedenceOfOperators(char operator1, char operator2){
        if (operator2 == '(' || operator2 == ')'){
            return false;
        }
        return (operator1 != '+') || (operator2 != '*');
    }

    public static void main(String[] args) {
        List<String> lines = readFileInList(
                "C:\\Users\\thoma\\IdeaProjects\\AdventOfCode2020\\src\\main\\java\\day18\\text.txt");
        System.out.println(evalAllLines(lines, 1));
        System.out.println(evalAllLines(lines, 2));
    }
}
