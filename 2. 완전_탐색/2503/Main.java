import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/*
 * 전체 경우의 수에서 스트라이크와 볼에 만족하는 경우만 List에 남기는 방식 
 * 스트라이크와 볼을 판단하기 위해 값 뿐만 아니라 숫자의 자리도 중요함 
 * 이를 위해 배열로 변환 후 사용
 * 
 * 기존의 리스트를 이용해 새로운 리스트를 계속 만드는 방식이기 때문에 Strema()을 이용하면 편함
 */

class solve2503 {

    List<Integer> candidates = new ArrayList<>();

    //최초엔 모든 경우의 수 초기화
    solve2503() {

        for (int i = 123; i < 989; i++) {

            int tmp[] = parse(i);
            if (!checkValid(tmp)) {
                candidates.add(i);
            }
        }
    }

    //배열로 변환
    int[] parse(int input) {

        int[] tmp = new int[3];

        tmp[0] = input / 100;
        tmp[1] = (input % 100) / 10;
        tmp[2] = (input % 100) % 10;

        return tmp;
    }

    //중복 자릿수 없음 && 0이 없는 숫자인지 확인
    boolean checkValid(int[] input) {

        if (input[0] == input[1] || input[0] == input[2] || input[1] == input[2]) {
            return true;
        }

        if(input[1] == 0 || input[2] == 0){
            return true;
        }

        return false;
    }

    //Strike 연산 자릿수와 값 모두 같은 수의 갯수 확인
    void getStrike(int [] input, int strike){

        candidates = candidates.stream()
            .filter(candidate -> {

                int [] tmp = parse(candidate);
                int count = 0;
                for(int i=0;i<3;i++){
                    if(tmp[i] == input[i]){
                        count++;
                    }
                }

                return count == strike;
            })
            .collect(Collectors.toList());
        
    }

    //ball 연산 자릿수가 다르고 같은 값인 수의 갯수 확인
    void getBall(int [] input, int ball){
        
        candidates = candidates.stream()
            .filter(candidate -> {

                int [] tmp = parse(candidate);
                int count = 0;
                for(int i=0;i<3;i++){
                    for(int j=0;j<3;j++){
                        if(tmp[i] == input[j] && i != j){
                            count++;
                        }
                    }
                }

                return count == ball;
            })
            .collect(Collectors.toList());
    }

    void check(int input, int strike, int ball) {

        int [] tmp = parse(input);
        getStrike(tmp, strike);
        getBall(tmp, ball);
    }

    void run(BufferedReader br) throws IOException {

        int numOfInput = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int i = 0; i < numOfInput; i++) {
            st = new StringTokenizer(br.readLine());
            int input = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            check(input, strike, ball);
        }

        System.out.println(candidates.size());
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2503 p = new solve2503();
        p.run(br);

        br.close();
    }
}