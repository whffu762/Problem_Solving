import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모리 때문에 인접 행렬은 안될듯?
 */

class solve11724{

    BufferedReader br;
    
    List<List<Integer>> graph = new ArrayList<>();
    boolean [] trace;
    int numOfNode;
    int startNode;


    solve11724(BufferedReader br){
        this.br = br;
    }

    void inputGraph() throws IOException{
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfNode = Integer.parseInt(st.nextToken()) + 1;
        int numOfLine = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());

        for(int i=0;i<numOfNode;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<numOfLine;i++){

            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            graph.get(nodeA).add(nodeB);
            graph.get(nodeB).add(nodeA);
        }

        for(int i=0;i<numOfNode;i++){
            Collections.sort(graph.get(i));
        }

    }
    
    void dfs(int node, StringBuilder sb){

        if(trace[node]){
            return;
        }

        trace[node] = true;
        sb.append(node).append(" ");

        List<Integer> childs = graph.get(node);
        for(int i=0;i<childs.size();i++){
            dfs(childs.get(i), sb);
        }

    }

    void bfs(int node, StringBuilder sb){

        Queue<Integer> forBfs = new LinkedList<>();
        trace[node] = true;
        forBfs.offer(node);
        sb.append(node).append(" ");

        while(!forBfs.isEmpty()){
            
            int now = forBfs.poll();
            List<Integer> childs = graph.get(now);
            
            for(int i=0;i<childs.size();i++){
                
                int child = childs.get(i);
                if(!trace[child]){
                    trace[child] = true;
                    forBfs.offer(child);
                    sb.append(child).append(" ");
                }
            }
        }
        
    }

    void run() throws IOException{

        inputGraph();

        trace = new boolean[numOfNode];
        StringBuilder sb = new StringBuilder();
        dfs(startNode, sb);

        trace = new boolean[numOfNode];
        sb.append("\n");
        bfs(startNode, sb);

        System.out.print(sb);

        
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11724 p = new solve11724(br);
        p.run();

        br.close();
    }
}