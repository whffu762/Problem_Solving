import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.Arrays;

/*
 * 양의 가중치 + 한 노드에서 모든 노드로의 경로 = 다익스트라
 * 
 * 다만 가능한 최대값을 잘 생각해봐야 함
 * 단순히 최대 가중치가 10이라고 경로가 10을 넘지 않을거라 생각하면 안 됨
 * shortPath[] 최댓값을 잘 생각해야 함
 */
class solve1753{

    List<List<int []>> graph = new ArrayList<>();
    int numOfNode;
    int start;

    solve1753(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());

        numOfNode = Integer.parseInt(st.nextToken());
        int numOfEdge = Integer.parseInt(st.nextToken());

        for(int i=0;i<numOfNode+1;i++){
            graph.add(new ArrayList<>());
        }

        start = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfEdge;i++){
            st = new StringTokenizer(br.readLine());

            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(from).add(new int [] {to, weight});
        }
    }

    void run(){

        int [] shortPath = new int[numOfNode+1];
        Arrays.fill(shortPath, Integer.MAX_VALUE);
        shortPath[start] = 0;

        boolean [] visited = new boolean[numOfNode+1];

        PriorityQueue<int []> queue = new PriorityQueue<>((after, before) ->{
            return after[1] - before[1];
        });
        queue.add(new int [] {start, 0});

        while(!queue.isEmpty()){

            int [] now = queue.poll();
            int nowNode = now[0];
            int nowWeight = now[1];

            if(visited[nowNode]){
                continue;
            } else {
                visited[nowNode] = true;
            }

            List<int []> connectedNodes = graph.get(nowNode);
            for(int i=0;i<connectedNodes.size();i++){

                int [] next = connectedNodes.get(i);
                int nextNode = next[0];
                int nextWeight = next[1];

                int newPath = nowWeight + nextWeight;
                if(newPath < shortPath[nextNode]){

                    shortPath[nextNode] = newPath;
                    queue.offer(new int [] {nextNode, newPath});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<shortPath.length;i++){

            int path = shortPath[i];
            if(path == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            } else {
                sb.append(shortPath[i]).append("\n");
            }
        }

        System.out.print(sb);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1753 p = new solve1753(br);
        p.run();

        br.close();
    }
}