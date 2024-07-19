import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
 * 동작 과정
 * 1. target의 소인수 갯수 result를 구함
 * 2. result의 소인수 갯수를 구해서 1이면 count 추가
 * 
 * 
 * 소인수 분해 과정
 * 1. target의 제곱근 sqrt를 구함
 * 2. target을 divisor로 나눔(divisior는 2부터 시작)
 * 3. 나머지가 0이면 그에 맞는 처리 수행
 * 3-2. 0이 아니면 divisor++
 * 4. 이를 divisor <= sqrt 일 동안 반복
 * 5. while문 종료 후 sqrt가 1이 아니면 그에 맞는 처리 수행
 * 
 * 
 * 3번의 그에 맞는 처리
 * - result에 소인수(divisor) 추가
 * - target을 divisor로 나눈 몫으로 대체
 * - divisor 2로 초기화
 * 
 * 5번의 그에 맞는 처리
 * - result에 소인수(divisor) 추가 
 * 
 * 5번에선 왜?
 * divisor가 sqrt를 넘어설 때까지 나눠떨어지지 않았다면 그 target도 소수인 것이기 때문에 추가해줘야 함
 * 하지만 모든 소인수가 구해진 후엔 target이 1이 되기 때문에 1인 경우는 제외
 * 
 * 문제점
 * - 몫을 소인수 분해하는 과정에서도 이전 target의 sqrt가 쓰임(뭔가 깔끔하지 않음)
 * - if문이 너무 많음 줄일 수 있을거 같은데..
 * 
 */
class solve1124{

    int start;
    int end;
    int count = 0;

    solve1124(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    void run() {

        for(int i=start;i<end+1;i++){
            int result = factorization(i);

            if(result != 1 && factorization(result) == 1){
                count++;
            }
        }

        System.out.println(count);
    }

    int factorization(int target){

        List<Integer> result = new ArrayList<>();
        int sqrt = (int) Math.sqrt(target);

        int divisor = 2;

        while(divisor <= sqrt){

            if(target % divisor == 0){
                result.add(divisor);
                target /= divisor;
                divisor = 2;
            } else {
                divisor++;
            }
        }

        if(target != 1){
            result.add(target);
        }

        return result.size();
    }

    boolean doFactorization(int target, int sqrt, List<Integer> result){

        for(int i=2;i<sqrt+1;i++){
            
            if(target % i == 0){
                result.add(i);
                return true;
            }

        }

        return false;
    }

    
}

public class Main {

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1124 p = new solve1124(br);
        p.run();

        br.close();
    }
}