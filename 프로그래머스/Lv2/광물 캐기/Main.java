import java.util.Arrays;

/*
 * 곡괭이를 선택하면 반드시 5개를 연속해서 캐야하기 때문에 5개마다 구성되는 광물들을 세서 해당 구역에 지정할 곡괭이를 정해야 함
 * 제일 좋은 곡괭이로는 가장 캐기 힘든 구역을 할당하는게 좋음
 * 다이아 - 쇠 - 돌 이 많은 순으로 내림차순 정렬하면 됨
 * 
 * 주의할 점은 도구의 갯수 * 5보다 광물의 갯수가 더 많은 경우인데 모든 광물은 순차적으로 캐야하기 때문에 index를 순차적으로 늘려가면서
 * 곡괭이 갯수보다 커지면 중단하는 식으로 구역을 나눠야 함
 */
class Solution {
    public int solution(int[] picks, String[] minerals) {
        
        int totalAmount = picks[0] + picks[1] + picks[2];
        int [][] attribute = new int [totalAmount][3];
        
        for(int i=0;i<totalAmount;i++){
            for(int j=0;j<5;j++){
                
                int index = i * 5 + j;
                
                if(index >= minerals.length){
                    break;
                }
                
                if(minerals[index].equals("diamond")){
                    attribute[i][0]++;
                }
                
                if(minerals[index].equals("iron")){
                    attribute[i][1]++;
                }
                
                if(minerals[index].equals("stone")){
                    attribute[i][2]++;
                }
            }
        }
        
        Arrays.sort(attribute, (after, before) -> {
            
            if(after[0] == before[0]){
                if(after[1] == before[1]){
                    return before[2] - after[2];
                }
                return before[1] - after[1];
            }
            return before[0] - after[0];
        });
        
        int answer = 0;
        int index = 0;
        
        for(int i=0;i<picks.length;i++){
            for(int j=0;j<picks[i];j++){
                answer += mine(i, attribute[index++]);
            }
        }
    
        return answer;
    }
    
    int mine(int tool, int [] minerals){
        
        int sum = 0;
        
        if(tool == 0){
            sum = minerals[0] + minerals[1] + minerals[2];
        }
        
        if(tool == 1){
            sum = minerals[0] * 5 + minerals[1] + minerals[2];
        }
        
        if(tool == 2){
            sum = minerals[0] * 25 + minerals[1] * 5 + minerals[2];
        }
        
        return sum;
    }
}