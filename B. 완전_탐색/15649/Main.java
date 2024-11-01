import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 첫번째
 * 1~N 중 중복 없이 M의 개의 숫자로 순열 만들면 됨 백트랙킹 이용의 기본
 */
class solve15649{

    int num;
    int pick;
    StringBuilder sb = new StringBuilder();
    int [] select;
    boolean [] visited;

    solve15649 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());


        select = new int [pick];
        visited = new boolean [num+1]; 
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

            if(!visited[i]){
                
                visited[i] = true;
                select[pointer] = i;
                select(pointer + 1);
                visited[i] = false;
            }
        }
    }
}


public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15649 p = new solve15649(br);
        p.run();

        br.close();
    }
}