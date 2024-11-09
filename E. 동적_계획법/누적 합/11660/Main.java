import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 직관적인 누적 합 방식 사용
 * 
 * 각 행의 누적합을 DP에 저장
 * start = (a, b), end = (x, y) 라고 하면
 * 
 * 1. a 부터 x까지 반복
 * 2. sum += dp[i][y] - dp[i][a-1] 
 * 
 * 시간 복잡도
 * 최대 횟수 10만 * 그래프의 최대 크기 1000 = 1억
 * 
 * 근데 이거 말고 BFS를 이용한 기똥찬 방식이 있음
 */
class solve11660{

    int size;
    int [][] value;
    int [][] cache;
    StringBuilder sb = new StringBuilder();

    solve11660(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        int numOfReq = Integer.parseInt(st.nextToken());

        value = new int [size+1][size+1];
        cache = new int [size+1][size+1];

        for(int i=1;i<size+1;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=1;j<size+1;j++){

                value[i][j] = Integer.parseInt(st.nextToken());
                cache[i][j] = value[i][j] + cache[i][j-1];
            }
        }

        for(int i=0;i<numOfReq;i++){

            st = new StringTokenizer(br.readLine());

            int [] start = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
            int [] end = { Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};

            sb.append(getSum(start, end)).append("\n");
        }
    }

    int getSum(int [] start, int [] end){

        int sum = 0;
        for(int i=start[0];i<end[0]+1;i++){
        
            sum += cache[i][end[1]] - cache[i][start[1]-1];   
        }

        return sum;
    }

    void run(){
        
        System.out.println(sb);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve11660 p = new solve11660(br);
        p.run();

        br.close();
    }
}