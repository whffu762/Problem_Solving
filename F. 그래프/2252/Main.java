import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.List;

/*
 * 위상 정렬 이용
 * 1. 진입 차수 0인 노드를 Q에 저장
 * 2. Q에서 값하나 빼서 그 값의 인접 노드 차수 -1
 * 3. 뺐을 때 진입차수 0 이면 Q에 추가
 * 4. 이걸 Q가 빌 때까지 반복
 */
class solve2252{
    
    Queue<Integer> queue = new ArrayDeque<>();
    List<List<Integer>> graph = new ArrayList<>();
    int[] degree;

    solve2252(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int num = Integer.parseInt(st.nextToken()) + 1;
        int numOfCompare = Integer.parseInt(st.nextToken());

        degree = new int[num];

        for(int i=0;i<num;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<numOfCompare;i++){

            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            graph.get(front).add(back);
            degree[back]++;
        }
    }

    void run() {

        for(int i=1;i<degree.length;i++){

            if(degree[i] <= 0){
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!queue.isEmpty()){

            int now = queue.poll();
            sb.append(now).append(" ");
            
            deleteNode(now);
        }

        System.out.println(sb);
    }

    void deleteNode(int node){

        List<Integer> list = graph.get(node);
        for(int i=0;i<list.size();i++){
            int child = list.get(i);
            degree[child]--;

            if(degree[child] == 0){
                queue.add(child);
            }
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solve2252 p = new solve2252(br);

        p.run();

        br.close();
    }
}