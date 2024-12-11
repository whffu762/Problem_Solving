import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 파라메트릭 서치
 * 탐색 범위
 * 여기선 high 값도 결과에 포함될 수 있기 때문에 같은 경우까지 계산해야 함
 * 
 * mid
 * mid가 0과 1일 때를 주의해서 다뤄야 함
 * mid 가 0인 경우엔 나눠줄 수 없는 것이므로 반복 종료
 * low + high가 1이면 0으로 취급되서 과자의 길이가 1인 경우를 확인하지 못 함 이를 처리해야 함
 * 
 * 파라미터 결정 조건
 * 과자의 갯수는 나누기 연산의 몫으로 계산
 */
class solve16401{

    int target;
    int num;
    int [] snacks;
    int high;
    int low;

    solve16401(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        target = Integer.parseInt(st.nextToken());
        num = Integer.parseInt(st.nextToken());
        snacks = new int [num];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            snacks[i] = Integer.parseInt(st.nextToken());
            high = Math.max(snacks[i], high);

        }
    }

    void run(){

        int mid;
        while(low <= high){

            mid = (low + high) > 1 ? (low + high) / 2 : (low + high);
            
            if(mid == 0){
                low = 1;
                break;
            }

            if(check(mid)) low = mid + 1;
            else high = mid - 1;
        }

        System.out.println(low-1);
    }

    boolean check(int now){

        int cnt = 0;
        for(int snack : snacks) {

            cnt += snack / now;
            if(cnt >= target) return true;
        }

        return false;
    }
}


public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve16401 p = new solve16401(br);
        p.run();

        br.close();
    }
}