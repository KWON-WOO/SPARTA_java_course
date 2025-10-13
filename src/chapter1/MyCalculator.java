package chapter1;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MyCalculator {
    private String operatorStack;
    private int expressionIndex;
    private List<Double> numStack;
    private char[] expressionArray;
    private int expressionLength;

    public double calculate(String expression) {
        operatorStack = "";     //연산 우선순위를 위한 연산자 스택 분리
        expressionIndex = 0;    //연산을 위해 받아온 문자열 인덱스
        numStack = new ArrayList<>();
        expressionArray = expression.toCharArray();
        expressionLength = expressionArray.length - 1;  //문자열 길이
        String stringNumber = "";   //받아온 문자열 숫자만 잘라낸 거
        double num1 = 0;
        double result = 0;
        num1 = returnNumber(expressionLength, expressionIndex);     //문자열에서 값 잘라서 num1에 넘겨줌.
//        System.out.println(num1 + "\t" + expressionArray[expressionIndex]);
//        System.out.println(stringNumber);
        if (dataErrorCheck(expressionArray[expressionIndex])) {
            char operator = expressionArray[expressionIndex++]; //연산자를 넘겨주고 인덱스를 추가함.
            operatorStack += operator;
            result = operationData(num1, expressionArray, operator);
            System.out.println(result);
            stringNumber = "";
        }
        for (int i = 0; i < operatorStack.length(); i++) {
            System.out.println("stackLength[" + i + "]=" + operatorStack.charAt(i));
        }return result;

    }

    private double operationData(double num1, char[] expression, char operator) {
        double num2 = 0;

        while (expressionIndex < expressionLength) {
            if (isNumber(expression[expressionIndex])) {
                num2 = returnNumber(expressionLength, expressionIndex); //2번째 값 잘라냄.
                 System.out.println("num2 = " + num2);
            } else {
                char operator2 = expression[expressionIndex++];
                if (operator == '+' || operator == '-') {
                    System.out.println("operator2 = " + operator2);
                    operatorStack += operator2;
                    numStack.add(num1);
                    num2 = operationData(num2, expression, operator2);

                    break;
                } else if (operator == '*') {
                    num1 = mul(num1, num2);
                    expressionIndex++;
                } else if (operator == '/') {
                    num1 = div(num1, num2);
                    expressionIndex++;
                } else{
                    break;
                }
            }
        }
        return num1;
    }

    private double returnNumber(int strLength, int index) {
        String numStr = "";
        char numChar;
        double result = 0;
        while (index <= strLength) {
            System.out.println(numStr);
            numChar = expressionArray[index];
            if (isNumber(numChar) || numChar == '.') {
                numStr += expressionArray[index++];
            } else break;
        }
        expressionIndex = index;
        result = Double.valueOf(numStr);
        System.out.println(result);
        return result;
    }

    private boolean dataErrorCheck(char chr) {
        if (chr == '+' || chr == '-' || chr == '*' || chr == '/')
            return true;
        else return false;
    }

    public boolean isNumber(char data) {
        if (data >= '0' && data <= '9') return true;
        else return false;
    }

    public double sum(double num1, double num2) { //덧셈
        return num1 + num2;
    }

    public double sub(double num1, double num2) { //뺄셈
        return num1 - num2;
    }

    public double mul(double num1, double num2) { //곱셈
        return num1 * num2;
    }

    public double div(double num1, double num2) { //나눗셈
        return num1 / num2;
    }

    public double pow(double num1, int b) { //제곱
        double result = 1;
        if (b > 0) result = num1;
        while (--b > 0) result *= num1;
        return result;
    }

//    public double abs(double a) { //절댓값
//        if (a == 0) return a;
//        else if  (a < 0) return -a;
//        else return a;
//    }
//
//    public double max(double a, double b) { //큰 값
//        if (b > a) return b;
//        else return a;
//    }
//
//    public double min(double a, double b) { //작은 값
//        if  (b < a) return b;
//        else return a;
//    }
//
//    public double avg(double a, double b) { //평균
//        return (a + b) / 2;
//    }

    public int factorial(int num1) {
        int result = num1;
        while (--num1 > 0) result *= num1;
        return result;
    }
}

