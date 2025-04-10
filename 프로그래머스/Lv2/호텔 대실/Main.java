import java.util.Arrays;

/*
 * 그리드 이용
 * 1. 시작 시간 오름차순으로 정렬 후 0번째 값을 rooms 배열에 저장
 * 2. N번째의 끝나는 시간+10이 N+1번째의 시작하는 시간보다 크면 해당 값을 대체
 * 2-2. rooms 배열을 모두 돌았는데도 없다면 rooms의 마지막에 추가
 * 3. 모두 다 돌았을 때 rooms에 저장된 갯수가 필요한 방임
 * 
 * 가장 먼저 시작되는 것부터 채워넣어주면 되는 간단한 문제
 */
class Solution {
	public int solution(String[][] book_time) {
		int answer = 0;

		int[][] times = new int[book_time.length][2];
		for (int i = 0; i < book_time.length; i++) {
			for (int j = 0; j < 2; j++) {

				String[] tmp = book_time[i][j].split(":");
				times[i][j] = Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
			}
		}

		Arrays.sort(times, (after, before) -> {

			if (after[0] == before[0]) {
				return after[1] - before[1];
			}
			return after[0] - before[0];
		});

		int[] rooms = new int[times.length];
		rooms[0] = times[0][1];
		int count = 1;

		for (int i = 1; i < times.length; i++) {
			if (!checkValid(rooms, times[i], count)) {
				rooms[count++] = times[i][1];
			}
		}

		return count;
	}

	boolean checkValid(int[] rooms, int[] time, int count) {

		for (int i = 0; i < count; i++) {

			if (rooms[i] + 10 <= time[0]) {
				rooms[i] = time[1];
				return true;
			}
		}

		return false;
	}
}