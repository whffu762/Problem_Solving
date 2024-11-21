import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 투포인터를 이용한 완전 탐색
 * 문제만 읽어보면 덱쓰는 것 같아서 그걸로 절대 안풀림 일단 완전 탐색이 불가능해서
 * 모든 반례를 다 찾지 않는 이상 불가능함 중점은 투 포인터
 * 
 * 기몬적인 흐름
 * 미리 꽂혀있는 걸 해체하는게 아니라 애초에 꽂으면서 조건이 맞을 때의 최댓값을 구하는 방식
 * i는 꽂히는 과일의 인덱스
 * j는 빠지는 과일의 인덱스
 * 
 * 1. i번째 과일을 꽂음
 * 2. 현재 과일의 종류 수가 2보다 큰지 확인
 * - 크면 3번으로 이동
 * - 작으면 4번으로 이동
 * 3. j번재 과일을 뺌
 * - 이걸 2번에서 확인한 종류 수가 2 이하가 될 때까지 반복
 * 4. 현재 result와 i - j + 1 중 최댓값을 구함
 */
class solve30804{

    int [] fruits = new int [10];
    int [] tang;

    solve30804 (BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());
        tang = new int [num];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){

            int tmp = Integer.parseInt(st.nextToken());
            tang[i] = tmp;
        }
    }

    void run(){
        
        int result = 0;
        int j = 0;
        for(int i=0;i<tang.length;i++){

            fruits[tang[i]]++;
            while(checkTang()) fruits[tang[j++]]--;
            result = Math.max(result, i - j + 1);
        }

        System.out.println(result);
    }

    boolean checkTang(){

        int tmp = 0;
        for(int f : fruits) if(f != 0) tmp++;
        
        if(tmp > 2) return true;
        return false;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve30804 p = new solve30804(br);
        p.run();
        
        br.close();
    }
}