import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class solve1149{

    int num;
    int [][] costs;

    solve1149 (BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        costs = new int [num][3];

        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[i][0] = Integer.parseInt(st.nextToken());
            costs[i][1] = Integer.parseInt(st.nextToken());
            costs[i][2] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        for(int i=0;i<num;i++){

            int color = 0;
            int cost = 1001;

            for(int j=0;j<3;j++){

                if(cost < costs[i][j]){

                    
                }

            }   


        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1149 p = new solve1149(br);
        p.run();
        
        br.close();
    }
}