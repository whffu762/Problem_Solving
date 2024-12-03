import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 모든 경우의 수 다 탐색
 * 최댓값이 500이라 시간 넉넉함
 * 
 * i, j를 기준으로 도형을 4방향으로 회전시킴으로써 완전 탐색을 수행
 * l의 경우 단순히 4방향만 하면 됨
 * ㅁ의 경우 회전이 필요없음
 * L의 경우 L과 뒤집은 L이 존재함
 * ㄹ의 경우 ㄹ과 뒤집은 ㄹ이 존재함
 * ㅗ의 경우 단순히 4방향만 하면 됨(뒤집은 모양이 회전시켜도 나옴)
 * 
 * 이 도형에 대한 좌표를 각각 작성해서 수행하는 방식 노가다임
 */
class solve14500{

    int row;
    int col;
    int [][] map;
    int result = 0;
    solve14500 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int [row][col];
        for(int i=0;i<row;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){

                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void run(){

        int [] shape1X = {0, -1, -2, -3, 0, 1, 2, 3, 0, 0, 0, 0, 0, 0, 0, 0};
        int [] shape1Y = {0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -2, -3, 0, 1, 2, 3};

        int [] shape31X = {0, -1, -1, -2, 0, 1, 1, 2, 0, 0, 1, 1, 0, 0, 1, 1};
        int [] shape31Y = {0, 0, 1, 1, 0, 0, 1, 1, 0, -1, -1, -2, 0, 1, 1, 2};
        
        int [] shape32X = {0, -1, -1, -2, 0, 1, 1, 2, 0, 0, -1, -1, 0, 0, -1, -1};
        int [] shape32Y = {0, 0, -1, -1, 0, 0, -1, -1, 0, -1, -1, -2, 0, 1, 1, 2};

        int [] shape2X = {0, -1, -2, 0, 0, 0, 1, 2, 0, 0, 0, 0, 0, -1, 1, 0, 0, 0, -1, 1};
        int [] shape2Y = {0, 0, 0, -1, 1, 0, 0, 0, -1, 1, 0, -1, -2, 0, 0, 0, 1, 2, 0, 0};
        
        int [] shape4X = {0, -1, -2, -1, -1, 0, 1, 2, 1, 1, 0, 0, 0, -1, 1, 0, 0, 0, -1, 1};
        int [] shape4Y = {0, 0, 0, -1, 1, 0, 0, 0, -1, 1, 0, -1, -2, -1, -1, 0, 1, 2, 1, 1};

        for(int i=0;i<row;i++){

            for(int j=0;j<col;j++){

                shape1(i, j, shape1X, shape1Y);
                shape1(i, j, shape31X, shape31Y);
                shape1(i, j, shape32X, shape32Y);

                shape2(i, j);

                shape3(i, j, shape2X, shape2Y);
                shape3(i, j, shape4X, shape4Y);
            }
        }

        System.out.println(result);
    }

    void shape1(int nowRow, int nowCol, int [] moveX, int [] moveY){

        int tmp = 0;
        for(int i=0;i<moveX.length;i++){

            int [] next = { nowRow + moveX[i], nowCol + moveY[i]};
            if(checkValid(next)){
                tmp += map[next[0]][next[1]];
            }

            if((i + 1) % 4 == 0){
                result = Math.max(result, tmp);
                tmp = 0;
            }
        }
    }

    void shape2(int nowRow, int nowCol){

        int tmp = 0;
        for(int i=nowRow;i<nowRow+2;i++){

            for(int j=nowCol;j<nowCol+2;j++){

                if(checkValid(new int [] {i, j}))
                tmp += map[i][j];
            }
        }

        result = Math.max(result, tmp);
    }

    void shape3(int nowRow, int nowCol, int [] moveX, int [] moveY) {

        int tmp = 0;
        int j = 0;
        for(int i=0;i<moveX.length;i++){
            
            boolean flag = false;
            j++;
            int [] next = {nowRow + moveX[i], nowCol + moveY[i]};
            if(checkValid(next)){
                tmp += map[next[0]][next[1]];
                flag = true;
            }

            if(j % 4 == 0 && flag){
                result = Math.max(result, tmp);
                tmp -= map[next[0]][next[1]];
            }

            if(j % 5 == 0) {
                result = Math.max(result, tmp);
                tmp = 0;
                j = 0;
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

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14500 p = new solve14500(br);
        p.run();

        br.close();
    }
}