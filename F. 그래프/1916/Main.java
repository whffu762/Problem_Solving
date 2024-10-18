import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 다익스트라 쓰는 간단한 문제
 * tmp
 */
class solve1916{

    int numOfCity, numOfBus;
    List<List<int []>> graph = new ArrayList<>();
    long [] result;
    Queue<Integer> queue = new ArrayDeque<>();
    int start, end;

    solve1916 (BufferedReader br) throws IOException{

        numOfCity = Integer.parseInt(br.readLine());
        numOfBus = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfCity+1;i++){
            graph.add(new ArrayList<>());
        }

        result = new long [numOfCity+1];
        Arrays.fill(result, Long.MAX_VALUE);

        for(int i=0;i<numOfBus;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(start).add(new int [] { end, weight });
        }

        for(int i=0;i<numOfCity;i++){

            Collections.sort(graph.get(i), (after, before)-> {
                return after[1] - before[1];
            });

        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());
    }

    void run(){

        queue.add(start);
        result[start] = 0;

        while(!queue.isEmpty()){

            int now = queue.poll();
            for(int [] next : graph.get(now)){

                long weight = result[now] + next[1];
                if(result[next[0]] > weight){
                    queue.add(next[0]);
                    result[next[0]] = weight;
                }
            }
        }
        
        System.out.println(result[end]);

    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1916 p = new solve1916(br);
        p.run();

        br.close();
    }
}