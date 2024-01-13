import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 메모이제이션 안 쓰면 시간 초과 뜸
 */

class solve1463 {

    int [] cache;

    int compute(int input){

        int result = 0;

        if(cache[input] != 0 || input == 1){
            return cache[input];   
        }

        if(input % 2 == 0 && input % 3 == 0){
            int tmp = Math.min(compute(input/2), compute(input/3));
            result = Math.min(tmp, compute(input-1));
        } else if(input % 2 == 0){
            result = Math.min(compute(input/2), compute(input-1));
        } else if(input % 3 == 0){
            result = Math.min(compute(input/3), compute(input-1));
        } else {
            result = compute(input-1);
        }

        cache[input] = result + 1;
        return cache[input]; 
    }


    void run(BufferedReader br) throws IOException {
        int input = Integer.parseInt(br.readLine());
        cache = new int[input+1];
        cache[1] = 0;

        System.out.println(compute(input));
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1463 p = new solve1463();
        p.run(br);

        br.close();
    }
}
