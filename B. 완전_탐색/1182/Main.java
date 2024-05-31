import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/*
 * 전혀 생각하지 못 한 방식으로 풀어야 하는 문제였음
 * 단순히 완전 탐색이라고 생각했는데 DFS를 이용해서 풀어야 함
 * 
 * 가능한 모든 부분 수열을 전부 탐색해야 하기 때문에 values[]의 각 원소가 
 * 부분 수열에 포함되는 경우와 포함되지 않는 경우를 모두 계산해야 하는데 그 과정을 DFS로 구현함
 * DFS는 그래프를 탐색할 때 쓰이는건데 원소의 포함 혹은 미포함을 트리로 만들 수 있기 때문에 DFS로 가능한 것임
 * 이걸 어떻게 생각해냄..?
 * 
 * 이에 대한 자세한 설명은 노션 참고
 */

class solve1182{

    int [] values;
    int target;

    solve1182 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfValues = Integer.parseInt(st.nextToken());
        values = new int [numOfValues];
        target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfValues;i++){
            values[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        int result = dfs(0, 0);
        
        if(target == 0){
            result--;
        }

        System.out.println(result);
    }

    int dfs(int depth, int sum){

        int result = 0;

        if(depth == values.length){

            if(sum == target){
                return 1;
            } else {
                return 0;
            }
        }

        result += dfs(depth+1, sum + values[depth]);
        result += dfs(depth+1, sum);
    
        return result;
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1182 p = new solve1182(br);
        p.run();

        br.close();
    }
}