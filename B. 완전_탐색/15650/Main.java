import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 두번째
 * 순열이 중복없는 오름차순이라 반복문이 시작되는 숫자가 이전 선택의 영향을 받음
 * 그것도 재귀 호출의 파라미터로 넘겨주면 됨
 */
class solve15650{

    int num;
    int pick;
    StringBuilder sb = new StringBuilder();
    int [] select;
    boolean [] visited;

    solve15650 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());


        select = new int [pick];
        visited = new boolean [num+1]; 
    }

    void run(){

        select(1, 0);
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

        for(int i=start;i<num+1;i++){

            if(!visited[i]){
                
                visited[i] = true;
                select[pointer] = i;
                select(i+1, pointer + 1);
                visited[i] = false;
            }
        }
    }
}


public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15650 p = new solve15650(br);
        p.run();

        br.close();
    }
}