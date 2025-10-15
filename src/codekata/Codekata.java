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

    public void solution218(int testCase) {
        Scanner sc = new Scanner(System.in);
        int[][] field;      //배열크기는 농장 크기를, 값은 양배추 유무를 나타냄.
        int row;
        int col;
        int count;          //남은 갯수
        int num;            //총 갯수
        int x;
        int y;
        int[][] point;

        for (int i = 0; i < testCase; i++) {
            row = sc.nextInt();
            col = sc.nextInt();
            num = sc.nextInt();
            count = num;
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
                field[x][y] = 1;
                point[j][0] = x;
                point[j][1] = y;
            }
        }
    }

    public int solution218Seach(int row, int col, int[][] field, int[] point, int count) {
            if (field[point[0]][point[1]] == 1) {
                field[point[0]][point[1]] = 2;
                count--;
                if (point[0] != 0) {     //좌측 검사
                    if (field[point[0] - 1][point[1]] == 1) {
                        field[point[0] - 1][point[1]] = 2;
                    }
                }
                if (point[0] != row - 1) {     //우측 검사
                    if (field[point[0] + 1][point[1]] == 1) {
                        field[point[0] + 1][point[1]] = 2;
                    }
                }
                if  (point[1] != 0) {    //상단 검사
                    if (field[point[0]][point[1] - 1] == 1) {
                        field[point[0]][point[1] - 1] = 2;
                    }
                }
                if (point[1] != col - 1) {   //하단 검사
                    if (field[point[0]][point[1] + 1] == 1) {
                        field[point[0]][point[1] + 1] = 2;
                    }
                }
            } //체크한 배추값 올려줌.
        return count;
    }
}

