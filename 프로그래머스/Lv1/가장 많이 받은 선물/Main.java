import java.util.Map;
import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        
        Map<String, Integer> indexOfPerson = new HashMap<>();
        for(int i=0;i<friends.length;i++){
            indexOfPerson.put(friends[i], i);
        }
        
        int [][] record = new int [friends.length][friends.length];
        for(int i=0;i<gifts.length;i++){
            
            String [] gift = gifts[i].split(" ");
            int sender = indexOfPerson.get(gift[0]);
            int receiver = indexOfPerson.get(gift[1]);
            
            record[sender][receiver]++;
        }
        
        int [] score = new int [record.length];
        initScore(record, score);
        
        int [] nextMonth = new int [record.length];
        for(int i=0;i<record.length;i++){
            for(int j=i+1;j<record.length;j++){
                
                if(i == j){
                    continue;
                }
                
                int biggerFriend = compare(record, i, j);
                if(biggerFriend == -1){
                    
                    biggerFriend = compare(score, i, j);
                    if(biggerFriend != -1){
                        nextMonth[biggerFriend]++;
                    }
                } else {
                    nextMonth[biggerFriend]++;
                }
            }    
        }
        
        int answer = 0;
        for(int i : nextMonth){
            answer = Math.max(answer, i);
        }
        
        return answer;
    }
    
    void initScore(int [][] record, int [] score){
        
        for(int i=0;i<record.length;i++){
            
            int numOfSend = 0;
            int numOfReceived = 0;
            for(int j=0;j<record.length;j++){
                
                numOfSend += record[i][j];
                numOfReceived += record[j][i];
            }
            
            score[i] = numOfSend - numOfReceived;
        }
    }
    
    int compare(int [] score, int value1, int value2){
        
        if(score[value1] - score[value2] > 0){
            return value1;
        } else if(score[value2] - score[value1] > 0){
            return value2;
        } else {
            return -1;
        }
    }
    
    int compare(int [][] record, int value1, int value2){
        
        if(record[value1][value2] - record[value2][value1] > 0){
            return value1;
        } else if(record[value2][value1] - record[value1][value2] > 0){
            return value2;
        } else {
            return -1;
        }
    }
}