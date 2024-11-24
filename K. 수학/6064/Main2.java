import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * CRT를 이용한 문제
 * 중국인의 나머지 정리임 간단히 설명하면 다음과 같음
 * - A % M = x
 * - A % N = y
 * 이 두 식을 만족하는 A가 존재하고 그 값은 유일하다는 정리임
 * 
 * 딱 이 문제에 맞는 정리임 이걸 이용하면 아주 빠르게 풀 수 있음
 * 예를 들어 10 12 3 9를 보자
 * - A % 10 = 3
 * - A % 12 = 9
 * 이를 만족하는 A를 찾는 것이 이 문제의 요지임
 * 
 * A를 어떻게 구할까? 
 * 일단 첫번째 수식을 만족하는 A 중에 두번째 수식도 만족하는 것을 찾으면 됨
 * 
 * 1. 3부터 시작해서 12로 나눠서 나머지가 9인지 확인
 * 2. 아니면 3에 10을 더하고 다시 확인
 * 3. 이걸 3이 M * N보다 커질때까지 반복
 * 
 * 근데 이럴 경우 아주 큰 허점이 있음 나머지가 12인 경우를 찾지 못함
 * 왜냐면 12로 나누기 때문에 0으로 나오기 때문임 하지만 문제에선 1 ~ M, N 까지의
 * 주기를 가진다고 나와있음 M, N도 챙겨야 함 
 * 해결법은 x와 y를 1씩 빼면 됨..
 * 
 * 아니 그래도 되나요? x와 y 둘 다 뺐기 때문에 상관없음
 */
class solve6064{

    int M, N, x, y;

    solve6064(int [] input) {

        M = input[0];
        N = input[1];
        x = input[2]-1;
        y = input[3]-1;
    }

    int run(){

        for (int i = x; i < (N * M); i += M) {
            if (i % N == y) {
                return i + 1;
            }
        }

        return -1;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int [] input = new int [4];
            for(int j=0;j<4;j++){
                input[j] = Integer.parseInt(st.nextToken());
            }

            solve6064 p = new solve6064(input);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}