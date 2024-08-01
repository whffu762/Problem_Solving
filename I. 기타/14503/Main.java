import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 항상 북쪽을 바라보고 있다고 가정하고 동작을 구현
 * 주변 환경이 저장된 배열을 시계방향으로 회전시킴으로써 방향 전환을 구현
 * 
 * rotate를 구현하는게 헷갈림 이에 대해선 노션에서 자세히
 */

class Robot {

    int row;
    int col;
    int direction;
    int [][] map;
    int [] move = {-1, 1};
    int result = 0;

    Robot (int row, int col, int direction, int [][] map){

        this.row = row;
        this.col = col;
        this.direction = direction;
        this.map = map;
        for(int i=0;i<4 - direction;i++){
            rotate();
        }
    }

    boolean checkAround(){

        for(int i=0;i<move.length;i++){
            if(map[row][col + move[i]] == 0){
                return true;
            }
        }
        
        for(int i=0;i<move.length;i++){
            if(map[row + move[i]][col] == 0){
                return true;
            }
        }

        return false;
    }
    
    void rotate(){

        int newRow = this.map[0].length;
        int newCol = this.map.length;

        int [][] result = new int[newRow][newCol];
        for(int i=0;i<newRow;i++){

            for(int j=0;j<newCol;j++){
                result[i][j] = this.map[newCol - j - 1][i];
            }
        }
        this.map = result;

        int tmp = this.row;
        this.row = this.col;
        this.col = newCol - tmp - 1;
    }

    boolean back(){
        
        this.row++;
        if(map[row][col] == 1){
            return false;
        }

        return true;
    }

    void checkFront(){
        
        if(map[row-1][col] == 0){
            this.row--;
        }
    }

    void clean(){

        if(map[row][col] == 0){
            result++;
            map[this.row][this.col] = 2;
        }
    }
}


class solve14503 {

    Robot robot;

    solve14503(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int [][] map = new int[row][col];

        st = new StringTokenizer(br.readLine());
        int startRow = Integer.parseInt(st.nextToken());
        int startCol = Integer.parseInt(st.nextToken());
        int direction = Integer.parseInt(st.nextToken());

        for(int i=0;i<row;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        robot = new Robot(startRow, startCol, direction, map);
    }

    void run(){

        while(true){

            robot.clean();

            if(robot.checkAround()){
                //있는 경우
                robot.rotate();
                robot.checkFront();
            }
            else {
                //없는 경우
                if(!robot.back()){
                    break;
                }
            }
        }
        System.out.println(robot.result);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14503 p = new solve14503(br);
        p.run();

        br.close();
    }
}