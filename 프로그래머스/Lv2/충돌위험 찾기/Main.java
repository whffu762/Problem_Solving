/*
 * 원래는 각 로봇마다 경로를 다 저장한 후에 마지막에 한꺼번에 충돌을 확인하려고 했는데
 * 그러면 3차원 배열이 필요함 max가 100이라 상관은 없는데 찝찝해서 각 움직임 후에 충돌 여부를 확인하는 방식으로 구현함
 * 근데 그려러다 보니 좀 구현이 복잡해짐
 * 
 * 필요한거
 * - 각 로봇의 현재 위치 (now 필드)
 * - 각 로봇의 목적지(indexOfDestination 필드)
 * 
 * indexOfDestination
 * 이걸 유지하는 이유는 각 로봇마다 목적지에 도착하는 시점이 다르기 때문에 현재 각 로봇마다의 목적지를 유지하기 위함임  
 * 1부터 시작하는 routes의 index가 저장됨 0은 초기 위치고 첫번째 목적지는 1, 1 이후엔 2 이런 식으로 로봇이 도착해야 하는 
 * 포인트의 인덱스가 저장된 배열의 인덱스임 뭔가 말이 복잡한데 
 * routes가 [[4, 2, 3, 5] [1, 2, 3, 5]]이고 indexOfDestinatino 배열에 [1, 4]가 저장되어 있다면 
 * - 0번 로봇의 목적지는 : 2
 * - 1번 로봇의 목적지는 : 5
 * 그래서 getDestination() 메소드를 따로 둬서 현재 가야할 목적지 포인트의 좌표를 구함
 * 또한 매 움직임 후 각 현재 위치를 확인해서 목적지에 도달한다면 index를 1 증가함
 * 그 후 routes[0].length까지 도달한다면 모든 목적지에 도달한 것이므로 finsih 처리를 수행함
 * 
 * 모든 routes를 도달한 로봇의 처리
 * now를 그대로 두면 목적지는 같지만 도달 시점이 다른 로봇과 충돌로 취급하게 됨
 * 그렇다고 모두 -1로 취급하면 종료한 로봇마다 충돌로 취급하게 됨
 * 그에 따라 각 로봇마다 다른 값으로 종료임을 유지하기 위해 -1에 각 인덱스를 곱하는 방식으로 구현
 * 
 * 움직임 구현
 * 행 먼저 움직이고 그 다음 열 움직이는 방식임(문제에서 제시됨)
 * 
 * 충돌 여부 확인
 * 충돌한 좌표가 다르다면 다른 충돌로 취급되지만 같은 좌표면 여러 로봇이 충돌됐어도 하나로 취급함
 * 이를 위해 visited를 유지해서 한 번 충돌이 났으면 count하지 않는 방식으로 구현
 * 
 * while문 탈출 시점
 * 만약 while 조건에 탈출 조건을 걸어둔다면 맨 마지막에 발생하는 충돌을 계산하지 못 함
 * 1. 모든 로봇의 움직임이 종료했는지 확인
 * 2. 충돌 횟수 계산
 * 3. 각 로봇에 접근해서 움직임이 종료했는지 확인
 * 4. 로봇 이동
 * 5. 이동 포인트가 N번째 목적지인지 확인
 * 
 * 이런 식으로 구현이 된다면
 * 맨 마지막에 같은 시점에 목적지를 도달한다면 1번에서 종료 조건이 발동되므로 로봇들의 충돌이
 * 계산되지 못함 
 * 참고로 시작 지점부터 충돌로 취급되기 때문에 충돌 횟수 계산이 이동 전에 이루어져야 함
 * 그래서 올바른 흐름은 다음과 같음
 * 
 * 전체적인 흐름
 * 1. 충돌 횟수 계산
 * 2. 모든 로봇의 움직임이 종료했는지 확인
 * 3. 각 로봇에 접근해서 움직임 종료 확인
 * 4. 로봇 이동
 * 5. 이동 포인트가 N번째 목적지인지 확인
 */
class Solution {
    
    int [][] now;
    int [] indexOfDestination;
    int [][] points;
    int [][] routes;
    
    public int solution(int[][] points, int[][] routes) {    
        
        this.points = points;
        this.routes = routes;
        
        init();
        int answer = 0;
        
        while(true){
            
            answer += checkCollision();
            
            if(checkFinish()){
                break;
            }
            
            for(int i=0;i<routes.length;i++){
            
                if(indexOfDestination[i] == routes[0].length){
                    now[i][0] = -1 * i;
                    now[i][1] = -1;
                    continue;
                }
                
                int [] destination = getDestination(i);
                
                move(now[i], destination);
                if(checkArrive(now[i], destination)){
                    indexOfDestination[i] += 1;
                }
            }
        }
        
        return answer;
    }
    
    void init() {
        
        now = new int [routes.length][2];
        indexOfDestination = new int [routes.length];
        
        for(int i=0;i<routes.length;i++){
            
            now[i][0] = points[routes[i][0]-1][0];
            now[i][1] = points[routes[i][0]-1][1];
            
            indexOfDestination[i] = 1;
        }
    }
    
    boolean checkFinish(){
        
        for(int i=0;i<indexOfDestination.length;i++){
            
            if(indexOfDestination[i] < routes[0].length){
                return false;
            }
        }
        
        return true;
    }
    
    boolean checkArrive(int [] a, int [] b){
        
        for(int i=0;i<a.length;i++){
            if(a[i] != b[i]){
                return false;
            }
        }
        
        return true;
    }
    
    int [] getDestination(int index){
        
        int now = routes[index][indexOfDestination[index]];
        return points[now-1];
    }
    
    void move(int [] start, int [] end){

        
        if(start[0] < end[0]){
            start[0] += 1;
            return;
        }
            
        if(start[0] > end[0]){
            start[0] -= 1;
            return;
        }
            
        if(start[1] < end[1]){
            start[1] += 1;
            return;
        }
            
        if(start[1] > end[1]){
            start[1] -= 1;
            return;
        }
    }
    
    int checkCollision(){
        
        int count = 0;
        boolean [][] visited = new boolean [101][101];
        
        for(int i=0;i<now.length;i++){
            for(int j=i+1;j<now.length;j++){
                
                if(checkArrive(now[i], now[j]) && !visited[now[i][0]][now[i][1]]){
                    visited[now[i][0]][now[i][1]] = true;
                    count++;
                }
            }
        }
        
        return count;
    }
}