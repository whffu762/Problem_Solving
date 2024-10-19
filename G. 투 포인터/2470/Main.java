import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 전형적인 투포인터 문제
 * 정렬 후 투포인터를 쓰는 문제
 * 
 * 크게 세 단계로 구성
 * 1. 탐색을 진행할 조건
 * 2. 탐색 동선
 * 3. 결과를 저장할 조건
 * 
 * 2. 탐색 동선
 * start(0)과 end(length-1) 에서 시작해서 두 값의 합의 절댓값이 최소인 조합을 찾아야 함
 * tmp > 0인 경우
 * tmp를 줄여야하므로 end의 인덱스를 줄임
 * tmp < 0인 경우
 * tmp를 늘려야하므로 start의 인덱스를 높임
 * 
 * 3. 결과를 저장할 조건
 * 위 과정에서 tmp의 절댓값이 < min 이면
 * 결과로 저장 및 min 최신화
 * -> 이를 통해 합이 0에 가까운 두 값을 구할 수 있음
 * 
 * 1. 탐색을 진행할 조건
 * 이 과정을 start가 end를 넘어서면 종료
 */
class solve2470 {

    int [] liquid;

    solve2470 (BufferedReader br) throws IOException{

        liquid = new int [Integer.parseInt(br.readLine())];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<liquid.length;i++){
            liquid[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(liquid);
    }

    void run(){

        int [] result = new int [2];
        
        int start = 0;
        int end = liquid.length-1;
        int min = Integer.MAX_VALUE;

        while(start < end){
            
            int tmp = liquid[start] + liquid[end];
            if(Math.abs(tmp) < min){

                min = Math.abs(tmp);
                result[0] = liquid[start];
                result[1] = liquid[end];
            }

            if(tmp < 0){
                start++;
            } else {
                end--;
            }
        }

        System.out.println(result[0] + " " + result[1]);
        
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2470 p = new solve2470(br);
        p.run();

        br.close();
    }
}