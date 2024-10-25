import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 정답률에 비해 쉬운 문제
 * 
 * 음수 : 음수 혹은 0일아 묶음
 * 양수 : 1을 제외한 양수끼리 묶음
 * 0 : 음수랑만 묶임
 * 1 : 누구와도 묶이지 않고 오로지 더해지기만 해야 함
 * 
 * 음수는 오름차순 정렬해서 묶고 양수는 내림차순 정렬해서 묶으면 됨
 */

class solve1744{

    PriorityQueue<Integer> plus = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> minus = new PriorityQueue<>();
    List<Integer> one = new ArrayList<>();

    solve1744 (BufferedReader br ) throws IOException{

        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num;i++){

            int now = Integer.parseInt(br.readLine());
            if(now > 1){
                plus.add(now);
            } else if (now == 1){
                one.add(1);
            } else {
                minus.add(now);
            }
        }
    }

    void run(){

        int result = getSum(plus) + getSum(minus);

        for(int o : one){
            result += o;
        }
        System.out.println(result); 
        
    }

    int getSum(PriorityQueue<Integer> pq) {

        int result = 0;
        while(pq.size() > 1){

            int a = pq.poll();
            int b = pq.poll();

            result += a * b;
        }

        if(!pq.isEmpty()){
            result += pq.poll();
        }

        return result;
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1744 p = new solve1744(br);
        p.run();

        br.close();
    }
}