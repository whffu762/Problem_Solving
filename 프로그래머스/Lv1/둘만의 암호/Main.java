import java.lang.StringBuilder;

class Solution {
    public String solution(String s, String skip, int index) {
        
        boolean [] ban = new boolean[26];
        for(int i=0;i<skip.length();i++){
            
            int c = skip.charAt(i) - 97;
            ban[c] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<s.length();i++){
            
            int decode = s.charAt(i) - 97;
            for(int j=0;j<index;j++){
                
                decode = ++decode % 26;
                if(ban[decode]){
                    j--;
                }
            }
            sb.append((char) (decode + 97));
        }
        
        return sb.toString();
    }
}