import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS로 푸는 쉬운 문젠데 엄청 해맸음
 * 1. 토마토가 여러 곳에 존재할 수 있음
 * 2. 날짜를 세야 함
 * 
 * 이 두가지를 취급하느라 BFS를 한 번에 돌리는게 아니라 while(true)로 두고
 * 각 day마다 돌려야 한다고 생각했음 근데 그렇게 하면 무조건 시간 초과 발생함
 * 
 * 출발점이 여러 곳이어도 어차피 Queue에 들어온 순서대로 퍼지기 때문에 그 순서만
 * 깨지 않는다면 문제 없음 그래서 출발점이 여러 곳이라도 BFS로 풀 수 있음
 * 
 * day를 세는건 최단 경로 찾는 것과 동일함 각 토마토에서 가장 멀리 떨어진 토마토를
 * 찾는 것이기 때문에 day[next] = day[now] + 1 방식임
 */
class solve7576{

    int row;
    int col;
    int [][] tomato;
    Queue<int []> queue = new ArrayDeque<>();
    int [] moveX = { -1, 1 ,0, 0};
    int [] moveY = { 0, 0, -1, 1};
    int [][] days;

    solve7576 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        tomato = new int[row][col];
        days = new int [row][col];

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

        int result = -1;
        boolean flag = false;
        for(int i=0;i<row;i++){

            for(int j=0;j<col;j++){
                
                result = Math.max(days[i][j], result);
                if(tomato[i][j] == 0){
                    result = -1;
                    flag = true;
                    break;
                }
            }
            if(flag){
                break;
            }
        }

        System.out.println(result);
    }

    void bfs(){

        while(!queue.isEmpty()){
    
            int [] now = queue.poll();

            for(int i=0;i<moveX.length;i++){

                int [] next = {now[0] + moveX[i], now[1] + moveY[i]};
                if(checkValid(next)){
                    tomato[next[0]][next[1]] = 1;
                    queue.add(next);
                    days[next[0]][next[1]] = days[now[0]][now[1]] + 1;
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
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve7576 p = new solve7576(br);
        p.run();

        br.close();
    }
}