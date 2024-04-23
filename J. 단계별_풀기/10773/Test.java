package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//10773
class solve10773 {

    Stack<Integer> stack = new Stack<>();

    void stackLib(int input){

        if (input == 0) {
            stack.pop();
        } 
        else {
            stack.push(input);
        }
    }


    int j = 0;
    int[] ary;

    void mine(int input) {

        if (input == 0) {
            ary[--j] = input;
        } 
        else {
            ary[j++] = input;
        }

    }

    void print(){
        
        int sum = 0;
        for(int tmp : stack){   //ary
            sum += tmp;
        }

        System.out.println(sum);
    }

    

    public void run(BufferedReader br) throws IOException {

        int n = Integer.parseInt(br.readLine());

        ary = new int[n];

        for (int i = 0; i < n; i++) {
            int input = Integer.parseInt(br.readLine());

            mine(input);
            stackLib(input);
        }

        print();
    }
}

class Test {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve10773 p = new solve10773();
        p.run(br);

        br.close();
    }
}