import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * LCS 기본 문제
 * 
 * DP를 이용해 이전 비교까지의 가장 긴 공통 수열의 길이를 저장하는 방식
 * DP를 전부 채운 후엔 마지막부터 탐색해서 공통 수열을 구함
 * 
 * DP 채우기
 * 두 문자열을 비교해서 현재 자리가
 * - 같으면 : 이전 비교 까지의 거리 + 1
 * - 다르면 : 현재 비교 중 최댓값
 * 
 * DP 탐색
 * 마지막부터 그래프 탐색하면 됨 (BFS or DFS)
 * 여기선 DFS가 BFS보다 빠른거 같은데.. 보통은 BFS가 더 빠를듯..?
 * 
 * 탐색 조건
 * - 진행 방향을 왼쪽 위(왼쪽과 위만 탐색하면 됨)
 * - 현재 노드와 값이 같은 노드로 이동
 * - 만약 없으면 대각선 위로 이동
 * 
 */
class solve17218{

    String first;
    String second;
    
    int [][] cache;
    boolean [][] visited;
    StringBuilder sb = new StringBuilder();

    solve17218 (BufferedReader br) throws IOException{

        first = br.readLine().trim();
        second = br.readLine().trim();

        cache = new int [first.length()+1][second.length()+1];
        visited = new boolean[first.length()+1][second.length()+1];
    }

    void run(){

        makeDP();
        bfs();
        System.out.println(sb.reverse());
    }

    void makeDP(){

        for(int i=1;i<first.length()+1;i++){

            for(int j=1;j<second.length()+1;j++){

                if(first.charAt(i-1) == second.charAt(j-1)){
                    cache[i][j] = cache[i-1][j-1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i-1][j], cache[i][j-1]);
                }
            }
        }
    }

    void bfs(){

        
        int [] moveRow = { -1, 0 };
        int [] moveCol = { 0, -1 };
    
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int [] { cache.length - 1, cache[0].length - 1});
        visited[cache.length-1][cache[0].length-1] = true;

        while(!queue.isEmpty()){

            int [] now = queue.poll();
            if(cache[now[0]][now[1]] == 0){
                break;
            }

            boolean flag = true;
            for(int i=0;i<moveCol.length;i++){

                int [] next = { now[0] + moveRow[i], now[1] + moveCol[i]};
                if(cache[next[0]][next[1]] == cache[now[0]][now[1]]){

                    flag = false;
                    if(!visited[next[0]][next[1]]){

                        visited[next[0]][next[1]] = true;
                        queue.add(next);
                    }
                }
            }

            if(flag){

                sb.append(first.charAt(now[0]-1));
                queue.add(new int [] {now[0]-1, now[1]-1});
            }
        }
    }
}

public class MainB {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve17218 p = new solve17218(br);
        p.run();

        br.close();
    }
}