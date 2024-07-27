import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 기본적인 DFS임
 * 다만 거기다가 백트랙킹 방식을 조금 곁드림 상태 유지를 위한 
 * true 지정 dfs 종료 후 false로 초기화
 * 
 * 또한 다음 이동할 좌표를 먼저 검사한 후 dfs 호출해야 시간 단축 됨
 */
class solve1987 {

    int row;
    int col;
    char[][] map;

    solve1987(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        this.row = Integer.parseInt(st.nextToken());
        this.col = Integer.parseInt(st.nextToken());

        this.visited = new boolean[row][col];
        this.map = new char[row][col];
        for (int i = 0; i < row; i++) {
            String now = br.readLine();
            for (int j = 0; j < col; j++) {
                this.map[i][j] = now.charAt(j);
            }
        }
    }

    void run() {

        dfs(0, 0, 0);
        System.out.println(result + 1);

    }

    int result = Integer.MIN_VALUE;
    int[] move = { -1, 1 };
    Deque<Character> visitedChars = new ArrayDeque<>();
    boolean[][] visited;

    void dfs(int x, int y, int count) {

        result = Math.max(count, result);
        visited[x][y] = true;
        visitedChars.add(map[x][y]);

        for (int i = 0; i < move.length; i++) {
            
            int nextRow = x + move[i];
            int nextCol = y + move[i];

            if(checkValid(nextRow, y)){
                dfs(x + move[i], y, count + 1);
            }
            
            if(checkValid(x, nextCol)){
                dfs(x, y + move[i], count + 1);
            }
        }
        visited[x][y] = false;
        visitedChars.removeLast();
    }

    boolean checkValid(int row, int col) {

        if (row < 0 || row >= this.row || col < 0 || col >= this.col) {
            return false;
        }

        if (visitedChars.contains(map[row][col])) {
            return false;
        }

        return !visited[row][col];
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1987 p = new solve1987(br);
        p.run();

        br.close();
    }
}