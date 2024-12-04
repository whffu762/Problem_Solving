import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 활동 선택 문제라고 생각했는데 아님
 * 활동 선택 문제는 하나의 주체가 시작과 끝이 명시된 활동들 중 가장 많이 선택할 수 있는 경우를 구함
 * 그래서 끝나는 시간을 기준으로 오름차순 정렬을 통해 구함
 * 근데 이건 모든 활동을 최대한 빽빽하게 정렬해야 하는 문제임 비슷한 결이긴 한데 명백히 다름
 * 하나의 강의실에서 가장 많은 강의를 선택하는 것과 최대한 빽빽하게 정렬하는 것은 비슷할 순 있지만 반례가 존재함
 * 
 * 5 6, 6 20 
 * 1 4, 4 8, 8 12
 * 위처럼 딱 시간이 맞는 경우 활동 선택 문제로 풀면 5 6 뒤에 8 12가 오게됨 하지만 그럴 경우
 * 
 * 5 6, 8 12
 * 1 4, 4 8
 * 6 20
 * 이렇게 빽빽하게 정렬한 경우와 달라지게 됨
 * 
 * 그래서 활동 선택 문제라고 생각하면 안 됨 강의의 끝 시간과 시작 시간의 차이를 최대한 짧게 만들어야 함
 * - 강의를 입력받을 땐 시작 시간을 기준으로 오름차순 정렬
 * - 강의를 배정할 땐 끝 시간을 기준으로 오름차순 정렬
 * 즉 강의실에 배정할 때 시작 시간이 가장 빠른 것과 종료 시간이 가장 빠른 것 둘을 비교하는 방식을 이용하면
 * 최대한 빽빽하게 강의 시간을 정렬할 수 있음
 * 
 * 반복을 제거
 * 만약 종료 시간이 가장 이른 강의에 들어가지 못한다면 이후 강의도 당연히 못 들어가게 됨 이를 이용하는 것임
 * 만약 들어갈 수 있다면 -> 해당 강의를 마지막 시간에서 제거하고 현재 강의를 삽입
 * 만약 들어갈 수 없다면 -> 그냥 현재 강의만 삽입
 */
class solve14500{

    PriorityQueue<int []> queue = new PriorityQueue<>((after, before) -> 
        after[0] == before[0] ? after[1] - before[1] : after[0] - before[0]);


    solve14500 (BufferedReader br) throws IOException{
        
        int num = Integer.parseInt(br.readLine());
        for(int i=0;i<num;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.add(new int [] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
    }

    void run(){

        PriorityQueue<Integer> ends = new PriorityQueue<>();
        ends.add(queue.poll()[1]);

        while(!queue.isEmpty()){

            int [] next = queue.poll();

            if(ends.peek() <= next[0]){
                ends.poll();
            }
            ends.add(next[1]);
        }

        System.out.println(ends.size());
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14500 p = new solve14500(br);
        p.run();

        br.close();
    }
}