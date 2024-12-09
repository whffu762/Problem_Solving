import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 전형적인 DP
 * 이 두 개의 값을 비교해서 최댓값과 최솟값을 구하면 됨 
 * - DP[next] (이미 계산된 값) 
 * - DP[now] + map[next] (비교될 새로운 값)
 *
 * 로직 자체는 BFS와 유사하게 가면 됨
 * - next는 이동 규칙에 따라 이동 (-1, 0, 1)
 * - 다만 단방향 이동이라 방문 여부를 따로 관리할 필요는 없음
 *
 * 최솟값과 최댓값
 * 최댓값은 어차피 이동에 따라 값의 증가만 발생하기 때문에 따로 추가적으로 초기화할 필요가 없음
 * 근데 최솟값은 최초값이 0이면 0만 나오게되므로 MAX_VALUE로 초기화를 해줘야 함 
 *  
 * 뭔가 좀 더 개선 방안이 있어보이긴 함
 * - 현재 row에서 최대 혹은 최소값만 이용하는 방식..
 */
class solve2096{

    int size;
    int [][] map;
    int [][] max;
    int [][] min;

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
                max[i][j] = now;
                min[i][j] = now;
            }
        }

        for(int i=1;i<size;i++){
            Arrays.fill(min[i], Integer.MAX_VALUE);
        }
    }

    void run(){

        int [] move = { -1, 0, 1 };
        for(int i=0;i<size-1;i++){

            for(int j=0;j<3;j++){
            
                for(int k=0;k<move.length;k++){

                    int [] next = { i + 1 , j + move[k] };
                    if(checkValid(next)){

                        max[next[0]][next[1]] = Math.max(max[next[0]][next[1]], max[i][j] + map[next[0]][next[1]]);
                        min[next[0]][next[1]] = Math.min(min[next[0]][next[1]], min[i][j] + map[next[0]][next[1]]);
                    }
                }
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

    boolean checkValid(int [] next){

        if(next[0] > -1 && next[0] < size && next[1] > -1 && next[1] < 3) return true;
        return false;
    }
}


public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2096 p = new solve2096(br);
        p.run();

        br.close();
    }
} 