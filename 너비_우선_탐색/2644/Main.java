package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
공통
인접리스트로 그래프 구성 
각 노드 간 연결 여부가 중요하기 때문에 인접리스트로 구성 어떤 값이 부모고 자식인지는 중요하지 않음 
때문에 모든 연결을 전부 저장함 예를 들어 1 - 2가 연결되면 relation 에 1 2와 2 1을 저장
이렇게 하는 이유는 그래프의 모든 노드에 쉽게 접근하기 위함임 입력받은 대로 부모 자식 관계를 저장하면
target 으로 입력된 값을 root로 그래프를 재구성하는 것이 불가능함 이동에 제한이 생기기 때문에 방향이
없는 그래프로 그리는게 BFS에 유리함

입력과 연결된 값 중 순회하지 않은 값 순회
대시 모두가 서로 연결되어있고 중복 연결이 많기 때문에 한 번 순회하면 node[] true로 변경해서 이미 
순회했음을 표시하고 이미 순회한 노드는 다시 접근하지 않음 또한 그래프 흐름의 역행을 방지하는 역할도 함 

DFS 로 풀기
인접리스트에 저장된 모든 요소에 접근을 시도하고 찾으면 종료
인자로 두 target 과 cnt 가 전달되도 한 번 dfs를 호출할 때 마다 cnt 증가

BFS 로 풀기
값을 찾든 말든 저장된 모든 요소에 접근 갈 수 있는 모든 요소에 먼저 접근해서 distance[] 를 채움
Queue를 생성해서 target 입력을 저장
1. 현재 큐에 저장된 0번째 값을 now 변수에 저장
2. now가 목표값인지 확인 맞으면 종료 아니면 진행
3. now 에 저장된 값과 연결된 노드에 차례로 접근
4. nodes[] 가 false면(첫 접근이면) 큐의 마지막에 저장하면서 distance[] 를 채움
5. 1번으로 이동

*/
class solve2644DFSBFS {

    List<List<Integer>> relation;
    boolean [] nodes;
    int result = -1;
    int target1;
    int target2;

    void input(BufferedReader br) throws IOException {
        int num_person = Integer.parseInt(br.readLine());
        nodes = new boolean [num_person + 1];
        relation = new ArrayList<>();
        for(int i=0;i<num_person+1;i++){
            relation.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        target1 = Integer.parseInt(st.nextToken());
        target2 = Integer.parseInt(st.nextToken());

        int num_relation = Integer.parseInt(br.readLine());
        for (int i = 0; i < num_relation; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            
            relation.get(parent).add(child);
            relation.get(child).add(parent);
        }

        distance = new int [num_person + 1];    //bfs에서 필요함
    }

    void dfs(int start, int end, int cnt){
        if(start == end){
            result = cnt;
            return;
        }

        nodes[start] = true;
        for(int i=0;i<relation.get(start).size();i++){
            int next = relation.get(start).get(i);
            if(!nodes[next]){
                dfs(next, end, cnt+1);
            }
        }
    }

    int [] distance;
    void bfs(int start, int end){
        Queue<Integer> q = new LinkedList<>();
        nodes[start] = true;
        q.add(start);
        distance[start] = 0;

        while(!q.isEmpty()){
            Integer now = q.poll();

            if(now == end){
                result = distance[end];
                return;
            }

            for(int next : relation.get(now)){
                if(!nodes[next]){
                    distance[next] = distance[now] + 1;
                    nodes[next] = true;
                    q.add(next);
                }
            }
        }

    }

    void run(BufferedReader br) throws IOException {
        input(br);

        //dfs(target1, target2, 0);
        bfs(target1, target2);
        System.out.println(result);

    }
}


class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2644DFSBFS p = new solve2644DFSBFS();
        p.run(br);

        br.close();
    }
}