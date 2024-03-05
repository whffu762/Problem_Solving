import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class solve9655 {


    void run(BufferedReader br) throws IOException{
        int input = Integer.parseInt(br.readLine());

        if(input % 2 == 0){
            System.out.println("CY");
        }
        else{
            System.out.println("SK");
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9655 p = new solve9655();
        p.run(br);

        br.close();
    }
}
