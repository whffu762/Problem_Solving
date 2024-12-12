import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 용액들을 차례로 순회하면서 각 용액에 가장 가까운 값을 이분 탐색으로 찾는 방식
 * 이분 탐색 시 주의할 점
 * - low를 i + 1부터 시작하고 low 와 high가 같을 때 까지 반복해야 함
 * 각 용액에 가장 가까운 값을 찾는 것이기 때문에 용액 자체는 탐색 대상에서 제외하고 그 외의 것들은 모두 탐색해야 함
 *
 * - low와 high를 그 위치에 맞게 -1 혹은 +1 해줘야 함 그렇지 않으면 무한 루프
 * low와 high가 같은 것까지 반복하기 때문
 * 
 * 근데 투포인터로 하는게 더 빠른 거 같음 투포인터로 하면 N이고 이거는 NlogN 아닌가?
 */
class solve2467 {

    int [] liquid;
    solve2467 (BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());
        liquid = new int [num];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            liquid[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        int min = Integer.MAX_VALUE;
        int result1 = 0;
        int result2 = 0;

        for(int i=0;i<liquid.length-1;i++){

            int low = i+1;
            int high = liquid.length-1;

            while(low <= high){

                int mid = (low + high) / 2;
    
                int tmp = liquid[i] + liquid[mid];
                if(Math.abs(tmp) < min){
                    min = Math.abs(tmp);
                    result1 = liquid[i];
                    result2 = liquid[mid];
                }
    
                if(liquid[mid] > -liquid[i]) high = mid - 1;
                else low = mid + 1;
            }
        }

        System.out.println(result1 + " " + result2);
    }
}

public class Main{

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2467 p = new solve2467(br);
        p.run();

        br.close();
    }
}