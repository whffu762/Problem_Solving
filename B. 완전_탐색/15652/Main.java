import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백트랙킹을 이용하는 문제
 * 
 * 선택을 M만큼 해야 한다면 M을 반복하면 풀지 못 함
 * - M번 재귀 호출을 하는 식으로 해야 함
 * - 반복은 선택지를 제공할 때 이용해야 함
 * 
 * 그 과정에서 조건 확인
 * - 같은 값이 존재하면 안 됨 이걸 직접 확인하면 너무 느림
 * - visited를 이용해서 특정 인덱스에 접근 여부를 확인
 * 
 * 백트랙킹 이용
 * 백트랙킹의 가장 큰 특징은 재귀 호출 전 후로 활성화 비활성화 하는 것임
 * - pointer를 이용하면 알아서 pointer 이후의 값은 무시됨
 * - visited (방문 여부)만 재귀 호출 가기전 활성화하고 갔다 온 후 비활성화
 * 
 */
class solve15654{

    int num;
    int pick;
    int [] value;
    boolean [] visited;
    StringBuilder sb = new StringBuilder();

    solve15654 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());

        value = new int [num];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(value);

        visited = new boolean [num];
    }

    void run(){

        selectIdx(new int [pick], 0);
        System.out.println(sb);
    }

    void selectIdx(int [] select, int pointer){

        if(pointer == pick){
            for(int i=0;i<pick;i++){
                sb.append(select[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0;i<num;i++){

            if(!visited[i]){

                visited[i] = true;
                select[pointer] = value[i];
                selectIdx(select, pointer + 1);
                visited[i] = false;
                //select[pointer] = 0;
            }
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15654 p = new solve15654(br);
        p.run();

        br.close();
    }
}