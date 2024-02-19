import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * DFS + 인접 리스트 방식
 * 
 * 이게 정답
 * 빈 리스트를 통해 접근 여부를 판단하는 V1과 달리 boolean 배열을 통해 접근 여부를 관리
 * 
 * 대략적인 방식
 * 1. 무방향 그래프로 저장
 * 2. 인접리스트에 DFS로 접근
 *      - 각 노드에 순차적으로 접근
 *      - 접근한 노드는 trace를 true로 변환
 *      - trace가 true면 더 진행하지 않고 다음 노드로 이동
 *      - 한 번도 접근한 적 없는 노드에 접근했을 때만 result++
 */
class solve11724{

    BufferedReader br;

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    boolean [] trace;   //이게 핵심 접근 여부를 파악하기 위한 수단

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

        if(trace[node]){
            return; //한 번 접근했으면 중단
        }

        trace[node] = true; //한 번 접근된 노드는 true로 변환

        ArrayList<Integer> childs = graph.get(node);    //인접리스트에 저장된 모든 노드에 접근
        for(int i=0;i<childs.size();i++){
            checkChilds(childs.get(i));
        }

    }

    void run() throws IOException{
        
        inputGraph(); 

        int result = 0;
        for(int i=1;i<trace.length;i++){
            
            if(!trace[i]){  //한 번도 접근한 적 없으면 갯수에 추가
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