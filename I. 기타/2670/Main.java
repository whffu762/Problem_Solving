import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 비교 기준이 되는 단어 : target
 * 비교 대상이 되는 단어 : now
 * 
 * 두 단어를 비교할 때 주의할 점 1
 * - 단어 길이에 따라 조건이 달라짐
 * 1. 같은 구성 : 길이가 같음 + 모든 구성이 동일함
 * 2. 비슷한 구성
 *      - 제거된 경우 : 길이가 1 짧음 + now의 모든 구성이 target의 알파벳이어야 함
 *      - 바뀐 경우 : 길이가 동일함 + now의 구성 중 하나를 제외하고 target의 알파벳이어야 함
 *      - 추가된 경우 : 길이가 1 김 + now의 구성 중 하나를 제외하고 target의 알파벳이어야 함
 * 
 * 
 * 두 단어를 비교할 때 주의할 점 2
 * - 두 단어를 비교하는 과정에서 단순히 알파벳만 비교하면 안 됨
 * 알파벳만 비교할 경우 중복된 알파벳으로 구성된 단어에 대한 검사에 구멍이 생김
 * 예를 들어 ABAAB 같이 A와 B가 여러번 나오는 단어의 경우 ABAB도 조건에 맞는 단어로 판단됨
 * 때문에 알파벳의 위치까지 생각해야 함
 * 이를 위해 [] visited를 이용해서 방문 여부까지 같이 확인하는 방식 
 * 
 * 
 * now를순차적으로 접근해서 target의 각 알파벳을 비교하는 방식
 * target을 하나씩 접근하면 visisted를 유지하기 애매함..
 */
class solve2607 {

    Character [] target;
    String [] input;
    boolean [] visited;

    solve2607(BufferedReader br) throws IOException{

        int numOfInput = Integer.parseInt(br.readLine());

        String target = br.readLine();
        this.target = new Character [target.length()];
        for(int i=0;i<target.length();i++){
            this.target[i] = target.charAt(i);
        }
        
        input = new String [numOfInput-1];
        for(int i=0;i<numOfInput-1;i++){
            input[i] = br.readLine();
        }
    }

    
    int check(String now){

        visited = new boolean[target.length];
        for(int i=0;i<now.length();i++){
            for(int j=0;j<target.length;j++){
                if(now.charAt(i) == target[j] && !visited[j]){
                    visited[j] = true;
                    break;
                }   
            }
        }

        int count = 0;
        for(int i=0;i<visited.length;i++){
            if(!visited[i]){
                count++;
            }
        }

        return count;
    }

    boolean checkSame(String now){

        if(now.length() != target.length){
            return false;
        }

        if(check(now) != 0){
            return false;
        }

        return true;
    }

    //길이 -1
    boolean checkSimilar1(String now){

        if(now.length() != target.length-1){
            return false;
        }

        if(check(now) != 1){
            return false;
        }

        return true;
    }

    //길이 같음
    boolean checkSimilar2(String now){

        if(now.length() != target.length){
            return false;
        }

        if(check(now) != 1){
            return false;
        }

        return true;
    }

    //길이 +1
    boolean checkSimilar3(String now){

        if(now.length() != target.length+1){
            return false;
        }

        if(check(now) != 0){
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

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2607 p = new solve2607(br);
        p.run();

        br.close();
    }
}