import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.List;

/*
 * 모든 경우의 수 구하는 방법
 * 그룹이 두 개일 경우엔 각 갯수를 곱하기만 하면 되지만 이 문제의 경우엔
 * 그룹의 조합되는 경우의 수도 생각해야 함
 * 
 * group1 : A, B, C
 * group2 : D, E
 * group3 : F, G, H, I
 * 
 * 예를 들어 위 예시의 경우
 * 한 개의 그룹으로 구성된 조합 : group1, group2, group3
 * 두 개의 그룹으로 구성된 조합 : (group1, group2), (group1, group3), (group2, group3)
 * 세 개의 그룹으로 구성되 조합 : (group1, group2, group3)
 * 
 * 이 경우의 수를 모두 구해야 함 하지만 각각의 조합을 하나 하나 계산하는건 시간이 너무 오래걸림
 * 
 * 이럴 경우 각각의 그룹을 선택하지 않는 경우까지 그룹의 원소로 취급하여 계산하면 간단하게 구할 수 있음
 * group1 : A, B, C, Null
 * group2 : D, E, Null
 * group3 : F, G, H, I, Null
 * 
 * 이렇게 함으로써 (group1, group2, group3) 이 조합으로 이루어질 수 있는 경우의 수만 구하면 모든 경우의 수를 구할 수 있음
 * 주의할 점은 Null만 선택되는 경우 1가지를 빼줘야 함
 * 
 * 번외로 모든 경우의 수를 구하는 방식도 구현했지만 시간 초과가 발생함
 * 000...0 ~ NNN...N 까지 모두 순회하는 방식은 Main2와 노션 참고
 */

class solve9375 {

    List<List<String>> allOfItems;

    void run(BufferedReader br) throws IOException{

        int numOfCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfCase;i++){
            input(br);
            sb.append(getCases()).append("\n");
        }

        System.out.println(sb);
    }

    void input(BufferedReader br) throws IOException{

        Map<String, List<String>> items = new HashMap<>();

        int numOfClothes = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i=0;i<numOfClothes;i++){
            st = new StringTokenizer(br.readLine());
            String item = st.nextToken();
            String type = st.nextToken();

            items.computeIfAbsent(type, key -> new ArrayList<>()).add(item);
        }

        allOfItems = new ArrayList<>(items.values());
    }

    int getCases() {

        int result = allOfItems.stream()
            .mapToInt(list -> list.size() + 1)
            .reduce(1, (x, y) -> x * y);
        
        return result - 1;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9375 p = new solve9375();
        p.run(br);

        br.close();
    }
}