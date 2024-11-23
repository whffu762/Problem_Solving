import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class solve5525{

    String p;
    String s;

    solve5525(BufferedReader br) throws IOException{
        
        int num = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){
            sb.append("IO");
        }
        sb.append("I");
        p = sb.toString();

        num = Integer.parseInt(br.readLine());

        s = br.readLine();
    }

    void run(){

        int result = 0;
        for(int i=0;i<s.length()-p.length()+1;i++){

            if(p.equals(s.substring(i, i+p.length()))){
                result++;
            }
        }

        System.out.println(result);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve5525 p = new solve5525(br);
        p.run();

        br.close();
    }
}