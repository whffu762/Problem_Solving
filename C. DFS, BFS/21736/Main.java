import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 간단한 그래프 탐색
 */
class solve21736{

    int row;
    int col;
    char [][] map;
    int [] start = new int [2];

    boolean [][] visited;

    solve21736 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new char [row][col];
        for(int i=0;i<row;i++){

            String input = br.readLine();
            for(int j=0;j<col;j++){

                char now = input.charAt(j);
                map[i][j] = now;
                if(now == 'I'){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        visited = new boolean[row][col];
    }

    void run(){

        int result = bfs();

        if(result == 0){
            System.out.println("TT");
        } else{
            System.out.println(result);
        }
    }

    int bfs(){

        Queue<int []> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start[0]][start[1]] = true;

        int [] moveRow = {-1, 1, 0, 0};
        int [] moveCol = {0, 0, -1, 1};
        int result = 0;
        while(!queue.isEmpty()){

            int [] now = queue.poll();
            if(map[now[0]][now[1]] == 'P'){
                result++;
            }

            for(int i=0;i<moveCol.length;i++){

                int [] next = {now[0] + moveRow[i], now[1] + moveCol[i]};
                if(checkValid(next)){
                    
                    visited[next[0]][next[1]] = true;
                    queue.add(next);
                }
            }
        }   

        return result;
    }

    boolean checkValid(int [] next){

        if(next[0] > -1 && next[0] < row && next[1] > -1 && next[1] < col){
            return !visited[next[0]][next[1]] && map[next[0]][next[1]] != 'X';
        }

        return false;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        solve21736 p = new solve21736(br);
        p.run();

        br.close();
    }
}