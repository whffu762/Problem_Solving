import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 운동 기구가 짝수일 경우와 홀수일 경우를 나눠서 생각하면 됨
 */

class solve20300{

    List<Long> list = new ArrayList<>();
    int numOfInput;


    Long whenEven(){

        Long min = 0L;
        for(int i=0;i<numOfInput/2;i++){
            long tmp = list.get(i) + list.get(list.size()-1-i);
            min = Math.max(tmp, min);
        }
        
        return min;
    }

    Long whenOdd(){

        Long min = list.get(list.size()-1);
        for(int i=0;i<numOfInput/2;i++){
            long tmp = list.get(i) + list.get(list.size()-2-i);
            min = Math.max(tmp, min);
        }
        
        return min;
    }


    void run(BufferedReader br) throws IOException{

        numOfInput = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<numOfInput;i++){
            list.add(Long.parseLong(st.nextToken()));
        }
        Collections.sort(list);

        long min = 0;
        if(numOfInput%2 == 0){
            min = whenEven();
            
        } else {
            min = whenOdd();
        }
        
        System.out.println(min);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve20300 p = new solve20300();
        p.run(br);

        br.close();
    }
}