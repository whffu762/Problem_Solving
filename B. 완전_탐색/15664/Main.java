import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 10
 * 
 * 9에서 했던 것에서 비내림차순으로 바꾸기만 하면 됨
 * 이전에 하던데로 정렬된 index 상태에서 시작 인덱스만 바꿔주면 됨
 */
class solve15664 {

    int num;
    int pick;
    int [] select;
    int [] value;
    boolean [] visitedIdx;

    StringBuilder sb = new StringBuilder();

    solve15664(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());

        value = new int [num];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(value);

        visitedIdx = new boolean[num];

        select = new int [pick];
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

        int before = 0;
        for(int i=start;i<num;i++){

            int now = value[i];

            if(!visitedIdx[i] && before != now){

                before = now;
                visitedIdx[i] = true;
                
                select[pointer] = now;
                select(i+1, pointer + 1);
                
                visitedIdx[i] = false;
            }
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15664 p = new solve15664(br);
        p.run();

        br.close();
    }
}