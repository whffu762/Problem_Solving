import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 아주 어려운 DP 이게 DP인줄도 몰랐다 야..
 * 
 * 두 가지 경우의 수가 있음
 * 1. A가 얼리어댑터인 경우 그의 이웃들은 얼리어댑터일 수도 있고 아닐 수도 있음
 * 2. A가 얼리어댑터가 아닌 경우 그의 이웃들은 반드시 얼리어댑터임
 * 이 두가지 경우의 수를 모두 계산한 다음 그 중 최솟값을 적용하는 방식임
 * 이를 위해선 서브 트리부터 진행되야 함 리프 노드는 어차피 0 아니면 1이니까 생략하고
 * 두 개의 리프를 가지는 서브 트리를 예로 들면
 * 해당 노드에서 최소 얼리어댑터 수는 
 * 두 리프 노드가 얼리어댑터인 경우 VS 부모 노드에서 얼리어댑터인 경우 + 리프는 아닐수도 일 수도 있는 경우 중 최소
 * 이 두 가지를 비교하는 것임 모든 노드에 대해 비교하면 됨
 */
class solve2533{

    int numOfNodes;
    List<List<Integer>> list = new ArrayList<>();
    int [][] cache;

    solve2533 (BufferedReader br) throws IOException{
        
    
        numOfNodes = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfNodes+1;i++){
            list.add(new ArrayList<>());
        }

        cache = new int [numOfNodes+1][2];

        for(int i=0;i<numOfNodes-1;i++){
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int tmp = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            
            list.get(tmp).add(tmp2);
            list.get(tmp2).add(tmp);
        }

    }

    void run(){

        makeTree(1, -1);
        System.out.println(Math.min(cache[1][0], cache[1][1]));
    }

    void makeTree(int now, int parent){

        cache[now][0] = 0;
        cache[now][1] = 1;

        List<Integer> childs = list.get(now);

        for(int child : childs){

            if(child != parent){

                makeTree(child, now);
                cache[now][0] += cache[child][1];
                cache[now][1] += Math.min(cache[child][0], cache[child][1]);
            }
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2533 p = new solve2533(br);
        p.run();

        br.close();
    }
}