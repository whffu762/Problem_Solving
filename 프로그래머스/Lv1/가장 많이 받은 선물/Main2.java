import java.util.Map;
import java.util.HashMap;

/*
 * 매우 리팩토링이 잘 된 코드
 * score를 record 초기화하면서 동시에 수행함
 * 보낸거 - 받은거니까 보낸 사람은 +1하고 받은 사람은 -1 하면 됨
 * 
 * 비교의 간소화
 * i와 j를 모두 0부터 시작해서 각각에 대해 cnt를 구하는 방식
 * 
 */
class Solution {
    public int solution(String[] friends, String[] gifts) {
        
        Map<String, Integer> indexOfPerson = new HashMap<>();
        for(int i=0;i<friends.length;i++){
            indexOfPerson.put(friends[i], i);
        }
        
        int [][] record = new int [friends.length][friends.length];
        int [] score = new int [record.length];
        for(int i=0;i<gifts.length;i++){
            
            String [] gift = gifts[i].split(" ");
            int sender = indexOfPerson.get(gift[0]);
            int receiver = indexOfPerson.get(gift[1]);
            
            record[sender][receiver]++;
            score[sender]++;
            score[receiver]--;
        }
                
        int answer = 0;
        for(int i=0;i<record.length;i++){
            
            int cnt = 0;
            for(int j=0;j<record.length;j++){
                
                if(i == j) {
                    continue;
                }
                
                if(record[i][j] > record[j][i]){
                    cnt++;
                } else if(record[i][j] == record[j][i] && score[i] > score[j]){
                    cnt++;
                }
            }
            
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
}