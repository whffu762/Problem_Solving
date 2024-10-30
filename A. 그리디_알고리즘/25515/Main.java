import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * Tree DP 문제
 * 서브 트리의 값이 전체 트리의 값에 일부임에 착안해 DP를 이용해서 Tree의 답을 구함
 * 
 * 서브 트리의 최대합 구하기
 * 이미 방문한 노드를 다시 지나갈 수 있기 때문에(방문했다 하더라도 최초 방문이 아니면 합에 추가되지 않음)
 * 노드 i를 루트로 하는 서브 트리의 합은 다음과 같은 경우의 수가 존재함
 * - cache[i] : 어떤 자식도 더해지지 않은 경우
 * - cache[i] + cache[i의 자식] : 자식이 더해지는 경우
 * 근데 문제는 자식이 N명일 경우 nC1 ~ nCn 까지의 합만큼의 경우의 수가 생김 이를 간소화 하려면 어떻게 해야할까?
 * 그냥 0번째 자식부터 차례로 넣어보면서 더 큰 값을 cache[i]에 저장하면 됨 그래서 N 번만 비교하면 해결됨
 * 
 * 핵심은 leaf부터 연산이 시작된다는 것임
 * 이를 위해선 leaf부터 연산이 된다는 것을 인지해야 함 이걸 모르면 구현할 때 헷갈림 서브 트리의 정답부터 구하기 때문에
 * 서브 트리를 계속 타고 들어가면 결국 leaf 노드가 루트 뿐인 서브 트리를 구성하게 됨 이걸 인진하고 들어가야 dfs로 로직 짜기 편함
 * 
 * root부터 접근하기 시작해서 연산은 leaf부터 이루어짐 
 * 뭔가 말이 이상해서 헷갈림;; leaf부터 연산한다고 leaf부터 접근할 수가 없음 일단 leaf가 하나도 아닐뿐더러 뭐가 leaf인지 
 * 저장된 트리 자료구조에선 알 수가 없음 뭐 어떻게 leaf를 했다치더라도 그거 부터 접근하게 되면 결국 그 leaf가 root로 하는
 * 새로운 트리로 구성되게 됨 그래서 root부터 접근해서 dfs로 leaf부터 연산해야 함 
 * 
 * 그렇게 해서 결과는 cache[root]
 * leaf 부터 해서 마지막에 결과는 root에 해당하는 값임
 * 
 */
class solve25515{

    List<List<Integer>> tree = new ArrayList<>();
    int [] value;
    long [] cache;
    
    solve25515(BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num;i++){
            tree.add(new ArrayList<>());
        }

        for(int i=1;i<num;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        value = new int [num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }

        cache = new long [num];
    }

    void run(){

        getTreeDP(0, -1);
        System.out.println(cache[0]);
    }

    void getTreeDP(int now, int parent){

        cache[now] = value[now];

        for(int child : tree.get(now)){

            if(child == parent) continue;
            getTreeDP(child, now);
            cache[now] = Math.max(cache[now], cache[now] + cache[child]);
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve25515 p = new solve25515(br);
        p.run();
        
        br.close();
    }
}