import java.util.Arrays;

/*
 * 26진수로 상호 변환만 하면 됨 근데 그게 어려움;
 * 
 * 0부터 시작하냐 1부터 시작하냐의 문제인데..
 * 0부터 시작할 경우 aa aaa 같이 첫번째 문자가 여럿인 경우를 구분하지 못 함 전부 0으로 처리되기 때문
 * 그래서 1부터 시작해야 함
 * 
 * 1부터 시작할 때 문제점은 숫자를 문자로 변환할 때 나머지가 0일 경우에 발생함 0에 해당하는 숫자가 존재하지 않기 때문에
 * 치환할 수가 없음 이를 해결하기 위해 0일 경우엔 몫에서 1을 빼고 26으로 취급하는 방식을 이용
 * 
 * 문자를 숫자로 치환할 땐 진법 변환과 동일한 방식으로 진행
 * 
 */
class Solution {
    
    public String solution(long n, String[] bans) {
        
        Arrays.sort(bans, (after, before) -> {
            
            if(after.length() == before.length()) {
                return after.compareTo(before);
            }
            
            return after.length() - before.length();
        });

        for(String ban : bans){
            
            long index = getIndex(ban);
            if(index <= n) {
                n++;
            }
        }
    
        String answer = getString(n);    
        return answer;
    }
    
    long getIndex(String ban) {
        
        long result = 0L;
        for(int i=0;i<ban.length();i++){
                
            char c = ban.charAt(i);
            result += (long) Math.pow(26, ban.length() - i - 1) * (int) (c - 97 + 1);
        }
        
        return result;
    }
    
    String getString(long target) {
        
        StringBuilder sb = new StringBuilder();
        while(target != 0){
            
            long share = target / 26;
            Long remain = target % 26;
                
            if(remain == 0){
                
                share--;
                remain = 26L;
            }
            
            target = share;
            
            char c = (char) (97 + remain.intValue() - 1);
            sb.append(String.valueOf(c));
        }
        
        return sb.reverse().toString();
    }
}