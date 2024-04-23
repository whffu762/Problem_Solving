import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 배열의 가운데 값 뽑고 그 가운데 값 기준으로 생기는 서브리스트의 가운데 값 뽑는 방식 
 * 병합 정렬 비슷하게 하면 되는데 리스트를 주고 받는 식으로 하니까 코드가 너무 지저분함
 * 
 * 리스트를 새로 생성한 이유가 같은 세대는 같은 리스트에 저장하기 위함이었음 한꺼번에 구해서 
 * 한꺼번에 저장하는 방식이었는데 depth를 유지하면 한꺼번에 구하지 않아도 됨...
 */
class solve9934{

    List<Integer> input = new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();

    void calculate(List<List<Integer>> subLists){

        List<Integer> sibilings = new ArrayList<>();
        List<List<Integer>> nextSubLists = new ArrayList<>();
        for(int i=0;i<subLists.size();i++){
            List<Integer> subList = subLists.get(i);
            
            if(subList.size() == 1){
                sibilings.add(subList.get(0));
                continue;
            }

            int n = subList.size() / 2;
            sibilings.add(subList.get(n));

            List<Integer> nextSubList1 = subList.subList(0, n);
            List<Integer> nextSubList2 = subList.subList(n+1, subList.size());
            
            nextSubLists.add(nextSubList1);
            nextSubLists.add(nextSubList2);
        }

        result.add(sibilings);

        if(subLists.get(0).size() == 1){
            return;
        }

        calculate(nextSubLists);
    }

    void run(BufferedReader br) throws IOException{

        int numOfN = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = (int) Math.pow(2, numOfN) - 1;
        for(int i=0;i<n;i++){
            input.add(Integer.parseInt(st.nextToken()));
        }
        
        List<List<Integer>> tmp = new ArrayList<>();
        tmp.add(input);
        calculate(tmp);

        for(int i=0;i<result.size();i++){
            List<Integer> list = result.get(i);
            for(int j=0;j<list.size();j++){
                System.out.print(list.get(j) + " ");
            }
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