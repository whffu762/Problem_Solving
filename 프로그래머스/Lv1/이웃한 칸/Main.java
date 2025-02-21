class Solution {
    public int solution(String[][] board, int h, int w) {
        
        int [] moveX = { -1, 1, 0, 0};
        int [] moveY = {0, 0, -1, 1};
        
        String target = board[h][w];
        int answer = 0;
        for(int i=0;i<moveX.length;i++){
            
            int [] next = { h + moveX[i], w + moveY[i]};
            
            if(next[0] > -1 && next[0] < board.length && 
               next[1] > -1 && next[1] < board[0].length){
                
                if(board[next[0]][next[1]].equals(target)){
                    answer++;
                }
            }
        }
        return answer;
    }
}