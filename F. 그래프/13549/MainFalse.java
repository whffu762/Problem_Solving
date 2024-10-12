import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 이렇게 풀면 안 됨
 * 단순 BFS로 풀면 첫 접근이 최단 거리임을 보장할 수 없기 때문에 틀림
 * 최단 거리임을 보장할 수 없는 이유는 가중치가 0인 정점이 존재하기 때문임
 * 아니 그래서 0인 것을 앞으로 보냈자나 라고 해도 먼저 접근한 값이 최소값임을
 * 보장할 수 있는 근거가 없음 그래서 다익스트라처럼 이전에 접근한 정점이라도 
 * 값이 갱신되면 다시 Queue에 넣는 방식으로 구현해야 함
 * 그게 0-1 BFS 다익스트라와 비슷한데 속도가 더 빠르다고 함 자세한건 노션
 * 
 * 다른 풀이에선 BFS 접근 순서에 따라 다르게 해야 한다 뭐 *2 -1 +1 이걸로 해야한다
 * 이런식으로 푸는데 사실 그건 이 문제의 성질에 따라 적용되는 특수한 성질임 알고리즘이라 할 수 없음
 */


class solve13549 {

    int start;
    int end;
    Deque<Integer> queue = new ArrayDeque<>();
    int [] result = new int [100001];
    int [] move = { -1, 1 };

    solve13549 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    void run() {

        Arrays.fill(result, -1);
        bfs();
        System.out.println(result[end]);
    }

    void bfs(){

        queue.add(start);
        result[start] = 0;

        while(!queue.isEmpty()){

            int now = queue.poll();
            int next;
            
            for(int i : move){

                next = now + i;
                if(checkValid(next)){
                    result[next] = result[now] + 1;
                    queue.addLast(next);
                    
                }
            }

            next = now * 2;
            if(checkValid(next)){
                result[next] = result[now];
                queue.addFirst(next);
            }
        }
    }

    boolean checkValid(int next){

        //범위 확인 및 최초 접근인지 확인
        if(next > -1 && next < result.length){
            return result[next] == -1;
        }

        return false;
    }
}

public class MainFalse {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve13549 p = new solve13549(br);
        p.run();

        br.close();
    }
}