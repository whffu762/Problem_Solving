class Solution {
    
    int count = 0;
    int [] code = new int [5];
    public int solution(int n, int[][] q, int[] ans) {
        
        makeCode(0, n, q, ans);
        return this.count;
    }
    
    void makeCode(int length, int n, int [][] q, int [] ans){
    
        if(length == 5){
        
            for(int i=0;i<q.length;i++){
                if(!checkValid(q[i], ans[i])){
                    return;
                }
            }
            
            this.count++;   
            return;
        }
        
        int start = 1;
        if(length != 0){
            start += code[length-1];
        }
        
        for(int i=start;i<n+1;i++){
            code[length] = i;
            makeCode(length+1, n, q, ans);
        }
    }
    
    boolean checkValid(int [] question, int ans){
    
        int count = 0;
        for(int i=0;i<code.length;i++){
            for(int j=0;j<question.length;j++){
                
                if(code[i] < question[j]){
                    break;
                }
                
                if(code[i] == question[j]){
                    count++;
                }
            }
        }
        
        if(count == ans){
            return true;
        }
        
        return false;
    }
}