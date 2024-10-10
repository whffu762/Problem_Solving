import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 다익스트라 이용해서 해결
 * BFS와 유사한데 Queue에 들어갈 조건만 좀 다름
 * 1. 최초 입력
 * 2. 새로운 경로 < 이전 경로
 * 
 * 참고로 최초 입력임을 유지하기 위해 -1로 초기화해둬야 함
 * 그냥 0으로 하면 이게 최초 입력인지 아니면 최단 거리가 0인건지 모름
 * 
 * 참고로 다른 알고리즘에서 BFS 만으로 풀 수 있다고 하는데 
 * 0-1 BFS 아니면 다 운 좋게 맞은 거임 단순 BFS 만으로 풀 수 있는 문제가 아님 
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
                if(checkValid(now, next)){
                    if(result[next] == -1 || result[next] > result[now] + 1){
                        result[next] = result[now] + 1;
                        queue.add(next);
                    }
                }
            }

            next = now * 2;
            if(checkValid(now, next)){
                if(result[next] == -1 || result[next] > result[now]){
                    result[next] = result[now];
                    queue.add(next);
                }
            }
        }
    }

    boolean checkValid(int now, int next){

        //범위 확인
        if(next > -1 && next < result.length){
            return true;
        }

        return false;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve13549 p = new solve13549(br);
        p.run();

        br.close();
    }
}