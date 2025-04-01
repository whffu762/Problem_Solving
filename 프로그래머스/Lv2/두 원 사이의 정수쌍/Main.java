/*
 * y 좌표만 구해서 가능한 것만 계산하면 됨
 * 내부의 원과 외부의 원 사이의 좌표를 구하는 것이기 때문에
 * 내부에 있는 원은 올림을 해야 가능한 정수고 외부에 있는 원은 내림을 해야 가능한 정수임
 */
class Solution {
    public long solution(int r1, int r2) {
        
        long answer = 0;
        double small = Math.pow(r1, 2);
        double big = Math.pow(r2, 2);
        
        for(int i=1;i<r2+1;i++){
            
            if(i < r1){
                answer += Math.floor(getY(i, big)) - Math.ceil(getY(i, small)) + 1;
            } else {
                answer += Math.floor(getY(i, big)) + 1;
            }
        }
        
        return answer * 4;
    }
    
    double getY(int x, double r){
        
        return Math.sqrt(r - Math.pow(x, 2));
    }
}