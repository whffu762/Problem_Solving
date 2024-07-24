import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백트랙킹 이용
 * 
 * 핵심 1
 * 연산자가 삽입 대상이기 떄문에 연산자를 기준으로 순회해야 함
 * 모든 연산자가 한 번 씩 삽입될 수 있도록
 * 
 * 핵심 2
 * 연산자가 그 자리에 들어갔던 적이 있는지는 구분할 필요가 없음
 * 지금 이 시점에 들어갈 연산자가 남아있는지만 확인함
 */

class solve14888{

    int numOfInput;
    int [] inputs;
    int [] operators = new int[4];

    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;

    solve14888 (BufferedReader br) throws IOException{

        numOfInput = Integer.parseInt(br.readLine());
        inputs = new int [numOfInput];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfInput;i++){
            inputs[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<4;i++){
            operators[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){
        
        backTracking(1, inputs[0]);
        
        System.out.println(max);
        System.out.println(min);
    }

    void backTracking(int depth, int result){

        if(depth == numOfInput){
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for(int i=0;i<4;i++){
            if(operators[i] > 0){

                operators[i]--;
                backTracking(depth + 1, calculate(result, inputs[depth], i));
                operators[i]++;
            }
        }
    }

    int calculate(int result, int next, int operator){

        switch (operator) {
            case 0:
                return result + next;
                
            case 1 :

                return result - next;

            case 2 :
                
                return result * next;

            case 3 :
                
                return result / next;
            
            default:
                return 0;
        }
    }


}

public class Main {
    
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14888 p = new solve14888(br);
        p.run();

        br.close();
    }
}