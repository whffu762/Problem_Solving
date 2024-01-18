import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/*
 * 숫자의 범위가 10의 100승까지 가능하기 때문에 수를 저장할 수 있는 자료형으로는 해결하지 못 함
 * 반드시 문자열을 이용해서 비교해야만 함
 */

class solve2870{

    List<String> result = new ArrayList<>();

    String checkZero(String input){

        while(input.length() != 1){
            String ch = String.valueOf(input.charAt(0));

            if(ch.equals("0")){
                input = input.substring(1);
            } else {
                break;
            }
        }
        
        return input;
    }

    void compute(String input){

        String regex = "^[0-9]+$";
        String tmp = "";

        for(int i=0;i<input.length();i++){
            String ch = String.valueOf(input.charAt(i));

            if(ch.matches(regex)){
                tmp += ch;
            } else {
                if(tmp != ""){
                    result.add(checkZero(tmp));
                    tmp = "";
                }
            }
        }
    }

    void run(BufferedReader br) throws IOException{

        int n = Integer.parseInt(br.readLine());
        for(int i=0;i<n;i++){
            compute(br.readLine() + "a");
        }

        Collections.sort(result, (next, previous)->{

            if(next.length() == previous.length()){
                int i = 0;
                while(true){
                    int result = next.charAt(i) - previous.charAt(i);
                    if(result != 0 || i == next.length()-1){
                        return result;
                    }
                    i++;
                }
            } else {
                return next.length() - previous.length();
            }
        });

        for(String t : result){
            System.out.println(t);
        }
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2870 p = new solve2870();
        p.run(br);

        br.close();
    }
}