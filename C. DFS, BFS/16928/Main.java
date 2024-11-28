import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS 이용하는데 방문 처리에 주의
 * 
 * 현재 위치가 만약 사다리 혹은 뱀이라면 이동되는 위치까지 방문 여부를 확인해야 함
 * 그렇지 않으면 이미 다녀간 칸이 다시  Queue에 들어가면서 최솟값을 보장할 수 없게 됨
 */
class solve16928{

    int numOfLadder;
    int numOfSnake;

    Map<Integer, Integer> ladderSnake = new HashMap<>(); 

    int [] distance = new int [101];
    boolean [] visited = new boolean[101];

    solve16928 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfLadder = Integer.parseInt(st.nextToken());
        numOfSnake = Integer.parseInt(st.nextToken());

        
        for(int i=0;i<numOfLadder;i++){

            st = new StringTokenizer(br.readLine());
            ladderSnake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        for(int i=0;i<numOfSnake;i++){

            st = new StringTokenizer(br.readLine());
            ladderSnake.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    void run(){

        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()){

            int now = queue.poll();

            for(int i=1;i<7;i++){
                
                int next = now + i;
                if(next > 100){
                    break;
                }

                if(!visited[next]){
                    
                    visited[next] = true;
                     
                    if(ladderSnake.get(next) != null){

                        next = ladderSnake.get(next);
                        if(!visited[next]){

                            visited[next] = true;
                        }
                    }
                    
                    distance[next] = distance[now] + 1;
                    queue.add(next);
                }
            }
        }

        System.out.println(distance[100]);
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve16928 p = new solve16928(br);
        p.run();

        br.close();
    }
}