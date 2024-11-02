import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 3
 * 
 * 재귀 호출만 잘 이용할줄 알면 됨
 */
class solve15651 {

    int num;
    int pick;
    StringBuilder sb = new StringBuilder();
    int [] select;
    
    solve15651(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());

        select = new int [pick];
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

        for(int i=1;i<num+1;i++){

            select[pointer] = i;
            select(pointer + 1);
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15651 p = new solve15651(br);
        p.run();

        br.close();
    }
}