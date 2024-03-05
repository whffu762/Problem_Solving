import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.List;

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
            maxBudget = Math.max(input, maxBudget); //요구하는 예산 중 최댓값 구하기
            budgetsSum += input;    //요구하는 예산의 총합 구하기
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

        //요구하는 예산의 총합 <= 총 예산인 경우
        if (totalBudget >= budgetsSum) {
            result = maxBudget;
            System.out.println(result);
            return;
        }

        //이진 탐색에 이용할 초기값 지정
        int low = 1;
        int high = maxBudget;

        while (low < high - 1) {

            int mid = (low + high) / 2; //기준값 구하기

            if (allocBudget(mid) > totalBudget) {
                high = mid;     //배정된 예산 > 총 예산 - 기준값을 낮추기 위해 high를 낮춤
            } else {
                low = mid;      //배정된 예산 < 총 예산 - 기준값을 높이기 위해 low를 높임
            }
        }

        result = low;       //상한값을 구하는 것이기 때문에 low가 최종적으로 움직이게 됨 이유는 위에 적혀있음
        System.out.println(result);
    }
}


public class Main2 {
    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Solve2512BinarySearch p = new Solve2512BinarySearch();
        p.run(br);

        br.close();
    }
}
