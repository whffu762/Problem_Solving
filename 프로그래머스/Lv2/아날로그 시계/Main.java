
/*
 * 시침, 분침, 초침의 속도 구해서 겹치면 count 늘리면 됨
 * 
 * 근데 문제는 각이 소숫점까지 존재하기 때문에 == 연산자로 비교할 수가 없음
 * - 현재 시침/분침의 위치와 1초 뒤의 위치
 * - 현재 초침의 위치와 1초뒤의 위치
 * 이 둘을 비교해서 현재는 초침 < 시침/분침 + 1초뒤 초침 > 시침/분침이면 겹치는 것으로 간주함
 * 
 * 이때 현재 기준으로 1초 뒤를 연산하기 때문에 end를 잘 계산해야 함
 * 반복문을 end까지 돌림으로써 end-1까지가 현재로 취급되도록 하고 초침과 다른 침을 비교할 때 1초 뒤의 위치가 같은 것까지 비교함
 * 
 * 또한 1초 뒤를 연산하기 때문에 이미 겹친 상태로 시작하는 경우에 대해선 검증을 하지 못함 때문에 그 부분은 따로 계산 
 * 
 * 시침과 분침의 위치가 같은 경우엔 -1 해줘야 함
 *  
 */
class Solution {
    
    int start;
    int end;
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        
        start = getSec(h1,m1,s1);
        end = getSec(h2,m2,s2);

        int answer = 0;
        if(start % 360 == 0){
            answer++;
        }
        
        for(int i=start;i<end;i++){
            answer += checkCross(i);
        }
        
        
        return answer;
    }
    
    int getSec(int h, int m, int s){
        
        return h*60*60 + m*60 + s;
    }
    
    int checkCross(int time){
        
        time %= 43200;
        
        double hourStart = (time / 120.0) % 360;
        double minStart = (time / 10.0) % 360;
        double secStart = (time * 6.0) % 360;
        
        if(++time > end){
            return 0;
        }
        double hourEnd = (time / 120.0) % 360;
        double minEnd = (time / 10.0) % 360;
        double secEnd = (time * 6.0) % 360;
        
        if(hourEnd == 0) hourEnd = 360;
        if(minEnd == 0) minEnd = 360;
        if(secEnd == 0) secEnd = 360;
        
        int count = 0;
        if(secStart < hourStart && secEnd >= hourEnd){
            count++;
        }
        
        if(secStart < minStart && secEnd >= minEnd){
            count++;
        }
        
        if(hourEnd == minEnd){
            count--;
        }
        
        return count;
    }
}