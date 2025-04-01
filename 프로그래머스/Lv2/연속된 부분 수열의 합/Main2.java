/*
 * start 혹은 end가 sequence의 범위를 벗어나는 경우는 start ~ end까지의 값이 k보다 적어서 end + 1이 되는 경우
 * 밖에 없는데 이럴 경우 항상 end만 증가함 범위 내에선 start는 end를 따라가기만 하는 구조임 그래서 end를 증가시킬 때만
 * 검사를 수행해주면 됨 이렇게 하면 코드가 간단해짐
 * 
 * start가 end를 따라가기만 하면 start <= end는 왜 씀?
 * 1 2 3 10 12 k=5 인 경우 범위를 벗어나지 않은 경우에 한해선 start가 end를 앞서게 됨 이를 위해서 start <= end 조건은
 * 포함시켜둠
 * 
 *  
 */
class Solution {
    public int [] solution(int[] sequence, int k) {
        
        int [] answer = new int [2];
        
        int sum = sequence[0];
        int start = 0;
        int end = 0;
        int size = Integer.MAX_VALUE;
        
        while(start <= end && end < sequence.length){
            
            if(sum == k){
                
                int nowSize = end - start;
                if(nowSize < size){
                    size = nowSize;
                    answer[0] = start;
                    answer[1] = end;   
                }
            }
            
            if(sum > k){
                sum -= sequence[start++];
            } else {
                if(++end < sequence.length){
                    sum += sequence[end];   
                }
            }
        }
        
        return answer;   
    }
}