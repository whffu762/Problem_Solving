import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
너비 우선 탐색인데 다른 방식으로 품..
 * 개요
 * 각 target의 root 까지의 모든 부모 노드를 배열에 저장하고 조건에 따라 결과를 달리 출력
 * 조건
 * 1. target1이 target2의 부모노드 중 하나인 경우(tmp2에 target1이 포함되는 경우)
 * 2. target2가 target1의 부모노드 중 하나인 경우(tmp1에 target2가 포함되는 경우)
 * 3. 서로가 서로의 부모노드가 아닌 경우(둘 다 아닌 경우)
 * 
 * 1번과 2번 조건인 경우 
 * 1촌씩 증가하기 때문에 tmp1 혹은 tmp2에 저장된 target1과 target2의 인덱스 차가 촌수에 해당함
 * 
 * 3번인 경우
 * tmp1과 tmp2에 저장된 root 가 같은 경우와 다른 경우로 분기됨
 * root 가 다른 경우 촌수를 계산할 수 없음
 * root 가 같은 경우 촌수를 계산
 * 계산하는 방식 
 * 서로 같은 부모노드를 가지는 곳까지의 노드만 남기고 두 배열의 노드의 개수를 더함
 * 
 * 입력 저장 방식
 * Map에 부모를 key 자식을 List에 넣어서 value로 저장 
 * 
 * 부모 노드 배열 저장 방식
 * 입력값(target) 부터 root 노드까지 저장
 * getParent(x)가 null 이면 더 이상 진행하지 않음
 * 
 * 
 */
class solve2644 {
    Map<Integer, List<Integer>> relation;
    int target1;
    int target2;

    //입력받는 방식이 Map에 부모와 자식으로 구성
    void input(BufferedReader br) throws IOException {
        int num_person = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        target1 = Integer.parseInt(st.nextToken());
        target2 = Integer.parseInt(st.nextToken());

        int num_relation = Integer.parseInt(br.readLine());
        relation = new HashMap<>();
        for (int i = 0; i < num_relation; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            if (relation.containsKey(parent)) {
                relation.get(parent).add(child);
            } else {
                List<Integer> childList = new ArrayList<>();
                childList.add(child);
                relation.put(parent, childList);
            }
        }
    }

    //입력의 부모를 구함
    Integer getParent(int x) {

        for (int parent : relation.keySet()) {
            if (relation.get(parent).contains(x)) {
                return parent;
            }
        }

        return null;
    }

    //target의 root까지의 부모 노드를 저장할 List
    List<Integer> tmp1 = new ArrayList<>();
    List<Integer> tmp2 = new ArrayList<>();

    //3번 조건의 결과를 연산
    int getResult() {

        List<Integer> common = new ArrayList<>();

        for (int x : tmp1) {
            if (tmp2.contains(x)) {
                common.add(x);
            }
        }

        for (int i : common) {
            tmp1.remove(tmp1.indexOf(i));
            tmp2.remove(tmp2.indexOf(i));
        }

        return tmp1.size() + tmp2.size();
    }

    void run(BufferedReader br) throws IOException {
        
        input(br);

        //각 target의 root 까지의 부모 노드 배열 저장
        Integer p1 = target1;
        Integer p2 = target2;

        while (true) {
            if (p1 == p2 && p1 == null) {
                break;
            }

            if (p1 != null) {
                tmp1.add(p1);
                p1 = getParent(p1);
            }

            if (p2 != null) {
                tmp2.add(p2);
                p2 = getParent(p2);
            }
        }

        // 입력의 종류에 따라 다른 결과 출력
        int result;
        if (tmp1.contains(target2)) {
            result = tmp1.indexOf(target2);
        }
        else if (tmp2.contains(target1)) {
            result = tmp2.indexOf(target1);
        }
        else {
            if (tmp1.get(tmp1.size() - 1) != tmp2.get(tmp2.size() - 1)) {
                result = -1;
            } else {
                result = getResult();
            }
        }

        System.out.println(result);
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2644 p = new solve2644();
        p.run(br);

        br.close();
    }
}
