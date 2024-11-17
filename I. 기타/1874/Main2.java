import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/*
 * 속도는 느리지만 훨씬 깔끔함 10ms 정도 차이남
 * 
 * 간략한 흐름
 * 이전엔 now를 중심으로 순회했지만 이번엔 target을 중심으로 순회함
 * 1. 현재 값과 만들어야 하는 수열 값 비교
 * - now <= target[i] -> push, now++, 문자열 + 추가
 * - 근데 이걸 now가 target[i]보다 커질 때까지 반복
 * 
 * 2. 현재 값과 만들어야 하는 수열 값 비교 2
 * - now > target[i] -> pop, i++, 3번으로 이동
 * 
 * 3. pop 내부에선 현재 target[i]와 stack에서 pop한 값을 비교함
 * - 같으면 - 추가 
 * - 다르면 NO로 설정, 기타 등등 마무리
 * 
 * 4. 아래 조건이 만족하는 동안 2번 반복
 * - stack에 원소가 들어있음
 * - target[i]가 now보다 작음(같아지면 탈출)
 * 
 * 5. 이 모든걸 i가 num+1보다 작은 동안(target을 전부 비교해야) 종료
 * 
 * 이렇게 해서 한번의 반복으로 수행되는 깔끔한 코드를 짤 수 있음
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

    void push(int i){
        stack.push(i);
        sb.append("+").append("\n");
    }

    boolean pop(int now){

        if(stack.pop() == now){
            sb.append("-").append("\n");
            return true;
        }

        stack.clear();
        sb.setLength(0);
        sb.append("NO");
        return false;
    }

    void run(){

        int now = 1;
        int i = 1;
        while(i<num+1){

            while(now <= target[i]) push(now++);

            while(!stack.isEmpty() && now > target[i]) {
                
                if(!pop(target[i++])){
                    i = num+1;
                    break;
                }
            }
        }

        System.out.println(sb);
    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1874 p = new solve1874(br);
        p.run();

        br.close();
    }
}