import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*
 * 최장 증가 수열 문제 - Longest Increasing Subsequence
 * 
 * 직관적으로 떠오르는 방법
 * 현재 값을 기준으로 앞에 나오는 작은 수를 세면 됨 다만 수열이기 때문에 순서가 지켜져야 함
 * 그래서 DP를 이용해 현재 값의 순위를 저장하고 그 중 순위가 가장 높은 값만 취급하는 것임
 * 
 * 간단한 흐름
 * 1. 1부터 시작해서 현재 인덱스 N에 들어있는 값의 순위를 cache[i]에 저장
 * 
 * 2. 순위 지정 방법
 * - 0부터 N까지 탐색해서 value[N]보다 작은 수 중 cache 값이 가장 큰 값의 인덱스를 구함
 * 
 * 3. 구해진 인덱스가 -1인지 확인
 * - -1이면 value[N]이 앞선 모든 수보다 작은 것이므로 1 cache[n]에 1저장
 * - 아니면 cache[n] = cache[구한 인덱스] + 1
 * 
 * 4. 매 반복에서 구해지는 cache 중 가장 큰 값을 저장
 * 
 * 근데 이 방식은 각 인덱스마다 N-1 만큼 반복되기 때문에 N^2의 복잡도를 가짐
 * 지금은 범위가 1000 이라 괜찮았지만 범위가 커지면 불가능함
 */

class solve11053{

    int num;
    int [] value;
    int [] cache;

    solve11053(BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());

        value = new int [num];
        cache = new int [num];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        cache[0] = 1;
        int result = 1;
        for(int i=1;i<num;i++){

            int tmp = getBefore(i);
            if(tmp == -1){
                cache[i] = 1;
            } else {
                cache[i] = cache[tmp] + 1; 
            }
            
            result = Math.max(result, cache[i]);
        }

        System.out.println(result);

    }

    int getBefore(int now){

        int max = 0;
        int idx = -1;
        for(int i=0;i<now;i++){
            
            if(value[i] < value[now] && cache[i] >= max){
                max = cache[i];
                idx = i;
            }   
        }

        return idx;
    }
}

public class Main {

    public static void main(String [] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11053 p = new solve11053(br);
        p.run();

        br.close();
    }
}