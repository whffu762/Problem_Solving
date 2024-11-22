import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/*
 * 1차 배열이지만 BFS를 이용함
 * x - 1, x + 1, x * 2인 노드와 가중치가 1로 연결되어 있는 그래프에서  최단거리 찾기
 * 
 * 주의할 점
 * - 가중치가 다 1이기 때문에 먼저 접근된 경우가 항상 최단 거리임 그래서 기존 값과 현재 값을 비교할 필요가 없음
 * - 술래가 대상보다 작으면 x - 1 동작만 유효하므로 그냥 빼기만 하면 됨
 * - 거리를 유지할 배열은 대상의 * 2만큼 유지되야 함 그 이유는 술래보다 앞서나갔다가 되돌아오는 길이 더 짧을 수 있기 때문임
 *   대표적인 예가 8 15 
 */
class solve1697{

    int start;
    int find;
    int end;
    boolean [] visited;
    int [] distance;

    solve1697(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        find = Integer.parseInt(st.nextToken());

        end = find * 2 + 1;
        distance = new int [end];
        visited = new boolean[end];
    }

    void run(){

        if(start > find) distance[find] = start - find;
        else bfs();
    
        System.out.println(distance[find]);
    }

    void bfs() {

        int [] move = { -1, 1, 2 };
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){

            int now = queue.poll();
            for(int i=0;i<move.length;i++){

                int next = move[i] == 2 ? now * move[i] : now + move[i];
                if(checkValid(next)){

                    queue.add(next);
                    distance[next] = distance[now] + 1;
                    visited[next] = true;
                }
            }
        }
    }

    boolean checkValid(int next){

        if(next > -1 && next < end) return !visited[next];
        return false;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1697 p = new solve1697(br);
        p.run();

        br.close();
    }
}