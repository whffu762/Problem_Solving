import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

class solve11047{
    

    void run(BufferedReader br) throws IOException{
        
        int numOfInput = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<numOfInput;i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list);

        double result = 0;
        for(int i=0;i<list.size()-1;i++){
            result += (double) list.get(i) / 2;
        }

        result += list.get(list.size()-1);

        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11047 p = new solve11047();
        p.run(br);

        br.close();
    }
}