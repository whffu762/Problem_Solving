import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 점화식 구하는 DP
 */
class solve9461{

    long [] cache = new long [101];
    int [] input;
    solve9461 (BufferedReader br) throws IOException{

        input = new int [Integer.parseInt(br.readLine())];
        for(int i=0;i<input.length;i++){

            input[i] = Integer.parseInt(br.readLine());
        }
    }

    void run(){

        cache[1] = 1;
        cache[2] = 1;
        cache[3] = 1;
        cache[4] = 2;
        cache[5] = 2;

        for(int i=5;i<cache.length;i++){

            cache[i] = cache[i-1] + cache[i-5];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<input.length;i++){

            sb.append(cache[input[i]]).append("\n");
        }

        System.out.println(sb);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9461 p = new solve9461(br);
        p.run();

        br.close();
    }
}