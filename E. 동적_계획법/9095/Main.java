import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * DP 문제
 * 사실상 규칙 찾기 문제;;
 * 
 * 1
 * 
 * 1 + 1
 * 2
 * 
 * 1 + 1 + 1
 * 1 + 2
 * 2 + 1
 * 3
 * 
 * 1 + 3
 * 1 + 1 + 2
 * 2 + 2
 * 1 + 1 + 1 + 1
 * 1 + 2 + 1
 * 2 + 1 + 1
 * 3 + 1
 * 
 * 1 + 1 + 3
 * 2 + 3
 * 1 + 1 + 1 + 2
 * 1 + 2 + 2
 * 2 + 1 + 2
 * 3 + 2
 * 1 + 3 + 1
 * ..
 * 
 * 이런 식으로 1, 2, 3의 합으로 이루어지기 때문에 앞선 3개의 경우의 수를 합친 것과 동일함
 * 
 * 
 */
public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());
        int [] tmp = new int [11];
        tmp[1] = 1;
        tmp[2] = 2;
        tmp[3] = 4;

        for(int i=4;i<11;i++){
            tmp[i] = tmp[i-1] + tmp[i-2] + tmp[i-3];
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){
            int target = Integer.parseInt(br.readLine());
            sb.append(tmp[target]).append("\n");
        }
        System.out.println(sb);
        br.close();
    }
}