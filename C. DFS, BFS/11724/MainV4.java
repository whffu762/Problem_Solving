import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS + 인접 리스트 방식
 */
class solve11724{

    BufferedReader br;

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    boolean [] trace;   

    solve11724 (BufferedReader br){
        this.br = br;
    }

    void inputGraph() throws IOException{
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNode = Integer.parseInt(st.nextToken());
        int numOfLine = Integer.parseInt(st.nextToken());

        trace = new boolean[numOfNode+1];

        for(int i=0;i<numOfNode+1;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<numOfLine;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph.get(end).add(start);
        }
    }

    void checkChilds(int node){

        Queue<Integer> forBfs = new LinkedList<>();
        forBfs.offer(node);
        trace[node] = true;

        while(!forBfs.isEmpty()){
            int now = forBfs.poll();

            ArrayList<Integer> childs = graph.get(now);
            for(int i=0;i<childs.size();i++){
                
                int child = childs.get(i);
                if(!trace[child]){
                    forBfs.offer(child);
                    trace[child] = true;
                }
            }
        }
    }

    void run() throws IOException{
        
        inputGraph(); 

        int result = 0;
        for(int i=1;i<trace.length;i++){
            if(!trace[i]){
                result++;
                checkChilds(i);
            }
        }
        
        System.out.println(result);

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