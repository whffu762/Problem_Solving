import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 경로가 +n, *2, *3 이 세 길이 존재하는 그래프에서의 최단 거리를 구하는 문제라고 생각하면 편함
 * 가중치는 1이기 때문에 BFS를 이용하면 됨
 */
class Solution {
	public int solution(int x, int y, int n) {

		int answer = -1;

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { x, 0 });
		boolean[] visited = new boolean[y + 1];

		while (!queue.isEmpty()) {

			int[] now = queue.poll();

			if (now[0] == y) {
				answer = now[1];
				break;
			}

			int[][] next = new int[3][2];
			next[0] = new int[] { now[0] + n, now[1] + 1 };
			next[1] = new int[] { now[0] * 2, now[1] + 1 };
			next[2] = new int[] { now[0] * 3, now[1] + 1 };

			for (int i = 0; i < next.length; i++) {
				if (next[i][0] <= y && !visited[next[i][0]]) {
					visited[next[i][0]] = true;
					queue.add(next[i]);
				}
			}
		}

		return answer;
	}
}