import java.util.Map;
import java.util.HashMap;

class Solution {
    public int[] solution(String[] keymap, String[] targets) {
        
        Map<Character, Integer> key = new HashMap<>();
        for(int i=0;i<keymap.length;i++){
            for(int j=0;j<keymap[i].length();j++){
                
                char now = keymap[i].charAt(j);
                final int index = j + 1;
                key.compute(now, (nowKey, oldValue) -> {
                    
                    if(oldValue == null){
                        return index;
                    }
                    
                    if(oldValue > index) {
                        return index;
                    }
                    
                    return oldValue;
                });
            }
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