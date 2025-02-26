class Solution {
    public int solution(int[] players, int m, int k) {
        
        int answer = 0;
        int numOfServer = 0;
        int [] addServer = new int [24];
        for(int i=0;i<players.length;i++){
            
            int numOfUser = players[i];
            int needAdd = numOfUser / m;
            
            if(i >= k){
                numOfServer -= addServer[i-k];
            }
            
            if(needAdd > numOfServer){
                int need = needAdd - numOfServer;
                numOfServer += need;
                answer += need;
                addServer[i] = need;
            }
        }
        
        return answer;
    }
}