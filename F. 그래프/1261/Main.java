import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 0 1 BFS를 이용해야 함
 * - 인접 노드에게 접근 후 각 노드 별로 확인
 * 
 * 확인하는 과정
 * 1. 현재 노드가 범위 내에 존재하는지 확인
 * 2. 현재 노드가 벽인지 확인
 * 3-1. 벽이면 최초이거나 혹은 result[next] > result[now] + 1 인지 확인
 * 4-1. 방문처리, 값 최신화, queue의 마지막에 추가
 * 
 * 3-2. 벽이 아니면 최초이거나 혹은 result[next] > result[now] 인지 확인
 * 4-1. 방문처리, 값 최신화, queue의 처음에 추가
 * 
 * 주의할 점
 * 가중치가 0아니면 1(빈 방이거나 벽) 이기 때문에 가능한 알고리즘임 가중치가
 * 그 외의 값이 존재한다면 가중치에 따라 최단 경로 3인방 써야 함
 * 
 * 값 최신화
 * 0이면 now와 next의 값이 같으므로 now의 값으로 최신화함
 * 1이면 now에서 next의 값이 1 증가해야 하므로 now의 값 + 1 로 최신화함
 * 
 * Queue에 추가
 * 0인 것들을 먼저 처리하도록 함으로써 불필요한 노드 접근을 줄일 수 있는 것임
 * 0이든 1이든 전부 아무렇게나 추가하면 메모리 초과 뜸
 */
class solve1261 {

    int row;
    int col;
    Deque<int []> queue = new ArrayDeque<>();
    int [] moveX = { -1, 1, 0, 0 };
    int [] moveY = { 0, 0, -1, 1 };
    boolean [][] visited;
    boolean [][] miro;
    int [][] result;

    solve1261 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());

        visited = new boolean[row][col];
        miro = new boolean [row][col];
        for(int i=0;i<row;i++){
            String input = br.readLine();
            for(int j=0;j<col;j++){
                
                if(input.charAt(j) == '0'){
                    miro[i][j] = true;
                } 
            }
        }
        result = new int [row][col];
    }

    void run() {

        bfs();
        System.out.println(result[row-1][col-1]);
    }

    void bfs(){

        queue.add(new int [] {0, 0});

        while(!queue.isEmpty()){

            int [] now = queue.poll();

            for(int i=0;i<moveX.length;i++){

                int [] next = { now[0] + moveX[i], now[1] + moveY[i] };
                if(checkValid(next)){

                    if(miro[next[0]][next[1]]){
                        int nextCount = result[next[0]][next[1]];
                        if(!visited[next[0]][next[1]] || nextCount > result[now[0]][now[1]]){
                            visited[next[0]][next[1]] = true;
                            result[next[0]][next[1]] = result[now[0]][now[1]];
                            queue.addFirst(next);
                        }
                    } else {
                        int nextCount = result[next[0]][next[1]];
                        if(!visited[next[0]][next[1]] || nextCount > result[now[0]][now[1]] + 1){
                            visited[next[0]][next[1]] = true;
                            result[next[0]][next[1]] = result[now[0]][now[1]] + 1;
                            queue.addLast(next);
                        }
                    }
                }
            }
        }
    }
    
    boolean checkValid(int [] next){

        if(next[0] > -1 && next[0] < row && next[1] > -1 && next[1] < col){
            return true;
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1261 p = new solve1261(br);
        p.run();

        br.close();
    }
}