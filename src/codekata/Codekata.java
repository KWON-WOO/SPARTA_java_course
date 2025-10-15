package codekata;

import java.util.Scanner;

public class Codekata {
    public void executeSolution() {
        int selectSolution;
        Scanner solutionSc = new Scanner(System.in);
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("솔루션 선택->");
            selectSolution = solutionSc.nextInt();
            switch (selectSolution) {
                case 0:
                    break;
                case 1:
                    System.out.println(solution1(sc.nextInt(), sc.nextInt()));
                    break;
                case 2:
                    System.out.println(solution2(sc.nextInt(), sc.nextInt()));
                    break;
                case 3:
                    System.out.println(solution3(sc.nextInt(), sc.nextInt()));
                    break;
                case 4:
                    System.out.println(solution4(sc.nextInt()));
                    break;
                case 5:
                    System.out.println(solution5(sc.nextInt(), sc.nextInt()));
                    break;
                case 6:
                    System.out.println(solution6(sc.nextInt(), sc.nextInt()));
                    break;
                case 7:
                    System.out.println(solution7(sc.nextInt(), sc.nextInt()));
                    break;
                case 8:
                    System.out.println(solution8(sc.nextInt(), sc.nextInt()));
                    break;
                case 218:
                    solution218(sc.nextInt());
                default:
            }
        } while (selectSolution != 0);
    }

    public int solution1(int num1, int num2) {   //두 수의 차 구하기
        return num1 - num2;
    }

    public int solution2(int num1, int num2) {   // 두 수의 곱 구하기
        return num1 * num2;
    }

    public int solution3(int num1, int num2) {
        return num1 / num2;
    }

    public int solution4(int age) {
        int year = 2022;
        return (year + 1) - age;
    }

    public int solution5(int num1, int num2) {
        return num1 == num2 ? 1 : -1;
    }

    public int solution6(int num1, int num2) {
        return num1 + num2;
    }

    public int solution7(int num1, int num2) {
        return num1 * 1000 / num2;
    }

    public int solution8(int num1, int num2) {
        return num1 * 1000 / num2;
    }

    public void solution185(int length) {
        Scanner sc = new Scanner(System.in);
        boolean errorFlag = true;
        int top = -1;
        int num = 1;
        int[] stack = new int[length];
        int[] inputNumber = new int[length];
        String resultData = "";          //결과값 담는 문자열

        for (int i = 0; i < length; i++) { //초기화 구문
            inputNumber[i] = sc.nextInt();
            stack[i] = 0;
        }
        for (int inputNum : inputNumber) {  //입력값 쪼개기
            while (true) {                  //해당값 가져올 때까지 반복 push
                if (inputNum >= num) {
                    resultData += '+';
                    stack[++top] = num++;
                } else break;
            }
            if (inputNum == stack[top]) {   //입력값을 가져왔을 때 pop
                resultData += '-';
                stack[top--] = 0;
            } else {
                errorFlag = false;     //문제 조건과 일치하지 않으면 에러
                break;
            }
        }
        if (errorFlag) {
            for (char result : resultData.toCharArray()) {
                System.out.println(result);
            }
        } else {
            System.out.println("NO");
        }
    }

    public void solution218(int testCase) {
        Scanner sc = new Scanner(System.in);
        int[][] field;      //배열크기는 농장 크기를, 값은 양배추 유무를 나타냄.
        int row;
        int col;
        int count;          //지렁이 수
        int num;            //총 갯수
        int x;
        int y;
        int[][] point;

        for (int i = 0; i < testCase; i++) {
            row = sc.nextInt();
            col = sc.nextInt();
            num = sc.nextInt();
            count = 0;
            field = new int[row][col];
            point = new int[num][];
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    field[j][k] = 0;   //배추유무값 초기화. 찾아보니 Arrays.fill(field,0)으로도 대체 가능하다고 함.
                }
            }
            for (int j = 0; j < num; j++) {
                x = sc.nextInt();
                y = sc.nextInt();
                field[y][x] = 1;
                point[j] = new int[2];
                point[j][0] = y;
                point[j][1] = x;
            }
            for (int j = 0; j < num; j++) {
                if (field[point[j][0]][point[j][1]] == 1) {
                    count++;
                    solution218Seach(row, col, field, point[j]);
                }
            }
            System.out.println(count);
        }
    }

    public void solution218Seach(int row, int col, int[][] field, int[] point) {
        int visit[][] = {
                {-1, 0},    //위 체크
                {1, 0},     //아래 체크
                {0, -1},    //왼쪽 체크
                {0, 1}};    //오른쪽 체크
        int searchX;
        int searchY;
        if (field[point[0]][point[1]] == 1) {
            field[point[0]][point[1]] = 2;
            for (int i = 0; i < 4; i++) {
                searchY = point[0] + visit[i][0];
                searchX = point[1] + visit[i][1];

                if (searchX >= 0 && searchX < col && searchY >= 0 && searchY < row) {
                    if (field[searchY][searchX] == 1) {
                        field[searchY][searchX] = 2;
                        solution218Seach(row, col, field, new int[]{searchY, searchX});
                    }

                }
            }
        }
//        if (field[point[0]][point[1]] == 1) {
//            field[point[0]][point[1]] = 2;
//            count--;
//            if (point[0] != 0) {     //좌측 검사
//                if (field[point[0] - 1][point[1]] == 1) {
//                    field[point[0] - 1][point[1]] = 2;
//                }
//            }
//            if (point[0] != row - 1) {     //우측 검사
//                if (field[point[0] + 1][point[1]] == 1) {
//                    field[point[0] + 1][point[1]] = 2;
//                }
//            }
//            if (point[1] != 0) {    //상단 검사
//                if (field[point[0]][point[1] - 1] == 1) {
//                    field[point[0]][point[1] - 1] = 2;
//                }
//            }
//            if (point[1] != col - 1) {   //하단 검사
//                if (field[point[0]][point[1] + 1] == 1) {
//                    field[point[0]][point[1] + 1] = 2;
//                } //체크한 배추값 올려줌.
//                return count;
//            }
//        }
    }
}
