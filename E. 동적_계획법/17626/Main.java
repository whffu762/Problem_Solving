import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * N까지 필요한 제곱근의 최소를 구하기 위해선 1부터 N-1까지의 최소가 필요함
 * 예를 들어 13을 구한다고 가정해보자 
 * 1 : 1을 구할 때 필요한 제곱근의 갯수
 * 12 : 12를 구할 때 필요한 제곱근의 갯수
 * 13은 이 둘의 합으로 이루어짐 이런 방식으로 나올 수 있는 경우의 수는 다음과 같음
 * 1 + 12
 * 2 + 11
 * 3 + 10
 * 4 + 9
 * 5 + 8
 * 6 + 7
 * 이 6가지 중 최소를 고르면 됨 하지만 이 방식은 수가 커질수록 비교 대상도 같이 증가함
 * N * N / 2 = N ^ 2
 * 
 * 시간 줄이기
 * 생각해보면 최소에는 제곱수가 포함될 수 밖에 없음 제곱근 하나로 구성이 가능하기 때문에
 * 1 + X의 값이기 때문 그래서 N보다 작은 제곱수들만 뽑아서 비교하면 비교 대상이 몇 안 됨
 * 10000까지도 100개밖에 안됨 
 * N * Root(N) = N ^ 1.5
 */
class solve17626{

    int input;
    int [] cache;

    solve17626 (BufferedReader br) throws IOException{

        input = Integer.parseInt(br.readLine());
        cache = new int [input+1];
    }

    void run(){

        cache[1] = 1;
        
        for(int i=2;i<cache.length;i++){

            cache[i] = getMin(i);
        }

        System.out.println(cache[input]);
    }

    int getMin(int now){

        if(Math.pow((int) Math.sqrt(now),2) == now){
            return 1;
        }
        
        int result = Integer.MAX_VALUE;
        // for(int i=1;i<now/2+1;i++){    

        //     result = Math.min(result, cache[i] + cache[now-i]);
        // }

        //개선된 방식
        for(int i=1;i<(int) Math.sqrt(now)+1;i++){

            int tmp = i * i;
            result = Math.min(result, cache[tmp] + cache[now-tmp]);
        }

        return result;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve17626 p = new solve17626(br);
        p.run();

        br.close();
    }
}