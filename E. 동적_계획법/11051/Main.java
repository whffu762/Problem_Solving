import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이항 계수란 경우의 수에서 나오는 nCr을 의미함
 * 
 * 점화식이 존재하는데 nCr = n-1Cr + n-1Cr-1
 * 이를 통해 DP로 구하면 됨
 */

class solve11051{

    int [][] cache;
    int n;
    int k;
    int m = 10007;

    solve11051(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        cache = new int[n+1][k+1];
    }

    void run(){

        for(int i=0;i<n+1;i++){
            cache[i][0] = 1;
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<k;j++){

                cache[i+1][j+1] = (cache[i][j] % m + cache[i][j+1] % m) % m;
            }
        }

        System.out.println(cache[n][k]);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11051 p = new solve11051(br);
        p.run();

        br.close();
    }
}