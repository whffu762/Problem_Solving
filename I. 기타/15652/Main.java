import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 재귀 호출을 이용하는 문제
 * 
 * 두 가지의 반복(?)이 존재함
 * 1. 111.. ~ NNN.. 까지 반복
 * 2. i번째 자리의 오버플로우 처리
 * 
 * 1번과 2번을 구분해야 풀 수 있음 
 * - 1번은 단순 반복으로 해결
 * - 2번은 재귀 호출을 이용해 특정 조건이 만족한다면 재귀 호출을 계속해야 함
 * 재귀 호출에 뭘 넣고 뭘 넣지 말지를 잘 판단해야 함
 * 
 * 
 * 흐름
 * 1. 출력
 * 2. 다음 수 구하기 및 종료 조건 검사
 * 
 * 다음 수 구하기
 * 1. 현재 pointer 증가
 * 2. 증가된 값이 max보다 크면 pointer 옮겨서 증가(재귀 호출)
 * 
 * 이 과정에서 반환값은 증가된 값으로 함으로써 종료 조건과 이후 값을 한꺼번에 처리
 */
class solve15652 {

    int max;
    int lenght;
    int [] value;
    solve15652(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        max = Integer.parseInt(st.nextToken());
        lenght = Integer.parseInt(st.nextToken());
        value = new int [lenght];
        Arrays.fill(value, 1);
    }

    void run(){

        StringBuilder sb = new StringBuilder();
        while(true){

            for(int i=0;i<value.length;i++){
                sb.append(value[i]).append(" ");
            }
            sb.append("\n");

            if(next(lenght - 1) == -1) break;
        }

        System.out.println(sb);
    }

    int next(int pointer){

        if(pointer < 0) return -1;

        int next = ++value[pointer];

        if(next > max){
            next = next(pointer-1);
            value[pointer] = next;
        }

        return next;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15652 p = new solve15652(br);
        p.run();

        br.close();
    }
}