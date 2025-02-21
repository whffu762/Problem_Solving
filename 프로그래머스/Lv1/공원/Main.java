import java.util.Arrays;

class Solution {
    public int solution(int[] mats, String[][] park) {
        
        int answer = -1;
        
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[0].length;j++){
                
                if(park[i][j].equals("-1")){
                    for(int k=0;k<mats.length;k++){
                        answer = Math.max(answer, getMat(park, i, j, mats[k]));
                    }   
                }
            }
        }
        
        return answer;
    }
    
    int getMat(String [][] park, int row, int col, int matSize){
        
        for(int i=row;i<row+matSize;i++){
            for(int j=col;j<col+matSize;j++){
                
                if(i == park.length || j == park[0].length){
                    return -1;
                }
                
                if(!park[i][j].equals("-1")){
                    return -1;
                }
            }
        }
        
        return matSize;
    }
}