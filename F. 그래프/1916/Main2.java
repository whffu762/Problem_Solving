import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 기존 방식의 다익스트라 구현
 * 
 * 1. PriorityQueue를 이용
 * -> 현재 Node 뿐만 아니라 해당 노드로의 최단 경로까지 같이 저장
 * -> 주의할 점은 해당 경로로 향하는 가중치를 저장하는게 아님
 * 
 * 2. visited 유지
 * -> 무작위로 Queue에 있는 Node에 접근하기 때문에(실제로는 가중치 순이지만..)
 * 만약 visited를 유지하지 않으면 queue에 매우 많은 Node가 저장될 수 있음
 * 
 * 3. queue에 저장된 가중치를 이용해서 newWeight 구함
 * 
 * 자세한건 노션 참고
 */
class solve1916{

    int numOfCity, numOfBus;
    List<List<int []>> graph = new ArrayList<>();
    int [] result;
    PriorityQueue<long []> queue = new PriorityQueue<>((after, before)-> {
        return after[1] - before[1];
    });
    int start, end;
    boolean [] visited; 

    solve1916 (BufferedReader br) throws IOException{

        numOfCity = Integer.parseInt(br.readLine());
        numOfBus = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfCity+1;i++){
            graph.add(new ArrayList<>());
        }

        result = new int [numOfCity+1];
        visited = new boolean[numOfCity+1];
        Arrays.fill(result, Integer.MAX_VALUE);

        for(int i=0;i<numOfBus;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start).add(new int [] { end, weight });
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    void run(){

        queue.add(new int [] {start, 0});
        result[start] = 0;

        while(!queue.isEmpty()){

            int [] now = queue.poll();
            int nowNode = now[0];
            int nowWeight = now[1];

            if(visited[nowNode]){
                continue;
            } else {
                visited[nowNode] = true;
            }

            for(int [] next : graph.get(nowNode)){

                int nextNode = next[0];
                int nextWeight = next[1];

                int weight = nowWeight + nextWeight;
                if(result[nextNode] > weight){
                    queue.add(new int [] {nextNode, weight});
                    result[nextNode] = weight;
                }
            }
        }
        
        System.out.println(result[end]);

    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1916 p = new solve1916(br);
        p.run();

        br.close();
    }
}