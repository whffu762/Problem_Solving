package pack2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Stack;

//28278 
//간단한 스택 구현인데 직접 구현했을 땐 스위치가 더 빠른 듯?
//내장 Stack 쓰는게 제일 빠름


class solve28278{

    void stackLib(BufferedReader br, StringBuilder sb) throws Exception{

        StringTokenizer st;
        Stack<Integer> stack = new Stack<>();

        int n = Integer.parseInt(br.readLine());
        
        String result;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            switch(command){
                case 1 : 
                        stack.push(Integer.parseInt(st.nextToken()));
                        break;

                case 2 :
                        result = stack.empty() ? "-1" : String.valueOf(stack.pop()); 
                        sb.append(result).append("\n");
                        break;

                case 3 : 
                        sb.append(String.valueOf(stack.size())).append("\n");
                        break;

                case 4 : 
                        result = stack.empty() ? "1" : "0";
                        sb.append(result).append("\n");
                        break;

                case 5 : 
                        result = stack.empty() ? "-1" : String.valueOf(stack.peek());
                        sb.append(result).append("\n");
                        break;

            }
        }

        System.out.println(sb);
    }

    void mine(BufferedReader br, StringBuilder sb) throws Exception{

        StringTokenizer st;

        class Stack1{
            List<Integer> list = new ArrayList<>();
            int idx = -1;

            void push(int x){
                list.add(++idx, x);
            }

            void pop(){
                if(idx == -1){
                    sb.append("-1").append("\n");
                }
                else{
                    sb.append(String.valueOf(list.get(idx))).append("\n");
                    list.remove(idx--);
                }
                
            }

            void size(){
                sb.append(String.valueOf(idx+1)).append("\n");
            }
            
            void isEmpty(){
                if(idx == -1){
                    sb.append("1").append("\n");
                }
                else{
                    sb.append("0").append("\n");
                }
            }

            void print(){
                if(idx == -1){
                    sb.append("-1").append("\n");
                }
                else{
                    sb.append(String.valueOf(list.get(idx))).append("\n");
                }
            }

        }
        Stack1 stack = new Stack1();
        
        int n = Integer.parseInt(br.readLine());
        
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int numCommand = st.countTokens();
            if(numCommand == 2){
                st.nextToken();
                int value = Integer.parseInt(st.nextToken());
                stack.push(value);
            }
            else{
                int command = Integer.parseInt(st.nextToken());
                if(command == 2){
                    stack.pop();
                }
                else if(command == 3){
                    stack.size();
                }
                else if(command == 4){
                    stack.isEmpty();
                }
                else if(command == 5){
                    stack.print();
                }
            }
            
        }

        System.out.println(sb);
    }

    void run(BufferedReader br) throws Exception{
        
        StringBuilder sb = new StringBuilder();
    
        stackLib(br, sb);
        mine(br, sb);

    }
}

class Test{
    public static void main(String[] args) throws Exception{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve28278 p = new solve28278();
        p.run(br);

        br.close();
    }
}

