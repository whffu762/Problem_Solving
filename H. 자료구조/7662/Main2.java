import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * TreeMap을 이용하는 방법
 * 앞서 두 개의 PQ를 이용했는데 하나의 TreeMap으로 구현이 가능함
 * 
 * TreeMap은 Key를 정렬하는 Map임
 * - 양쪽을 다 접근 가능 firstKey(), lastKey()
 * - Map의 compute() 메소드를 이용하여 value 가 null이면 key도 사라짐
 * 이를 통해 갯수가 0이면 null로 취급해서 Map에서 아예 삭제하는 것으로 PQ를 대체
 * 
 * 이를 이용하면 해결되는 점
 * PQ는 우선순위가 가장 높은 값 하나만 접근이 가능했음 그래서 오름차순 내림차순을 각각 유지했는데
 * TreeMap은 Map이기 때문에 Key의 어느 방향이든 접근이 가능함
 * 
 * 아니 근데 이럴거면 애초에 List에 쓰지 왜 Map을 씀?
 * 라는 생각이 든다면 PQ를 왜 썼는지 다시 생각해보자.. 애초에 PQ는 왜 썼는가 정렬을 알아서 해주는 간편함도 있지만 
 * 시간 복잡도만 친다면 List는 값을 삭제할 때 연산이 너무 많기 때문에(N-1개의 연산) tree 형태로 저장함으로써 
 * 삭제 연산의 시간을 줄일 수 있는 것임
 * 
 * 그럼 Deque를 쓰면요?
 * Deque는 정렬을 못 함.. Comparator 인자를 받지 않음
 * 
 * 그래서 TreeMap을 쓰는 것임
 * - 값이 중복 가능하기 때문에 숫자의 갯수를 유지해야 함
 * - Key값을 tree로 관리해서 정렬 속도가 빠름
 */
class solve7662{

    TreeMap<Integer, Integer> treeMap = new TreeMap<>();

    solve7662(BufferedReader br) throws IOException{

        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            int value = Integer.parseInt(st.nextToken());

            if(command.equals("I")) treeMap.compute(value, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
            else getMinOrMax(value);
        }
    }

    void getMinOrMax(int value){

        if(treeMap.isEmpty()) return;

        treeMap.compute(value == 1 ? treeMap.lastKey() : treeMap.firstKey(), 
        (key, oldValue) -> oldValue == 1 ? null : oldValue - 1);
    }

    String run(){

        if(treeMap.isEmpty()) return "EMPTY";
        return treeMap.lastKey() + " " + treeMap.firstKey();
    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){
            solve7662 p = new solve7662(br);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}