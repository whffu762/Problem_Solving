import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class solve11055{

    int num;
    int [] value;
    int [] cache;

    solve11055 (BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());

        value = new int [num];
        cache = new int [num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        cache[0] = value[0];

        int max = value[0];
        for(int i=1;i<num;i++){

            int result = LIS(i);
            if(result == -1){
                cache[i] = value[i];
            } else {
                cache[i] = cache[result] + value[i];
            }

            max = Math.max(max, cache[i]);
        }

        System.out.println(max);
    }

    int LIS(int now){

        int idx = -1;
        int max = 0;
        for(int i=0;i<now;i++){
            
            if(value[i] < value[now] && cache[i] > max){

                max = cache[i];
                idx = i;
            }
        }

        return idx;
    }   
}

public class Main {

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve11055 p = new solve11055(br);
        p.run();

        br.close();
    }
}