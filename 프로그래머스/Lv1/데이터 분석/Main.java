import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    
    Map<String, Integer> index = new HashMap<>();
    
    public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
        
        index.put("code", 0);
        index.put("date", 1);
        index.put("maximum", 2);
        index.put("remain", 3);
        
        int [][] answer = select(data, index.get(ext), val_ext);
        sort(answer, index.get(sort_by));
    
        return answer;
    }
    
    int [][] select(int [][] data, int ext, int val_ext){
        
        List<int []> select = new ArrayList<>();    
        for(int i=0;i<data.length;i++){
            
            if(data[i][ext] < val_ext){
                select.add(data[i]);
            }
        }    
        
        int [][] result = new int [select.size()][data[0].length];
        for(int i=0;i<result.length;i++){
            for(int j=0;j<result[0].length;j++){
                
                result[i][j] = select.get(i)[j];
            }
        }
        
        return result;
    }
    
    void sort(int [][] data, int sort_by){
        
        Arrays.sort(data, (after, before) -> {
            return after[sort_by] - before[sort_by];
        });
    }
}