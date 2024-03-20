import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.List;

/*
 * 시간 초과 떴지만 뭔가 아까운 코드
 * 
 * 동작 방식은 모든 경우의 수를 검사하는 방식임
 * 간단할 거라 생각했지만 생각보다 어려웠음 각 그룹이 조합되는 경우의 수를
 * 전부 확인해야 하기 때문에 두 가지의 루프가 필요함
 *  1. 조합되는 그룹의 원소의 갯수
 *  2. 그룹의 원소
 * 
 * group1 : A, B, C
 * group2 : D, E
 * group3 : F, G, H, I
 * group4 : J, K, L, M
 *
 * 예를 들어 위 예시에서 3개를 이용해 조합하는 경우
 * (group1, group2, group3)
 * (group1, group2, group4)
 * (group1, group3, group4)
 * (group2, group3, group4)
 * 
 * 위 처럼 그룹의 원소까지 루프를 이용해 순회해야 함
 * 그래서 0 0 0 0 ~ 4 4 4 4 순회하면서 유효한 조합만 검사하는 방식으로 구현
 * 
 *
 * 
 * 
 * 
 * 
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

        int result = 0;
        //조합에 이용되는 원소의 갯수를 i로 활용해 반복
        for(int i=1;i<allOfItems.size()+1;i++){
            result += computeNumOfCase(i);
        }

        return result;
    }

    int computeNumOfCase(int numOfItem){

        //조합될 그룹을 index[]에 저장
        int [] index = new int [numOfItem];
        for(int i=0;i<index.length;i++){
            index[i] = i;
        }

        int maxIndex = allOfItems.size() - 1;

        int result = 0;
        while(true){
            //유효한 그룹의 조합만 검사사
            if(checkValidIndex(index)){
                int tmp = 1;
                for(int i=0;i<index.length;i++){
                    tmp *= allOfItems.get(index[i]).size();
                }
                
                result += tmp;
            }

            /*
                조합 연산 -> 검사 -> 인덱스 변경 이 순서임
                이상하게 여기서 시간을 엄청 잡아먹음
                while이랑 if 쓸 땐 먼저 연산 순서부터 생각하고 짜라..
            */
            //모든 조합이 검사됐는지 확인
            if(!checkFinish(index, maxIndex)){
                //조합을 1씩 증가
                updateIndex(index, maxIndex);
            } else {
                break;
            }
        }

        return result;
    }

    boolean checkValidIndex(int [] index){

        for(int i=1;i<index.length;i++){
            //오름차순인지 확인
            if(index[i-1] > index[i]){
                return false;
            }

            //중복값이 있는지 확인
            if(index[i-1] == index[i]){
                return false;
            }
        }

        return true;
    }

    boolean checkFinish(int [] index, int maxIndex){
        
        for(int i=0;i<index.length;i++){
            if(index[i] != maxIndex){
                return false;
            }
        }
        
        return true;
    }

    //이게 핵심
    /*
     * 00...0 ~ NN...N 까지 순회할 수 있도록 하는게 생각보다 훨신 어려움
     * 노션 참고
     */
    void updateIndex(int [] index, int maxIndex){

        int now = index.length - 1;
        while(-1 < now && index[now] == maxIndex){
            index[now] = 0;
            now--;
        }
        if(-1 < now){
            index[now]++;
        }
    }
}

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9375 p = new solve9375();
        p.run(br);

        br.close();
    }
}


