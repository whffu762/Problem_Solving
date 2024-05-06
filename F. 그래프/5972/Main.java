import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * 다익스트라로 풀어야 함
 * 가중치가 있기 때문에 단순한 BFS로 푸는건 너무 어려움 이에 대해선 노션 참고
 */
class solve5972{

    int destination;
    Map<Integer, List<int []>> farm = new HashMap<>();

    solve5972(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        destination = Integer.parseInt(st.nextToken());
        int numOfRoad = Integer.parseInt(st.nextToken());

        for(int i=0;i<numOfRoad;i++){
            st = new StringTokenizer(br.readLine());
            
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cow = Integer.parseInt(st.nextToken());

            farm.computeIfAbsent(start, key -> new ArrayList<>()).add(new int [] {end, cow});
            farm.computeIfAbsent(end, key -> new ArrayList<>()).add(new int [] {start, cow});
        }
    }

    void run(){

        int [] oldRoute = new int [destination + 1];
        Arrays.fill(oldRoute, Integer.MAX_VALUE);
        
        boolean [] visited = new boolean[destination + 1];

        PriorityQueue<int []> queue = new PriorityQueue<>((after, before) -> {
            return after[1] - before[1];
        });
        queue.offer(new int [] {1 , 0});

        while(!queue.isEmpty()){

            int [] now = queue.poll();
            int nowFarm = now[0];

            if(visited[nowFarm]){
                continue;
            } else {
                visited[nowFarm] = true;
            }

            List<int []> connectedFarm = farm.get(nowFarm);
            for(int i=0;i<connectedFarm.size();i++){

                int [] next = connectedFarm.get(i);
                int nextFarm = next[0];
                int nextCow = next[1];

                int tmp = oldRoute[nowFarm] + nextCow;
                if(tmp < oldRoute[nextFarm]){
                    oldRoute[nextFarm] = tmp;
                    queue.offer(new int [] {nextFarm, tmp});
                }
            }
        }

        System.out.println(oldRoute[destination]);
    }
}

public class Main{
    public static void main(String []args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve5972 p = new solve5972(br);
        p.run();

        br.close();
    }
}