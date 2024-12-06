import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS로 완전 탐색하면 되는데 중요한건 다른 것임
 * - 데이터를 저장하는 방식
 * - 인접 노드로의 진행 방식
 * 
 * 데이터를 저장하는 방식
 * 트리 형태가 아님 안쪽에 있는 노드들은 바로 위 depth 두 노드에서 공유하는 형태기 때문에 일차 배열에 저장할 수 없음
 * 그렇다고 2차 배열 다 쓰기엔 메모리가 반은 낭비됨 그래서 가변 배열로 다룸
 * 
 * 
 * 인접 노드로의 진행 방식
 * 위와 같은 이유로 단순히 트리 순회하듯이 할 수 없음 현재 노드에서 현재 노드의 인덱스와 인덱스 + 1 두 노드가 인접 노드임
 * 이를 이용해서 현재 idx를 기준으로 idx+2 까지 진행시키면 됨
 * 
 * 그 외 주의할 점
 * 공유되는 노드가 존재하기 때문에 접근하는 모든 노드를 Queue에 삽입하면 안 됨 그렇게하면 메모리 초과가 발생함 
 * cache 값이 변경될 때만 Queue에 넣어야 함
 * 
 */
class solve1932{

    int num;
    int [][] tri;
    int [][] cache;

    solve1932 (BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        
        tri = new int [num][];
        cache = new int [num][];
        for(int i=0;i<num;i++) {
            
            tri[i] = new int [i+1];
            cache[i] = new int [i+1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<i+1;j++){
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void run(){

        Queue<int []> queue = new ArrayDeque<>();
        cache[0][0] = tri[0][0];
        queue.add(new int [] { 0, 0 });

        int result = 0;
        while(!queue.isEmpty()){

            int [] now = queue.poll();
            int depth = now[0];
            int idx = now[1];

            if(depth == num - 1){

                result = Math.max(result, cache[depth][idx]);
                continue;
            }

            for(int i=idx;i<idx+2;i++){

                int [] next = { depth + 1, i};
                int tmp = cache[now[0]][now[1]] + tri[next[0]][next[1]];
                if(cache[next[0]][next[1]] < tmp){

                    cache[next[0]][next[1]] = tmp;
                    queue.add(next);
                }
            }
        }

        System.out.println(result);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1932 p = new solve1932(br);
        p.run();

        br.close();
    }
}