import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 투 포인터를 이용하는 문제
 * 
 * start ~ end까지의 합 >= target인 경우 중 가장 짧은 수열을 선택
 * 여기서 start ~ end 까지의 합을 반복문으로 구할 경우 N = 10만 인데 N^2이라 시간 초과가 발생함
 * 
 * 그래서 어떻게 하냐
 * 0부터 진행하면서 
 * - start가 늘어날 경우 기존 sum에서 뺌
 * - end가 늘어날 경우 sum에 더함
 * 이러면 반복문 없이 start ~ end까지의 합을 구할 수 있음
 * 
 * 간단한 알고리즘
 * 초기화
 * - start, end = 0
 * - sum = ary[start]
 * - result = 최댓값
 * 
 * 1. sum과 target을 비교
 * - sum < target : 2-1번으로 이동
 * - sum >= target : 2-2번으로 이동
 * 
 * 2-1. sum이 target보다 작을 경우 sum을 증가시켜야 함 
 *  - end++
 *  - end의 값이 ary.length를 초과했는지 확인
 *  - 초과할 경우 : break
 *  - 초과하지 않을 경우 : sum 에다 ary[end] 추가
 *
 * 2-2. sum이 target보다 크거나 같은 경우 숭열의 길이를 줄여봐야 함
 *  - result와 현재 수열의 길이를 비교해서 최소 구함
 *  - sum에서 start값 빼기
 *  - start++
 * 
 * 3. 이후 모든 배열을 다 돌아본 후
 *  - result가 초기화했을 때와 동일하면 result = 0
 * 
 */
class solve1806{

    int [] ary;
    int target;    

    solve1806 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        ary = new int [num];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){

            ary[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        int start = 0;
        int end = 0;

        long sum = ary[start];
        int result = Integer.MAX_VALUE;

        while(true){

            if(sum < target){
                end++;
                if(end < ary.length){
                    sum += ary[end];
                } else {
                    break;
                }
            } else {
                result = Math.min(result, end - start + 1);
                sum -= ary[start];
                start++;
            }
        }

        if(result == Integer.MAX_VALUE){
            result = 0;
        }

        System.out.println(result);
    }
}

public class Main{

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1806 p = new solve1806(br);
        p.run();

        br.close();
    }
}
