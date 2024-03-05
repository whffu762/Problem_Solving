import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class solve3986{
    
    Stack<Character> stack = new Stack<>();
    int count = 0;

    boolean checkWord(String input){

        for(int i=0;i<input.length();i++){
            if(stack.empty()){
                stack.add(input.charAt(i));
            } else{
                Character now = input.charAt(i);
                if(now == stack.peek()){
                    stack.pop();
                }
                else {
                    stack.add(now);
                }
            }
        }

        if(stack.empty()){
            return true;
        }

        return false;
    }
    
    void run(BufferedReader br) throws IOException{
        int numOfInput = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfInput;i++){
            if(checkWord(br.readLine())){
                count++;
            }
            stack.clear();
        }

        System.out.println(count);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve3986 p = new solve3986();
        p.run(br);

        br.close();
    }
}