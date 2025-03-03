import java.util.Queue;
import java.util.ArrayDeque;

/*
 * bfs를 한 번만 돌아야 함
 * 석유가 발견되면 bfs로 모든 석유 구역을 조회하고 해당 구역의 가장 큰 열까지 
 * 석유의 양을 더해주면 됨 
 * 
 * 주의할 점은 열을 우선으로 순회해야 함
 * 행을 우선으로 하면 다음과 같은 반례가 존재함
 * 0 0 1 0 0
 * 0 1 1 0 0
 */
class Solution {
    
    int [] oil;
    boolean [][] visited;
    int [] moveX = {-1, 1, 0, 0};
    int [] moveY = {0, 0, -1, 1};
    
    public int solution(int[][] land) {
        
        oil = new int [land[0].length];
        visited = new boolean [land.length][land[0].length];
        
        for(int j=0;j<land[0].length;j++){
            for(int i=0;i<land.length;i++){
                
                if(land[i][j] == 1 && !visited[i][j]){
                    int [] result = bfs(land, i, j);
                    for(int k=j;k<result[1]+1;k++){
                        oil[k] += result[0];
                    }
                }
            }
        }
        
        int answer = oil[0];
        for(int i=1;i<oil.length;i++){
            answer = Math.max(answer, oil[i]);
        }
        
        return answer;
    }
    
    int [] bfs(int [][] land, int startRow, int startCol){
        
        int maxCol = -1;
        int amount = 0;
        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int [] {startRow, startCol});
        visited[startRow][startCol] = true;
        
        while(!queue.isEmpty()){
            
            int [] now = queue.poll();
            amount++;
            maxCol = Math.max(maxCol, now[1]);
            
            for(int i=0;i<moveX.length;i++){
                
                int [] next = {now[0] + moveX[i], now[1] + moveY[i]};
                
                if(checkValid(next) && land[next[0]][next[1]] == 1){
                    visited[next[0]][next[1]] = true;
                    queue.add(next);
                }
            }
        }
        
        return new int [] {amount, maxCol};
    }
    
    boolean checkValid(int [] next){
        
        if(next[0] > -1 && next[0] < visited.length && next[1] > -1 && next[1] < visited[0].length){
            return !visited[next[0]][next[1]];
        }
        
        return false;
    }
}