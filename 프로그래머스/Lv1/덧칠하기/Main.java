class Solution {
    public int solution(int n, int m, int[] section) {
        
        boolean [] wall = new boolean[n+1];
        for(int i=0;i<section.length;i++){
            wall[section[i]] = true;
        }
        
        int answer = 0;
        int now = 1;
        while(now < wall.length){
            
            if(wall[now]){
                answer++;
                now += m - 1;
            }
            
            now++;
        }
        
        return answer;
    }
}