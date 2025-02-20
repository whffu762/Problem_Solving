import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

class Solution {
    public int solution(int[] mats, String[][] park) {
        
        Arrays.sort(mats);
        int answer = 0;
        
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[0].length;j++){
                if(park[i][j].equals("-1")){
                    answer = getMat(mats, i, j, park);
                }
            }
        }
        
        return answer;
    }
    
    int getMat(int [] mats, int row, int col, String [][] park){
        
        int result = 0;
        for(int i=mats.length-1;i>-1;i--){
            
            boolean flag = true;
            int matSize = mats[i];
            
            for(int j=row;j<row+matSize;j++){
                for(int k=col;k<col+matSize;k++){
                    if(j < park.length && k < park[0].length && !park[j][k].equals("-1")){
                        flag = false;
                        break;
                    }
                }
                
                if(!flag){
                    break;
                }
            }
            
            if(flag){
                result = matSize;
                break;
            }
        }
        
        return result;
    }
}

public class Main {

    public static void main(String [] args){

        int [] mats = new int [] { 5, 3 ,2};

        String [][] park = new String [][] {
            {"A", "A", "-1", "B", "B", "B", "B", "-1"}, 
            {"A", "A", "-1", "B", "B", "B", "B", "-1"}, 
            {"-1", "-1", "-1", "-1", "-1", "-1", "-1", "-1"},
            {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"},
            {"D", "D", "-1", "-1", "-1", "-1", "-1", "F"},
            {"D", "D", "-1", "-1", "-1", "-1", "E", "-1"}
        };

        Solution p = new Solution();
        p.solution(mats, park);
    }
}