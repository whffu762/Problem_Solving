import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 7576 문제에 3차 배열로 바뀐 것 뿐임
 * 2차 배열로 취급하면 각 height의 경계에서 x-1, x+1 연산에서 다른 칸으로 넘어가기 때문에
 * 반드시 칸별로 배열을 나눠야 함 그 부분만 주의하면 BFS 동일
 * 
 */
class solve7569{

    int row;
    int col;
    int height;
    int [][][] tomato;
    Queue<int []> queue = new ArrayDeque<>();

    solve7569 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        height = Integer.parseInt(st.nextToken());

        tomato = new int [height][row][col];
        for(int i=0;i<height;i++){

            for(int j=0;j<row;j++){
                
                st = new StringTokenizer(br.readLine());
                for(int k=0;k<col;k++){
                    int now = Integer.parseInt(st.nextToken());
                    tomato[i][j][k] = now;
                    if(now == 1){
                        queue.add(new int [] {i, j, k});
                    }
                }
            }
        }
    }

    void run(){

        bfs();
        System.out.println(checkRipe());
    }

    void bfs(){

        int [] moveZ = {-1, 1, 0, 0, 0, 0};
        int [] moveX = {0, 0, -1, 1, 0, 0};
        int [] moveY = {0, 0, 0, 0, -1, 1};

        while(!queue.isEmpty()){

            int [] now = queue.poll();

            for(int i=0;i<moveX.length;i++){

                int [] next = {now[0] + moveZ[i], now[1] + moveX[i], now[2] + moveY[i]};
                if(checkValid(next)){

                    tomato[next[0]][next[1]][next[2]] = tomato[now[0]][now[1]][now[2]] + 1;
                    queue.add(next);
                }
            }
        }
    }

    boolean checkValid(int [] next){

        if(next[0] > -1 && next[0] < height && next[1] > -1 && next[1] < row && next[2] > -1 && next[2] < col){
            return tomato[next[0]][next[1]][next[2]] == 0;
        }

        return false;
    }

    int checkRipe(){

        int result = -1;
        for(int i=0;i<height;i++){

            for(int j=0;j<row;j++){

                for(int k=0;k<col;k++){
                    int now = tomato[i][j][k];
                    result = Math.max(result, now);
                    if(now == 0){
                        return -1;
                    }
                }
            }
        }

        return result - 1;
    }
}

public class Main {
    
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve7569 p = new solve7569(br);
        p.run();

        br.close();
    }
}