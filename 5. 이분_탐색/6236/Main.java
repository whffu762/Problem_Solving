import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/* 
 *  모자라면 K원을 다시 인출함 => 입력된 금액 중 최댓값이 될 수 있는 최솟값임
 *  정확히 M번을 맞추기 위해 => 횟수가 더 적은 경우에 대해선 신경쓰지 않고 금액만 신경쓰면 됨
 *  
 *  최솟값은 입력값중 최댓값이고 최댓값은 모든 수의 합 이걸로 파라메트릭 서치하면 됨
*/

class solve6236{

    int numOfDay;
    int numOfWithdraw;
    int [] plan;
    int max = 0;
    int sum = 0;

    solve6236 (BufferedReader br) throws IOException{
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfDay = Integer.parseInt(st.nextToken());
        numOfWithdraw = Integer.parseInt(st.nextToken());

        plan = new int [numOfDay];
        for(int i=0;i<numOfDay;i++){
            int input = Integer.parseInt(br.readLine());
            plan[i] = input;
            max = Math.max(max, input);
            sum += input;
        }
    }

    boolean checkLessCount(int mid){

        int remain = mid;
        int count = 1;

        for(int i=0;i<numOfDay;i++){

            if(remain < plan[i]){
                count++;
                remain = mid;
            }

            if(count > numOfWithdraw){
                return false;
            }
            
            remain -= plan[i];
        }

        return true;
    }

    void run(){

        int start = max;
        int end = sum;
        int result = start;

        while(start <= end){

            int mid = (start + end) / 2;

            if(checkLessCount(mid)){
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve6236 p = new solve6236(br);
        p.run();

        br.close();
    }
}