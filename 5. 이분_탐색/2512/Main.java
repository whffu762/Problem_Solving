import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.List;

/*
내가 푼 방법
 * 발생하는 경우의 수가 두 가지임
 * 1. 할당할 수 있는 총 예산 >= 지역에서 요구한 예산의 합
 * 2. 할당할 수 있는 총 예산 < 지역에서 요구한 예산의 합
 * 
 * 1번의 경우 모든 지역에게 원하는 값을 나눠줄 수 있으므로 가장 많이 요구한 지역을 따라가면 됨
 * 
 * 2번의 경우 할당할 수 있는 최대한 해줘야 함
 * 그래서 일단 총 예산을 요구하는 지역의 갯수만큼 나눠서 각 지역에 나눠주고 그 이후 예산이
 * 더 필요한 지역과 요구한 예산보다 더 많이 받은 지역을 구분해서 처리하고 한 번 할당을 완료할 때마다 
 * 총 예산과 지역을 최신화하면서 예산을 모두 쓸 때까지 나눠줄 때까지 이 과정을 반복해야 함
 * 
 * 1. 더 나눠줄 수 있는지 확인
 * - 총 예산이 0인지 확인
 * - 총 예산 > 지역의 갯수 - 총 예산이 지역의 갯수보다 작으면 더 이상 나눠줄 수 없기 때문임
 *
 * 2. 우선 모든 지역에게 동일하게 나눠줌 
 * - tmp = 총 예산 / 지역의 갯수
 * - 최종 결과가 저정될 result에 tmp를 더해둠
 * - 총 예산에서 tmp * 지역의 갯수만큼 뺌
 * 
 * 3. 요구한 값보다 더 많이 받은 지역과 더 적게 받은 지역을 구분해서 처리함
 * - diff = 각 지역에서 요구한 값 - tmp
 * - diff < 0 (요구한 예산보다 더 많이 받은 경우) 
 * : 할당이 완료됐으니 더 받은 값은 총 예산에 돌려주고 List에서 제외
 * - diff > 0 (요구한 예산보다 더 적게 받은 경우)
 * : 할당이 덜 됐으니 List에 남겨둠 tmp가 제외된 diff의 값이 List에 남게됨
 * 
 * 4. 한 번 할당이 완료된 후 다시 1번부터 시작
 * - 총 예산은 할당해주고 남은 값이 저장되어 있음
 * - 요구하는 지역에는 할당이 완료된 지역은 제외됨 
 * - 할당이 더 필요한 지역만 남아있고 각 지역은 더 필요한 값만 저장되어 있음
 * 
 */

class Solve2512Mine{

    List<Integer> budgets = new ArrayList<>();
    Integer totalBudget;
    Integer result = 0;

    void input(BufferedReader br) throws IOException{
        
        int numOflocal = Integer.parseInt(br.readLine());
        StringTokenizer inputBudgets = new StringTokenizer(br.readLine());
        for(int i=0;i<numOflocal;i++){
            budgets.add(Integer.parseInt(inputBudgets.nextToken()));
        }
        Collections.sort(budgets);
        totalBudget = Integer.parseInt(br.readLine());
    }

    void init(){
        //일단 각 지역에 예산 분배
        int tmp = totalBudget / budgets.size();
        
        //분배된 값 저장
        result += tmp;
        
        //총 예산에서 분배된만큼 제외
        totalBudget -= tmp * budgets.size();
        
        budgets = budgets.stream()
            .map(budget -> {
                //각자 요구한 예산 - 분배받은 예산
                int diff = budget - tmp;
                
                //각자 요구한 예산보다 분배받은 예산이 더 크면
                if(diff < 0){
                    totalBudget -= diff;    //더 받은 만큼 총 예산에 돌려줌
                }
                return diff;
            }).filter(diff -> diff > 0)     //덜 받은 지역만 남겨서 다시 List 생성
            .collect(Collectors.toList());
    }

    int getSum(){
        return budgets.stream()
            .reduce(0, (a, b) -> a + b);
    }

    void run(BufferedReader br) throws IOException{
        
        input(br);

        if(getSum() > totalBudget){
            while(budgets.size() != 0 && totalBudget >= budgets.size()){    //더 나눠줄 수 있는지 확인
                init();
            }
        } 
        // 총 예산 >= 지역에서 요구한 예산의 합
        else {
            result = budgets.get(budgets.size()-1); 
        }
        
        System.out.println(result);
    }
}

/*
파라메트릭 서치(이진 탐색) 
요약하면 이진 탐색과 유사한 방식으로 연산됨 최초로 설정된 범위에서 최소값을 low 최대값을 high로
설정한 후 중앙값을 이용해서 연산한 후 그 결과값에 따라 low 혹은 high에 중앙값을 대입하는 방식으로 
범위를 좁혀나가는 알고리즘임 이거에 대한 자세한 설명은 notion 참고

위 방식과 마찬가지로 경우의 수는 두 가지임
총 예산 > 각 지역이 요구하는 예산의 합 : 위와 동일함
총 예산 < 각 지역이 요구하는 예산의 합 : 파라메트릭 서치를 이용

1. 필요한 값들을 입력받음
- 입력받으면서 budgets 중 최댓값과 budgets의 총합을 구함

2. 값 초기화
- low : 1(각 지역에 할당될 수 있는 최소 예산)
- high : maxBudget(각 지역에서 요구한 예산 중 최대값)

3. 이진 탐색 알고리즘
- low와 high의 중앙값 mid 연산
- mid를 기준으로 할당되는 예산 총합 계산
- 할당되는 예산 총합 > 총 예산 : mid를 low에 할당(다음 연산에 사용될 mid를 높이기 위함)
- 할당되는 예산 총합 < 총 예산 : mid를 high에 할당(다음 연산에 사용될 mid를 낮추기 위함)

여기서 중요한 점
- 위 과정을 low < high - 1 때 까지 진행함
이유는 low가 가질 수 있는 최댓값은 high - 1인 값임 왜냐면 high에 해당하는 상황은 
전부 총 예산 > 각 지역이 요구하는 예산의 합인 상황에서 걸러지기 때문임

- 결과값은 low임
이유는 여기서 구하는건 총 예산보다 작은 값을 도출해내는 값 중 최댓값이기 때문에 정답에 가까워질수록 low만 움직이게 됨
정답을 구한 상황에서 할당되는 예산 총합은 총 예산보다 작을 것이고 때문에 low 에 mid값이 할당됨

상한값을 구하냐 하한값을 구하냐에 따라 정답으로 취급할 값을 low 혹은 high로 적당히 정해야 함 
 */

class Solve2512BinarySearch {

    List<Integer> budgets = new ArrayList<>();
    Integer totalBudget;
    Integer result;
    Integer maxBudget = -1;
    Integer budgetsSum = 0;

    void input(BufferedReader br) throws IOException {

        int numOflocal = Integer.parseInt(br.readLine());
        StringTokenizer inputBudgets = new StringTokenizer(br.readLine());
        for (int i = 0; i < numOflocal; i++) {
            int input = Integer.parseInt(inputBudgets.nextToken());
            maxBudget = Math.max(input, maxBudget);
            budgetsSum += input;
            budgets.add(input);
        }

        totalBudget = Integer.parseInt(br.readLine());
    }

    int allocBudget(int upperLimit) {
        
        //mid 보다 작으면 그대로 더하고 mid보다 크면 mid를 더함
        return budgets.stream()
                .mapToInt(budget -> Math.min(budget, upperLimit))
                .sum();
    }

    void run(BufferedReader br) throws IOException {

        input(br);

        if (totalBudget >= budgetsSum) {
            result = maxBudget;
            System.out.println(result);
            return;
        }

        int low = 1;
        int high = maxBudget;

        while (low < high - 1) {
            int mid = (low + high) / 2;
            if (allocBudget(mid) > totalBudget) {
                high = mid;
            } else {
                low = mid;
            }
        }

        result = low;
        System.out.println(result);
    }
}


public class Main {
    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Solve2512Mine p = new Solve2512Mine();
        //Solve2512BinarySearch p = new Solve2512BinarySearch();
        p.run(br);

        br.close();
    }
}
