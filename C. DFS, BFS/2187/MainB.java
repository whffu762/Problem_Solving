import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 최단 경로를 구하기 위해선 BFS로 해야 함
 * DP처럼 이전 값을 저장해서 다음 값을 연산하는 방식
 * 
 * DFS로 하면 시간 초과가 발생할 수 있음(전수 조사니까) 또한 백 트랙킹이 필요함
 */

class solve2187{

    boolean [][] map;
    int [][] visited;
    int [] canMove = { 1 , -1 };
    Queue<int []> queue = new ArrayDeque<>();

    boolean checkValid(int row, int col){

        if(row < 0 || row > map.length-1 || col < 0 || col > map[1].length-1){
            return false;
        }

        if(visited[row][col] != 0){
            return false;
        }

        return map[row][col];
    }
    

    void getNear(int [] now){

        for(int i=0;i<canMove.length;i++){

            int nowRow = now[0] + canMove[i];
            if(checkValid(nowRow, now[1])){
                queue.add(new int [] {nowRow, now[1]});
            }
        }

        for(int i=0;i<canMove.length;i++){
            
            int nowCol = now[1] + canMove[i];
            if(checkValid(now[0], nowCol)){
                queue.add(new int [] {now[0], nowCol});
            }
        }
    }


    void getPath(){

        
        visited[0][0] = 1;
        queue.add(new int [] {0, 0});
        
        while(!queue.isEmpty()){

            int [] now = queue.poll();
            int nowDistance = visited[now[0]][now[1]];

            getNear(now);
            for(int [] child : queue){

                int childDistance = visited[child[0]][child[1]];
                if(childDistance == 0){
                    visited[child[0]][child[1]] = nowDistance + 1;
                } else {
                    visited[child[0]][child[1]] = Math.min(childDistance, nowDistance+1);
                }
            }
        }
    }




    void run(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfRow = Integer.parseInt(st.nextToken());
        int numOfCol = Integer.parseInt(st.nextToken());
        map = new boolean[numOfRow][numOfCol];
        visited = new int [numOfRow][numOfCol];

        for(int i=0;i<numOfRow;i++){
            
            String input = br.readLine();
            for(int j=0;j<numOfCol;j++){
                if(input.charAt(j) == '1'){
                    map[i][j] = true;
                } else {
                    map[i][j] = false;
                }
            }
        }

        getPath();
        System.out.println(visited[numOfRow-1][numOfCol-1]);
    }
}


public class Main {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2187 p = new solve2187();
        p.run(br);

        br.close();
    }
}