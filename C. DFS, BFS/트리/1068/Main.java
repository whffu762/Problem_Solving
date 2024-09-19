import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * DFS를 이용한 간단한 문제
 * 주목할 점은 두 가지
 * 1. 실제로 노드를 지우는 방식은 비효율적임
 * 2. 트리에서 노드를 지울 때 주의할 점
 * 3. 예외 처리를 최소화하는 flag
 * 
 * 실제로 노드를 지우게 되면
 * 문제에서 해당 노드를 지우랬다고 실제로 노드를 지우고 리프 노드를 세면 dfs를 두 번 수행함
 * 비효율적임 현재 문제에선 통과될지 몰라도 입력값이 늘어나거나 하면 시간 초과가 발생할 것임
 * (안 그래도 비효율적인 재귀 호출을 두번이나..) 그래서 실제로 지우진 않고 해당 노드는 건너뛰는
 * 조건식을 이용해 무시하고 리프 노드를 카운트 하는 식으로 진행되야 함
 * 
 * 트리에서 노드르 지울 때 주의할 점
 * 노드를 지울 때 현재 노드가 없어진 경우도 다시 확인해야 함 현재 상황에서 cut 노드를 지웠다고 가정했을 때
 * 그 부모가 리프 노드가 되는지를 판단해야 한다는 의미임
 * 
 * 예외 처리 최소화하는 flag
 * 아래 코드를 보면 내가 작성한 코드에선 여러 예외 사항을 처리하기 위해 여러 조건문이 포함되어 있음
 * 많지는 않지만 코드가 지저분함 이걸 flag를 이용해서 처리할 수 있음
 * 리프 노드란건 자식이 없는 것을 의미함 그래서 시작할 땐 항상 true로 했다가 자식이 포함되어 있으면
 * false로 바꾸는 방식으로 구현하면 훨씬 깔끔해짐
 * 다만 주의할 점은 루트 노드가 제거된 경우엔 감지하지 못 함 그래서 따로 처리해야 함
 * 하지만 내 방법으로 하면 루트 노드건 뭐건 상관없이 동작함 그래서 사용하는 조건문의 갯수는 동일함
 * 시간도 동일하고 해서 코드의 가독성 정도인듯 
 */
class solve1068{

    int numOfNodes;
    int cut;
    int root;
    List<List<Integer>> tree = new ArrayList<>();

    solve1068(BufferedReader br) throws IOException{
        
        numOfNodes = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<numOfNodes;i++){
            tree.add(new ArrayList<>());
        }

        
        for(int i=0;i<numOfNodes;i++){

            int now = Integer.parseInt(st.nextToken());       
            if(now == -1){
                root = i;
                continue;
            }
            tree.get(now).add(i);
        }
        cut = Integer.parseInt(br.readLine());
    }

    int result;
    void run(){

        if(cut != root){
            dfs(root);
        }

        //dfs(-1, root);
        System.out.println(result);

    }

    //더 깔끔한 코드
    void dfs(int now){

        boolean isLeaf = true;
        List<Integer> childs = tree.get(now);
        
        for(int i=0;i<childs.size();i++){
            if(childs.get(i) == cut){
                continue;
            }
            isLeaf = false;
            dfs(childs.get(i));
        }
        
        if(isLeaf){
            result++;
        }
    }

    //내가 통과한 코드
    void dfs(int parent, int now){

        //제거되면 그냥 통과
        if(now == cut){
            //현재 접근한 노드가 루트가 아니고 현재의 부모가 나 말곤 자식이 없으면 내 부모가 리프 노드임
            if(parent != -1 && tree.get(parent).size() == 1){
                result++;
            }
            return;
        }

        //현재 접근한 노드의 자식이 없으면 리프 노드임
        List<Integer> childs = tree.get(now);
        if(childs.isEmpty()){
            result++;
            return ;
        }
        
        for(int i=0;i<childs.size();i++){
            dfs(now, childs.get(i));
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1068 p = new solve1068(br);
        p.run();

        br.close();
    }
}