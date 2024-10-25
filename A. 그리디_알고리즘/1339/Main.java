import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
 * 첫 풀이
 * 1. 자릿수로 정렬
 * 2. 더 많은 걸로 정렬
 * 두 기준으로 정렬했을 때 더 높은 값 취급
 * -> 반례 ABB BC BC BC BC BC BC BC BC BC 이런 식으로 더 많은 게 높을 수도 있음 
 * 
 * 반례에 대한 풀이
 * 1. 문자별로 자릿수 자체를 값으로 환산 
 * 2. 그거 내림차순 정렬해서 값 할당
 * 자릿수 * 할당된 값 
 * 
 * 여기서 주의할 점
 * 알파벳은 최대 10개지만 A ~ Z중 어떤 알파벳이 나올지 모름 그래서 모든 알파벳을 저장할 배열을
 * 전부 미리 선언해놔야 함 
 * 
 */

class solve1339{

    List<String> words = new ArrayList<>();

    int [][] forInput = new int [26][2];
    int [] forConvert = new int [26];

    PriorityQueue<int []> pq = new PriorityQueue<>((after, before)->{
        return before[1] - after[1];
    });

    solve1339 (BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num;i++){
            String input = br.readLine();
            words.add(input);
            checkWord(input);
        }
    }

    void checkWord(String input){

        for(int i=0;i<input.length();i++){

            forInput[input.charAt(i) - 65][0] = input.charAt(i) - 65;
            forInput[input.charAt(i) - 65][1] += (int) Math.pow(10, input.length() - i - 1);
        }
    }


    void run(){

        for(int i=0;i<forConvert.length;i++){
            pq.add(forInput[i]);
        }

        int value = 9;
        while(!pq.isEmpty()){
            
            int [] now = pq.poll();
            if(now[1] != 0){
                forConvert[now[0]] = value--;
            }
        }


        int result = 0;
        for(int i=0;i<forConvert.length;i++){

            result += forConvert[i] * forInput[i][1];
        }
        System.out.println(result);
    }
}

public class Main {

    public static void main (String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1339 p = new solve1339(br);
        p.run();

        br.close();
    }
}