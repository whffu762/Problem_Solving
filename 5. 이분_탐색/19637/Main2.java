import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * 알고리즘은 동일함
 * 다만 입력을 key가 power고 value가 name으로 Map에 저장함
 * 이 과정에서 중복되는 power는 배제함
 * 
 * 가장 큰 문제점은 Map을 HashMap으로 만들었을 땐 indexOutOfBounds가 발생했음
 * 원인은 map의 keySet()을 받을 때 입력된 순서가 보장되지 않아서 오름차순으로 정렬되어 있지 않았음
 * 1 3 17 8 19 이런 식으로 저장되어 있어서 이진 탐색이 제대로 동작하지 않았던 것임
 * LinkedHashMap을 이용해서 Map을 만들면 해결됨 
 * 
 */

class solve19637{

    Map<Integer, String> nameAndPower = new LinkedHashMap<>();
    List<Integer> powers;
    
    String judgeName(int target){

        int high = powers.size() - 1;
        int low = 0;
        int result = 0;

        while(low <= high){
            
            int mid = (low + high) / 2;

            if( target <= powers.get(mid)){
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return nameAndPower.get(powers.get(result));
    }


    void run(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int numOfName = Integer.parseInt(st.nextToken());
        int numOfUser = Integer.parseInt(st.nextToken());

        for(int i=0;i<numOfName;i++){
            
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            int power = Integer.parseInt(st.nextToken());

            if(nameAndPower.get(power) == null){
                nameAndPower.put(power, name);
            }
        }

        powers = new ArrayList<>(nameAndPower.keySet());
        

        for(int i=0;i<numOfUser;i++){
            int target = Integer.parseInt(br.readLine());
            sb.append(judgeName(target)).append("\n");
        }

        System.out.println(sb);
    }
}

public class Main2{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve19637 p = new solve19637();
        p.run(br);

        br.close();
    }
}