import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class solve3474{

    int compute(int input){
        
        int max = 5;
        int result = 0;
        while(max <= input){
            result += input / max;
            max *= 5;
        }
        
        return result;
    }

    void run(BufferedReader br) throws IOException{

        int numOfInput = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<numOfInput;i++){
            sb.append(compute(Integer.parseInt(br.readLine()))).append("\n");
        }

        System.out.println(sb);
    }
}


public class Main{
    
    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve3474 p = new solve3474();
        p.run(br);

        br.close();
    }
}