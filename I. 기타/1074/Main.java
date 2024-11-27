import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 현재 위치를 대푯값으로 변환해서 처리 대푯값은 Z의 시작 부분으로 취급
 * 예시로 봐야 이해가 편함
 * 
 * 예를 들어 길이가 8인 사각형의 (6, 2)를 변환한다면
 * 길이가 2인 사각형의 경우 (6, 2) (6, 3) (7, 2) (7, 3) 이 네 개의 좌표로 Z를 이룸
 * 거기서 (6, 2)는 Z의 0번째 요소임
 * 기준점 (6, 2)로부터 거리는 0 = 2^(2*0) * 0
 * 
 * 길이가 4인 사각형의 경우 (4, 0) (4, 2) (6, 0) (6, 2) 이 네 개의 좌표로 Z를 이룸
 * 거기서 (6, 2)는 Z의 3번째 요소임
 * 기준점 (4, 0)으로부터의 거리는 12 = 2^(2*1) * 3
 * 
 * 길이가 8인 사각형의 경우 (0, 0) (0, 4) (4, 0) (4, 4) 이 네 개의 좌표로 Z를 이룸
 * 거기서 (6, 2)가 포함된 (4, 0)은 2번째 요소임
 * 기준점 (0, 0)으로부터 (4, 0)까지의 거리는 32 = 2^(2*2) * 2
 * 
 * (0, 0)으로부터 44번째에 존재함을 알 수 있음
 * 
 * 간단한 흐름
 * 1. 대푯값으로부터 현재 좌표의 거리 구하기
 * 2. 현재 좌표의 대푯값 구하기
 * 3. 대푯값이 (0, 0)일 때까지 반복 -> 이 반복 횟수가 N임(사각형의 길이)
 * 
 * 대푯값으로부터 거리 구하기
 * x짝 y짝 -> 0
 * x짝 y홀 -> 1
 * x홀 y짝 -> 2
 * x홀 y홀 -> 3
 * 
 * 대푯값 구하기
 * x, y가 홀이면 -> --
 * x, y가 짝이면 그대로
 * 그 후 x ,y 모두 / 2
 */

class solve1074{

    int N;
    int x;
    int y;

    solve1074(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
    }

    void run(){

        int result = 0;
        int size = 0;
        while(N-- > 0){

            int seqOfNow = check(x, y);
            x /= 2;
            y /= 2;

            result += seqOfNow * (int) Math.pow(2, size++ * 2);
        }

        System.out.println(result);
    }

    int check(int x, int y){

        if(x % 2 == 0 ){

            if(y % 2 == 0) return 0;
            y--;
            return 1;
        }
        else {

            x--;
            if(y % 2 == 0) return 2;
            y--;
            return 3;
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1074 p = new solve1074(br);
        p.run();

        br.close();
    }
}