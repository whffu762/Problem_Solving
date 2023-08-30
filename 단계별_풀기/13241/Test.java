package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1934
//13241
class solve1269{

    void sec(BufferedReader br) throws IOException{
        
        
    }

    void cal1(long max, long min){
        //1934 이게 더 빠르긴 함..
        int common = 1;
        for(int i=1;i<min+1;i++){
            if(max % i == 0 && min % i == 0){
                common = i;
            }
        }

        System.out.println(min * max / common);
    }
    
    void cal2(long max, long min){
        //유클리드 호제법
        /*
         * 최대공약수를 구하는 공식
         * a 와 b 의 최대공약수는 a % b 와 b 의 최대공약수와 같음
         * 
         */

        long tmp1 = max;
        long tmp2 = min;
        while(min != 0){
            long common = max % min;
            max = min;
            min = common;
        }

        System.out.println(tmp1 * tmp2 / max);
    }

    void first(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());
        long max = Math.max(a,b);
        long min = Math.min(a,b);

        cal1(max, min);
        cal2(max, min);
    }
}

class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1269 s = new solve1269();
        s.first(br);

        br.close();
    }    
    
}