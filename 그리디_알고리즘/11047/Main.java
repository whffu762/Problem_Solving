package pack2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.IOException;

class solve11047{

    void run(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        List<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            int tmp = Integer.parseInt(br.readLine());
            if(tmp > target){
                break;
            }

            list.add(tmp);
        }

        int result = 0;

        for(int i=list.size()-1;i>-1;i--){
            result += target / list.get(i);
            target %= list.get(i);
        }

        System.out.println(result);
        
    }
}

class Main{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11047 p = new solve11047();
        p.run(br);

        br.close();
    }
}