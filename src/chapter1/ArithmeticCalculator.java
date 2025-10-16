package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.DoubleBinaryOperator;

public class ArithmeticCalculator<T extends Number> {
    private List<Double> resultList;
    private boolean errorCheck; //비정상적인 값이 들어올 때 출력과 리스트추가를 막기 위한 변수
    public enum Operation {         //요구사항에 있던 enum. 해당 값을 받아와
        /**람다버전. values()로 조건검사
         * 혹은 오퍼레이터를 직접 지정해서 apply로 실행.*/
        Addition("+", (num1, num2) -> num1 + num2),
        Subtraction("-", (num1, num2) -> num1 - num2),
        Multiplication("*", (num1, num2) -> num1 * num2),
        Division("/", (num1, num2) -> num1 / num2),;


        private String symbol;
        private DoubleBinaryOperator op;        //연산과 관련된 인터페이스. 매개변수로 오퍼랜드 2개를 받는다.
        Operation(String symbol, DoubleBinaryOperator op) {
            this.symbol = symbol;
            this.op = op;
        }

        public String getSymbol() {
            return symbol;
        }
        public Double apply(Double number1, Double number2){
            return op.applyAsDouble(number1, number2);
        }
            /** 추상화 버전.
             */
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




    };
    public ArithmeticCalculator() {
        errorCheck = true;
        resultList = new ArrayList<Double>();
    }

    public Double execute(T num1, T num2,String operator, String select) {
        Scanner sc = new Scanner(System.in);
        if (select.equals("get"))
            getResultList();
        else if (select.equals("set")){
            System.out.println("수정할 인덱스값을 입력하십쇼.->");
            int index1 = sc.nextInt();
            System.out.println("수정할 값 입력해주십쇼.->");
            Double value = sc.nextDouble();
            setResultList(index1, value);
        }

        else if (select.equals("remove"))
            removeResult();
        else if (select.equals("select")) {
            System.out.println("기준값을 입력해주세요.->");
            getLargerThanInput(sc.nextDouble());
        }
        double result;
        result = calculate(num1, num2, operator);
        if (errorCheck) {
            System.out.println("결과값 ->" + result);
            resultList.add(result);
        }
        return result;
    }

    public Double calculate(T number1, T number2, String operator) { //실제로 연산에 들어가는 메서드.
        double num1 = number1.doubleValue();
        double num2 = number2.doubleValue();
        Double result = null;
        for (Operation enumOperator: Operation.values()) {
            if (enumOperator.getSymbol().equals(operator)) {
                    result = enumOperator.apply(num1, num2);
//                    errorCheck = true;
//                    result = null;
//                    System.out.println("잘못된 값을 입력 받았습니다. 다시 입력해주세요");

                break;
            }
        }
        return result;
    }

    public void getLargerThanInput(Double num) {
        resultList.stream()
                .filter(item -> item > num.doubleValue());
    }

    public void getResultList() {
        System.out.println("결과값");
        for (int i = 0; i <= resultList.size(); i++) {
            System.out.println(i + ". " + resultList.get(i));
        }
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
