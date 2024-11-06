import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * LIS를 구할 때 cache에 해당 인덱스의 값이 수열에서 가지는 순번을 저장했음
 * 그걸 위해선 현재 인덱스 N 을 기준으로 0 ~ N까지의 반복이 각 index마다 
 * 반복되서 N^2 의 시간복잡도를 가짐
 * 이런 불필요한 시간 복잡도를 개선하기 위한 방식이 바로 이 방법임
 * 
 * 
 * cache에 저장되는 값이 다름
 * cache의 index에 해당하는 길이의 수열에 들어올 수 있는 수들 중 최소값이 저장됨
 * 
 * 9 1 3 5 2 4 7
 * 예를 들어 위와 같은 수열이 있다면 cache[0]에는 처음엔 9가 들어왔다가 최종적으로 1이 저장되게 됨
 * 이전 방식으로 풀었을 때 cache에 9와 1 모두 1이 저장됨 이번엔 그 1이 저장되는 수 중 최솟값만 
 * 저장하는 방식인 것임
 * 다른 예시 cache[1]은 3이 들어왔다가 최종적으론 2가 저장되게 됨 
 * 그렇게 해서 생성되는 cache 1 2 4 7
 * 
 * cache에 그렇게 저장되면 뭐가 좋음? 
 * cache에는 수열의 N번째 자릿수 중 최소인 값이 저장되기 때문에 N + 1번째 자릿수를 판단할 때는 
 * N번째 자릿수보다 크기만 하면 되는 것임
 * 즉 9 1 3 5 2 4 7에서 이후에 어떤 수열이 나오더라도 7보다 크기만 하면 일단 5번째 값에 해당되는 것임
 * 자세한건 노션 참고
 * 
 * 
 * 그래서 간단한 흐름
 * 1. cache[pointer-1] < now (cache의 마지막 값과 현재값 비교)
 * - now가 더 크면 cache[pointer++] = now;
 * 
 * 2. 그렇지 않으면 
 * - now를 이분 탐색해서 해당 값이 들어갈 위치 지정
 * - 이를 통해 수열의 최솟값이 cache에 저장되는 것을 보장함
 * 
 */

class solve11053{

    int num;
    int [] value;
    int [] cache;

    List<Integer> cache2 = new ArrayList<>();

    solve11053(BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        value = new int [num];
        cache = new int [num];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run() {

        cache[0] = value[0];
        int pointer = 1;
        for(int i=1;i<num;i++){

            int now = value[i];
            if(cache[pointer-1] < now){
                cache[pointer++] = now;
            } else {
                
                int idx = Arrays.binarySearch(cache, 0, pointer, now);
                if(idx < 0){
                    idx = -(idx + 1);
                }
                cache[idx] = now;
            }
        }

        System.out.println(pointer);
    }

    //List를 이용해도 됨
    void run2() {

        cache2.add(value[0]);

        for(int i=1;i<num;i++){

            int now = value[i];
            if(cache2.get(cache2.size()-1) < now){
                cache2.add(now);
            } else {

                int idx = Collections.binarySearch(cache2, now);
                if(idx < 0){
                    idx = -(idx + 1);
                }
                cache2.set(idx, now);
            }
        }
        System.out.println(cache2.size());
    }
}

public class Main {

    public static void main(String [] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11053 p = new solve11053(br);
        p.run();
        p.run2();

        br.close();
    }
}