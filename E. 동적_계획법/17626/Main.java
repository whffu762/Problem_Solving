import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 각 숫자의 최소 제곱근의 갯수를 구해서 최소를 구하는 방식
 * 근데 시간이 너무 오래 걸림 500ms 나오는데 딴거 보니까 100ms 까지 가능..
 */
class solve17626{

    int input;
    int [] cache;

    solve17626 (BufferedReader br) throws IOException{

        input = Integer.parseInt(br.readLine());
        cache = new int [input+1];
    }

    void run(){

        cache[1] = 1;
        
        for(int i=2;i<cache.length;i++){

            cache[i] = getMin(i);
        }

        System.out.println(cache[input]);
    }

    int getMin(int now){

        if(Math.pow((int) Math.sqrt(now),2) == now){
            return 1;
        }
        
        int result = Integer.MAX_VALUE;
        for(int i=1;i<now/2+1;i++){    

            result = Math.min(result, cache[i] + cache[now-i]);
        }

        return result;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve17626 p = new solve17626(br);
        p.run();

        br.close();
    }
}