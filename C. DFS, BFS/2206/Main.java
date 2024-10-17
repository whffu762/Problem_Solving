import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;


/*
 * BFS + 시간 복잡도 최적화
 * visited를 3차 배열로 유지해야 함
 * 좌표와 벽 통과 여부 이 둘을 유지
 * 
 * 벽을 통과하지 않고 진행된 방문 -> visited [][][0]에 방문 여부 저장
 * 벽을 통과한 후 진행된 방문 -> visited[][][1]에 방문 여부 저장
 * 
 * 벽을 언제 부쉈건 간에 전부 visited[][][1]에 저장해도 될까? 
 * 뭐가 꼬이는지는 몰라도 뭔가 꼬이지 않을까? 싶음
 * 만약 경로를 추적해야 한다면 아마 뒤섞이긴 할 것임 하지만 여기서 구하는건 최단 거리이기 때문에
 * 경로가 뒤섞이건 말건 알 바 아님 그래서 하나의 배열에 유지해도 되는 것임
 * 
 * 아니 그렇게 하면 하나의 벽만 부수는지는 어떻게 보장함?
 * 그건 visited로 보장하는게 아니라 조건으로 제어하는 거라 조건만 빈틈없이 짜면 됨
 * 
 * 사용할 조건
 * 1. 벽을 부순적이 없는 경우 진행 가능 
 * - 빈 공간 + 방문 안 함
 * - 벽
 * 
 * 2. 벽을 부순적이 있는 경우 진행 가능
 * - 빈 공간 + 방문 안 함
 * 
 * 이렇게 하면 벽을 부수는 것이 딱 한 번임을 보장할 수 있음
 * 다만 주의할 점은 앞서 말했다시피 부순 경우와 부수지 않은 경우의 방문 여부를 유지하는 배열이 
 * 다르기 때문에 확인 및 방문 처리할 배열을 잘 선택해야 함
 * 
 */

class Node {

    int x;
    int y;
    boolean flag;

    Node (int x, int y, boolean flag){
        this.x = x;
        this.y = y;
        this.flag = flag;
    }
}

class solve2206{

    int row, col;
    boolean [][] graph;
    boolean [][][] visited;
    Queue<Node> queue = new ArrayDeque<>();
    
    solve2206(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        graph = new boolean[row][col];
        visited = new boolean[row][col][2];

        for(int i=0;i<row;i++){

            String input = br.readLine();
            for(int j=0;j<col;j++){
                
                if(input.charAt(j) == '0'){
                    graph[i][j] = true;
                }
            }
        }
    }

    void run(){

        System.out.println(bfs());
    }

    int bfs(){

        int [] moveX = { -1, 1, 0, 0};
        int [] moveY = { 0, 0, -1, 1};

        queue.add(new Node(0, 0, false));
        
        visited[0][0][0] = true;
        visited[0][0][1] = true;
        
        int result = 1;

        while(!queue.isEmpty()){

            int size = queue.size();

            for(int i=0;i<size;i++){

                Node now = queue.poll();
                if(checkArrived(now.x, now.y)){
                    return result;
                }
                
                for(int j=0;j<moveX.length;j++){
    
                    int nextX = now.x + moveX[j];
                    int nextY = now.y + moveY[j];
                    if(checkValid(nextX, nextY)){

                        //부순 적 있음 + 벽 아님
                        if(now.flag && graph[nextX][nextY] && !visited[nextX][nextY][1]){
                            visited[nextX][nextY][1] = true;
                            queue.add(new Node(nextX, nextY, true));
                        }

                        //부순 적 없음 + 벽임
                        if(!now.flag && !graph[nextX][nextY]){
                            visited[nextX][nextY][1] = true;
                            queue.add(new Node(nextX, nextY, true));
                        }

                        //부순 적 없음 + 벽 아님
                        if(!now.flag && graph[nextX][nextY] && !visited[nextX][nextY][0]){
                            visited[nextX][nextY][0] = true;
                            queue.add(new Node(nextX, nextY, false));
                        }
                    }
                }
            }
            result++;

        }

        return -1;
    }

    boolean checkArrived(int x, int y){

        if(x == row-1 && y == col-1){
            return true;
        }

        return false;
    }

    boolean checkValid(int x, int y){

        if(x > -1 && x < row && y > -1 && y < col){
            return true;
        }

        return false;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2206 p = new solve2206(br);
        p.run();

        br.close();
    }
}