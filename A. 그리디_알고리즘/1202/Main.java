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
 * 1. 먼저 가방과 보석 모두 오름차순 정렬
 * 2. 크기가 작은 가방부터 그 가방에 들어갈 수 있는 모든 보석을 PQ에 저장
 * - 이때 한 번 접근한 보석은 다음 가방에서도 다시 접근하지 않도록 함
 * 3. PQ가 비어있지 않다면 가격 내림차순으로 정렬 후 result에 추가
 * 4. 이걸 모든 가방에 적용할 때까지 반복
 * 
 * 예시 상황
 * 가방 무게 20, 30, 40
 * 보석 무게 10 20 31 35 
 * 
 * 보석이 중복되진 않나?
 * 20짜리 가방을 검사할 때 PQ에 10 20이 들어감
 * 30짜리 가방을 검사할 땐 31부터 검사함
 * 즉 이전 가방에서 검사한 가방 이후부터 검사함으로써 중복을 제거
 * 
 * PQ를 이용하는 이유
 * 이전 검사 때 남아있는 보석이 더 가벼워도 값어치가 나갈 경우가 존재함
 * 혹은 위 예시 상황처럼 30에 해당하는 보석이 없을 수 있음
 * 그래서 PQ에 이전 가방에서 검사한 보석까지 저장해두고 현재 가방에서 검사한 보석까지 해서
 * 모두 통틀어서 더 비싼 걸 고르기 위함임
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