class Solution {
    
    int [] diffs;
    int [] times;
    public int solution(int[] diffs, int[] times, long limit) {
        
        this.diffs = diffs;
        this.times = times;
        
        int answer = 0;
        int start = 1;
        int end = 0;
        for(int i=0;i<diffs.length;i++){
            end = Math.max(end, diffs[i]);
        }
        //end++

        // while(start < end){
            
        //     int mid = (start + end) / 2;
        //     long now = calTime(mid);
            
        //     if(now > limit){
        //         start = mid + 1;
        //     } else {
        //         answer = mid;
        //         end = mid;
        //     }
        // }
        
        while(start <= end){
            
            int mid = (start + end) / 2;
            long now = calTime(mid);
            
            if(now > limit){
                start = mid + 1;
            } else {
                answer = mid;
                end = mid - 1;
            }
        }

        return answer;
    }
    
    long calTime(int level){
        
        int prev = 0;
        long time = 0L;
        for(int i=0;i<diffs.length;i++){
            
            if(level < diffs[i]){
                time += (times[i] + prev) * (diffs[i] - level) + times[i];
            } else {
                time += times[i];
            }
            
            prev = times[i];
        }
        
        return time;
    }
}