import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 기존 방법과 동일한데 더한 값을 비교하는게 아니라 최솟값 혹은 최댓값을 구하고 더하는 방식
 * 
 * 0. now는 1행부터 시작
 * 1. max[][] 혹은 min[][]의 now-1 행에서 최대와 최솟값을 추출함
 * 2. 해당 값을 now에 더함
 * 
 * 이전 방법과 next의 내용이 다른데 이는 관점만 다를 뿐 현재 행에을 기준으로 이전 행에서 값을 가져오는 방식은 동일함
 */
class solve2096{

    int size;
    int [][] map;
    int [][] max;
    int [][] min;
    int [] move = { -1, 0, 1 };

    solve2096 (BufferedReader br) throws IOException{

        size = Integer.parseInt(br.readLine());
        map = new int [size][3];
        max = new int [size][3];
        min = new int [size][3];

        for(int i=0;i<size;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                
                int now = Integer.parseInt(st.nextToken());
                map[i][j] = now;
            }
        }

        for(int i=0;i<3;i++){
            max[0][i] = map[0][i];
            min[0][i] = map[0][i];
        }
    }

    void run(){

        for(int i=1;i<size;i++){

            for(int j=0;j<3;j++){
                
                int [] tmp = getMinAndMax(i, j);
                max[i][j] = map[i][j] + tmp[0];
                min[i][j] = map[i][j] + tmp[1];
            }
        }

        int maxResult = max[size-1][0];
        int minResult = min[size-1][0]; 
        for(int i=1;i<3;i++){

            maxResult = Math.max(maxResult, max[size-1][i]);
            minResult = Math.min(minResult, min[size-1][i]);
        }

        System.out.println(maxResult + " " + minResult);
    }

    int [] getMinAndMax(int row, int col){

        int [] result = new int [2];
        int nowMax = 0;
        int nowMin = Integer.MAX_VALUE;

        for(int k=0;k<move.length;k++){

            int [] before = { row - 1 , col + move[k] };
            if(checkValid(before)){
                
                nowMax = Math.max(nowMax, max[before[0]][before[1]]);
                nowMin = Math.min(nowMin, min[before[0]][before[1]]);
            }
        }

        result[0] = nowMax;
        result[1] = nowMin;
        return result;
    }

    boolean checkValid(int [] next){

        if(next[0] > -1 && next[0] < size && next[1] > -1 && next[1] < 3) return true;
        return false;
    }
}


public class Main2{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2096 p = new solve2096(br);
        p.run();

        br.close();
    }
} 