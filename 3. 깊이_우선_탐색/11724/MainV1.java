import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * DFS긴 한데 많이 느림 
 * 대략적인 방식
 *  1. 그래프를 인접 리스트로 저장
 *  2. 인접 리스트를 1부터 시작해서 순회 
 *  3. 인접 리스트에 저장된 노드를 깊이 우선 탐색
 *      - 접근한 노드는 리스트에서 삭제
 *      - 때문에 요소가 남아있는 리스트는 이전 노드와 연결되어 있지 않은 노드임
 *      - 어떤 노드와도 연결되어 있지 않은 단일 노드를 식별하기 위해 trace를 이용
 *      - 한 번이라도 접근된 노드는 trace에 저장됨
 *  4. 모든 탐색이 끝난 후 한 번도 접근되지 않은 노드까지 결과에 추가 
 *  
 */

class solve11724{

    BufferedReader br;

    ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    Set<Integer> trace = new HashSet<>();
    int numOfNode;
    int numOfLine;

    solve11724 (BufferedReader br){
        this.br = br;
    }

    //입력된 그래프를 무방향 그래프로 저장
    void inputGraph() throws IOException{
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfNode = Integer.parseInt(st.nextToken());
        numOfLine = Integer.parseInt(st.nextToken());

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

        ArrayList<Integer> childs = graph.get(node);

        while(!childs.isEmpty()){                 //연결되어 있는 모든 노드에 접근할 때까지 지속
            int child = childs.remove(0);   //한 번 접근한 노드는 삭제
            trace.add(child);                     //한 번 접근한 노드는 trace에 추가 set이기 때문에 중복이 없음
            checkChilds(child);                   //깊이 우선 탐색
        }

    }

    void run() throws IOException{
        
        inputGraph(); 

        int result = 0;
        for(int i=1;i<numOfNode+1;i++){ //모든 노드를 순회
            
            if(!graph.get(i).isEmpty()){    //노드가 남아있는 리스트는 이전 노드와 연결되있지 않은 노드임
                result++;
            }
            checkChilds(i);
        }

        result += numOfNode - trace.size(); //마지막에 한 번도 접근되지 않은 단일 노드를 계산
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