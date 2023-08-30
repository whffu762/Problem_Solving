package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

//1157

class Run {

    String input;
    HashMap<Character, Integer> result = new HashMap<>();

    public void run(BufferedReader br) throws IOException{

        input = br.readLine().toUpperCase();

                

        for(int i=0;i<input.length();i++){
            char x = input.charAt(i);
            result.put(x, 0);
        }

        for(int i=0;i<input.length();i++){
            char x = input.charAt(i);
            int t = result.get(x);
            result.put(x, t+1);
        }

        int max = 0;
        for(int x : result.values()){
            max = Math.max(x, max);
        }

        int count = 0;
        Character target = null;
        for(char x : result.keySet()){
            if(max == result.get(x)){
                target = x;
                count++;
            }
        }

        if(count > 1){
            System.out.println("?");
        }
        else{
            System.out.println(target);
        }
    }
}

public class Main { 
    public static void main(String[] args) {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Run x = new Run();

        try{
            x.run(br);
        }catch(IOException e){}
        

    }
}
