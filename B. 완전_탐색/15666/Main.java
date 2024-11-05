import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 12번째
 * 
 * 1. 비내림차순
 * 2. 수열 요소 중복 가능
 * 3. 수열 자체는 중복 불가
 * 4. 배열의 요소가 제공됨
 * 
 * pointer 유지 -> 수열을 원하는 갯수만큼 선택
 * start 유지 -> 수열을 비내림차순 보장
 * 재귀 내 반복을 start부터 시작 -> 수열 요소 중복 가능
 * before 유지 -> 수열 자체 중복 제거
 */
class solve15666 {

    int num;
    int pick;
    int [] value;
    int [] select;
    StringBuilder sb = new StringBuilder();

    solve15666 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());

        value = new int [num];
        select = new int [pick];

        st = new StringTokenizer(br.readLine());    
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(value);
    }

    void run(){

        select(0, 0);
        System.out.println(sb);
    }

    void select(int start, int pointer){
        
        if(pointer == pick){

            for(int i : select){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for(int i=start;i<num;i++){
            
            int now = value[i];
            if(before != now){

                before = now;
                select[pointer] = now;
                select(i, pointer+1);
            }
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15666 p = new solve15666(br);
        p.run();

        br.close();
    }
}