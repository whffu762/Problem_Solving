import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
 * 백트랙킹 문제임
 * 로직은 금방 짰는데 두 가지 때문에 오래 걸림
 * 
 * 1. 왜 blanks를 반복 시키면 안 되지?
 * 2. 왜 map[now]를 0으로 초기화해야 하지?
 * 
 * 1번
 * 백트랙킹 메소드 내부에서 실행될 반복은 현재 상태에서의 반복뿐임 즉 1 ~ 9까지 대입해보는 동작만 해당됨
 * blanks를 순회하는 동작은 현재 상태에서 수행되야 하는 반복이 아닌 백트래킹 전체의 목적임 그래서 이건 파라미터로 진행상황을 넘겨줘야 함
 * 만약 포함되도 결과는 동일하게 나옴 다만 불필요한 연산이 계속되는 것임
 * 이에 대해선 노션 백트랙킹 재귀 메소드 항목 참고
 * 
 * 2번 
 * 나는 0으로 초기화하지 않고 지금 판단할 부분을 제외하고 검사하게끔 했는데 안 됨
 * 그 이유는 한 줄에 0이 여러 개인 경우 잘못되 동작을 하기 때문임
 * 0 0 0 0 0 0 0 0 0
 * 한 행이 위와 같다고 할 때 8번째 열의 값에서 1~9까지 전부 넣어봤는데 안 되면 7번째 열로 다시 되돌아감
 * 이 상황에서 8번째 열을 0으로 초기화하지 않으면 9가 그대로 남아있음 근데 내가 continue 하게끔 한건 7번째 열의 값만 제외했기 때문에
 * 7번째 열을 다시 채울 때 조건의 만족 여부와 상관 없이 8번째에 있는 9는 못 넣게 됨
 * 
 */

class solve2580 {

    int[][] map = new int[9][9];
    List<int[]> blanks = new ArrayList<>();

    solve2580(BufferedReader br) throws IOException {

        for (int i = 0; i < 9; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {

                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 0) {
                    blanks.add(new int[] { i, j });
                }
                map[i][j] = tmp;
            }
        }
    }

    void run() {
        backTracking(0);
    }

    void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    void backTracking(int depth) {

        if (depth == blanks.size()) {
            print();
            System.exit(0);
        }

        int[] now = blanks.get(depth);
        for (int j = 1; j < 10; j++) {

            if (checkValid(now[0], now[1], j)) {
                map[now[0]][now[1]] = j;
                backTracking(depth + 1);
            }
        }

        map[now[0]][now[1]] = 0;
    }

    boolean checkValid(int row, int col, int input) {

        for (int i = 0; i < 9; i++) {

            if (map[i][col] == input) {
                return false;
            }

            if (map[row][i] == input) {
                return false;
            }
        }

        int startRow = 3 * Math.floorDiv(row, 3);
        int startCol = 3 * Math.floorDiv(col, 3);
        for (int i = startRow; i < startRow + 3; i++) {
            for (int j = startCol; j < startCol + 3; j++) {

                if (map[i][j] == input) {
                    return false;
                }
            }
        }

        return true;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2580 p = new solve2580(br);
        p.run();

        br.close();
    }
}