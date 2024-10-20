import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 그리디 - 활동 선택 문제
 * 활동 선택 문제에 대해 모르면 못 품
 * 간단히 말하면 주어진 활동 중 가장 많은 활동 횟수를 찾을 때 종료 시간이 빠른 것부터 취급하는 방식임
 * 이 방식이 타당한 이유는 종료 시간이 빠른 것부터 처리해야 그 이후의 선택지가 더 많아지기 때문임 자세한건 노션 참고
 * 이걸 회의실에 적용하면 됨
 * 
 * 회의실 가능 조건 
 * 이전 회의 종료 시간 <= 현재 회의 시작 시간 
 * - 이전 회의가 끝난 이후에 예약 가능
 * - 끝나자마자 시작이 가능하기 때문에 = 까지 포함
 * - 해당 조건이 만족할 때만 이전 회의 종료 시간을 최신화
 * 
 * 로직 자체는 쉽지만 활동 선택 문제를 모르면 정당성을 확신하기 어려움;;
 */
class solve1931{

    PriorityQueue<int []> queue = new PriorityQueue<>((after, before) -> {
        
        if(after[1] == before[1]){
            return after[0] - before[0];
        }

        return after[1] - before[1];
    });

    solve1931 (BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());
        
        for(int i=0;i<num;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            queue.add(new int [] {start, end});
        }
    }

    void run(){

        int result = 0;
        int previousEnd = -1;
        while(!queue.isEmpty()){

            int [] now = queue.poll();
            
            if(now[0] >= previousEnd){
                result++;
                previousEnd = now[1];
            }
        }
        
        System.out.println(result);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1931 p = new solve1931(br);
        p.run();

        br.close();
    }
}