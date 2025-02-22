import java.util.Map;
import java.util.stream.IntStream;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        
        Map<Character, Integer> key = new HashMap<>();
        for(int i=0;i<keymap.length;i++){

            String keys = keymap[i];
            IntStream.range(0, keys.length()).forEach(j -> {
                
                char now = keys.charAt(j);
                key.compute(now, (nowKey, oldValue) -> 
                    (oldValue == null || oldValue > j) ? j : oldValue
                );
            });
        }
        
        int [] answer = new int [targets.length];
        for(int i=0;i<targets.length;i++){
            
            int count = 0;
            for(int j=0;j<targets[i].length();j++){
                
                Integer value = key.get(targets[i].charAt(j)); 
                if(value == null){
                    count = -1;
                    break;
                }
                
                count += value;
            }
            answer[i] = count;
        }
        
        return answer;
    }
}