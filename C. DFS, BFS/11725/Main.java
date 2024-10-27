import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * tree 생성하면서 부모 구하면 됨
 * 
 * 준비
 * 인접 리스트로 양방향 관계 정의
 * -> 누가 부모인지 모르기 때문에 양방향 관계로 해야됨
 * 
 * 로직
 * BFS로 1부터 시작해서 자식들에 접근해서 부모 구하면 됨
 * -> 부모인 노드들은 이전에 이미 방문 처리 함으로써 구분함
 * 
 */
class solve11725{

    List<List<Integer>> tree = new ArrayList<>();
    int [] parent;
    Queue<Integer> queue = new ArrayDeque<>();
    boolean [] visited;

    solve11725 (BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num+1;i++){
            tree.add(new ArrayList<>());
        }
        parent = new int [num+1];
        visited = new boolean[num+1];
        
        for(int i=0;i<num-1;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());

            tree.get(tmp1).add(tmp2);
            tree.get(tmp2).add(tmp1);
        }
    }

    void run(){

        queue.add(1);
        visited[1] = true;

        while(!queue.isEmpty()){

            int now = queue.poll();

            for(int child : tree.get(now)){

                if(!visited[child]){

                    parent[child] = now;
                    visited[child] = true;
                    queue.add(child);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=2;i<parent.length;i++){
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }
}

public class Main {

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11725 p = new solve11725(br);
        p.run();

        br.close();
    }
}