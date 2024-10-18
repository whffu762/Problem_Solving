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
 * Main과 Main2의 다익스트라 방식이 살짝 다름 속도는 50ms 정도로 Main2가 더 빠름
 * 
 * 기존 다익스트라와 차이점
 * 1. graph를 가중치 오름차순으로 정렬해서 인접 노드를 저장함
 * -> Queue에 들어가는 노드를 최소한으로 함
 * 
 * 2. visited를 운영하지 않음
 * -> 어차피 오름차순으로 정렬해놔서 모두 다 조회하는 방식으로 해도 딱히 상관없음
 * 
 * 3. Queue에 들어가는 값이 int 하나임
 * -> 노드 인덱스만 집어넣고 최단 경로는 result에 있는 정보를 이용함
 * 
 * 원래 다익스트라는 Queue를 PriorityQueue로 해서 가중치가 가장 짧은 경로부터 접근함
 * 그래서 visited가 없으면 Node가 중복되서 계속 queue로 들어와서 시간/메모리 초과가 발생할 수 있음
 * 근데 여기선 정렬을 해놔서 queue로 들어오는 노드 수가 적음
 * 또한 어차피 최단 경로가 result에 저장되기 때문에 queue를 통해 전달하지 않는 방식을 이용함
 * 
 * 근데 뭐 이러면 뭐해 더 느린데 그냥 원래 방식데로 쓰는게 좋음
 * 
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