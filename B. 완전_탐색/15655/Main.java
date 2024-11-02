import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 6
 * 
 * 두 가지를 신경쓰면 됨
 * - 재귀 호출과 재귀 호출의 시작점 제어
 * - 배열이 따로 존재함
 * 
 * 고른 수열이 오름차순이어야 하기 때문에 시작점을 재귀 호출의 파라미터로 넘김
 * 배열이 따로 존재하기 때문에 반복되는 값을 인덱스로 취급
 */
class solve15655 {

    int num;
    int pick;
    StringBuilder sb = new StringBuilder();
    int [] select;
    int [] value;

    solve15655(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        value = new int [num];
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
        select = new int [pick];
        Arrays.sort(value);
    }

    void run(){

        select(0, 0);
        System.out.println(sb);
    }

    void select(int start, int pointer){

        if(pointer == pick){
            for(int s : select){
                sb.append(s).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start;i<num;i++){

            select[pointer] = value[i];
            select(i + 1, pointer + 1);
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15655 p = new solve15655(br);
        p.run();

        br.close();
    }
}