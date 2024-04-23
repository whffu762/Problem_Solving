import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

/*
파라메트릭 서치(이진 탐색) 

1. 필요한 값들을 입력받음
- 입력받으면서 budgets 중 최댓값과 budgets의 총합을 구함

2. 값 초기화
- low : 1(각 지역에 할당될 수 있는 최소 예산)
- high : maxBudget(각 지역에서 요구한 예산 중 최대값)

3. 이진 탐색 알고리즘
- low와 high의 중앙값 mid 연산
- mid를 기준으로 할당되는 예산 총합 계산
- 할당되는 예산 총합 > 총 예산 : low에 mid + 1을 할당(다음 연산에 사용될 mid를 높이기 위함)
- 할당되는 예산 총합 < 총 예산 : high에 mid - 1을 할당(다음 연산에 사용될 mid를 낮추기 위함)

여기서 중요한 점
- 위 과정을 low <= high 때 까지 진행함
low 와 high가 만나는 지점까지 검사하고 그에 따라 결과가 결정됨

- 가능한 예산 중 최댓값을 구해야 함
high가 결과값으로 이용됨 하지만 여기선 더 직관적으로 제시된 조건 총 예산보다 더 적거나 같은 예산을 이용한
경우에 result에 그 결과를 저장하는 방식을 이용
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

        //이진 탐색에 이용할 초기값 지정
        int low = 1;
        int high = maxBudget;
        int result = 0;

        while (low <= high) {

            int mid = (low + high) / 2; //기준값 구하기

            if (allocBudget(mid) <= totalBudget) {
                result = mid;
                low = mid + 1;     //배정된 예산 <= 총 예산 - 기준값을 높이기 위해 low를 높임
            } else {
                high = mid - 1;      //배정된 예산 < 총 예산 - 기준값을 낮추기 위해 high를 낮춤
            }
        }

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
