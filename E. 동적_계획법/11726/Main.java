import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

class solve11726{

    int n;
    BigInteger [] cache;
    BigInteger mod;

    solve11726(BufferedReader br) throws IOException{

        n = Integer.parseInt(br.readLine()) + 2;

        cache = new BigInteger[n];
        cache[0] = new BigInteger("0");

        mod = new BigInteger("10007");
    }

    void run(){

        cache[1] = new BigInteger("1");
        cache[2] = new BigInteger("2");

        for(int i=3;i<n;i++){
            
            cache[i] = cache[i-1].add(cache[i-2]).mod(mod);
        }

        System.out.println(cache[n-2]);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve11726 p = new solve11726(br);
        p.run();

        br.close();
    }
}