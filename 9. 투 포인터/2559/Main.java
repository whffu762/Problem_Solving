import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class solve2559{

    int numOfDays;
    int during;
    int [] eachTemp;

    solve2559(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        numOfDays = Integer.parseInt(st.nextToken());
        during = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        eachTemp = new int [numOfDays];
        for(int i=0;i<numOfDays;i++){
            eachTemp[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        int start = 0;
        int end = during;
        int result = Integer.MIN_VALUE;

        //for문으로 풀기
        for(int i=0;i<numOfDays-during+1;i++){
            int tmp = 0;
            for(int j=i;j<i+during;j++){
                tmp += eachTemp[j];
            }

            result = Math.max(result, tmp);
        }

        //while문으로 풀기
        while(end <= numOfDays){
            int tmp = 0;
            for(int i=start;i<end;i++){
                tmp += eachTemp[i];
            }
            result = Math.max(result, tmp);
            start++;
            end++;
        }

        System.out.println(result);
    }
}


public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2559 p = new solve2559(br);
        p.run();

        br.close();
    }
}