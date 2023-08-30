package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

//4949
//출력이 대문자인지 소문자인지 잘 확인합시다..
/*
 * 경우의 수
 * 1. 아무것도 없는 경우 
 * - ( 나 [  들어옴
 * - ) 나 ] 들어옴
 * 
 * 2. 뭐 있는 경우
 * - ( 나 [  들어옴
 * - ) 나 ] 들어옴
 * 
 * 3. 괄호 아예 안들어옴
 * 
 */
class solve4949 {

    Stack<Character> stack = new Stack<>();

    String extract(String input) {
        for (int i = 0; i < input.length(); i++) {
            
            char now = input.charAt(i);
            if (now == '(' || now == '[') {
                stack.push(now);
            }

            else if(now == ')'){
                if(stack.empty() || stack.lastElement() != '('){
                    return "no";
                }
                else{
                    stack.pop();
                }
            }
            else if(now == ']'){
                if(stack.empty() || stack.lastElement() != '['){
                    return "no";
                }
                else{
                    stack.pop();
                }
            }

        }

        if (stack.empty()) {
            return "yes";
        }

        else {
            return "no";
        }
    }

    void run(BufferedReader br) throws IOException {
        StringBuilder sb = new StringBuilder();
        String input = "";

        while (true) {
            input = br.readLine();
            if (input.equals(".")) {
                break;
            }

            sb.append(extract(input)).append("\n");
            stack.clear();
        }

        System.out.println(sb);
    }
}

class Test {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve4949 p = new solve4949();
        p.run(br);

        br.close();
    }
}