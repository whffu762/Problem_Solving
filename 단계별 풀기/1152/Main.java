package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//1152

class Run {
    String input;

    public void run(Scanner scan) {
        input = scan.nextLine();
        input = input.strip();
        String [] t = input.split(" ");
        int n = t.length;

        //공백만 들어왔을 때 trim 혹은 strip으로 제거되고 
        //남은 문자열은 ""(빈 문자열)임 null 이나 " "(공백)이 아님
        if(t[0].equals("")){    
            n = 0;
        }

        System.out.print(n);
    }

    public void run2() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //BufferReader가 Scanner 보다 더 빠름
        input = br.readLine();
        input = input.strip();
        String [] t = input.split(" ");
        int n = t.length;

        if(t[0].equals("")){    
            n = 0;
        }

        System.out.print(n);
    }
}

public class Main { 
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        
        Run x = new Run();
        try{
            x.run2();
        }catch(IOException e){

        }
        
        scan.close();
    }
}
