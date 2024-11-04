import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 11번째
 * 
 * - 인덱스 중복은 허용
 * - 동일한 구성의 수열 허용 안함
 * 
 * before만 유지하고 재귀 호출의 파라미터로는 pointer만 넘김
 * 
 */
class solve15665{

    int num;
    int pick;
    int [] value;
    int [] select;
    StringBuilder sb = new StringBuilder();

    solve15665 (BufferedReader br) throws IOException{

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

        select(0);
        System.out.println(sb);

    }

    void select (int pointer){

        if(pointer == pick){

            for(int i : select){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for(int i=0;i<num;i++){

            int now = value[i];
            if(before != now ){
                before = now;
                select[pointer] = now;
                select(pointer + 1);
            }
        }
    }
}

public class Main{

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15665 p = new solve15665(br);
        p.run();

        br.close();
    }
}