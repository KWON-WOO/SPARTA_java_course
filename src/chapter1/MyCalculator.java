package chapter1;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class MyCalculator {
    String operatorStack;
    int expressionArrayIndex;
    List<Double> numStack;

    public double calculate(String expression) {
        operatorStack = "";
        expressionArrayIndex = 0;
        numStack = new ArrayList<>();
        char[] expressionArray = expression.toCharArray();
        int expressionLength = expressionArray.length;
        String stringNumber = "";
        double num1 = 0;
        double result = 0;
        for (expressionArrayIndex = 0; expressionArrayIndex < expressionLength; expressionArrayIndex++) {
//            System.out.println(expressionArray[expressionArrayIndex]);
            if (isNumber(expressionArray[expressionArrayIndex])
                    || expressionArray[expressionArrayIndex] == '.'
            || expressionArrayIndex == expressionLength - 1) {
                stringNumber += expressionArray[expressionArrayIndex];
            } else {
//                System.out.println(stringNumber);
                num1 = Double.valueOf(stringNumber);
                result = operationData(num1, expressionArray, expressionArray[expressionArrayIndex++]);
                stringNumber = "";
            }
        }
        return result;
    }

    public double operationData(double num1, char[] expression, char operator) {
        String strNum = "";
        double num2 = 0;
        for (; expressionArrayIndex < expression.length; ++expressionArrayIndex) {
            System.out.println(expression[expressionArrayIndex]);
//            System.out.println(operator);
            if (isNumber(expression[expressionArrayIndex])) {
                strNum += expression[expressionArrayIndex];
            } else {
                System.out.println();
                num2 = Double.valueOf(strNum);
                if (operator == '+' || operator == '-') {
                    operatorStack += expression[expressionArrayIndex];
                    numStack.add(num1);
                    operationData(num2, expression, expression[expressionArrayIndex]);
                    break;
                } else {
                    if (operator == '*') {
                        num1 = mul(num1, num2);
                    } else {
                        num1 = div(num1, num2);
                    }
                    strNum = "";
                }
            }
        }
        return 1.1;
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

