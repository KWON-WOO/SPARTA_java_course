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
        ADDITION("+", (num1, num2) -> num1 + num2),
        SUBTRACTION("-", (num1, num2) -> num1 - num2),
        MULTIPLICATION("*", (num1, num2) -> num1 * num2),
        DIVISION("/"){
            @Override
            public Double apply(Double number1, Double number2) {
                if (number2 == 0) {
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
        resultList = new ArrayList<>();
    }

    /**
     * 함수 실행 부분. 해당 객체에서 저장하고 있는 결과값을 다룰 수 있는 함수.
     * 결과값을 가져오는 get, 수정하는 set, 삭제하는 remove,
     * 선택값보다 큰 값을 가져오는 select로 구분.
     * @param select
     * @return
     */
    public boolean execute(String select) {
        Scanner sc = new Scanner(System.in);
        switch (select) {
            case "get":
                getResultList();
                break;
            case "set":
                System.out.println("수정할 인덱스값을 입력하십쇼.->");
                int index1 = sc.nextInt();
                if (index1 >= resultList.size()) {
                    System.out.println("인덱스값이 잘못됨");
                    break;
                }
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
            default:
                return false;
        }
        System.out.println("\n");
        return true;
    }

    /**
     * 실제로 연산을 수행하는 함수. 연산은 Operation에서 하고 예외처리만 해준다.
     * 연산자가 잘못 입력되었거나 0을 나누는 등 결과값을 리스트에 추가하지 않고 별도의 문구를 출력한다.
     * @param number1 피연산자(operand)를 받아오는 변수
     * @param number2 피연산자(operand)를 받아오는 함수
     * @param operator 연산자를 가져오는 함수.
     * @return
     */
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
        if (result != null) {
            System.out.println("결과값 ->" + result);
            resultList.add(result);
        } else {
            System.out.println("잘못된 값을 입력 받았습니다. 다시 입력해주세요");
        }
        return result;
    }

    /**
     * 함수명에서 알 수 있듯이 기입한 값보다 높은 값들을 출력해주는 함수.
     * @param num 출력할 값들의 기준값을 설정해주는 매개변수.
     */
    public void getLargerThanInput(Double num) {
        AtomicInteger i = new AtomicInteger(1);
        resultList.stream()
                .filter(item -> item > num.doubleValue())
                .forEach(item -> System.out.println( i.incrementAndGet() + ". " + item));
    }

    /**
     * getter 함수.
     * 결과값이 담기는 resultList의 List를 출력해주는 함수.
     * 별도의 매개변수를 필요로 하지 않고 반환값 없이 콘솔에서만 출력한다.
     */
    public void getResultList() {
        AtomicInteger i = new AtomicInteger(0);
        System.out.println("결과값");
        resultList.stream()
                .forEach(item -> System.out.println( i.incrementAndGet() + ". " + item));
//        for (int i = 0; i <= resultList.size(); i++) {
//            System.out.println(i + ". " + resultList.get(i));
//        }
    }

    /**
     * 결과값이 담기는 setResultList의 값을 수정하는 함수.
     * 수정을 위해 수정할 결과값이 들어있는 인덱스와 수정할 값을 받아온다.
     *
     * @param index  수정할 결과값의 인덱스가 들어가는 부분.
     * @param result    실제로 수정할 값을 기입.
     */
    public void setResultList(int index, Double result) {
        getResultList();
        resultList.set(index, result);
        System.out.println("수정완료됨");
    }

    /**
     * 결과값을 지워주는 함수.
     * 결과값이 담기는 resultList가 비어있다면 별도의 출력문을 콘솔에 출력해줌.
     * 무조건 제일 먼저 들어온 값을 지우도록 설계함.
     */
    public void removeResult() {
        if  (resultList.isEmpty()) {
            System.out.println("리스트값이 없음");
        }
        resultList.remove(0);
    }
}
