import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백트랙킹 비스무리한 거 + 완전 탐색 이용하는 문제 (2529번과 비슷한 알고리즘)
 * 
 * 동작 흐름
 * 1. 0번째 치킨집부터 순서대로 open
 * 2. 계속 open 하다가 열린 지점의 수와 M이 같으면 거리 계산
 * 3. 거리 계산 : 모든 집부터 치킨 지점까지의 거리 중 최솟값의 합
 * 4. 거리 비교 : 저장된 값과 거리 계산의 결과를 비교해서 최솟값 저장
 * 
 * 
 * 3번부턴 별거 아닌데 1, 2번 구현이 매우 어려움 모든 경우의 수를 탐색해야 하기 때문에 어떻게 진행시킬지가 중요함 
 * 이 부분은 노션 백 트랙킹 부분 참고
 */

class solve15686{

    int numOfChicken;
    List<int []> chickens = new ArrayList<>();
    List<int []> homes = new ArrayList<>();

    int result = Integer.MAX_VALUE;
    boolean [] opendStore;

    solve15686(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        int sizeOfMap = Integer.parseInt(st.nextToken());
        numOfChicken = Integer.parseInt(st.nextToken());
        
        for(int i=0;i<sizeOfMap;i++){
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<sizeOfMap;j++){

                String now = st.nextToken();

                if(now.equals("1")){
                    homes.add(new int [] {i, j});
                }

                if(now.equals("2")){
                    chickens.add(new int [] {i, j});
                }
            }
        }

        opendStore = new boolean [chickens.size()];
    }

    void run(){

        dfs(0, 0);

        System.out.println(result);

    
    }

    void dfs(int start, int numOfOpened){

        if(numOfOpened == numOfChicken){    //선택된 지점의 갯수와 입력값이 동일하면 거리 계산

            int totalChickenDistance = 0;
            for(int i=0;i<homes.size();i++){

                int eachChickenDistanceTMP = Integer.MAX_VALUE;
                for(int j=0;j<chickens.size();j++){

                    if(opendStore[j]){
                        
                        int eachChickenDistance = Math.abs(homes.get(i)[0] - chickens.get(j)[0]) + Math.abs(homes.get(i)[1] - chickens.get(j)[1]);
                        eachChickenDistanceTMP = Math.min(eachChickenDistance, eachChickenDistanceTMP);
                    }
                }

                totalChickenDistance += eachChickenDistanceTMP;
            }

            result = Math.min(result, totalChickenDistance);
        }

        //이게 핵심 이걸로 치킨집이 열릴 수 있는 모든 경우의 수를 확인할 수 있음
        for(int i=start;i<chickens.size();i++){
            opendStore[i] = true;
            dfs(i + 1, numOfOpened + 1);
            opendStore[i] = false;
        }
    }
}

public class Main{

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve15686 p = new solve15686(br);
        p.run();

        br.close();
    }
}