import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/*
 * 문제가 너무 쉬우니까 자료구조에 대해 좀 더 알아보자
 * .compute() 메소드를 통해 없으면 1로 초기화하고 있으면 기존 값에 1을 더하는 기능 구현
 * 
 *  Map을 정렬
 *  key 값으로 정렬하려면 TreeMap을 이용하면 되긴 함 근데 여기선 value 값을 기준으로 정렬해야 하는데
 *  그런 자료구조가 존재하지 않음 직접 구현해야 함
 *  1. Map의 key-value 쌍을 Set으로 추출할 수 있음 .entrySet() -> 반환형은 Set<Map.Entry<K,V>>
 *  2. Set은 정렬 할 수 없음 List로 바꿔줘야 함
 *  3. ArrayList의 생성자 인자로 Set을 넣어줄 수 있음 이걸로 Set -> List로 쉽게 변환
 *  4. Map.Entry.getValue()로 value 값에 접근 가능, getKey()로 key값에 접근 가능
 *  5. Comparator는 반드시 int를 반환해야 함 그래서 compareTo()로 비교해야 함
 */
class Solution {
	public int solution(int k, int[] tangerine) {

		Map<Integer, Long> count = new LinkedHashMap<>();
		for (int weight : tangerine) {
			count.compute(weight, (key, oldValue) -> {
				return (oldValue == null) ? 1 : oldValue + 1;
			});
		}

		List<Map.Entry<Integer, Long>> values = new ArrayList<>(count.entrySet());
		Collections.sort(values, (after, before) -> {
			return before.getValue().compareTo(after.getValue());
		});

		int answer = 0;
		long limit = 0;
		for (Map.Entry<Integer, Long> entry : values) {
			limit += entry.getValue();
			answer++;

			if (limit >= k) {
				break;
			}
		}

		return answer;
	}
}