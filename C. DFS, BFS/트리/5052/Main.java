import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List; 

/*
 * 전화번호를 이용해 트리를 구성한 다음 Leaf 노드의 갯수를 이용해 일관성 확인
 * leaf 노드의 갯수가 전화번호와 같다면 일관성이 있는 것이고 다르다면 일관성이 없는 것임
 * 
 * 트리 문제의 핵심은 데이터를 트리 구조로 저장하는 방법임
 * 주의할 점
 * 1. 트리의 자식의 갯수가 정해지지 않음
 * 2. 여러 개의 트리가 존재할 수 있음
 * 
 * 
 * 트리의 자식의 갯수가 정해지지 않음
 * value와 next로 구성되는 Tel 클래스임 next는 List<Tel>로 다음 노드의 정보가 포함되어 있음
 * 이렇게 List<>로 next를 정의함으로써 여러 개의 Tel을 저장할 수 있고 조회하기도 편함
 * 
 * 여러 개의 트리가 존재할 수 있음
 * 전화번호의 시작 숫자가 다른 번호는 다른 트리를 구성해야 함
 * 자식의 갯수가 정해지지 않은 것과 동일함 다만 둘의 차이점은 Tel의 필드냐 run()을 위한 필드냐의 차이임
 * 핵심은 이것도 List<Tel>로 정의함으로써 두 가지 동작을 하나의 메소드로 구현할 수 있음
 * 1. 전화번호의 시작점에 따라 각각의 트리를 생성할 수 있음
 * 2. 전화번호의 시작점이 같은 번호지만 이후가 다른 번호도 추가할 수 있음
 * 아름답다..
 */
class Tel {

    char value;
    List<Tel> next = new ArrayList<>();

    Tel (char value){

        this.value = value;
    }
}

class solve5052{

    int numOfTel;
    List<Tel> trees = new ArrayList<>();
    int numOfLeaf;

    solve5052 (BufferedReader br) throws IOException{

        numOfTel = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfTel;i++){

            String input = br.readLine();
            makeTree(trees, input, 0);
        }
    }

    void makeTree (List<Tel> tels, String input, int depth){

        if(input.length() == depth){
            return;
        }

        Tel nowTel = checkExist(tels, input.charAt(depth));
        
        if(nowTel != null){
        
            makeTree(nowTel.next, input, depth+1);
        } else {
        
            Tel newTel = new Tel(input.charAt(depth));
            tels.add(newTel);
            makeTree(newTel.next, input, depth+1);
        }
    }

    Tel checkExist(List<Tel> tels, char now){

        for(Tel tel : tels){

            if(tel.value == now){
                return tel;
            }
        }

        return null;
    }

    String run(){

        for(Tel tel : trees){
            dfs(tel);
        }

        if(numOfLeaf == numOfTel){
            return "YES";
        } else {
            return "NO";
        }
    }

    void dfs(Tel tel){

        if(tel.next.isEmpty()){
            numOfLeaf++;
        }

        for(Tel nextTel : tel.next){
            dfs(nextTel);
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int numOfCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfCase;i++){
            solve5052 p = new solve5052(br);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}