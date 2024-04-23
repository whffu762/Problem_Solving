import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class solve11399{
    

    void run(BufferedReader br) throws IOException{
        
        int numOfInput = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        while(st.hasMoreTokens()){
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list, (pre, pro)->{
            return pro - pre;
        });

        int result = 0;
        for(int i=0;i<numOfInput;i++){
            result += list.get(i) * (i + 1);
        }

        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11399 p = new solve11399();
        p.run(br);

        br.close();
    }
}