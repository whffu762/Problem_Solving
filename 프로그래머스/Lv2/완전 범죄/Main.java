class Solution {
    public int solution(int[][] info, int n, int m) {
        
        int sumOfA = 0;
        for(int i=0;i<info.length;i++){
        
            sumOfA += info[i][0];
        }
        
        int [][] cache = new int [m][info.length+1];
        for(int i=1;i<cache.length;i++){
            for(int j=1;j<cache[0].length;j++){
                
                int a = info[j-1][0];
                int b = info[j-1][1];
                
                if(i < b){
                    cache[i][j] = cache[i][j-1];
                } else {
                    cache[i][j] = Math.max(cache[i][j-1], cache[i-b][j-1] + a);
                }
            }
        }
        
        int answer = sumOfA - cache[cache.length-1][cache[0].length-1];
        if(answer >= n) {
            answer = -1;
        }
        
        return answer;
    }    
}