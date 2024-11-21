import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/*
 * 주목해야할 점
 * - Math.round에는 double이 들어가야 함
 * - -1.8을 반올림하면 -2 (큰 수로 가는게 아니라 가까운 수로 감)
 */
class solve2108{

    int sum;
    int [] nums;
    HashMap<Integer, Integer> frequency = new HashMap<>();

    solve2108 (BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());
        nums = new int [num];

        for(int i=0;i<num;i++){

            int tmp = Integer.parseInt(br.readLine());
            sum += tmp;
            nums[i] = tmp;
            
            frequency.compute(tmp, (key, oldValue) -> {
                return (oldValue == null) ? 1 : oldValue + 1;
            });
        }

        Arrays.sort(nums);
    }

    void run(){

        StringBuilder sb = new StringBuilder();

        
        long avg = Math.round((double) sum / nums.length);
        int mid = nums[nums.length/2];
        
        int freq = 0;
        for(int v : frequency.keySet()){

            freq = Math.max(freq, frequency.get(v));
        }

        List<Integer> tmp = new ArrayList<>();
        for(int v : frequency.keySet()){

            if(frequency.get(v) == freq){
                tmp.add(v);
            }
        }
        Collections.sort(tmp);

        int fValue = 0;
        if(tmp.size() > 1){
            fValue = tmp.get(1);
        } else {
            fValue = tmp.get(0);
        }


        int diff = nums[nums.length-1] - nums[0];

        sb.append(avg).append("\n")
        .append(mid).append("\n")
        .append(fValue).append("\n")
        .append(diff).append("\n");
        
        System.out.println(sb);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2108 p = new solve2108(br);
        p.run();

        br.close();
    }
}