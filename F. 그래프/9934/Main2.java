import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/*
 * 재귀 함수에서 호출을 여러번 하는 상황에서 첫번째 재귀 호출에서 return 되면 이 전에 입력된 파라미터를 이용함
 * 자세한 건 노션 참고 
 * 
 * 그래프의 depth를 인덱스로 활용하면 같은 세대의 요소들로만 묶어서 저장할 수 있음
 */


class solve9934{

    int numOfN;
    List<Integer> input = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    void calculate(int start, int end, int depth){

        if(depth == numOfN){
            return;
        }

        int mid = (start + end) / 2;

        result.get(depth).add(input.get(mid));

        calculate(start, mid - 1, depth + 1);
        calculate(mid + 1, end, depth + 1);
    }

    void run(BufferedReader br) throws IOException{

        numOfN = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfN;i++){
            result.add(new ArrayList<>());
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = (int) Math.pow(2, numOfN) - 1;
        for(int i=0;i<n;i++){
            input.add(Integer.parseInt(st.nextToken()));
        }
        
        calculate(0, input.size() - 1, 0);

        for(int i=0;i<result.size();i++){
            
            result.get(i).stream()
                .forEach(e -> System.out.print(e + " "));
            System.out.println();
        }
    }
}

class Main{

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9934 p = new solve9934();
        p.run(br);

        br.close();
    }
}