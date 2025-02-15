import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


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