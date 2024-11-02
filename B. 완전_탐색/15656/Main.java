import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 7
 * 
 * 배열이 따로 존재하기 때문에 반복되는 값을 인덱스로 취급
 */
class solve15656 {

    int num;
    int pick;
    StringBuilder sb = new StringBuilder();
    int [] select;
    int [] value;

    solve15656(BufferedReader br) throws IOException{

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

        select(0);
        System.out.println(sb);
    }

    void select(int pointer){

        if(pointer == pick){
            for(int s : select){
                sb.append(s).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<num;i++){

            select[pointer] = value[i];
            select(pointer + 1);
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15656 p = new solve15656(br);
        p.run();

        br.close();
    }
}