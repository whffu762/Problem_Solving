import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 나머지 연산 분배 법칙
 * 
 * (x + y) % m = (x % m + y % m) % m
 * 덧셈뿐만 아니라 뺄셈, 곱셈도 만족함 증명은 노션에서
 */

class solve1904{
    
    int [] cache;
    
    void pibonachi(int n){
        
        cache = new int[n+2];
        cache[1] = 1;
        cache[2] = 2;

        for(int i=3;i<n+1;i++){
            cache[i] = (cache[i-1] + cache[i-2]) % 15746;
        }
        
        System.out.println(cache[n]);
    }
    
    void run(BufferedReader br) throws IOException{

        int n = Integer.parseInt(br.readLine());    
        pibonachi(n);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1904 p = new solve1904();
        p.run(br);
        
        br.close();
    }
}