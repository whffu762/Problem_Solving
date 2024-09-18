import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 주목할 점 
 * - 최단 거리 알고리즘
 * - DFS를 호출할 횟수
 * - 시작 입력값을 조심하자
 * 
 * 최장 거리는 최단 거리라고 취급하면 편함 그냥 min만 max로 바꿈
 * 그럼 최단 거리라고 하고 이 문제에 적합한 최단 거리 알고리즘이 뭘까
 * 트리기 때문에 DFS를 사용하면 됨
 * 원래는 최단 거리에서 가장 멀리 떨어져있는게 DFS임 완전 탐색이기 때문에 효율이
 * 안나오기 때문인데 Tree인 경우엔 경로가 단 하나로 구성되서 DFS가 오히려 더 효과적임
 * 
 * DFS 호출 횟수
 * 모든 경우를 탐색하는건 10000개기 때문에 시간 초과가 발생함 그래서 리프 노드에서
 * 출발하는 것만 취급했음 -> 실제로 이렇게 하면 통과함
 * 근데 시간이 좀 오래걸림 1700ms 정도 아슬아슬하게 끝남
 * 근데 리프 노드가 몇개건 딱 두번만 호출하고 해결할 수 있음 
 * 사실 루트부터 가장 먼 거리에 존재하는 노드를 구하고 그 노드부터 가장 먼 노드를 구하면 됨
 * 뭔가 이런걸 생각해내기가 쉽지 않음
 * 
 * 시작 입력값
 * 내가 틀렸던 이유가 노드가 1개만 존재할 때의 결과값 때문이었음
 * max를 -1로 초기값으로 설정해둬서 노드가 1인 경우 -1이 출력되는 것이었음
 * 이걸 조심하자
 */
class solve1967{

    int size;
    List<List<int []>> tree = new ArrayList<>();
    boolean [] visited = new boolean[size];

    solve1967(BufferedReader br) throws IOException{

        size = Integer.parseInt(br.readLine()) + 1;
        visited = new boolean[size];

        for(int i=0;i<size;i++){
            tree.add(new ArrayList<>());
        }

        StringTokenizer st;
        for(int i=1;i<size-1;i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            List<int []> tmp = tree.get(parent);
            tmp.add(new int [] {child, weight});

            List<int []> tmp2 = tree.get(child);
            tmp2.add(new int [] {parent, weight});
        }
    }

    int max = 0;
    int idx = 0;
    void run(){

        //모든 리프 노드 조회
        // for(int i=0;i<tree.size();i++){

        //     if(tree.get(i).size() == 1){
        //         dfs(i, 0);
        //         init();
        //     }
        // }

        //가장 먼 노드를 구하고 그 노드에서 가장 먼 노드를 구함
        dfs(1, 0);
        init();
        dfs(idx, 0);

        System.out.println(max);
    }

    void init (){

        for(int i=0;i<size;i++){
            visited[i] = false;
        }
    }

    void dfs(int now, int distance) {

        visited[now] = true;
        //max = Math.max(max, distance);
        
        if(max < distance){
            max = distance;
            idx = now;
        }
        

        List<int []> nowNode = tree.get(now);
        for(int i=0;i<nowNode.size();i++){
            
            int [] next = nowNode.get(i);
            int near = next[0];

            if(!visited[near]){
                dfs(near, distance + next[1]);
            }
        }
    }
}

public class MainD{

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1967 p = new solve1967(br);
        p.run();

        br.close();
    }
}