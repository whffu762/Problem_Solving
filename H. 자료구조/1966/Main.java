import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 현재보다 큰 값이 존재할 경우
 * -> 큐 뒤에 추가
 * 
 * 존재하지 않을 경우
 * -> 현재 값이 target인지 확인
 * 
 * 현재 값이 target 이면
 * -> 종료
 * 
 * 아니면
 * -> cnt 증가
 * -> 갯수 감소
 * 
 */
class solve1966{

    int num;
    int target;
    Queue<int []> queue = new ArrayDeque<>();
    int [] cache = new int [10];

    solve1966 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        num = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            int now = Integer.parseInt(st.nextToken());
            queue.add(new int [] {i, now});
            cache[now]++;
        }
    }

    int run(){

        int cnt = 0;
        while(true){

            int [] now = queue.poll();
            
            if(check(now)){
                cnt++;
                cache[now[1]]--;
                if(now[0] == target) break;
            } else {
                queue.add(now);
            }
        }

        return cnt;
    }

    boolean check(int [] now){


        for(int i=now[1]+1;i<cache.length;i++){

            if(cache[i] != 0){
                return false;
            }
        }

        return true;
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){

            solve1966 p = new solve1966(br);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);
        
        br.close();
    }
}