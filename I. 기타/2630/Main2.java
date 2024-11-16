import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 재귀 호출을 이용해도 똑같긴 한데 뭔가 코드가 깔끔하자나
 */
class solve2630{

    int num;
    int [][] square;
    int [] result = new int [2];

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

        checkSquare(new int [] {0, num, 0, num});

        StringBuilder sb = new StringBuilder();
        sb.append(result[0]).append("\n");
        sb.append(result[1]);
        System.out.println(sb);
    }

    void checkSquare(int [] now){
        int color = checkSame(now);
        if(color != -1){

            result[color]++;
            return;
        }

        int rowStart = now[0];
        int rowEnd = now[1];
        int colStart = now[2];
        int colEnd = now[3];
        int rowMid = (rowStart + rowEnd) / 2;
        int colMid = (colStart + colEnd) / 2;

        checkSquare(new int [] {rowStart, rowMid, colStart, colMid});
        checkSquare(new int [] {rowStart, rowMid, colMid, colEnd});
        checkSquare(new int [] {rowMid, rowEnd, colStart, colMid});
        checkSquare(new int [] {rowMid, rowEnd, colMid, colEnd});
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
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2630 p = new solve2630(br);
        p.run();

        br.close();
    }
}