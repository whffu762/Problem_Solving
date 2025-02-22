import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

class Solution {
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        Map<String, Integer> mapOfTerms = new HashMap<>();
        for(int i=0;i<terms.length;i++){
            String [] tmp = terms[i].split(" ");
            mapOfTerms.put(tmp[0], Integer.parseInt(tmp[1]));
        }
        
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<privacies.length;i++){
            
            String [] tmp = privacies[i].split(" ");
            String date = tmp[0];
            String condition = tmp[1];
            
            int validTerm = mapOfTerms.get(condition);
            int [] endDate = toIntAry(date.split("\\."));
            getValidDate(endDate, validTerm);
            
            int [] todayDate = toIntAry(today.split("\\."));
            if(!checkValid(todayDate, endDate)){
                result.add(i+1);
            }
        }
        
        return result.stream().mapToInt(i -> i).toArray();
    }
    
    
    int [] toIntAry(String [] ary){
        
        return new int [] {Integer.parseInt(ary[0]), Integer.parseInt(ary[1]), Integer.parseInt(ary[2])};
    }
    
    void getValidDate(int [] date, int validTerm){
        
        date[1] += validTerm;
        date[0] += date[1] / 12;
        date[1] = date[1] % 12;
        
        if(date[1] == 0){
            date[0]--;
            date[1] = 12;
        }
    }
    
    boolean checkValid(int [] today, int [] endDate){
        
        if(today[0] < endDate[0]){
            return true;
        }
        
        if(today[0] > endDate[0]){
            return false;
        }
        
        if(today[1] < endDate[1]){
            return true;
        }
        
        if(today[1] > endDate[1]){
            return false;
        }
        
        if(today[2] < endDate[2]){
            return true;
        }
        
        return false;
    }
}