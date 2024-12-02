import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 핵심은 두 가지임
 * 1. L과 R 연산 구현
 * 2. BFS의 경로 추적
 * 
 * L과 R을 문자열로 다루지 않고 나머지 연산만으로 구현할 수 있음
 * 
 * BFS의 경로 추적
 * visited와 함께 tracking 배열까지 같이 유지하는 방식
 * trackding[next] = now를 통해 현재 노드 바로 이전 노드를 저장하는 방식
 * 
 * 이후엔 재귀 호출 혹은 stack을 이용해 시작 노드까지 가면 됨
 */
class solve9019{

    int start;
    int end;
    boolean [] visited = new boolean[10000];
    int [] tracking = new int [10000];
    String [] commands = new String [10000];
    StringBuilder sb = new StringBuilder();

    solve9019 (int start, int end){

        this.start = start;
        this.end = end;
    }

    String run(){
        
        bfs();
        tracking(end);
        return sb.reverse().toString();
    }

    void bfs(){

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        
        while(!queue.isEmpty()){

            int now = queue.poll();

            for(int i=0;i<4;i++){

                int next;
                String command;
                if(i == 0) {
                    next = (now * 2) % 10000;;
                    command = "D";
                }
                else if(i == 1) {
                    next = now == 0 ? 9999 : now -1;
                    command = "S";
                }
                else if (i == 2) {
                    next = (now % 1000) * 10 + now / 1000;
                    command = "L";
                }
                else {
                    next = (now % 10) * 1000 + now / 10;
                    command = "R";
                }
            
                if(!visited[next]){

                    visited[next] = true;
                    tracking[next] = now;
                    commands[next] = command;
                    queue.add(next);
                }
            }
        }
    }

    void tracking(int now){

        if(now == start) return;
        sb.append(commands[now]);
        tracking(tracking[now]);
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            solve9019 p = new solve9019(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            sb.append(p.run()).append("\n");
        }
        System.out.println(sb);
        
        br.close();
    }
}