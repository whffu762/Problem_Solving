import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 음수 값을 최대한 크게 해줘야 하기 때문에
 *  -를 기준으로 먼저 나누고 그 안에서 +를 연산해야 함
 * 
 * 처음과 마지막은 반드시 숫자로 시작한다고 했으니 첫 값은 무조건 양수 하나가 들어감
 * 0번째의 연산 결과로 초기화하고 그 이후부턴 결과를 전부 빼주면 됨
 * 
 * 주의할 점은 +와 -로 나눌 때 앞에 \\를 붙여줘야 함
 */
class solve1541{

    String [] splitWithMinus;
    int [] result;

    solve1541(BufferedReader br) throws IOException{

        String input = br.readLine();
        splitWithMinus = input.split("\\-");
    }

    int getSumOfPlus(String plus){

        String [] splitWithPlus = plus.split("\\+");

        int sum = 0;
        for(String value : splitWithPlus){
            sum += Integer.parseInt(value);
        }

        return sum;
    }


    void run(){

        int result = getSumOfPlus(splitWithMinus[0]);
        for(int i=1;i<splitWithMinus.length;i++){

            result -= getSumOfPlus(splitWithMinus[i]);
        }

        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1541 p = new solve1541(br);
        p.run();

        br.close();
    }
}