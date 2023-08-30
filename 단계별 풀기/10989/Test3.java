package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//10989
//Counting sort (계수 정렬)
//입력의 경우의 수가 작은 경우 사용하는 정렬
/*
 * 알고리즘
 * 입력의 경우의 수 만큼의 크기를 가지는 배열 A 생성
 * 입력이 저장되는 배열 B 생성
 * 결과를 저장할 배열 C 생성
 * 
 * 값이 들어오면 그 값이 입력ㄷ된 횟수를 카운트
 * 카운트가 완료되면 A를 이용해서 누적합을 구함
 * A에는 값이 0 이거나 값이 해당 값이 들어가야 할 마지막 index +1이 입력되게 됨
 * B에는 정렬해야 할 값들이 저장되어 있음
 * B를 index로 A에 접근 -> 나온 A의 값에 --연산 -> 연산 결과를 index로 C에 B 값 저장
 *  
 */
class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        int [] ary = new int [num];
        int [] cnt = new int [10001];
        int [] result = new int [num];
        
        int max = 10000;
        for(int i=0;i<num;i++){
            ary[i] = Integer.parseInt(br.readLine());
            cnt[ary[i]]++;
            max = Math.max(max, ary[i]);
        }

        for(int i=1;i<=max;i++){
            cnt[i] += cnt[i-1];
        }

        for(int i=0;i<num;i++){
            result[--cnt[ary[i]]] = ary[i];
        }

        for(int x : result){
            sb.append(x).append("\n");
        }


        System.out.println(sb);
        br.close();
    }    
    
}