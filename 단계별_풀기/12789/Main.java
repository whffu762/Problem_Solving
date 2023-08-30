import java.util.Stack;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//12789
/*
 * 조건식 정리해서 안쓰면 메모리 초과나는 듯
 * 일괄적으로 정리해서 최소한으로 써야 됨
 * 경우의 수
 * 1. 줄이 다 빈 경우
 * - 스택도 빈 경우
 * - 스택이 남은 경우(스택 == cnt, 스택 != cnt)
 * 
 * 2. 줄이 남아있는 경우
 * - 줄 = cnt
 * - 스택 = cnt
 * - 둘 다 같지 않은 경우
 *
 * 여기서 스택에서 같아도 줄을 pop 해서 안됐던거임
 */

class solve12789{

    void usingAry(int [] ary){
        int n = ary.length;

        Stack<Integer> stack = new Stack<>();
        int cnt = 1;
        int idx = 0;
        while(true){
            int now;
            if(idx > n - 1){
                if(stack.empty()){
                    break;
                }
                else{
                    if(stack.lastElement() == cnt){
                        stack.pop();
                        cnt++;
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                now = ary[idx];
                if(now == cnt){
                    cnt++;
                    idx++;
                }
                else if(!stack.empty() && stack.lastElement() == cnt){
                    stack.pop();
                    cnt++;
                }
                else{
                    stack.push(now);
                    idx++;
                }
            }
        }

        if(cnt - 1 == n){
            System.out.println("Nice");
        }
        else{
            System.out.println("Sad");
        }


    }

    void usingStack(int [] ary){

        int n = ary.length;
        Stack<Integer> line = new Stack<>();
        for (int i = n - 1; i > -1; i--) {
            line.push(ary[i]);
        }

        Stack<Integer> stack = new Stack<>();
        int cnt = 1;
        while (true) {

            if (line.empty()) {
                if (stack.empty()) {
                    break;
                } else {
                    if (stack.lastElement() == cnt) {
                        stack.pop();
                        cnt++;
                    } else {
                        break;
                    }
                }

            } else {

                int now = line.lastElement();
                if (now == cnt) {
                    line.pop();
                    cnt++;
                } else if (!stack.empty() && stack.lastElement() == cnt) {
                    stack.pop();
                    cnt++;
                } else {
                    stack.push(now);
                    line.pop();
                }

            }
        }

        if (cnt - 1 == n) {
            System.out.println("Nice");
        } else {
            System.out.println("Sad");
        }

    }


    void run(BufferedReader br) throws IOException{
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] ary = new int[n];
        for (int i = 0; i < n; i++) {
            ary[i] = Integer.parseInt(st.nextToken());
        }

        usingAry(ary);
        usingStack(ary);

    }

}
class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve12789 p = new solve12789();
        p.run(br);
        
        br.close();
    }
}