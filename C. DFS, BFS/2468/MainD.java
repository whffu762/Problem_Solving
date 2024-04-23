import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 깊이우선 탐색이 조~금 더 빠름 범위가 좁아서 그런 듯
 */
class solve2468 {

    int[][] area;
    int[] move = { -1, 1 };
    int maxRain = -1;

    solve2468(BufferedReader br) throws IOException {

        int size = Integer.parseInt(br.readLine());
        area = new int[size][size];

        for (int i = 0; i < size; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {
                area[i][j] = Integer.parseInt(st.nextToken());
                maxRain = Math.max(maxRain, area[i][j]);
            }
        }
    }

    void run() {

        int max = -1;

        for (int i = 0; i < maxRain; i++) {
            max = Math.max(checkSafeArea(i), max);
        }
        
        System.out.println(max);
    }

    int checkSafeArea(int rain){
        
        int count = 0;
        boolean [][] visited = new boolean[area.length][area.length];
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area.length; j++) {
                if (!visited[i][j] && area[i][j] > rain) {
                    count++;
                    dfs(new int[] { i, j }, rain, visited);
                }
            }
        }

        return count;
    }

    void dfs(int[] now, int rain, boolean [][] visited) {

        int x = now[0];
        int y = now[1];
        visited[x][y] = true;

        for(int i=0;i<move.length;i++){

            int nextRow = x + move[i];
            if(nextRow > -1 && nextRow < area.length){

                if(!visited[nextRow][y] && area[nextRow][y] > rain){
                    dfs(new int [] { nextRow, y }, rain, visited);
                }
            }
        }

        for(int i=0;i<move.length;i++){

            int nextCol = y + move[i];
            if(nextCol > -1 && nextCol < area.length){

                if(!visited[x][nextCol] && area[x][nextCol] > rain){
                    dfs(new int [] { x, nextCol }, rain, visited);
                }
            }
        }
    }
}

public class MainD {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2468 p = new solve2468(br);
        p.run();

        br.close();
    }
}