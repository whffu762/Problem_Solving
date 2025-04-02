import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
 * DFS로 완전 탐색을 수행하는 문제임
 * 
 * 주의할 점
 * DFS를 재귀호출로 구현하면 스택오버플로우가 발생하여 런타임 예외가 터짐 반드시 스택으로 구현해야 함
 * 
 * 주목할 점
 * 두 개의 순환이 필요함
 * 1. 방향 순환 R일 경우 0 1 2 3 0 .. / L일 경우 0 3 2 1 0 .. 
 * 2. 범위를 벗어난 경우 순환
 * 이 두 개를 두 개의 메소드로 나눠서 구현함
 * 
 */

class Solution {
    
    int count = 0;
    int [] start = new int [2];
    
    int row;
    int col;
    
    char [][] graph;
    boolean [][][] visited;
    
    public int[] solution(String[] grid) {
        
        row = grid.length;
        col = grid[0].length();
        
        graph = new char [row][col];
        visited = new boolean [row][col][4];
        
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                graph[i][j] = grid[i].charAt(j);
            }
        }
        
        List<Integer> result = new ArrayList<>();
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                for(int k=0;k<4;k++){
                    if(!visited[i][j][k]){
                        count = 1;
                        
                        start[0] = i * col + j;
                        start[1] = k;
                        
                        int nextDirection = getNextDirection(graph[i][j], k);
                        int [] nextNode = getNextNode(i, j, nextDirection);
                        
                        shoot(nextNode[0], nextNode[1], nextDirection);
                        result.add(count);
                    }
                }
            }
        }
        
        return result.stream().mapToInt(Integer::intValue).sorted().toArray();
    }
    
    void shoot(int nowRow, int nowCol, int nowDirection){
        
        Stack<int []> stack = new Stack<>();
        visited[nowRow][nowCol][nowDirection] = true;
        stack.push(new int [] {nowRow, nowCol, nowDirection});
        
        while(!stack.isEmpty()){
            
            int [] now = stack.pop();
            if(now[0] * col + now[1] == start[0] && now[2] == start[1]){
                break;
            }
            
            int nextDirection = getNextDirection(graph[now[0]][now[1]], now[2]);
            int [] nextNode = getNextNode(now[0], now[1], nextDirection);
            
            count++;
            visited[nextNode[0]][nextNode[1]][nextDirection] = true;
            
            stack.push(new int [] {nextNode[0], nextNode[1], nextDirection});   
        }
    }
    
    int getNextDirection(char sign, int direction){
        
        if(sign == 'R'){
            return (direction + 1) % 4;
        }
        
        if(sign == 'L'){
            return (direction + 3) % 4;
        }
        
        return direction;
    }
    
    int [] getNextNode(int nowRow, int nowCol, int direction){
        
        int [] next = new int [2];
        if(direction == 0){
            next[0] = nowRow;
            if(col == 1){
                next[1] = nowCol;   
            } else {
                next[1] = (nowCol + 1) % col;                
            }
        }
        
        if(direction == 1){
            if(row == 1){
                next[0] = nowRow;
            } else {
                next[0] = (nowRow + 1) % row;   
            }
            next[1] = nowCol;
        }
        
        if(direction == 2){
            next[0] = nowRow;
            if(col == 1) {
                next[1] = nowCol;
            } else {
                next[1] = (nowCol + (col - 1)) % col;   
            }
        }
        
        if(direction == 3){
            if(row == 1) {
                next[0] = nowRow;
            } else {
                next[0] = (nowRow + (row - 1)) % row;
            }
            next[1] = nowCol;
        }
        
        return next;
    }
}