import java.util.Arrays;

/*
 * 그리드를 이용한 풀이
 * 가장 먼저 끝나는 지점 순으로 정렬하고
 * 끝나는 지점-0.5가 그 다음 지점과 겹치면 마저 진행하고 겹치지 않으면 미사일이 하나 추가되야 함
 * 이때 맨 마지막은 항상 계산되지 않으므로 1부터 시작해야 함 
 */
class Solution {
    public int solution(int[][] targets) {
        
        int answer = 1;
        Arrays.sort(targets, (after, before) -> {
            if(after[1] == before[1]){
                return after[0] - before[0];
            }
            return after[1] - before[1];
        });
        
        int i = 0;
        while(i < targets.length){
            
            double point = targets[i][1] - 0.5;;
            while(++i < targets.length){
                
                if(point < targets[i][0]){
                    answer++;
                    break;
                }
            }
        }        
        
        return answer;
    }
}