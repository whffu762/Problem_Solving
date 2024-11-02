import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * N과 M 시리즈 9번째
 * 배열에 중복된 값이 존재함 그리고 중복된 수열은 출력하면 안 됨
 * 9 4 9 1이 입력될 경우 1 9가 두 번 출력되는데 이걸 방지해야 함
 * 
 * 주의할 점
 * - 같은 idx로 수열을 이루면 안 됨
 * - 같은 구성으로 이루어진 수열이 중복되면 안 됨
 * 
 * 핵심
 * 1번 조건의 경우엔 기존처럼 visited를 이용해서 풀 수 있음 하지만 2번 조건은 다름 
 * 현재 pointer(재귀 호출의 depth)에서만 같은 숫자가 반복되면 안 됨 다른 depth라면 
 * 같은 숫자를 반복해도 상관없음 
 * 예를 들어 1 9 9 같은 수열도 가능해야 함 하지만 1 9 9 가 또 나와선 안되는 것임
 * 이걸 어떻게 하냐 기존 visited는 전역으로 유지했지만 2번 조건을 위한 visited는 
 * 재귀 호출 내에서만 유지하면 됨 그래서 아래와 같이 visited 배열을 매 dpeth마다 생성
 * 하고 있음 근데...
 * 
 * 다른 사람꺼..
 * 후보군을 이미 정렬해놨기 때문에 중복되는 숫자가 오는 경우는 연속으로 옴 예를 들어
 * 1 1 1 2 2 3 3 이런 식으로 그래서 굳이 visited를 유지할 필요없이 이전 값만 유지해서
 * 이전 값과 같은지만 확인하면 됨..  
 *  
 */
class solve15656 {

    int num;
    int pick;
    int [] select;
    int [] value;
    boolean [] visitedIdx;

    StringBuilder sb = new StringBuilder();

    solve15656(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        pick = Integer.parseInt(st.nextToken());

        value = new int [num];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(value);

        visitedIdx = new boolean[num];

        select = new int [pick];
    }

    void run(){

        select(0, 0);
        System.out.println(sb);
    }

    void select(int beforeVisited, int pointer){

        if(pointer == pick){
            for(int s : select){
                sb.append(s).append(" ");
            }
            sb.append("\n");
            return;
        }

        int before = 0;
        for(int i=0;i<num;i++){

            int now = value[i];
            // boolean [] visitedValue = new boolean [10001];
            // if(!visitedIdx[i] && !visitedValue[now]){
                
            //     visitedIdx[i] = true;
            //     visitedValue[now] = true;                
            //     select[pointer] = now;
            //     select(now, pointer + 1);
                
            //     visitedIdx[i] = false;
            // }

            if(!visitedIdx[i] && before != now){

                before = now;
                
                visitedIdx[i] = true;
                
                select[pointer] = now;
                select(now, pointer + 1);
                
                visitedIdx[i] = false;
            }
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15656 p = new solve15656(br);
        p.run();

        br.close();
    }
}