import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 주의할 점은 문자의 배치를 바꿀 수 없다는 점임
 * 예를 들어 000111100000 이 같은 상황이면
 * 000011 - 오답
 * 000110 - 정답
 * 
 * 이거만 알면 간단함
 * 먼저 갯수를 세고
 * 0은 앞에 나오는걸 추가하고 1은 뒤에 나오는걸 추가하면 됨
 */

class solve20310{

    String input;
    
    solve20310 (BufferedReader br) throws IOException{
        input = br.readLine();
    }

    void run(){

        int zero = 0;
        int one = 0;
        for(int i=0;i<input.length();i++){
            if(input.charAt(i) == '0'){
                zero++;
            } else {
                one++;
            }
        }

        zero /= 2;
        one /= 2;

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<input.length();i++){

            if(input.charAt(i) == '0' && zero > 0){
                sb.append(0);
                zero--;
            }

            if(input.charAt(i) == '1'){
        
                if(one > 0){
                    one--;
                } else {
                    sb.append(1);
                }
            }
        }

        System.out.println(sb);
    }
}


public class Main{

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve20310 p = new solve20310(br);
        p.run();

        br.close();
    }
}