import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 위치가 작은 구멍부터 시작해서 테이프를 붙이고 테이프가 붙은 위치를 유지하면 됨
 * 
 * 테이프 위치 계산
 * 구멍-0.5 ~ 구멍 + 테이프 + 0.5 까지니까 소숫점 귀찮게 계산하지 말고 그냥 
 * 구멍 + 테이프 -1까지 덮이는 것으로 취급
 * 
 * 필요한 테이프 갯수 계산
 * 구멍 > 테이프 위치일 때만 연산
 * - 테이프 위치 계산
 * - 테이프 갯수 ++
 * 
 */
class solve1449{

    int [] needFix;
    int tape;

    solve1449(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfFix = Integer.parseInt(st.nextToken());
        tape = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        needFix = new int [numOfFix];
        for(int i=0;i<numOfFix;i++){
            needFix[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(needFix);
    }

    void run(){
        
        int result = 0;
        int now = 0;
        for(int i=0;i<needFix.length;i++){

            int fix = needFix[i];
            if(now < fix){

                now = fix + tape - 1;
                result++;
            }
        }

        System.out.println(result);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1449 p = new solve1449(br);
        p.run();

        br.close();
    }
}