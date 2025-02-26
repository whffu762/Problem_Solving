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
        
        return now.replaceAll("[^0-9a-z-_.]", "");
    }
    
    String step3(String now){
        
        return now.replaceAll("[.]+", ".");
    }
    
    String step4(String now){
        
        return now.replaceAll("^[.]|[.]$", "");
    }
    
    String step5(String now){
        
        return now.equals("") ? "a" : now;
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