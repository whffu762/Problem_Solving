import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 2579 계단 오르기와 비슷한 문제
 * 
 * i-2번째 값과 i-1번째 값 중 최댓값을 이용하는 방식
 * 막상 들으면 쉬운데 생각해내기가 쉽지 않은듯
 */
class solve9465{

    int size;
    int [] stamp1;
    int [] stamp2;

    solve9465 (BufferedReader br) throws IOException{

        size = Integer.parseInt(br.readLine());
        stamp1 = new int [size];
        stamp2 = new int [size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++) stamp1[i] = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<size;i++) stamp2[i] = Integer.parseInt(st.nextToken());
    }

    int run(){

        if(size != 1){

            stamp1[1] += stamp2[0];
            stamp2[1] += stamp1[0];
    
            for(int i=2;i<size;i++){
    
                int tmp = Math.max(stamp1[i-2], stamp2[i-2]);
                stamp1[i] += Math.max(stamp2[i-1], tmp);
                stamp2[i] += Math.max(stamp1[i-1], tmp);
            }
        }
        
        return Math.max(stamp1[size-1], stamp2[size-1]);
    }   
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        for(int i=0;i<num;i++){

            solve9465 p = new solve9465(br);
            sb.append(p.run()).append("\n");
        }
        
        System.out.println(sb);
        br.close();
    }
}