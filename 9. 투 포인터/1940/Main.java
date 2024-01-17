import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * 완전 탐색
 * 모든 경우의 수를 전부 계산하는 간단한 방식
 * 
 * 투 포인터
 * 배열을 오름차순 정렬해두고 최솟값과 최댓값부터 시작해서 조건에 따라 포인터를 달리하는 방식
 * - tmp가 목표와 일치하면 두 포인터 모두 이동
 * - tmp가 목표보다 작으면 tmp 값을 늘려야 함
 * - tmp가 목표보다 크면 tmp 값을 낮춰야 함
 */


class solve1940{

    int numOfInput;
    int standard;
    int [] inputs;

    int bruteForce(){

        int result = 0;
        for(int i=0;i<numOfInput-1;i++){
            for(int j=i+1;j<numOfInput;j++){
                if(inputs[i] + inputs[j] == standard){
                    result++;
                }
            }
        }

        return result;
    }

    int twoPointer(){

        Arrays.sort(inputs);
        int i = 0;
        int j = numOfInput - 1;
        int result = 0;

        while(i<j){
            int tmp = inputs[i] + inputs[j];
            if(tmp == standard){
                i++;
                j--;
                result++;
            }
            else if(tmp < standard){
                i++;
            }
            else if(tmp > standard){
                j--;
            }
        }


        return result;
    }

    void run(BufferedReader br) throws IOException{

        numOfInput = Integer.parseInt(br.readLine());
        standard = Integer.parseInt(br.readLine());
        
        inputs = new int [numOfInput];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfInput;i++){
            inputs[i] = Integer.parseInt(st.nextToken());
        }
        
        //int result = bruteForce();
        int result = twoPointer();

        System.out.println(result);

    }
}


public class Main{
    
    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1940 p = new solve1940();
        p.run(br);

        br.close();
    }
}