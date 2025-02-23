class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        
        int [] answer = new int [balls.length];
        for(int i=0;i<balls.length;i++){
            
            int tmp = Integer.MAX_VALUE;
            if(!(startX > balls[i][0] && startY == balls[i][1])){
                tmp = Math.min(tmp, calDistance(xFlip(0, startX, startY), balls[i]));
            }
               
            if(!(startX < balls[i][0] && startY == balls[i][1])){
                tmp = Math.min(tmp, calDistance(xFlip(m, startX, startY), balls[i]));
            }
            
            if(!(startX == balls[i][0] && startY > balls[i][1])){
                tmp = Math.min(tmp, calDistance(yFlip(0, startX, startY), balls[i]));
            }
               
            if(!(startX == balls[i][0] && startY < balls[i][1])){
                tmp = Math.min(tmp, calDistance(yFlip(n, startX, startY), balls[i]));
            }
               
            answer[i] = tmp;
        }
        
        return answer;
    }
    
    int calDistance(int [] start, int [] target){
        return (int) (Math.pow(start[0] - target[0], 2) + Math.pow(start[1] - target[1], 2));
    }
    
    int [] xFlip(int x, int startX, int startY){
        return new int [] { 2*x - startX, startY };
    }
    
    int [] yFlip(int y, int startX, int startY){
        return new int [] { startX, 2*y - startY };
    }
}