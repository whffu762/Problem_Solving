import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 조금 더 깔끔한 버전
 * 어차피 거리를 계산하기 때문에 1로 바꾸지 않고 그냥 거리만 유지하는 방식
 * 근데 속도는 비슷함 속도를 줄이려면 입력에서 바꿔야 할 듯
 */
class solve7576{

    int row;
    int col;
    int [][] tomato;
    Queue<int []> queue = new ArrayDeque<>();
    int [] moveX = { -1, 1 ,0, 0};
    int [] moveY = { 0, 0, -1, 1};

    solve7576 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        tomato = new int[row][col];

        for(int i=0;i<row;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp == 1){
                    queue.add(new int [] {i, j});
                } 
                
                tomato[i][j] = tmp;
            }
        }
    }

    void run(){

        bfs();
        System.out.println(checkResult());
    }

    void bfs(){

        while(!queue.isEmpty()){
    
            int [] now = queue.poll();

            for(int i=0;i<moveX.length;i++){

                int [] next = {now[0] + moveX[i], now[1] + moveY[i]};
                if(checkValid(next)){
                    tomato[next[0]][next[1]] = tomato[now[0]][now[1]] + 1;
                    queue.add(next);
                }
            }
        }
    }

    boolean checkValid(int [] next){

        if(next[0] > -1 && next[0] < row && next[1] > -1 && next[1] < col){
            return tomato[next[0]][next[1]] == 0;
        }

        return false;
    }

    int checkResult(){

        int result = -1;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){

                int tmp = tomato[i][j];
                result = Math.max(tmp,result);
                if(tmp == 0){
                    return -1;
                }
            }
        }

        return result - 1;

    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve7576 p = new solve7576(br);
        p.run();

        br.close();
    }
}