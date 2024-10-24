import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/*
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