package chapter1;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Calculator {
    private List<Integer> resultList;   //강의에 나온 배열 사용하여 결과값 저장.
    private boolean errorCheck; //비정상적인 값이 들어올 때 출력과 리스트추가를 막기 위한 변수
//    private int resultListIndex;          //컬렉션 타입을 이용하면서 불필요해짐.

    public Calculator() {
        resultList = new ArrayList<Integer>();  //조건에 맞게 수정.
        errorCheck = true;
//        resultListIndex = -1;   //이후 배열에 추가할 때 [++index]방식을 이용하기 위해 초기값을 -1로 설정
    }

    /**
     * 함수 실행부분. 실행의 모든 부분을 관리함. 첫 입력값을 String으로 받아와 조건검사까지 모두 시행한다.
     * @return
     */
    public boolean execute() {
        Scanner sc = new Scanner(System.in);
        boolean loopContinue = true;
        int num1;
        int num2;
        int result;
        char operator;
        String operatorStr;
        String num1Str;

        System.out.print("첫번째 숫자 입력 또는 get, set, remove 입력\n" +
                "(입력을 제외한 나머지는 저장값이 있을 때만 동작)->");
        num1Str = sc.nextLine();

        switch (num1Str) {
            case "get":
                if (!resultList.isEmpty())
                    getResultList();
                break;
            case "set":
                if (!resultList.isEmpty())
                    setResultList();
                break;
            case "remove":
                if (!resultList.isEmpty())
                    removeResult();
                break;
            default:
                num1 = Integer.parseInt(num1Str);
                System.out.print("두번째 숫자 입력->");
                num2 = sc.nextInt();
                sc.nextLine();
                System.out.println("사칙연산 기호 입력(+,-,*,/ 이 4개만 입력) ->");
                operatorStr = sc.nextLine();
                operator = operatorStr.charAt(0);
                result = calculate(num1, num2, operator);
                if (errorCheck) {
                    System.out.println("결과값 ->" + result);
                    resultList.add(result);
                } else {
                    System.out.println("잘못된 값을 입력 받았습니다. 다시 입력해주세요");
                }
        }
        return loopContinue;
    }

    /**
     * 연산이 실행되는 부분.
     * 나눗셈의 경우 2번째 피연산자 num2가 0일 경우 별도의 출력문 출력.
     * 연산자가 아닌 문자가 들어왔을 경우에 결과값을 출력하지 않도록 boolean값을 세팅.
     * @param num1 피연산자1
     * @param num2 피연산자2
     * @param operator 연산자
     * @return
     */
    public int calculate(int num1, int num2, char operator) { //실제로 연산에 들어가는 메서드.
        int result = 0;
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    System.out.println("2번째 입력값이 0입니다. 처음부터 다시 입력해주세요.");
                    errorCheck = false;
                } else {
                    result = num1 / num2;
                }
                break;
            default:
                errorCheck = false;
        }

        return result;
    }
    /**
     * getter 함수.
     * 결과값이 담기는 resultList의 List를 출력해주는 함수.
     * 별도의 매개변수를 필요로 하지 않고 반환값 없이 콘솔에서만 출력한다.
     */
    public void getResultList() {
        System.out.println("결과값");
        for (int i = 0; i <= resultList.size(); i++) {
            System.out.println(i + ". " + resultList.get(i));
        }
    }
    /**
     * 결과값이 담기는 setResultList의 값을 수정하는 함수.
     * 수정을 위해 수정할 결과값이 들어있는 인덱스와 수정할 값을 받아온다.
     * 별도의 매개변수 없이 함수 내에서 매개변수를 모두 받는다.
     */
    public void setResultList() {
        Scanner sc = new Scanner(System.in);
        int index;
        int result;
        getResultList();
        while (true) {
            System.out.println("수정할 인덱스->");
            index = sc.nextInt();
            if (index >= resultList.size())
                System.out.println("인덱스값이 잘못됨");
            else
                break;
        }
        System.out.println("수정할 값->");
        result = sc.nextInt();
        resultList.set(index, result);
        System.out.println("수정완료됨");
    }
    /**
     * 결과값을 지워주는 함수.
     * 무조건 제일 먼저 들어온 값을 지우도록 설계함.
     */
    public void removeResult() {
        resultList.remove(0);
//        int result;           //조건에 맞게 수정함.
//        if (resultListIndex > 0) {
//            for (int i = 0; i < resultListIndex; i++) {
//                resultList[i] = resultList[i + 1];
//            }
//            resultList[resultListIndex--] = 0;
//        } else{
//            resultList[resultListIndex--] = 0;
//        }
    }
}
