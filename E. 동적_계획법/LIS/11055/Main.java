import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * LIS를 조금 비틀면 됨
 * LIS에선 최장 부분 수열이어서 DP에 수열의 순번이 저장됐음
 * 하지만 여기선 수열의 길이와 상관없이 합이 가장 큰 수열을 구하는 것이기 때문에
 * 각 인덱스까지의 합을 저장함 9 1 3 4 2 의 경우 9 1 4 9 3 이 저장되는 것임

 * 주의할 점
 * 수열의 길이와 합의 크기가 비례한다고 생각할 수 있지만 그렇지 않음
 * 반례 : 1 8 3 4 9 
 * 가장 긴 수열은 1 3 4 9 지만 가장 큰 수열은 1 8 9임
 * 
 * 알고리즘은 앞선 11053과 비슷함 
 * - value[now] > value[i] (현재 값보다 작고)
 * - DP의 값이 최대인 항목의 인덱스를 반환함
 * 
 * 근데 DP에 저장되는 값이 각 인덱스까지의 합
 * 
 * 참고로 여기선 시간 복잡도를 더 줄이는 방법이 없음
 * 11053에선 최장이기 때문에 최솟값만 저장해서 이전 값을 전부 탐색할 필요가 없었음
 * 근데 여기선 값의 크기를 저장해야 하는데 지금 큰 값도 나중엔 작아질 수 있고 지금 작은 값도
 * 나중에 커질 수 있어서 무조건 답을 구할 수 있는 값이 없음 
 *
 */
class solve11055{

    int num;
    int [] value;
    int [] cache;

    solve11055 (BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());

        value = new int [num];
        cache = new int [num];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<num;i++){
            value[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        cache[0] = value[0];

        int max = value[0];
        for(int i=1;i<num;i++){

            int result = LIS(i);
            if(result == -1){
                cache[i] = value[i];
            } else {
                cache[i] = cache[result] + value[i];
            }

            max = Math.max(max, cache[i]);
        }

        System.out.println(max);
    }

    int LIS(int now){

        int idx = -1;
        int max = 0;
        for(int i=0;i<now;i++){
            
            if(value[i] < value[now] && cache[i] > max){

                max = cache[i];
                idx = i;
            }
        }

        return idx;
    }   
}

public class Main {

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve11055 p = new solve11055(br);
        p.run();

        br.close();
    }
}