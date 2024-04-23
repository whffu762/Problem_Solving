import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 기본적인 흐름
 * 맨 아랫줄부터 시작(각각의 윗줄보다 큰 수들이니까)
 * 1. 맨 아래 값은 모두 List에 저장
 * 2. n-1번째 줄부터 각 줄의 가장 작은 값을 구하고 그 윗줄과 비교해서 큰 값들만 List에 저장
 * 3. 첫째줄까지 반복
 * 4. list의 값 정렬 후 원하는 출력
 * 
 * 이를 Priority Queue로 간단하게 구현할 수 있음
 */

class solve2075{

    int n;
    int [][] map;

    solve2075(BufferedReader br) throws IOException{

        n = Integer.parseInt(br.readLine());
        map = new int [n][n];

        for(int i=0;i<n;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void run(){

        List<Integer> list = new ArrayList<>();

        int min = Integer.MAX_VALUE;
        for(int i=0;i<n;i++){

            min = Math.min(min, map[n-1][i]);
            list.add(map[n-1][i]);
        }

        int nextMin = Integer.MAX_VALUE;
        for(int i=n-2;i>-1;i--){
            
            for(int j=0;j<n;j++){
                
                int now = map[i][j];
                if(min < now){
                    nextMin = Math.min(now, nextMin);
                    list.add(now);
                }
            }

            min = nextMin;
            nextMin = Integer.MAX_VALUE;
        }

        Collections.sort(list, (after, before) ->{

            return before - after;
        });

        System.out.println(list.get(n-1));
    }
}

public class Main{

    public static void main(String [] args) throws IOException{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2075 p = new solve2075(br);
        p.run();

        br.close();
    }
}