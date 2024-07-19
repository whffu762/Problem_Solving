import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 동작흐름은 Main과 동일함
 * 
 * 이러니 저러니해도 핵심은 두 가지임
 * - 1은 소수가 아니다
 * - 소수는 소인수를 1개 가진다
 * 
 * 
 * 최종 판단
 * 마지막 남은 소인수가 1이 아니면 갯수에 소인수의 갯수에 포함되야 함
 * 또한 1은 소수가 아니므로 소수인 경우 소인수가 자기 자신 하나임 
 * 
 * result 판단
 * 최종 판단 이후의 결과값인 소인수의 갯수가 소수인지 다시 확인함
 * 이 때 소인수의 갯수가 1인 경우는 마찬가지로 1은 소수가 아니므로 제외
 * 그 소인수의 갯수를 다시 소인수분해해서 소인수가 1인 값들의 갯수를 구함
 * 
 * 달라진점
 * 재귀 호출을 이용해서 target에 맞는 sqrt가 적용되게끔 함 그 과정에서 소인수의 갯수는 재귀가 호출된 횟수 + "최종 판단" 임
 * 재귀 호출 횟수를 파라미터로 넘기면서 유지하는 방법을 해보려고 했는데 지역 변수로 유지하는 깔끔한 방법은 없는듯 함.. 
 * 
 * 개선점
 * - sqrt가 각 target에 맞게 적용됨
 * - 속도가 두 배 빨라짐
 * - 코드가 깔끔해짐
 */
class solve1124{

    int start;
    int end;
    int result = 0;
    int primeFactor;

    solve1124(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    void run() {

        for(int i=start;i<end+1;i++){
            this.primeFactor = 0;
            factorization(i);

            if(primeFactor != 1){
                int tmp = primeFactor;
                primeFactor = 0;
                factorization(tmp);

                if(primeFactor == 1){
                    result++;
                }
            } 
        }

        System.out.println(result);
    }

    void factorization(int target){

        int sqrt = (int) Math.sqrt(target);
        int divisor;
        for(divisor=2;divisor<sqrt+1;divisor++){
            
            if(target % divisor == 0){
                primeFactor++;
                factorization(target/divisor);
                break;
            }
        }

        if(divisor == sqrt+1 && divisor != 1){
            primeFactor++;
        }
    }
}

public class MainV2 {

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1124 p = new solve1124(br);
        p.run();

        br.close();
    }
}