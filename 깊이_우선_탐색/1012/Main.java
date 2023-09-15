package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이동된 좌표에서도 다시 이동될 수 있으므로 깊이 우선 탐색으로 구현
 * 
 * 중복 검사 방지 : 한 번 검사한 좌표는 false 로 설정
 * 중복 카운트 방지 : cal() 함수에 flag 를 넣어서 첫 접근에만 true 로 설정
 * 범위 밖인지 검사 : checkValid()
 * 이동을 순차적으로 진행 : move[] 로 가로 세로 순차적으로 이동 
 * 
 * 재귀 호출을 통해 깊이 우선 탐색
 * 다시 호출될 때는 flag 를 flase 로 설정해서 중복 카운트를 방지
 */
class solve1012 {
    boolean[][] base;
    int col;
    int row;
    int num_target;
    int[] move = { -1, 1 };
    int result;

    void input(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        num_target = Integer.parseInt(st.nextToken());

        base = new boolean[row][col];
        for (int j = 0; j < num_target; j++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            base[y][x] = true;
        }
    }

    void cal(int y, int x, boolean flag) {
        
        if (base[y][x]) {
            base[y][x] = false;

            if(flag){
                result++;
            }

            for (int m : move) {
                int nextCol = x + m;
                if (checkValid(y, nextCol)) {
                    cal(y, nextCol, false);
                }
            }

            for (int m : move) {
                int nextRow = y + m;
                if(checkValid(nextRow, x)){
                    cal(nextRow, x, false);
                }
            }

        }

    }

    boolean checkValid(int y, int x) {
        if (-1 < x && x < col && -1 < y && y < row) {
            return true;
        }
        return false;
    }

    void run(BufferedReader br) throws IOException {
        int num_case = Integer.parseInt(br.readLine());

        for (int i = 0; i < num_case; i++) {
            result = 0;
            input(br);
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < col; k++) {
                    cal(j, k, true);
                }
            }

            System.out.println(result);
        }
    }
}

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1012 p = new solve1012();
        p.run(br);

        br.close();
    }
}