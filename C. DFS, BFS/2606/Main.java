import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class solve2606{

    List<List<Integer>> graph = new ArrayList<>();
    boolean [] visited;
    Queue<Integer> queue = new ArrayDeque<>();

    solve2606 (BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());
        for(int i=0;i<num+1;i++){
            graph.add(new ArrayList<>());
        }
        visited = new boolean [num+1];

        int numOfConnection = Integer.parseInt(br.readLine());
        for(int i=0;i<numOfConnection;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }
    }

    void run(){

        System.out.println(bfs());
    }

    int bfs(){

        queue.add(1);
        int result = 0;
        visited[1] = true;

        while(!queue.isEmpty()){

            int now = queue.poll();

            for(int next : graph.get(now)){

                if(!visited[next]){

                    result++;
                    visited[next] =true;
                    queue.add(next);
                }
            }
        }

        return result;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2606 p = new solve2606(br);
        p.run();

        br.close();
    }
}