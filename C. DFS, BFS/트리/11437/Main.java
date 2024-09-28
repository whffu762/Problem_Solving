import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 부모 배열 이용하면 되는 간단한 문제 
 * 이전에 3584와 비슷한 문제임 근데 약간의 차이점이 있음
 * 1. 입력 순서가 부모 - 자식 관계임이 보장되지 않음
 * 2. root가 1로 고정임
 * 
 * 이 때문에 입력을 받을 땐 일단 인접 리스트로 저장해뒀다가 입력이 다 끝난 후에
 * 1을 기준으로 트리를 생성해줘야 함 그 트리를 기준으로 공통 부모 찾는건 동일함
 * 
 * LCA (최소 공통 부모) 찾는 방법
 * 1. 3584에서 했던데로 루트까지의 모든 부모 구한 다음에 비교한느 방법
 * 2. 11437에서 구현한 방법 
 * 3. 더 빠른 방법 있는데 그건 나중에 자세히 하는걸로 .. 일단 넘어가자
 * 
 * 일단 여기서 구현한 방법
 * 1. 첫번째 값으로 root로 가면서 방문한 노드를 visited로 유지
 * 2. 두번째 값으로 root로 가면서 visited가 true면 그 값을 반환 
 * 이 방법이 시간이 좀 걸림 아무래도 최악의 경우 root까지 가야해서 그런듯
 * 
 */
class solve11437{

    int [] tree;
    boolean [] visited;
    int [][] target;
    int result;
    List<List<Integer>> tmpTree = new ArrayList<>();

    solve11437 (BufferedReader br) throws IOException{

        int size = Integer.parseInt(br.readLine());
        for(int i=0;i<size+1;i++){
            tmpTree.add(new ArrayList<>());
        }
        tree = new int [size+1];
        visited = new boolean[size+1];
        
        for(int i=1;i<size;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            tmpTree.get(tmp).add(tmp2);
            tmpTree.get(tmp2).add(tmp);
        }
        makeTree(1);

        size = Integer.parseInt(br.readLine());

        target = new int [size][2];
        for(int i=0;i<size;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            target[i][0] = Integer.parseInt(st.nextToken());
            target[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    void makeTree(int now){

        visited[now] = true;
        List<Integer> childs = tmpTree.get(now);

        for(int child : childs){

            if(!visited[child]){
                tree[child] = now;
                makeTree(child);
            }
        }
    }

    void run(){

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<target.length;i++){
            result = 1;
            Arrays.fill(visited, false);
            getParent(target[i][0]);
            getParent(target[i][1]);
            sb.append(result).append("\n");
        }

        System.out.println(sb);

    }

    void getParent(int now){

        while(now != 1){

            if(visited[now]){
                result = now;
                return;
            } else {
                visited[now] = true;
                now = tree[now];
            }
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve11437 p = new solve11437(br);
        p.run();

        br.close();
    }
}