import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * 연구실의 최대 크기가 8 x 8 이기 때문에 벽이 쳐질 수 있는 모든 경우의 수를 계산해도 됨
 * 64 * 63 * 62 < 2억
 * 
 * 벽 치기
 * 1. 연구실이 고정 길이 배열이기 때문에 벽을 칠 수 있는 조합은 일차 배열로 진행하면서 값으로부터 2차 배열의 인덱스를 얻을 수 있음
 * ex) 3 * 5 행렬에서 10은 2(10/5)행 0(10%5)열 에 해당함
 * 
 * 2. 벽은 0인 곳에만 쳐질 수 있음
 * 1에다 치는건 불필요한 연산을 한 번 더하는 정도라 상관없을 수 있지만 2에다 치는건 2를 지워버리기 때문에 결과가 틀림
 * 
 * 
 * 안전한 공간 카운트
 * 1. 가중치가 1인 그래프기 때문에 BFS로 진행하면 빠름
 */

class solve14502{

    int row;
    int col;
    int [][] lab;

    solve14502(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        
        lab = new int [row][col];
        for(int i=0;i<row;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                lab[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void run(){

        int max = 0;
        int [] tmp = new int [row * col];
        for(int i=0;i<tmp.length;i++){
            for(int j=i+1;j<tmp.length;j++){
                for(int k=j+1;k<tmp.length;k++){
                    int [] walls = { i, j, k };
                    int [][] nowCase = makeWall(walls);

                    if(nowCase != null){
                        int result = getSafeArea(nowCase);
                        max = Math.max(result, max);
                    }
                }
            }
        }

        System.out.println(max);
    }

    int [][] makeWall(int [] wallsIndex){

        int [][] tmpLab = new int [row][col];
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                tmpLab[i][j] = lab[i][j];
            }
        }

        int [][] walls = new int [3][2];
        for(int i=0;i<wallsIndex.length;i++){
            walls[i] = getIndexFrom(wallsIndex[i]);
            int row = walls[i][0];
            int col = walls[i][1];

            if(lab[row][col] != 0){
                return null;
            }

            tmpLab[walls[i][0]][walls[i][1]] = 1;
        }

        return tmpLab;
    }

    int getSafeArea(int [][] tmpLab){
        
        boolean [][] visited = new boolean [row][col];

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++) {
                if(tmpLab[i][j] == 2 && !visited[i][j]) {
                    spreadVirus(tmpLab, visited, i, j);
                }
            }
        }

        int count = 0;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(tmpLab[i][j] == 0){
                    count++;
                }
            }
        }

        return count;
    }

    int [] getIndexFrom(int now){

        int [] index = new int [2];
        index[0] = now / col;
        index[1] = now % col;

        return index;
    }

    boolean checkValid(int [][] tmpLab, boolean [][] visited, int rowOfVirus, int colOfVirus){

        if(rowOfVirus < 0 || rowOfVirus >= this.row || colOfVirus < 0 || colOfVirus >= this.col){
            return false;
        }

        if(tmpLab[rowOfVirus][colOfVirus] != 0){
            return false;
        }

        return !visited[rowOfVirus][colOfVirus];
    }


    void spreadVirus(int [][] tmpLab, boolean [][] visited, int rowOfVirus, int colOfVirus){

        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int [] { rowOfVirus, colOfVirus});
        visited[rowOfVirus][colOfVirus] = true;

        int [] canMove = {-1, 1};

        while(!queue.isEmpty()){

            int [] now = queue.poll();
            int nowRow = now[0];
            int nowCol = now[1];

            for(int i=0;i<canMove.length;i++){

                int nextCol = nowCol + canMove[i];
                if(checkValid(tmpLab, visited, nowRow, nextCol)){
                    
                    visited[nowRow][nextCol] = true;
                    tmpLab[nowRow][nextCol] = 2;
                    queue.add(new int [] {nowRow, nextCol});
                }
            }

            for(int i=0;i<canMove.length;i++){

                int nextRow = nowRow + canMove[i];
                if(checkValid(tmpLab, visited, nextRow, nowCol)){
                    
                    visited[nextRow][nowCol] = true;
                    tmpLab[nextRow][nowCol] = 2;
                    queue.add(new int [] {nextRow, nowCol});
                }
            }
        }
    }
}


public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14502 p = new solve14502(br);
        p.run();

        br.close();
    }
}