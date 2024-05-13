import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;

//limit > end의 갯수
class solve20922{

    int limit;
    List<Integer> input = new ArrayList<>();
    int [] count;
    boolean [] visited;

    solve20922(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i=0;i<length;i++){
            input.add(Integer.parseInt(st.nextToken()));
            max = Math.max(max, input.get(i));
        }

        visited = new boolean[length];
        count = new int [max+1];
    }

    void run(){

        int result = 0;

        int start = 0;
        int end = 0;

        while(end != input.size()){

            int now = input.get(end);
            int numOfNow = count[now];

            if(!visited[end]){
                visited[end] = true;
                numOfNow = ++count[now];
            }

            if(numOfNow > limit){     
                result = Math.max(result, end - start);   
                count[input.get(start)]--;
                start++;
            } else {
                end++;
            }
        }

        result = Math.max(result, end - start);
        System.out.println(result);
    }


}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve20922 p = new solve20922(br);
        p.run();

        br.close();
    }
}