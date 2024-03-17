import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 문제를 헷갈리게 적어뒀음
 * 본인보다 큰 값이 있으면 가장 앞으로 보내는게 아니라
 * 본인보다 큰 값이 있으면 본인보다 큰 값중에 가장 앞에 있는 값의 바로 앞으로 보내는 것
 * 예를 들어 1 2 4 5 3 6 이면 3을 1 앞으로 보내는 것이 아니라 4 앞으로 보내는 것임
 * 
 * 근데 사실 직접 옮길필요 없이 순서대로 진행되는 경우 각 요소의 앞에 있는 것 중 본인보다 큰 값의 갯수의 합이
 * 옮겨지는 것의 갯수와 동일함
 */
class solve10431 {

    int [][]cases;

    int count = 0;
    void input(BufferedReader br) throws IOException{

        Integer numOfCase = Integer.parseInt(br.readLine());
        cases = new int [numOfCase + 1][20];

        StringTokenizer st;
        for(int i=0;i<numOfCase;i++){
            st = new StringTokenizer(br.readLine());
            int index = Integer.parseInt(st.nextToken());
            for(int j=0;j<20;j++){
                cases[index][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    int move(int index, int to, int from){

        int [] nowCase = cases[index];
        int now = nowCase[from];
        for(int i=from;i>to;i--){
            nowCase[i] = nowCase[i-1];
        }
        nowCase[to] = now; 
        
        return from - to;
    }


    int getCount(int index){

        int [] now = cases[index];
        
        count = 0;
        for(int i=1;i<20;i++){
            for(int j=0;j<i;j++){
                if(now[j] > now[i]){
                    count += move(index, j, i);
                }
            }
        }

        return count;
    }

    void run(BufferedReader br) throws IOException{

        input(br);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<cases.length;i++){
            sb.append(i).append(" ").append(getCount(i)).append("\n");
        }

        System.out.println(sb);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve10431 p = new solve10431();
        p.run(br);

        br.close();
    }
}


