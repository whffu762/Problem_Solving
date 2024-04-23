import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
 * DFS + 인접 행렬 방식
 * 
 * V2와 동일한 방식으로 그래프 저장만 인접 행렬에 함
 * 속도는 이게 더 빠름 근데 노드와 간선이 많아지면 쓸 수 없음
 */

class solve11724{

    BufferedReader br;

    boolean[][] graph;
    boolean [] trace;
    int result = 0;

    solve11724 (BufferedReader br){
        this.br = br;
    }

    void inputGraph() throws IOException{
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNode = Integer.parseInt(st.nextToken());
        int numOfLine = Integer.parseInt(st.nextToken());

        graph = new boolean[numOfNode+1][numOfNode+1];
        trace = new boolean[numOfNode+1];

        for(int i=0;i<numOfLine;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start][end] = true;
            graph[end][start] = true;
        }
    }

    void checkChilds(int node){

        if(trace[node]){
            return;
        } else {
            trace[node] = true;
            for(int i=0;i<trace.length;i++){
                if(graph[node][i]){
                    checkChilds(i);
                }
            }
        }
    }

    void run() throws IOException{
        
        inputGraph(); 

        for(int i=1;i<trace.length;i++){
            if(!trace[i]){
                result++;
            }
            checkChilds(i);
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