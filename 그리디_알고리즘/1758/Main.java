import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class solve1758{

    void run(BufferedReader br) throws IOException{
        int num = Integer.parseInt(br.readLine());

        List<Integer> tips = new ArrayList<>();
        for(int i=0;i<num;i++){
            tips.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(tips, (pre, pro)->{
            return pro - pre;
        });

        Long result = 0L;

        for(int i=0;i<num;i++){
            long tmp = tips.get(i) - i;
            if(tmp > 0){
                result += tmp;   
            }
        }

        System.out.println(result);

    }
}

class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1758 p = new solve1758();
        p.run(br);

        br.close();
    }
}