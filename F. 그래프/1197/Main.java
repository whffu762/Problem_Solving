import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 크루스칼 알고리즘 이용
 * 최소 스패닝 트리(MST) 를 만들기 위한 알고리즘
 * 
 * 최소 스패팅 트리
 * 그래프의 모든 정점을 연결짓는 트리 중 그 가중치가 최소인 것
 * 
 * 크루스칼 알고리즘
 * 그리디 기반으로 가중치가 최소인 간선을 먼저 접근하는 방식
 * 가중치가 최소인 것을 찾는 알고리즘이기 때문에 그래프를 다룰 때
 * 가중치를 중심으로 다룸
 * 
 * 로직 흐름
 * 1. 노드 별 가중치를 오름차순으로 정렬
 * 2. 가중치가 최소인 간선에 접근
 * 3. 순환이 존재하는지 확인
 * 4-1. 순환이 존재하면 skip
 * 4-2. 순환이 존재하지 않으면 두 정점을 연결
 * 5. 모든 간선을 확인할 때 까지 2번 반복
 * 
 * 간선을 연결하는 작업은 부모 배열을 바꾸는 방식으로 수행함ㄴ
 * 
 * 자세한건 노션 참고
 * 
 */
class Weight{

    int nodeA;
    int nodeB;
    int weight;

    Weight(int nodeA, int nodeB, int weight){
        this.nodeA = nodeA;
        this.nodeB = nodeB;
        this.weight = weight;
    }
}

class solve1197{

    PriorityQueue<Weight> weights = new PriorityQueue<>((after, before) -> {
        return after.weight - before.weight;
    });
    int [] parent;

    solve1197 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNode = Integer.parseInt(st.nextToken());
        int numofWeights = Integer.parseInt(st.nextToken());

        for(int i=0;i<numofWeights;i++){
            st = new StringTokenizer(br.readLine());
            weights.add(new Weight(Integer.parseInt(st.nextToken())
                                    ,Integer.parseInt(st.nextToken())
                                    ,Integer.parseInt(st.nextToken())));
        }

        parent = new int [numOfNode+1];
        for(int i=0;i<numOfNode+1;i++){
            parent[i] = i;
        }
    }

    void run(){

        int weight = 0;
        while(!weights.isEmpty()){

            Weight now = weights.poll();

            int parentOfA = getParent(now.nodeA);
            int parentOfB = getParent(now.nodeB);
            //순환이 존재하는지 확인
            if(parentOfA == parentOfB){
                continue;
            }

            weight += now.weight;
            setParent(parentOfA, parentOfB);    
        }

        System.out.println(weight);
    }

    //두 정점을 연결
    void setParent(int pOfA, int pOfB){

        if(pOfA < pOfB){
            parent[pOfB] = pOfA;
        } else {
            parent[pOfA] = pOfB;
        }
        
    }

    //현재 노드의 부모를 조회
    int getParent(int node){

        if(node == parent[node]){
            return node;
        }

        return parent[node] = getParent(parent[node]);
    }
}


public class Main {

    public static void main (String [] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1197 p = new solve1197(br);
        p.run();

        br.close();
    }
}