import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
 * 파라메트릭 서치할 땐 값이 큰 경우가 대부분임 오버플로우 조심합시다 
 */
class solve2805{

    List<Integer> trees = new ArrayList<>();
    int target;
    int maxTree = 0;

    void input(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfTree = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfTree;i++){
            int tmp = Integer.parseInt(st.nextToken());
            trees.add(tmp);
            maxTree = Math.max(maxTree, tmp);
        }
    }
    
    long getTree(long height){
        
        return trees.stream()
            .mapToLong(tree -> tree - height)
            .filter(tree -> tree > 0)
            .sum();
    }

    void run(BufferedReader br) throws IOException{

        input(br);

        long low = 0;
        long high = maxTree;
        long result = 0;

        while(low <= high){

            long mid = (low + high) / 2;

            if(target <= getTree(mid)){
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2805 p = new solve2805();
        p.run(br);

        br.close();
    }
}