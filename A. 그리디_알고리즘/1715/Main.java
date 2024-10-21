import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * 문제 이해하는게 제일 어려운 문제임 뭔 문제를 이딴 식으로 적어놓냐;;;
 * 문제에선 10 20 30 40에서 (10 + 20) + (30 + 40) = 100 이런 식으로 적어놔서 100이 정답인 것처럼
 * 해놨는데 실제 답은 30 + 60 + 100 으로 190임 문제를 이상하게 설명함 아니면 예시라도 그럴 듯 한거 두던가;
 * 더하는 과정에서 필요한 모든 비교 횟수를 구하는 것임
 * 
 * 문제만 이해하면 풀이는 간단함 더해져서 생긴 카드 묶음도 Queue에 넣고 최소만 취급하는 방식임
 * 간단한 흐름
 * 1. 뭉치에서 최소값 두 개만 뽑아서 합침
 * 2. 합친 것을 result에 더함
 * 2-2. 합친 것을 queue에 추가
 * 3. 이걸 queue가 하나만 남을 때 까지 반복
 * 
 * 여기서 남은 하나는 합쳐진 카드의 총 갯수임
 */
class solve1715{

    PriorityQueue<Long> queue = new PriorityQueue<>();

    solve1715(BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num;i++){
            queue.add(Long.parseLong(br.readLine()));
        }
    }

    void run(){

        
        long result = 0;
        while(queue.size() > 1){

            long tmp = queue.poll() + queue.poll();
            result+= tmp;
            queue.add(tmp);
        }

        System.out.println(result);
    }
}


public class Main {

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1715 p = new solve1715(br);
        p.run();

        br.close();
    }
}