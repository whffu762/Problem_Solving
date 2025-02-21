/**
 * 택배 상자 개수 : n
 * 열의 크기 : w
 * target : num
 * 
 * 동작 흐름
 * 1. 짝수열과 홀수열에 따라 다르게 배열에 저장 
 * 2. 원하는 값을 찾음
 * 3. 그 행부터 마지막 행이거나 0이 나올때 까지 증가
*/

class Solution {
    public int solution(int n, int w, int num) {
        
        int floor = n / w + 1;
        int [][] stack = new int [floor][w];
    
        stackBox(stack, n);
        int answer = getTarget(stack, num);
        
        return answer;
    }
    
    void stackBox(int [][] stack, int n){
        
        int count = 1;
        int i = 0;
        int j = 0;
        
        while(count <= n) {
            
            if(i % 2 == 0){
                stack[i][j] = count++;
            } else {
                stack[i][stack[0].length-j-1] = count++;
            }
            
            j++;
            if(j == stack[0].length){
                j = 0;
                i++;
            }
        }
    }
    
    int getTarget(int [][] stack, int num){
        
        int row;
        int col;
        int i = 0;
        int j = 0;
        
        while(true){
            
            if(stack[i][j] == num){
                row = i;
                col = j;
                break;
            }
            
            j++;
            if(j == stack[0].length){
                j = 0;
                i++;
            }
        }
        
        int result = 1;
        while(++row < stack.length){
            
            if(stack[row][col] == 0){
                break;
            }
            
            result++;
        }
        return result;
    }
}

public class Main {

    public static void main(String [] args) {

        Solution p = new Solution();
        p.solution(2, 1, 1);
    }
}