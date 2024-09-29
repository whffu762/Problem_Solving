import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
 * 주의할 점
 * 1. 순환 감지해야 됨
 * 2. 순환이 감지되도 일단 연결된 노드는 모두 탐색해야 함
 * 
 * 순환의 조건
 * 1. 이전에 방문한 노드에 다시 접근
 * 2. 그 노드가 부모가 아니어야 함
 * 
 * 순환 감지되도 마저 탐색
 * 1. 순환이 감지되면 false를 반환
 * 2. status를 통해 false를 유지하면서 마저 모든 노드 접근 처리
 */
class solve4803{

    int numOfNodes;
    List<List<Integer>> graph = new ArrayList<>();
    boolean [] visited;

    solve4803(int nodes, List<List<Integer>> graph) {

        this.numOfNodes = nodes;
        this.graph = graph;
        visited = new boolean[numOfNodes+1];
    }

    int run(){

        int result = 0;
        for(int i=1;i<numOfNodes+1;i++){

            if(!visited[i] && dfs(i, -1, true)){
                
                result++;
            }
        }

        return result;
    }

    boolean dfs(int now, int parent, boolean status){

        visited[now] = true;
        List<Integer> childs = graph.get(now);

        for(int child : childs){

            if(child != parent){
                
                if(visited[child]){
                    return false;
                } else {
                    status = dfs(child, now, status);
                }
                
            }
        }
        
        return status;
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int caseNo = 1;
        
        while(true){

            String head = "Case " + caseNo++ + ": ";
            StringTokenizer st = new StringTokenizer(br.readLine());
            int nodes = Integer.parseInt(st.nextToken());
            int links = Integer.parseInt(st.nextToken());
            
            if(nodes == 0 && links == 0){
                break;
            }

            List<List<Integer>> graph = new ArrayList<>();
            for(int i=0;i<nodes+1;i++){
                graph.add(new ArrayList<>());
            }

            for(int i=0;i<links;i++){
                st = new StringTokenizer(br.readLine());
                int tmp1 = Integer.parseInt(st.nextToken());
                int tmp2 = Integer.parseInt(st.nextToken());

                graph.get(tmp1).add(tmp2);
                graph.get(tmp2).add(tmp1);
            }

            solve4803 p = new solve4803(nodes, graph);
            int result = p.run();
            
            if(result == 0){
                sb.append(head + "No trees.").append("\n");
            } else if(result == 1){
                sb.append(head + "There is one tree.").append("\n");
            } else {
                sb.append(head + "A forest of " + result + " trees.").append("\n");
            }
        }

        System.out.println(sb);

        br.close();
    }
}
