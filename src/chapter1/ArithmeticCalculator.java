package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.DoubleBinaryOperator;

public class ArithmeticCalculator<T extends Number> {
    private List<Double> resultList;

    public enum Operation {         //요구사항에 있던 enum. 해당 값을 받아와
        /**
         * 람다버전. values()로 조건검사
         * 혹은 오퍼레이터를 직접 지정해서 apply로 실행.
         */
        Addition("+", (num1, num2) -> num1 + num2),
        Subtraction("-", (num1, num2) -> num1 - num2),
        Multiplication("*", (num1, num2) -> num1 * num2),
        Division("/"){
            @Override
            public Double apply(Double number1, Double number2) {
                if (number1 == 0 || number2 == 0) {
                    throw new ArithmeticException();
                }
                return number1 / number2;
            }
        },
        ;


        private final String symbol;
        private final DoubleBinaryOperator op;        //연산과 관련된 인터페이스. 매개변수로 오퍼랜드 2개를 받는다.

        Operation(String symbol, DoubleBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }
        Operation(String symbol){               //오버라이딩을 위한 함수.
            this.symbol = symbol;
            this.op = null;
        }
        public String getSymbol() {
            return symbol;
        }

        public Double apply(Double number1, Double number2) {
            return op.applyAsDouble(number1, number2);
        }
        /** 추상화 버전.*/
      /*          Addition("+"){
            public Double apply(Double number1, Double number2) {       //추상 메서드 구체화
                return number1 + number2;
            }
        }, Subtraction("-"){
            public Double apply(Double number1, Double number2) {
                return number1 - number2;
            }
        }, Multiplication("*"){
            public Double apply(Double number1, Double number2) {
                return number1 * number2;
            }
        }, Division("/"){
            public Double apply(Double number1, Double number2) {
                if (number2 == 0) {
                    System.out.println("2번째 입력값이 0입니다. 처음부터 다시 입력해주세요.");
                    return null;
                } else {
                    return number1 / number2;
                }
            }
        };
        public abstract Double apply(Double number1, Double number2); */      //연산 담당할 추상메서드


    }

    public ArithmeticCalculator() {
        errorCheck = true;
        resultList = new ArrayList<>();
    }

    public Double execute(T num1, T num2, String operator, String select) {
        Scanner sc = new Scanner(System.in);
        switch (select) {
            case "get":
                getResultList();
                break;
            case "set":
                System.out.println("수정할 인덱스값을 입력하십쇼.->");
                int index1 = sc.nextInt();
                System.out.println("수정할 값 입력해주십쇼.->");
                setResultList(index1, sc.nextDouble());
                break;
            case "remove":
                removeResult();
                break;
            case "select":
                System.out.println("기준값을 입력해주세요.->");
                getLargerThanInput(sc.nextDouble());
                break;
        }
        Double result;
        result = calculate(num1, num2, operator);
        if (result != null) {
            System.out.println("결과값 ->" + result);
            resultList.add(result);
        } else {
            System.out.println("잘못된 값을 입력 받았습니다. 다시 입력해주세요");
        }
        return result;
    }

    public Double calculate(T number1, T number2, String operator) { //실제로 연산에 들어가는 메서드.
        double num1 = number1.doubleValue();
        double num2 = number2.doubleValue();
        Double result = null;
        for (Operation enumOperator : Operation.values()) {
            if (enumOperator.getSymbol().equals(operator)) {
                try{
                    result = enumOperator.apply(num1, num2);
                } catch(ArithmeticException e) {
                    result = null;
                }
                break;
            }
        }
        return result;
    }

    public void getLargerThanInput(Double num) {
        AtomicInteger i = new AtomicInteger(1);
        resultList.stream()
                .filter(item -> item > num.doubleValue())
                .forEach(item -> System.out.println( i.incrementAndGet() + ". " + item));
    }

    public void getResultList() {
        AtomicInteger i = new AtomicInteger(1);
        System.out.println("결과값");
        resultList.stream()
                .forEach(item -> System.out.println( i.incrementAndGet() + ". " + item));
//        for (int i = 0; i <= resultList.size(); i++) {
//            System.out.println(i + ". " + resultList.get(i));
//        }
    }

    public void setResultList(int index, Double result) {
        getResultList();
        while (true) {
            System.out.println("수정할 인덱스->");
            if (index >= resultList.size())
                System.out.println("인덱스값이 잘못됨");
            else
                break;
        }
        System.out.println("수정할 값->");
        resultList.set(index, result);
        System.out.println("수정완료됨");
    }

    public void removeResult() {
        resultList.remove(0);
    }
}
