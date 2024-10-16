import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 두 가지 경로가 존재하는 그래프의 최단 거리를 구하면 됨
 * 순환이 존재하지 않기 때문에 dfs로도 가능함
 * 또한 가중치가 1이기 때문에 bfs로도 가능함
 * 
 * 처음엔 bfs로 풀었는데 입력의 범위가 10억이라 cache를 쓸 수 없음(메모리 초과)
 * 근데 bfs로 최단 거리를 구하기 위해선 반드시 cache가 필요함 그래서 dfs로 풀었는데
 * 다른 풀이 보니까 bfs로도 가능했음 해당 코드는 다른 파일 보고 여기선 dfs만
 * 
 * dfs에서 주의할 점
 * - 답이 없을 수도 있음
 * - 경로가 둘인 경우에서 depth 유지
 * 이 두 가지만 조심하면 됨
 * 
 * 답이 없을 수도 있기 때문에
 * 전역 변수로 flag를 두고 만족할 때만 해당 값을 true로 바꿨음
 * 
 * 경로가 두 가지의 depth
 * 경로를 전역 변수로 둘 경우 첫번째 경로에선 제대로 동작하지만 두번째 경로에선 이전 경로의
 * 리프의 depth를 이용하게 됨 그래서 백트랙킹처럼 빼줘야 하는데 그러면 또 결과 값에 영향을 주게 됨
 * 전역 변수로 둬도 가능은 할 수 있지만 이것 저것 복잡함 그래서 그냥 파라미터로 전달하고 함수가 끝나면
 * 반환하는 방식으로 구현함
 * 
 * 외적으로 주의할 점
 * - 값의 범위
 * 값의 범위가 10억이기 때문에 int로 충분할 것 같지만 n * 10 + 1 연산이 수행되기 때문에 오버플로우가 발생함
 * 그래서 long으로 해줘야 함 
 */
class solve16953{

    long start;
    long end;
    boolean flag;

    solve16953 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Long.parseLong(st.nextToken());
        end = Long.parseLong(st.nextToken());
        
    }

    void run(){ 

        int result = dfs(start, 1);

        if(flag){
            System.out.println(result);
        } else {
            System.out.println(-1);
        }
    }

    int dfs(long now, int depth){
    
        if(now == end){
            flag = true;
            return depth;
        }

        int next = depth + 1;
        long tmp1 = now * 2;
        if(!flag && tmp1 <= end){
            depth = dfs(tmp1, next);

        }

        long tmp2 = now * 10 + 1;
        if(!flag && tmp2 <= end){
            depth = dfs(tmp2, next);
        }

        return depth;
        
    }
}

public class MainD {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve16953 p = new solve16953(br);
        p.run();

        br.close();
    }
}