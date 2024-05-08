import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 가중치가 존재하지 않기 때문에 BFS로 푸는게 가장 효율적임
 * 각 육지마자 BFS 돌려서 최댓값 찾고
 * 각 BFS의 최댓값 중 최댓값을 찾는 방식
 * 
 * 최단 경로 탐색이라 DFS로 하면 귀찮아질 듯
 * 
 */
class solve2589{

    int row;
    int column;
    boolean [][] map;
    int [][] result;
    int [] canMove = { -1, 1 };

    solve2589(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        column = Integer.parseInt(st.nextToken());
        
        map = new boolean[row][column];

        for(int i=0;i<row;i++){

            String input = br.readLine();
            for(int j=0;j<column;j++){

                if(input.charAt(j) == 'L'){
                    map[i][j] = true;
                }
            }
        }
    }

    int bfs(int x, int y){

        int max = 0;

        boolean [][] visited = new boolean[row][column];

        result = new int [row][column];

        Queue<int []> queue = new ArrayDeque<>();
        queue.offer(new int [] {x, y});
        visited[x][y] = true;
        result[x][y] = 0;

        while(!queue.isEmpty()){

            int [] now = queue.poll();
            int nowX = now[0];
            int nowY = now[1];

            max = Math.max(result[nowX][nowY], max);

            for(int i=0;i<canMove.length;i++){

                int nextX = nowX + canMove[i];
                if(nextX < 0 || row <= nextX){
                    continue;
                }

                if(!visited[nextX][nowY] && map[nextX][nowY]){         
                    result[nextX][nowY] = result[nowX][nowY] + 1;
                    visited[nextX][nowY] = true;
                    queue.offer(new int [] {nextX, nowY});
                }
            }

            for(int i=0;i<canMove.length;i++){

                int nextY = nowY + canMove[i];
                if(nextY < 0 || column <= nextY){
                    continue;
                }

                if(!visited[nowX][nextY] && map[nowX][nextY]){         
                    result[nowX][nextY] = result[nowX][nowY] + 1;
                    visited[nowX][nextY] = true;
                    queue.offer(new int [] {nowX, nextY});   
                }
            }
        }

        return max;
    }

    void run(){

        int result = 0;
        for(int i=0;i<map.length;i++){
            for(int j=0;j<map[0].length;j++){
                if(map[i][j]){
                    result = Math.max(result, bfs(i, j));
                }
            }
        }

        System.out.println(result);
    }
}


public class MainB{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2589 p = new solve2589(br);
        p.run();

        br.close();
        
    }
}