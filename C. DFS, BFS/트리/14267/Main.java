import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 풀이 과정
 * 1. 부모 배열을 인접 리스트로 변환
 * 2. 칭찬 시작점을 tree에 유지
 * 3. 생성된 트리 dfs
 * 
 * 풀이는 간단함 근데 틀림 놓친 부분이 있음
 * 한 사람이 여러 번 칭찬받을 수 있음 즉
 * 2 2
 * -1 1
 * 2 4
 * 2 5
 * 위와 같이 2에 칭찬이 두 번 될 수 있음
 * 
 */

class Node {

    int value;
    List<Integer> childs = new ArrayList<>();    

    void addChild(int child){
        childs.add(child);
    }

    void setValue(int value){
        this.value += value;
    }

    List<Integer> getChilds (){
        return this.childs;
    }

    int getValue(){
        return this.value;
    }
}

class solve14267 {

    List<Node> tree = new ArrayList<>();
    int [] result;
    int root;

    solve14267(BufferedReader br) throws IOException{

        int numOfEmp;
        int numOfCompl;

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfEmp = Integer.parseInt(st.nextToken());
        numOfCompl = Integer.parseInt(st.nextToken());

        for(int i=0;i<numOfEmp+1;i++){
            tree.add(new Node());
        }
        result = new int [numOfEmp+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1;i<numOfEmp+1;i++){

            int now = Integer.parseInt(st.nextToken());
            if(now > 0){
                tree.get(now).addChild(i);
            } else {
                root = i;
            }
        }

        for(int i=0;i<numOfCompl;i++){

            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            tree.get(now).setValue(value);
        }
    }

    void run(){

        dfs(root, 0);

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<result.length;i++){
            sb.append(result[i]).append(" ");
        }
        System.out.println(sb);
    }

    void dfs(int nowIdx, int parentIdx){

        Node now = tree.get(nowIdx);
        result[nowIdx] = now.getValue() + result[parentIdx];

        for(int child : now.getChilds()){
            dfs(child, nowIdx);
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14267 p = new solve14267(br);
        p.run();

        br.close();
    }
}