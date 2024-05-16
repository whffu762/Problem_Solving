import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 비교 기준이 되는 단어 : target
 * 비교 대상이 되는 단어 : now
 * 
 * 이 전 풀이에서 알파벳의 위치도 함께 판단해야 한다고 했는데
 * 이를 다르게 생각하면 나오는 알파벳의 갯수를 파악하는 것임
 * 
 * 이 풀이의 핵심은 입력을 숫자로 바꿔 인덱스처럼 이용하는 것임
 * 
 * A~Z 까지 26개로 int 배열을 초기화하고 
 * 각 입력을 'A'로 빼서 0~25까지의 값으로 만들어 배열의 인덱스처럼 사용함
 * 문자의 갯수를 셀 때 유용할 듯
 * 
 */
class solve2607 {

    int [] target = new int [26];
    int length;
    String [] input;

    solve2607(BufferedReader br) throws IOException{

        int numOfInput = Integer.parseInt(br.readLine());

        String target = br.readLine();
        length = target.length();
        for(int i=0;i<target.length();i++){
            int word = target.charAt(i) - 'A';
            this.target[word]++;
        }
        
        input = new String [numOfInput-1];
        for(int i=0;i<numOfInput-1;i++){
            input[i] = br.readLine();
        }
    }

    
    int check(String now){

        int [] target = this.target.clone();

        int cnt = 0;
        for(int i=0;i<now.length();i++){

            int word = now.charAt(i) - 'A';
            
            if(target[word] > 0){
                cnt++;
                target[word]--;
            }
        }
        
        return cnt;
    }

    boolean checkSame(String now){

        if(now.length() != length){
            return false;
        }

        if(check(now) != length){
            return false;
        }

        return true;
    }

    //길이 -1
    boolean checkSimilar1(String now){

        if(now.length() != length-1){
            return false;
        }

        if(check(now) != length-1){
            return false;
        }

        return true;
    }

    //길이 같음
    boolean checkSimilar2(String now){

        if(now.length() != length){
            return false;
        }

        if(check(now) != length-1){
            return false;
        }

        return true;
    }

    //길이 +1
    boolean checkSimilar3(String now){

        if(now.length() != length+1){
            return false;
        }

        if(check(now) != length){
            return false;
        }

        return true;
    }

    void run(){

        int result = 0;
        for(int i=0;i<input.length;i++){

            String now = input[i];
            if(checkSame(now) || checkSimilar1(now) || checkSimilar2(now) || checkSimilar3(now)){
                result++;
            }
        }

        System.out.println(result);
    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2607 p = new solve2607(br);
        p.run();

        br.close();
    }
}