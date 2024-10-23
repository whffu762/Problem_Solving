import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 그리드인데 사실 그건 중요한게 아님
 * 그냥 이 방식을 생각해내는게 제일 어려움 시간 초과 때문에 개빡임
 * LinkedList로 해서 파라메트릭 서치해도 시간 초과남 자세한건 노션 참고
 * 
 * 그래서 로직
 * 1. 가방 별로 접근해서 그 가방에 들어갈 수 있는 모든 보석 pq에 저장
 * 2. pq는 가격 순으로 정렬하고 받아오면 됨
 * 3. 모든 가방 다 돌릴 때 까지 반복
 * 
 */
class solve1202{

    List<int []> dia = new ArrayList<>();
    List<Integer> bags = new ArrayList<>();
    
    solve1202 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfDia = Integer.parseInt(st.nextToken());
        int numOfBag = Integer.parseInt(st.nextToken());

        for(int i=0;i<numOfDia;i++){
            st = new StringTokenizer(br.readLine());
            dia.add(new int [] {
                Integer.parseInt(st.nextToken()), 
                Integer.parseInt(st.nextToken())
            });
        }

        for(int i=0;i<numOfBag;i++){
            bags.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(dia, (after, before)-> {
            if(after[0] == before[0]){
                return before[1] - after[1];
            }

            return after[0] - before[0];
        });
        Collections.sort(bags);
    }

    void run(){

        PriorityQueue<Integer> canGet = new PriorityQueue<>((after, before) -> {
            return before - after;
        });

        long result = 0;
        for(int i=0, j=0; i<bags.size();i++){

            while(j < dia.size() && dia.get(j)[0] <= bags.get(i)){
                canGet.add(dia.get(j++)[1]);
            }

            if(!canGet.isEmpty()){
                result += canGet.poll();
            }
        }

        System.out.println(result);
    }

}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1202 p = new solve1202(br);
        p.run();

        br.close();
    }
}