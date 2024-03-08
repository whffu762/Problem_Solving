import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 핵심은 f(n-2) + f(n-1) = f(n) 
 * 그리고 메모이제이션
 * 
 * 재귀호출 할 필요없이 그냥 피보나치 계산하듯이 배열에다 계산해도 됨
 */

class solve2193{

    long [] cache;

    long compute(int length){

        if(length < 3){
            return 1;
        }

        if(cache[length] != 0){
            return cache[length];
        }

        cache[length] = compute(length - 2) + compute(length - 1); 
        return cache[length];
    }

    void run(BufferedReader br) throws IOException{

        int input = Integer.parseInt(br.readLine());
        cache = new long [input+1];
        System.out.println(compute(input));
    }
}


class v2{

    long [] results;

    void run(BufferedReader br) throws IOException{

        int n = Integer.parseInt(br.readLine());
        
        results = new long[n+2];
        results[1] = 1;
        results[2] = 1;

        for(int i=3;i<n+1;i++){
            results[i] = results[i-1] + results[i-2];
        }

        System.out.print(results[n]);
    }
}

 
public class Main{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2193 p = new solve2193();
        p.run(br);

        br.close();
    }
}