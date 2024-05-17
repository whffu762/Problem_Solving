import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 거꾸로 읽어도 똑같은 문자열을 만들어야 함  
 * 
 * 불가능한 경우
 * 갯수가 홀수개인 알파벳이 2개 이상 존재하면 불가능함
 * 
 * 저장 방식
 * 단어의 갯수는 알파벳을 A로 뺀 값을 인덱스로 이용한 배열에 저장함
 * 예를 들어 A=0 B=1 ... Z=25가 저장됨
 * 
 * 결과물을 [] result에 저장함 원하는 인덱스에 마음대로 넣을 수 있어야 하기 때문에 배열로 선언
 * 
 * 동작 방식
 * 1. 입력의 A ~ Z까지의 갯수를 셈
 * 2. A부터 2로 나눠서 앞 뒤로 단어를 붙임 붙이면서 갯수도 뺌
 * 3. 남은 갯수가 1이면 cnt를 중가하고 맨 가운데에 붙임
 * 4. cnt가 1개보다 많으면 불가능
 */
class solve1213{

    int [] word = new int [26];
    char [] result;

    solve1213(BufferedReader br) throws IOException{

        String input = br.readLine();
        result = new char [input.length()];

        for(int i=0;i<input.length();i++){
            int now = input.charAt(i) - 'A';
            this.word[now]++;
        }
    }

    void run(){

        char now = 'A';
        int idx = 0;
        int cnt = 0;
        for(int i=0;i<word.length;i++){

            int numOfNow = word[i] / 2;
            for(int j=0;j<numOfNow;j++){
                result[idx] = now;
                result[result.length-idx-1] = now;
                word[i] -= 2;
                idx++;
            }

            if(word[i] > 0){
                result[result.length/2] = (char) (i + 'A');
                cnt++;
            }
            
            now++;
        }
        
        if(cnt > 1){
            System.out.println("I'm Sorry Hansoo");
        } else {
            
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<result.length;i++){
                sb.append(result[i]);
            }
            System.out.println(sb);
        }
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1213 p = new solve1213(br);
        p.run();

        br.close();

    }
}