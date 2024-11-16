import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 간단한 흐름
 * 1. 현재 상태를 확인함
 * - -1 : 다른 색이 존재
 * - 0 : 모두 흰색
 * - 1 : 모두 파란색
 * 
 * 2. -1이면 분할
 * - 분할은 일단 노가다임
 * - 분할된 사각형을 queue에 추가
 * 
 * 3. -1이 아니면 해당 result[idx]++
 * 
 * 4. queue가 빌 때까지 반복
 * 
 * 문제점 사각형 나누는건 어쩔 수 없는데 코드가 너무 찝찝함
 * 이래서 재귀호출을 쓰는거다~
 */
class solve2630{

    int num;
    int [][] square;

    solve2630 (BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        square = new int [num][num];

        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<num;j++){

                square[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void run(){

        int [] result = new int [2];

        Queue<int []> squares = new ArrayDeque<>();
        squares.add(new int [] {0, num, 0, num});

        while(!squares.isEmpty()){

            int [] now = squares.poll();

            int tmp = checkSame(now);
            if(tmp == -1){
                
                for(int [] next : splitSquare(now)){
                    squares.add(next);
                }
                
            } else {
                result[tmp]++;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append("\n");
        sb.append(result[1]);
        System.out.println(sb);
    }

    int checkSame(int [] now){

        int rowStart = now[0];
        int rowEnd = now[1];
        int colStart = now[2];
        int colEnd = now[3];

        for(int i=rowStart;i<rowEnd;i++){

            for(int j=colStart;j<colEnd;j++){
                if(square[rowStart][colStart] != square[i][j]){
                    return -1;
                }
            }
        }

        return square[rowStart][colStart];
    }

    int [][] splitSquare(int [] now){

        int [][] result = new int [4][4];

        int rowStart = now[0];
        int rowEnd = now[1];
        int colStart = now[2];
        int colEnd = now[3];
        int rowMid = (rowStart + rowEnd) / 2;
        int colMid = (colStart + colEnd) / 2;

        result[0][0] = rowStart;
        result[0][1] = rowMid;
        result[0][2] = colStart;
        result[0][3] = colMid;

        result[1][0] = rowStart;
        result[1][1] = rowMid;
        result[1][2] = colMid;
        result[1][3] = colEnd;

        result[2][0] = rowMid;
        result[2][1] = rowEnd;
        result[2][2] = colStart;
        result[2][3] = colMid;

        result[3][0] = rowMid;
        result[3][1] = rowEnd;
        result[3][2] = colMid;
        result[3][3] = colEnd;

        return result;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2630 p = new solve2630(br);
        p.run();

        br.close();
    }
}