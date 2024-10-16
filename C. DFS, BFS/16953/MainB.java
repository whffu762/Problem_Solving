import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 메모이제이션(cache)가 없으면 BFS로 못 풀 것 같았음
 * depth를 유지할 수 없다고 생각했기 때문임 현재 queue에 들어있는 값들이
 * 어떤 depth의 값인지 구분할 수 없기 때문에 cnt가 poll()한 횟수만큼
 * 늘어날 수 밖에 없다고 생각했는데 이걸 해결하는 아주 좋은 방법이 있음
 * 
 * 현재 접근한 queue의 값을 한 주기에 처리하는 것임
 * 아래처럼 queue의 size를 구해서 한꺼번에 queue에 추가하고 그 이후에
 * cnt를 증가하는 방식 이걸 생각 못했네;;;
 */

class solve16953{

    long start;
    long end;
    Queue<Long> queue = new ArrayDeque<>();

    solve16953 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());   
    }

    void run(){ 

        System.out.println(bfs());
    }

    int bfs(){

        queue.add(start);
        int cnt = 1;
        
        while(!queue.isEmpty()){

            int size = queue.size();
            for(int i=0;i<size;i++){

                long now = queue.poll();
                if(now == end){
                    return cnt;
                }

                long tmp1 = now * 2;
                if(tmp1 <= end){
                    queue.add(tmp1);
                }

                long tmp2 = now * 10 + 1;
                if(tmp2 <= end){
                    queue.add(tmp2);
                }
            }
            cnt++;
        }

        return -1;
    }
}

public class MainB {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve16953 p = new solve16953(br);
        p.run();

        br.close();
    }
}