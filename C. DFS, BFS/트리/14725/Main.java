import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 핵심
 * 1. 색다른 방식의 입력으로 트리 만들기
 * 2. child 출력하기 전에 정렬
 * 
 * 기존 방식과 다른 종류의 입력 타입이라 그 부분만 생각해내면 됨
 * 일단 여기선 root가 정해져있고 부모에서 자식 방향으로만 순회하면 되기 때문에 
 * 재귀적 클래스에 저장했음 트리 구성 순서는 다음과 같음
 * 1. root(now)의 childs 를 검사해서 value가 존재하는지 확인
 * 2-1. 존재하면 해당 노드 반환
 * 2-2. 존재하지 않으면 value로 새로운 노드 생성해서 추가 후 그 노드 반환
 * 3. 반환된 노드를 now에 저장
 * 4. 이를 leaf까지 반복
 * 
 * 같은 depth에 존재하면 사전 순으로 정렬해야 함
 * 근데 이걸 매 입력마다 수행하는건 비효율적이니까 출력하기 직전에 정렬하는 것으로
 */

class Node {

    String value;

    List<Node> childs = new ArrayList<>();

    Node (String value){
        this.value = value;
    }

    void addChild(Node child){
        childs.add(child);
    }

    boolean equals(String input){

        if(this.value.equals(input)){
            return true;
        }

        return false;
    }
}

class solve14725 {

    Node root = new Node("root");

    int numOfLeaf;

    solve14725 (BufferedReader br) throws IOException{

        int numOfLeaf = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfLeaf;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfData = Integer.parseInt(st.nextToken());

            Node now = root;
            for(int j=0;j<numOfData;j++){
                now = makeTree(now, st.nextToken());
            }   
        }
    }

    Node makeTree (Node now, String input){

        for(Node child : now.childs){

            if(child.equals(input)){
                return child;
            }
        }

        Node newChild = new Node(input);
        now.addChild(newChild);

        return newChild;
    }

    void run(){

        printTree(root, 0);
        System.out.println(sb);
    }

    StringBuilder sb = new StringBuilder();
    void printTree(Node now, int depth){

        Collections.sort(now.childs, (pre, pro) -> {
            return pre.value.compareTo(pro.value);
        });

        for(Node child : now.childs){
            
            for(int i=0;i<depth;i++){
                sb.append("--");
            }

            sb.append(child.value).append("\n");
            printTree(child, depth+1);
        }
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14725 p = new solve14725(br);
        p.run();
        
        br.close();
    }
}
