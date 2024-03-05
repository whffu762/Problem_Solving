import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 핵심은 f(n-2) + f(n-1) = f(n) 
 * 그리고 메모이제이션
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

 
public class Main{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2193 p = new solve2193();
        p.run(br);

        br.close();
    }
}

/* 메모리 초과 뜸; 
메모리 초과 뜰만한게 90 넣으면 int로 연산하면 오버플로우 남
class solve2193{

    int length;
    int [] cache;
    int compute(String str){

        if(str.length() == length-1){
            if(str.endsWith("0")){
                return 2;
            }

            return 1;
        }
    
        int result = 0;
        if(str.endsWith("0")){
            result = compute(str+"0") + compute(str+"1");
        }
        else{
            result = compute(str+"0");
        }

        return result;
    }

    void run(BufferedReader br) throws IOException{

        length = Integer.parseInt(br.readLine());
        cache = new int [length+1];

        int result = compute("10");
        System.out.println(result);
    }
}
*/