import java.lang.StringBuilder;

class Solution {
    public String solution(String new_id) {
        
        String answer = step1(new_id);
        answer = step2(answer);
        answer = step3(answer);
        answer = step4(answer);
        answer = step5(answer);
        answer = step6(answer);
        answer = step7(answer);
        
        return answer;
    }
    
    String step1(String now){
        
        return now.toLowerCase();
    }
    
    String step2(String now){
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<now.length();i++){
            
            char c = now.charAt(i);
            if((int) c > 47 && (int) c < 58){
                sb.append(c);
            }
            
            if((int) c > 96 && (int) c < 123){
                sb.append(c);
            }
            
            if(c == '-' || c == '_' || c == '.'){
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
    
    String step3(String now){
        
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        
        for(int i=0;i<now.length();i++){
            
            char c = now.charAt(i);
            if(c == '.'){
                
                if(!flag){
                    sb.append(c);
                }
                flag = true;
                continue;
            } 
            
            flag = false;
            sb.append(c);
        }
        
        return sb.toString();
    }
    
    String step4(String now){
        
        int start = 0;
        int end = now.length();
        
        if(now.charAt(start) == '.'){
            start++;
        }
        
        if(now.charAt(end-1) == '.'){
            end--;
        }
        
        if(start > end){
            return null;
        }
        return now.substring(start, end);
    }
    
    String step5(String now){
        
        if(now == null){
            return "a";
        }
        
        return now;
    }
    
    String step6(String now){
        
        if(now.length() > 15){
            return step4(now.substring(0, 15));
        }
        
        return now;
    }
    
    String step7(String now){
        
        StringBuilder sb = new StringBuilder(now);
        int count = now.length();
        while(count < 3){
            sb.append(now.charAt(now.length()-1));
            count++;
        }
        
        return sb.toString();
    }
}