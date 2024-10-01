import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
 * 백트랙킹 이용해서 dfs
 * 경로의 가중치가 다른 갈랫길의 경로에는 영향을 주면 안 됨 그래서 백트랙킹 이용
 * 또한 완전 탐색이긴 한데 결국 목표값 찾으면 거기서 탐색 중지하도록 해서 속도 개선
 */
class solve1240{

    List<List<int []>> tree;

    int start;
    int end;

    boolean [] visited;

    solve1240(List<List<int []>> tree, int start, int end) {

        this.tree = tree;
        this.start = start;
        this.end = end;
        this.visited = new boolean[tree.size()+1];
    }

    int run(){

        dfs(start, 0);
        return result;
    }

    int result;
    boolean flag;

    void dfs(int now, int distance){

        visited[now] = true;
        if(now == end){
            flag = true;
            result = distance;
            return;
        }
        
        for(int [] child : tree.get(now)){

            if(!flag && !visited[child[0]]){
                dfs(child[0], distance + child[1]);   
            }
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfNodes = Integer.parseInt(st.nextToken());
        int numOfTarget = Integer.parseInt(st.nextToken());

        List<List<int []>> tree = new ArrayList<>();
        
        for(int i=0;i<numOfNodes+1;i++){
            tree.add(new ArrayList<>());
        }

        for(int i=1;i<numOfNodes;i++){

            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree.get(tmp).add(new int [] {tmp2, weight});
            tree.get(tmp2).add(new int [] {tmp, weight});
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfTarget;i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            solve1240 p = new solve1240(tree, start, end);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}
