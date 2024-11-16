import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 간단한 흐름
 * 1. 현재 값과 만들어야 하는 수열 값 비교
 * - now <= target[i] -> push, i++, 문자열 + 추가
 * - now > target[i] -> pop, j++, 2번으로 이동
 * 
 * 2. pop한 값이 target[i]와 같은지 비교
 * - 같으면 문자열 - 추가
 * - 다르면 NO로 초기화, stack 비우고 종료
 * 
 * 3. 이걸 now가 max보다 커질 때까지 반복
 * 
 * 4. stack이 다 비워지지 않았으면 나머지 다 pop
 * 
 * 근데 문제점 속도는 그럭저럭 괜찮은데 pop 부분 코드가 중복임..
 * pop한 값과 target[i]의 비교를 좀 더 깔끔하게 할 수 없을까?
 */
class solve1874{

    int num;
    int [] target;
    Stack<Integer> stack = new Stack<>();
    StringBuilder sb = new StringBuilder();

    solve1874(BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        target = new int [num+1];

        for(int i=0;i<num;i++){
            target[i+1] = Integer.parseInt(br.readLine());
        }
    }

    void run(){

        int i = 1;
        int j = 1;
        
        while(i<num+1){

            if(i <= target[j]){
                stack.push(i++);
                sb.append("+").append("\n");
            } else {
                if(target[j] == stack.pop()){
                    sb.append("-").append("\n");
                    j++;
                } else {
                    sb.setLength(0);
                    sb.append("NO");
                    stack.clear();
                    break;
                }
            }
        }

        while(!stack.isEmpty()){
            if(target[j] == stack.pop()){
                sb.append("-").append("\n");
                j++;
            } else {
                sb.setLength(0);
                sb.append("NO");
                stack.clear();
                break;
            }
        }
    
        System.out.println(sb);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1874 p = new solve1874(br);
        p.run();

        br.close();
    }
}