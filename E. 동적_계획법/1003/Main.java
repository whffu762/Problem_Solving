import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 간단하 동적 계획법
 * 점화식만 구하면 풀리는 문제
 */
public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int [][] tmp = new int [41][2];

        tmp [0][0] = 1;
        tmp [0][1] = 0;
        tmp [1][0] = 0;
        tmp [1][1] = 1;

        for(int i=2;i<41;i++){
            tmp[i][0] = tmp [i-1][0] + tmp[i-2][0];
            tmp[i][1] = tmp [i-1][1] + tmp[i-2][1];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){

            int now = Integer.parseInt(br.readLine());
            sb.append(tmp[now][0] + " " + tmp[now][1]).append("\n");
        }
        System.out.println(sb);
        
        br.close();
    }
}