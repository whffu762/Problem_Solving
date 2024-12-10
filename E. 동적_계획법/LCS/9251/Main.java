import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * LCS 기본 문제
 *
 * DP 흐름
 * 같으면 대각선 뒤에 값 + 1
 * 다르면 위와 왼쪽 중 큰 값을 유지
 * 
 * 주목해야 할 특징
 * - 단순히 길이만 유지하며 되서 따로 BFS를 수행할 필요는 없음
 * - 행과 열에 0 패딩이 필요함 lcs[i-1][j-1] 이걸 수행해야 해서 그게 더 편함
 */
class solve9251{

    int [][] lcs;
    String str1;
    String str2;

    solve9251 (BufferedReader br) throws IOException{

        str1 = br.readLine();
        str2 = br.readLine();
        lcs = new int [str1.length()+1][str2.length()+1];
    }

    void run(){

        for(int i=1;i<str1.length()+1;i++){

            for(int j=1;j<str2.length()+1;j++){

                if(str1.charAt(i-1) == str2.charAt(j-1)) lcs[i][j] = lcs[i-1][j-1] + 1;
                else lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
            }
        }

        System.out.println(lcs[str1.length()][str2.length()]);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9251 p = new solve9251(br);
        p.run();

        br.close();
    }
}