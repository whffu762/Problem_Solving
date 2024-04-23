import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class solve11727{

    int n;
    BigInteger [] cache;
    BigInteger mod;

    solve11727(BufferedReader br) throws IOException{

        n = Integer.parseInt(br.readLine()) + 1;

        cache = new BigInteger[n];
        cache[0] = new BigInteger("0");

        mod = new BigInteger("10007");
    }

    void run(){

        cache[1] = new BigInteger("1");

        for(int i=2;i<n;i++){
            
            if(i % 2 ==0){
                cache[i] = cache[i-1].multiply(BigInteger.TWO).add(BigInteger.ONE).mod(mod);
            } else {
                cache[i] = cache[i-1].multiply(BigInteger.TWO).subtract(BigInteger.ONE).mod(mod);
            }
        }

        System.out.println(cache[n-1]);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve11727 p = new solve11727(br);
        p.run();

        br.close();
    }
}