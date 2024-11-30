import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DP를 이용하는 문제
 * 백트랙킹을 이용할 경우 완전 탐색을 하게되는데 시간복잡도가 3 * 2 ^ N-1 이라 시간초과됨
 * 
 * cache[n][0] = costs[n][0] + Math.min(cache[n-1][1], cache[n-1][2])
 * cache[n][1] = costs[n][1] + Math.min(cache[n-1][0], cache[n-1][2])
 * cache[n][2] = costs[n][2] + Math.min(cache[n-1][0], cache[n-1][1])
 * 
 * 세가지 색상에 대해 현재 집까지 도달하기까지의 최솟값들을 저장하면서 오면 됨
 * 이때 같은 색상이 연달아 나오면 안되는 것만 주의하면 됨 
 * 
 */
class solve1149{

    int num;
    int [][] costs;
    int [] minCost;
    
    int [][] cache;

    solve1149 (BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        costs = new int [num][3];
        minCost = new int [num];

        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());

            int min = Math.min(costs[i][0], costs[i][1]);
            costs[i][2] = Integer.parseInt(st.nextToken());
            minCost[i] = Math.min(min, costs[i][2]);
        }

        cache = new int [num][3];
        for(int i=0;i<3;i++){
            cache[0][i] = costs[0][i];   
        }
    }

    void run(){

        for(int i=1;i<num;i++){

            for(int j=0;j<3;j++){

                cache[i][j] = getMin(i-1, costs[i][j], j);
            }
        }

        int result = Math.min(cache[num-1][0], cache[num-1][1]);
        result = Math.min(result, cache[num-1][2]);

        System.out.println(result);        
    }

    int getMin(int now, int value, int beforeColor){

        int min = Integer.MAX_VALUE;
        for(int i=0;i<3;i++){

            if(beforeColor == i) continue;
            min = Math.min(min, cache[now][i]);
        }

        return min + value;
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1149 p = new solve1149(br);
        p.run();
        
        br.close();
    }
}