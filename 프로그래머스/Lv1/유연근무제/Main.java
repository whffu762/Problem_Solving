/*
schedules : 희망 출근 시간
timelogs : 출근 기록
startday : 시작 날짜 1 ~ 7 1이 월요일

주의할 점은 요일 판단
0 ~ 6까지 그냥 월화수목금토일인줄 알았는데 1일차 2일차.. n일차 였음 
그래서 시작일로부터 주말을 판단하는 로직이 필요함 단순히 0부터 시작해서 순환하는 수열에서
5 6을 판단하기만 하면 됨
*/

class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        
        int answer = 0;
        for(int i=0;i<schedules.length;i++){
            
            if(checkTime(timelogs[i], schedules[i] + 10, startday)){
                answer++;
            }            
        }
        
        return answer;
    }
    
    boolean checkTime(int [] timelog, int purpose, int startday){
        
        int limit = getTime(purpose);
        for(int i=0;i<7;i++){
            
            int day = (startday + i - 1) % 7;
            if(day == 5 || day == 6){
                continue;
            }
            
            if(limit < timelog[i]){
                return false;
            }
        }
        
        return true;
    }
    
    int getTime(int before) {
        
        int hour = before / 100;
        int min = before % 100;
        if(min >= 60) {
            hour += min / 60;
            min %= 60;
        }
        
        return hour * 100 + min;
    }
}