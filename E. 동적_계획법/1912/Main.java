import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

/*
 * DP로 풀어야 시간초과 안 남
 * 
 * 이전까지의 합과 현재 값을 비교해서 더 큰 값을 저장하는 방식
 * 
 * 원래는 양수끼리는 다 더해서 하나로 취급하고 투포인터를 이용했는데 시간초과 뜸
 * 또 문제점이 양수끼리 더하는 과정에서 0을 버리기 때문에 0과 음수만으로 이루어진 경우 제대로 동작하지 않음
 * 
 */

class solve1912{

    List<Integer> values = new ArrayList<>();
    
    solve1912(BufferedReader br) throws IOException{

        int numOfValue = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfValue;i++){
            values.add(Integer.parseInt(st.nextToken()));
        }
    }

    void run(){

        int [] cache = new int [values.size()+1];

        int max = Integer.MIN_VALUE;

        for(int i=1;i<values.size()+1;i++){
            
            int now = values.get(i-1);
            int tmp = cache[i-1] + now;
            cache[i] = Math.max(now, tmp);

            max = Math.max(max, cache[i]);
        }

        System.out.println(max);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1912 p = new solve1912(br);
        p.run();

        br.close();
    }
}