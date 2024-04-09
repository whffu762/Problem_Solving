import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
    기본적인 흐름
    1. input 에 순차적으로 접근
    2. input[i] 를 가지는 숫자가 나올 때 까지 min 증가(무한 루프)
    3. 그런 숫자가 나오면 무한 루프 종료
    
    여기서 주의해야 할 점
    1. 하나의 min 이 여러 input[i] 를 포함할 수 있음
    예를 들어 11 이면 11도 가능하고 101도 가능함
    
    2. 포함하더라도 input[i] 보다 뒤에 있어야 함
    예를 들어 ...23... 에서 min 이 32인 상황이면 32는 33으로 진행되야 함 32 하나로 모두 포함하지 못 함
    때문에 min 에서 뒤에 있으면 input[i] 에서도 마찬가지로 뒤에 있어야 함
    
    이를 위해 숫자가 위치한 index 이후와 min 의 뒤에 있는 모든 숫자를 판단해야 함
 */
class solve1515 {

    String input;

    solve1515(BufferedReader br) throws IOException {
        input = br.readLine();
    }

    void run() {

        int min = 0;
        for (int i = 0; i < input.length(); i++) {

            char now = input.charAt(i);
            while(true){

                String minString = String.valueOf(++min);
                int indexOfNow = minString.indexOf(now);
                if(indexOfNow != -1){

                    //숫자가 포함되는 경우 min 의 모든 요소들과 바로 뒤에 있는 숫자를 비교
                    for(int j=indexOfNow+1;j<minString.length();j++){

                        //input 의 모든 숫자를 검사했으면 종료
                        if(i+1 >= input.length()){
                            break;
                        }

                        //만약 바로 뒤에 있는 숫자가 동일하면 해당 숫자는 화인할 필요가 없으므로 넘어감
                        if(minString.charAt(j) == input.charAt(i+1)){
                            i++;
                        }
                    }

                    break;
                }
            }
        }

        System.out.println(min);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1515 p = new solve1515(br);
        p.run();

        br.close();
    }
}