import java.util.Queue;
import java.util.ArrayDeque;

class Solution {
    
    int [][] store;
    int answer;
    
    Queue<int []> queue;
    boolean [][] visited;
    int [] moveX = {0, 0, -1, 1};
    int [] moveY = {-1, 1, 0, 0};
    
    public int solution(String[] storage, String[] requests) {
        
        init(storage);
        answer = storage.length * storage[0].length();
        
        for(int i=0;i<requests.length;i++){
            
            int target = (int) requests[i].charAt(0);
            if(requests[i].length() > 1){
                useCrain(target);
            } else {
                bfs();
                useLift(target);
            }
        }

        return answer;
    }
    
    void bfs(){
        
        queue = new ArrayDeque<>();
        visited = new boolean[store.length][store[0].length];
        queue.add(new int [] {0, 0});
        visited[0][0] = true;
        
        while(!queue.isEmpty()){
            
            int [] now = queue.poll();
            
            for(int i=0;i<moveX.length;i++){
                
                int [] next = {now[0] + moveX[i], now[1] + moveY[i]};
                if(checkValid(next)){
                    store[next[0]][next[1]] = 0;
                    visited[next[0]][next[1]] = true;
                    queue.add(next);
                }
            }
        }
    }
    
    boolean checkValid(int [] next){
        
        if(next[0] > -1 && next[0] < store.length && next[1] > -1 && next[1] < store[0].length){
            
            if(store[next[0]][next[1]] == 0 || store[next[0]][next[1]] == -1){
                return !visited[next[0]][next[1]];    
            }
        }
        
        return false;
    }
    
    void useCrain(int target){
        
        for(int i=1;i<store.length-1;i++){
            for(int j=1;j<store[0].length-1;j++){
                
                if(store[i][j] == target){
                    store[i][j] = -1;
                    answer--;
                }
            }
        }
    }
    
    void useLift(int target){
        
        for(int i=1;i<store.length-1;i++){
            for(int j=1;j<store[0].length;j++){
                
                if(store[i][j] == target && checkPossible(i, j)){
                    store[i][j] = -1;
                    answer--;
                }
            }
        }
    }
    
    boolean checkPossible(int row, int col){
        
        for(int i=0;i<moveX.length;i++){
            
            int [] next = {row + moveX[i], col + moveY[i]};
            if(store[next[0]][next[1]] == 0){
                return true;
            }
        }
        
        return false;
    }
    
    void init(String [] storage){
        
        store = new int [storage.length+2][storage[0].length()+2];
        
        for(int i=1;i<storage.length+1;i++){
            for(int j=1;j<storage[0].length()+1;j++){
                store[i][j] = (int) storage[i-1].charAt(j-1);
            }
        }
    }
}