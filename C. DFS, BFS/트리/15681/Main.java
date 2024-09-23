import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 인접리스트를 트리로 만들면서 각 노드의 자식 수를 카운트하면 됨
 * 
 * 주의할 점
 * 1. DP 이용하지 않으면 안 됨
 * 2. DP 순서가 자식 먼저임
 * 
 * DP 이용
 * 어떤 서브 트리의 노드 갯수를 요구할지 모르기 때문에 모든 노드에 대한 수를 구해야 함
 * 
 * DP 순서
 * 자식 노드 먼저 카운트 한 다음에 부모를 카운트 할 수 있음 당연한게 
 * 부모의 갯수 = 자식의 갯수1 + 자식의 갯수2 .. 마지막 자식의 갯수
 * 위 형태로 부모의 갯수를 구함 리프 노드부터 갯수를 구해야 함
 * 근데 문제는 시작점으로 root가 제공된다는 점임 
 * 
 * 그래서 root로 먼저 호출하고 재귀 호출 이후에 갯수를 초기화 해야 함
 */

class solve15681{
    
    int numOfNodes;
    int root;
    int [] query;

    int [] nodes;

    List<List<Integer>> list = new ArrayList<>();

    solve15681(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfNodes = Integer.parseInt(st.nextToken());
        root = Integer.parseInt(st.nextToken());
        query = new int [Integer.parseInt(st.nextToken())];
        nodes = new int [numOfNodes + 1];

        for(int i=0;i<numOfNodes+1;i++){
            list.add(new ArrayList<>());
        }

        for(int i=0;i<numOfNodes-1;i++){
            st = new StringTokenizer(br.readLine());

            int tmp = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());

            list.get(tmp).add(tmp2);
            list.get(tmp2).add(tmp);
        }

        for(int i=0;i<query.length;i++){
            query[i] = Integer.parseInt(br.readLine());
        }
    }

    void run(){

        makeTree(root, -1);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<query.length;i++){

            sb.append(nodes[query[i]]).append("\n");
        }

        System.out.println(sb);
    }

    void makeTree(int now, int parent){

        nodes[now] = 1;
    
        for(int child : list.get(now)){
            
            if(child != parent){
                makeTree(child, now);
                nodes[now] += nodes[child];
            }
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15681 p = new solve15681(br);
        p.run();

        br.close();
    }
}