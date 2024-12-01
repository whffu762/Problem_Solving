import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * PriorityQueue 두 개를 이용하는 방식
 * 오름차순과 내림차순 두 가지를 모두 유지해야 함 이를 하나의 PQ로 유지해면서 명령이 입력될 때마다
 * 오름차순 혹으 내림차순 정렬을 하게 되면 (NLogN)^2의 시간복잡도를 가지고 입력이 1,000,000개 이기 때문에
 * 시간 초과가 발생할 수 밖에 없음
 * 이걸 오름차순 PQ와 내림차순 PQ 두 가지를 각각 유지해야 함
 * 
 * 두 PQ의 값이 동기화되야 함
 * 하나의 PQ에서 지워진 값은 다른 PQ에서도 지워져야 함 이를 위해 Map으로 데이터를 유지함
 * Map에 숫자를 key로하고 갯수를 value로 해서 0이면 없는 데이터로 취급
 * 
 * 값을 추출할 때 흐름
 * 1. 1이냐 -1이냐에 따라 오름차순 PQ와 내림차순 PQ중 선택
 * 2. PQ에서 poll
 * 3. null인지 확인 
 *  - null이면 null 반환
 *  - null 아니면 4번으로 이동
 * 4. map에서 poll한 값을 키로 갯수 확인
 *  - 갯수가 0이면 다시 2번으로
 *  - 갯수가 0이 아니면 map에서 갯수 하나 지우고 poll한 값 반환
 * 
 * 근데 여기서 key를 정렬하는 Map이 있다고 함 ㄷㄷ
 */
class solve7662{

    HashMap<Integer, Integer> map = new HashMap<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> min = new PriorityQueue<>();

    solve7662(BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if(command.equals("I")) add(value);
            else getMaxOrMin(value);
        }
    }

    void add(int value){

        max.add(value);
        min.add(value);

        map.compute(value, (key, oldValue) -> {
            if(oldValue == null) return 1;
            return oldValue + 1;
        });
    }

    Integer getMaxOrMin(int value){

        PriorityQueue<Integer> tmp;
        if(value == 1) tmp = max;
        else tmp = min;

        while(true){

            Integer now = tmp.poll();
            if(now == null) return null;
            
            int numOfNow = map.get(now);
            if(numOfNow != 0){

                map.compute(now, (key, oldValue) -> oldValue - 1);
                return now;
            }
        }
    }

    String run(){

        Integer max = getMaxOrMin(1);
        Integer min = getMaxOrMin(-1);

        if(max == null) return "EMPTY";
        if(min == null) min = max;
        return max + " " + min;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){
            solve7662 p = new solve7662(br);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}