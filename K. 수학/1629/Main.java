import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

/*
 * 수학을 알아야 함
 * - 지수 법칙
 * - 나머지 분배 법칙
 * 
 * 지수 법칙 : a^x * a^y = a^(x + y)
 * 나머지 분배 법칙 : (a % x * b % y) % y = (a * b) % y
 * 
 * 이 둘을 이용해서 거듭 제곱을 반씩 나눠가면서 연산 이를 재귀 호출을 이용해서 구현하면 됨
 * 
 * 그 외
 * 나머지 최댓값이 약 21억이고 제곱이 최대 약 21억이라 그냥 평번한 자료형으로는 오버플로우 발생함
 * BigDecimal로 연산해야 함
 * 
 */
public class Main {

    static int a;
    static int b;
    static int c;

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        System.out.println(cal(b));

        br.close();
    }

    static int cal(int now){

        if(now == 1) return a % c;

        BigDecimal tmp = BigDecimal.valueOf(cal(now /2)).pow(2);
        if(now % 2 != 0) tmp = tmp.multiply(BigDecimal.valueOf(a));

        return Integer.parseInt(tmp.remainder(BigDecimal.valueOf(c)).toString());
    }
}