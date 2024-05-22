import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
 * 두 가지 값을 구해야 함
 * - 등수 (target 보다 큰 값의 갯수)
 * - ranking 내에서의 위치 (target 보다 크거나 같은 값의 갯수)
 * 
 * ranking 내에서의 위치가 ranking의 크기보다 
 * - 크거나 같으면 -1
 * - 작으면 등수를 출력함
 */
class solve1205{

    List<Integer> ranking;
    int target;
    int sizeOfRanking;
    
    solve1205(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfInit = Integer.parseInt(st.nextToken());
        ranking = new ArrayList<>();

        target = Integer.parseInt(st.nextToken());
        sizeOfRanking = Integer.parseInt(st.nextToken());

        if(numOfInit > 0){
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<numOfInit;i++){
                ranking.add(Integer.parseInt(st.nextToken()));
            }
        }
    }

    void run(){

        int rank = 1;
        int index = 0;

        for(int score : ranking){

            if(target < score){
                rank++;
            }

            if(target <= score){
                index++;
            }
        }

        if(index >= sizeOfRanking){
            rank = -1;
        }

        System.out.println(rank);
    }
}


public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1205 p = new solve1205(br);
        p.run();

        br.close();

    }
}