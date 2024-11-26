import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * start를 기준으로 BFS
 * 이후 벽이 아니면서 0인 곳은 -1(갈 수 없는 곳)
 */
class solve14940{

    int row;
    int col;
    boolean [][] map;
    boolean [][] visited;
    int [][] result;
    int [] start = new int [2];
    

    solve14940(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new boolean [row][col];
        visited = new boolean[row][col];
        result = new int [row][col];

        for(int i=0;i<row;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){

                int now = Integer.parseInt(st.nextToken());
                if(now == 2){
                    start[0] = i;
                    start[1] = j;
                } else if(now == 1){
                    map[i][j] = true;
                }
            }
        }
    }

    void run(){

        Queue<int []> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;
        
        int [] moveRow = {-1, 1, 0, 0};
        int [] moveCol = {0, 0, -1, 1};

        while(!queue.isEmpty()){

            int [] now = queue.poll();

            for(int i=0;i<moveCol.length;i++){

                int [] next = {now[0] + moveRow[i], now[1] + moveCol[i]};
                if(checkValid(next)){

                    visited[next[0]][next[1]] = true;
                    result[next[0]][next[1]] = result[now[0]][now[1]] + 1;
                    queue.add(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<row;i++){

            for(int j=0;j<col;j++){
                
                int now = result[i][j];
                if(now == 0 && map[i][j]){
                    sb.append(-1).append(" ");
                } else {
                    sb.append(result[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    boolean checkValid(int [] next){

        if(next[0] > -1 && next[0] < row && next[1] > -1 && next[1] < col){
            return !visited[next[0]][next[1]] && map[next[0]][next[1]];
        }

        return false;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14940 p = new solve14940(br);
        p.run();

        br.close();
    }
}