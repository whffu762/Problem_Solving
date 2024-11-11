import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 대표적인 DP 문제
 * 
 * cache[n]을 구하는 방법이 두 가지가 존재하고 그 중 최댓값을 구하는 문제
 * 
 * cache[n]의 경우의 수
 * - n-1 계단에서 올라온 경우
 *  n-2 계단은 밟으면 안 됨 그렇기 때문에 (3개의 연속된 게단은 밟으면 안 됨)
 *  n-3까지의 최댓값 + n-1의 값 + n의 값임
 *  cache[n-3] + stairs[n-1] + stairs[n]
 * 
 * - n-2 계단에서 올라온 경우
 *  n-2 계단에서 올라온 경우
 *  딱히 밟으면 안되는 칸은 없음 그냥 n-2까지의 최댓값만 필요함
 *  cache[n-2] + stairs[n]
 * 
 * 이 두 경우의 수 중 최댓값을 저장하면 됨
 * 
 * 
 * 주의할 점
 * cache[0]의 경우 0이 최대임
 * cache[1]의 경우 staris[0] + staris[1]이 최대임
 * cache[2]의 경우 stairs[0] + stairs[2], stairs[1] + stairs[2] 이 둘 중 하나가 최대임
 * 
 * 근데 배열의 크기가 2이하 일 수 있기 떄문에 애초부터 배열을 최댓값을 초기화해두는게 편함
 * 배열 크기 검사해도 되고 ㅇㅇ
 */
public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int [] stairs = new int [301];

        for(int i=0;i<num;i++){

            stairs[i] = Integer.parseInt(br.readLine());
        }

        int [] cache = new int [301];
        cache[0] = stairs[0];
        cache[1] = stairs[0] + stairs[1];
        cache[2] = Math.max(stairs[0] + stairs[2], stairs[1] + stairs[2]);
        
        for(int i=3;i<num;i++){

            cache[i] = Math.max(cache[i-2] + stairs[i], cache[i-3] + stairs[i-1] + stairs[i]);
        }

        System.out.println(cache[num-1]);
        
        br.close();
    }
}