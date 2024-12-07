import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 구현해놓고 보면 되게 단순한데 생각보다 생각해내기 쉽지 않음
 * Stack을 써야되나 뭐 이런 생각도 드는데 그냥 재귀 호출만으로 해결 가능함
 * 
 * 이름에 맞게 연산(여기선 단순히 sb에 삽입)의 위치가 결정됨
 * 전위 - 왼쪽 오른쪽 자식으로 접근하기 전에 연산
 * 중위 - 왼쪽 자식 접근 오른쪽 자식 접근 전에 연산
 * 후위 - 왼쪽 오른쪽 자식 모두 접근 후 연산 
 */
class solve1991{

    int num;
    int [][] tree;
    int forIdx = 65;
    StringBuilder sb = new StringBuilder();

    solve1991(BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        tree = new int [num][2];

        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int parent = st.nextToken().charAt(0) - forIdx;
            int left = st.nextToken().charAt(0) - forIdx;
            int right = st.nextToken().charAt(0) - forIdx;

            tree[parent][0] = left;
            tree[parent][1] = right;
        }
    }

    void run(){

        pre(0);
        sb.append("\n");

        in(0);
        sb.append("\n");

        post(0);

        System.out.println(sb);
    }

    void pre(int now){

        sb.append((char) (now + forIdx));
        
        int [] childs = tree[now];
        if(childs[0] > 0) pre(childs[0]);
        if(childs[1] > 0) pre(childs[1]);
    }

    void in(int now){

        int [] childs = tree[now];

        if(childs[0] > 0) in(childs[0]);
        sb.append((char) (now + forIdx));
        if(childs[1] > 0) in(childs[1]);   
    }

    void post(int now){

        int [] childs = tree[now];

        if(childs[0] > 0) post(childs[0]);
        if(childs[1] > 0) post(childs[1]);
        sb.append((char) (now + forIdx));   
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
        solve1991 p = new solve1991(br);
        p.run();

        br.close();
    }
}