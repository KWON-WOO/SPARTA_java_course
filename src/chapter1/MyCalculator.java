package chapter1;

public class MyCalculator {

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

    public double abs(double a) { //절댓값
        if (a == 0) return a;
        else if  (a < 0) return -a;
        else return a;
    }

    public double max(double a, double b) { //큰 값
        if (b > a) return b;
        else return a;
    }

    public double min(double a, double b) { //작은 값
        if  (b < a) return b;
        else return a;
    }

    public double avg(double a, double b) { //평균
        return (a + b) / 2;
    }

    public int factorial(int num1) {
        int result = num1;
        while (--num1 > 0) result *= num1;
        return result;
    }
}

