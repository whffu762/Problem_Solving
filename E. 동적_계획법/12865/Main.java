import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 배낭 문제
 * 
 * DP를 이용한 알고리즘으로 직관적으로 생각해내기 꽤 어려움
 * 물건을 넣을 때 그 물건이 들어갈 공간을 마련했을 때의 최대 가치를 가져오는 방식임
 *
 * 2차 배열에서 행은 가방의 무게고 열은 각 물건임
 * 저장되는 값은 가방의 무게에 따라 들어갈 수 있는 최대 가치임
 * 즉 1kg일 때 들어갈 수 있는 최대 가치를 계산, 2kg일 때 들어갈 수 있는 최대 가치.. 이런 식으로 계산해나가는데 
 * 각 물건 별로 물건을 포함시키거나 제외할 수 있음 그 둘을 비교해서 더 높은 가치를 저장하는 것임
 * 
 * 물건을 제외했을 땐 cache[i][j-1] : 바로 이전까지의 값 그대로임
 * 물건을 포함시켰을 땐 cache[i-물건의 무게][j-1] + 물건의 가치 : 현재 물건을 넣기 위한 공간을 확보했을 때의 최대값 + 현재 물건의 가치
 * 
 */
class solve12865{

    int [][] cache;
    int [][] luggage;
    int weight;

    solve12865(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        int num = Integer.parseInt(st.nextToken());
        weight = Integer.parseInt(st.nextToken());

        luggage = new int [num][2];

        for(int i=0;i<num;i++){

            st = new StringTokenizer(br.readLine());
            luggage[i][0] = Integer.parseInt(st.nextToken());
            luggage[i][1] = Integer.parseInt(st.nextToken());
        }

        cache = new int [weight+1][num];
    }

    void run(){

        for(int i=1;i<cache.length;i++){

            for(int j=0;j<cache[0].length;j++){
                
                if(j == 0){
                    
                    if(i>=luggage[j][0]) {
                        cache[i][j] = luggage[j][1];
                    }
                    continue;
                }

                if(i<luggage[j][0]){
                    cache[i][j] = cache[i][j-1];
                } else {
                    cache[i][j] = Math.max(cache[i][j-1], luggage[j][1] + cache[i-luggage[j][0]][j-1]);
                }
            }
        }

        System.out.println(cache[cache.length-1][cache[0].length-1]);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve12865 p = new solve12865(br);
        p.run();

        br.close();
    }
}