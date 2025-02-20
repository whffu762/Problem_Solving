import java.util.Arrays;

class Solution {
    public int solution(int[] wallet, int[] bill) {
        
        int answer = 0;
        Arrays.sort(wallet);
        
        while(true) {
               
            Arrays.sort(bill);
            if(checkPutIn(wallet, bill)){                
                break;
            }
            
            answer++;
            bill[1] /= 2;
        }
        
        return answer;
    }
    
    boolean checkPutIn(int [] wallet, int [] bill){
        
        if(wallet[0] >= bill[0] && wallet[1] >= bill[1]){
            return true;
        }
        
        return false;
    }
}