import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 일단 단순히 카운트만 하는 바업
 * IOIOIO... 를 찾으면 됨 때문에 I를 먼저 찾고 그 이후 OI의 갯수를 세는 방식으로 구현
 * 
 * 1. N번째 글자가 I인지 확인
 * - 맞으면 2번으로 이동
 * - 아니면 5번으로 이동
 * 
 * 2. 이후 글자가 O 그 이후 글자가 I 인지 확인
 * - 맞으면 3-1번으로 이동
 * - 아니면 3-2번으로 이동
 * 
 * 3-1. OI가 맞으면
 * - OI의 갯수 1증가
 * - N 1 증가
 * 
 * 3-2. OI가 아니면
 * - OI의 갯수 0으로 초기화
 * 
 * 4. OI의 갯수가 num인지 확인해서 맞으면
 * - 결과 1 증가
 * - OI 갯수 하나 감소
 * 
 * 5. 이걸 N이 s.length - 2일때가지 반복
 * 
 * 3-1에서 N을 1 증가시키는 이유 
 * 현재 I라면 다음은 O임 어차피 볼 필요없어서 그 다음 I로 넘기는 거임
 *  
 * 4에서 OI 갯수를 1 감소시키는 이유
 * 3-1에서 인덱스가 증가하면서 OI가 하나 줄어들기 때문
 */
class solve5525{

    int num;
    String s;

    solve5525(BufferedReader br) throws IOException{
        
        num = Integer.parseInt(br.readLine());
        Integer.parseInt(br.readLine());
        s = br.readLine();
    }

    void run(){

        int tmp = 0;
        int result = 0;
        for(int i=0;i<s.length()-2;i++){

            if(s.charAt(i) == 'I'){
                
                if(s.charAt(i+1) == 'O' && s.charAt(i+2) == 'I'){
                    tmp++;
                    i++;
                } else {
                    tmp = 0;
                }

                if(tmp == num){
                    result++;
                    tmp--;
                }
            }
        }

        System.out.println(result);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve5525 p = new solve5525(br);
        p.run();

        br.close();
    }
}