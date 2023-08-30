package pack3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//1152

class Run {
    String input;

    public void run(BufferedReader br) throws IOException{

        String input = br.readLine();
        input.strip();
        String [] xAry = input.split(" ");
        int result = xAry.length;
        

        if(xAry[0].equals("")){
            result = 0;
        }

        System.out.println(result);

    }
}

public class Main { 
    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Run x = new Run();
        try{
            x.run(br);
        }catch(IOException e){

        }

    }
}
