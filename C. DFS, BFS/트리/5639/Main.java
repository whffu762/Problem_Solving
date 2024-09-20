import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * 새로운 유형임
 * 주목할 점
 * - 갯수가 정해지지 않았을 때 입력이 끝날 때까지 입력받기
 * - 트리를 저장하는 방식
 * 
 * 문제를 위해 해야할 건 두 가지였음
 * 1. 전위 탐색을 기반으로 트리 생성
 * 2. 생성된 트리로 후위 탐색
 * 되게 헷갈렸지만 트리를 저장하는 방식 하나만 이해하면 풀 수 있음 이걸 전위 탐색을 기반으로 후위 탐색으로 
 * 바로 넘어갈수도 있긴한데 그건 넘어가자
 * 
 * 갯수가 정해지지 않았을 때
 * 보통 여러 개가 입력되면 그 갯수가 정해지는데 이건 바로 입력부터 시작됨 그렇다고 입력이 끝난 것을 표시해주는 것도
 * 아니라서 입력이 끝났음을 알아서 판단해야 함 
 * br.readLine()의 결과물이 null 일 때 까지 입력받으면 됨 아래 구현에선 입력 결과가 "" 인 것도 확인했는데 이건 직접
 * 테스트 코드 입력할 때 필요한거고 답안 제출할 땐 없어도 정답처리 됨 
 * 
 * 트리를 저장하는 방식
 * 재귀적 클래스를 이용함
 * 클래스의 필드로 본인의 인스턴스를 가지는 형태로 LinkedList를 직접 구현하는 형태라고 생각하면 편함 본인을 필드로 가지기 때문에
 * 하나의 인스턴스만 있으면 모든 노드로 접근할 수 있음 (그 하나의 노드가 root 임) 
 */

class Node {

    int value;
    
    Node [] childs = new Node[2];

    Node(int value){
        this.value = value;
    }

    Node getLeft(){
        return childs[0];
    }

    Node getRight() {
        return childs[1];
    }

    void setLeft(Node left){
        this.childs[0] = left;
    }

    void setRight(Node right){
        this.childs[1] = right;
    }
}

class sovle5639{
    
    Node root;
    List<Integer> result = new ArrayList<>();

    sovle5639 (BufferedReader br) throws IOException{
        
        root = new Node(Integer.parseInt(br.readLine()));

        while(true){

            String tmp = br.readLine();
            if(tmp == null){
                break;
            }

            int input = Integer.parseInt(tmp);
            makeTree(root, input);
        }
    }

    void run(){

        dfs(root);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<result.size();i++){
            sb.append(result.get(i)).append("\n");
        }
        System.out.println(sb);

    }

    void dfs(Node now){

        for(int i=0;i<2;i++){
            if(now.childs[i] != null){
                dfs(now.childs[i]);
            }
        }
        result.add(now.value);
    }

    void makeTree(Node nowNode, int input){

        Node left = nowNode.getLeft();
        Node right = nowNode.getRight();

        if(input < nowNode.value){

            if(left == null){
                nowNode.setLeft(new Node(input));
            } else{
                makeTree(left, input);
            }

        } else {

            if(right == null){
                nowNode.setRight(new Node(input));
            } else {
                makeTree(right, input);
            }
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        sovle5639 p = new sovle5639(br);
        p.run();

        br.close();
    }
}