import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/*
 * BFS를 이용한 간단한 문제
 */
class Solution {

	boolean[][] visited;
	int[] moveX = { -1, 1, 0, 0 };
	int[] moveY = { 0, 0, -1, 1 };

	public int[] solution(String[] maps) {

		visited = new boolean[maps.length][maps[0].length()];

		List<Integer> result = new ArrayList<>();
		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				if (checkValid(new int[] { i, j }, maps)) {
					result.add(bfs(new int[] { i, j }, maps));
				}
			}
		}

		if (result.isEmpty()) {
			return new int[] { -1 };
		}

		return result.stream().sorted().mapToInt(Integer::intValue).toArray();
	}

	boolean checkValid(int[] next, String[] maps) {

		if (-1 < next[0] && next[0] < maps.length && -1 < next[1] && next[1] < maps[0].length()) {

			return maps[next[0]].charAt(next[1]) != 'X' && !visited[next[0]][next[1]];
		}

		return false;
	}

	int bfs(int[] start, String[] maps) {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start[0]][start[1]] = true;
		int sum = 0;

		while (!queue.isEmpty()) {

			int[] now = queue.poll();
			sum += Integer.parseInt(String.valueOf(maps[now[0]].charAt(now[1])));

			for (int i = 0; i < moveX.length; i++) {

				int[] next = { now[0] + moveX[i], now[1] + moveY[i] };
				if (checkValid(next, maps)) {
					visited[next[0]][next[1]] = true;
					queue.add(next);
				}
			}
		}

		return sum;
	}
}