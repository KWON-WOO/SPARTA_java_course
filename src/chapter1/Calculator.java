package chapter1;

import java.util.Scanner;

public class Calculator {
    private int[] resultList;   //강의에 나온 배열 사용하여 결과값 저장.
    private boolean errorCheck; //비정상적인 값이 들어올 때 출력과 리스트추가를 막기 위한 변수
    private int resultListIndex;

    public Calculator() {
        resultList = new int[100];  //넉넉하게 100개로 설정.
        errorCheck = true;
        resultListIndex = -1;   //이후 배열에 추가할 때 [++index]방식을 이용하기 위해 초기값을 -1로 설정
    }

    public boolean excute() {
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
        if (num1Str.equals("exit")) {
            loopContinue = false;
        } else if (num1Str.equals("set")) {
            if (resultListIndex != -1)
                setResultList();
        } else if (num1Str.equals("remove")) {
            if (resultListIndex != -1)
                removeResult();
        } else if (num1Str.equals("get")) {
            if (resultListIndex != -1)
                getResultList();
        }
        else {
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
                resultList[++resultListIndex] = result;
            } else {
                System.out.println("잘못된 값을 입력 받았습니다. 다시 입력해주세요");
            }
        }
        return loopContinue;
    }

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

    public void getResultList() {
        System.out.println("결과값");
        for (int i = 0; i <= resultListIndex; i++) {
            System.out.println(i + ". " + resultList[i]);
        }
    }

    public void setResultList() {
        Scanner sc = new Scanner(System.in);
        int index;
        int result;
        getResultList();
        while (true) {
            System.out.println("수정할 인덱스->");
            index = sc.nextInt();
            if (index > resultListIndex)
                System.out.println("인덱스값이 잘못됨");
            else
                break;
        }
        System.out.println("수정할 값->");
        result = sc.nextInt();
        resultList[index] = result;
        System.out.println("수정완료됨");
    }

    public void removeResult() {
        int result;
        if (resultListIndex > 0) {
            for (int i = 0; i < resultListIndex; i++) {
                resultList[i] = resultList[i + 1];
            }
            resultList[resultListIndex--] = 0;
        } else{
            resultList[resultListIndex--] = 0;
        }
    }

}
