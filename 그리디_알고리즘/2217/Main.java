import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


class solve2217{

    void run(BufferedReader br) throws IOException{
        
        int num = Integer.parseInt(br.readLine());
        
        List<Integer> lopes = new ArrayList<>();
        for(int i=0;i<num;i++){
            lopes.add(Integer.parseInt(br.readLine()));    
        }

        Collections.sort(lopes, (a, b)->{
            return b - a;
        }
        );

        int result = lopes.get(0);
        for(int i=1;i<num;i++){
            int tmp = lopes.get(i) * (i+1);
            if(tmp > result){
                result = tmp;
            }
        }

        System.out.println(result);
        
    }
}

class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2217 p = new solve2217();
        p.run(br);

        br.close();
    }
}
 