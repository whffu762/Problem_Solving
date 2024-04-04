import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 거꾸로해도 같은 경우를 구해야 함 예를 들어 ABA, ABCBA, ABBA, AAA 등 ..
 * (전체 갯수 - 거꾸로해도 같은 갯수) / 2 + 거꾸로해도 같은 갯수
 * 
 * 전체 경우의 수는 이전에 구했고 거꾸로 해도 같은 갯수의 규칙을 찾아야 함
 * 
 * 거꾸로 해도 같은 것이 나오는 경우는 아래와 같음
 *  - 1짜리 두 개가 양 사이드로 붙는 경우(이 블럭을 A라 하자)
 *  - 2짜리 두 개가 양 사이드로 붙는 경우(이 블럭을 B라 하자)
 *  - 다른 2짜리 두 개가 양 사이드로 붙는 경우(이 블럭을 C라 하자)
 * 
 * 때문에 그 사이에 들어갈 수 있는 것들은 N-2 와 N-4의 값들임
 * 예를 들어 6인 경우
 * A xxxx A
 * B xx B
 * C xx C
 * 이렇게 3가지가 가능함
 * xxxx에는 N이 4에 해당하는 거꾸로해도 같은 경우가 들어갈 수 있고
 * xx에는 N이 2에 해당하는 거꾸로해도 같은 경우가 들어감
 * 
 * 때문에 X[n] = X[n-2] + x[n-4] * 2 가 성립됨
 */

class solve1720{

    int input;
    long [] all;
    long [] duplicate;

    solve1720(BufferedReader br) throws IOException{
        
        input = Integer.parseInt(br.readLine());
        all = new long [input + 3];
        all[1] = 1;
        all[2] = 3;

        duplicate = new long [input + 5];
        duplicate[1] = 1;
        duplicate[2] = 3;
        duplicate[3] = 1;
        duplicate[4] = 5;
    }

    void run(){

        for(int i=3;i<input+1;i++){

            all[i] = all[i-2] * 2 + all[i-1];
        }

        for(int i=5;i<input+1;i++){
            duplicate[i] = duplicate[i-2] + duplicate[i-4] * 2;
        }
        
        long result = (all[input] - duplicate[input]) / 2 + duplicate[input];
        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1720 p = new solve1720(br);
        p.run();

        br.close();
    }
}