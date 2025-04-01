/*
 * 투포인터를 활용한 문제 최댓 갯수가 100만이기 때문에 완전 탐색을 수행하게 되면 시간 초과가 발생함(100만의 제곱)
 * 
 * 근데 코드가 좀 더러움
 * 조건문이 너무 많은데 start 혹은 end가 증가한 후 sequence보다 작음을 보장할 수 없기 때문에 그 유효성을 검사하는 작업이
 * 포함되야만 함 이를 깔끔하게 정리한게 Main2.java
 */
class Solution {
    public int [] solution(int[] sequence, int k) {

        int [] answer = new int [2];

        int sum = sequence[0];
        int start = 0;
        int end = 0;
        boolean flag = true;
        int size = Integer.MAX_VALUE;

        while(start <= end){

            if(sum == k){

                int nowSize = end - start;
                if(nowSize < size){
                    size = nowSize;
                    answer[0] = start;
                    answer[1] = end;   
                }
            }

            if(sum > k){
                flag = true;
                start++;
            } else {
                flag = false;
                end++;
            }

            if(start == sequence.length || end == sequence.length){
                break;
            }

            if(flag){
                sum -= sequence[start-1];
            } else {
                sum += sequence[end];
            }
        }

        return answer;   
    }
}