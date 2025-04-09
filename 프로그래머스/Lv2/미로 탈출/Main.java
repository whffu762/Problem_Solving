import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 시작점에서 -> Lever
 * Lever -> Exit
 * 이 둘을 더하면 됨
 * 근데 뭐에 씌웠는지 Lever를 가는 과정에서 Exit를 거쳐야하는지 확인해야 된다고 생각했음 아...................
 */
class Solution {

	char[][] graph;
	int[] exit = new int[2];
	int[] moveX = { -1, 1, 0, 0 };
	int[] moveY = { 0, 0, -1, 1 };
	boolean flag;

	public int solution(String[] maps) {

		graph = new char[maps.length][maps[0].length()];
		int[] start = new int[3];
		int[] lever = new int[3];

		for (int i = 0; i < maps.length; i++) {
			for (int j = 0; j < maps[0].length(); j++) {
				graph[i][j] = maps[i].charAt(j);
				if (graph[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				}

				if (graph[i][j] == 'L') {
					lever[0] = i;
					lever[1] = j;
				}

				if (graph[i][j] == 'X') {
					exit[0] = i;
					exit[1] = j;
				}
			}
		}

		int toLever = bfs(start, 'L');
		if (toLever == -1) {
			return -1;
		}

		int toExit = bfs(lever, 'E');
		if (toExit == -1) {
			return -1;
		}

		return toLever + toExit;
	}

	boolean checkValid(int[] now, boolean[][] visited) {

		if (-1 < now[0] && now[0] < graph.length && -1 < now[1] && now[1] < graph[0].length) {

			return !visited[now[0]][now[1]] && graph[now[0]][now[1]] != 'X';
		}

		return false;
	}

	int bfs(int[] start, char target) {

		boolean[][] visited = new boolean[graph.length][graph[0].length];
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start[0]][start[1]] = true;
		int count = 0;

		while (!queue.isEmpty()) {

			int[] now = queue.poll();
			if (graph[now[0]][now[1]] == target) {
				return now[2];
			}

			for (int i = 0; i < moveX.length; i++) {

				int[] next = { now[0] + moveX[i], now[1] + moveY[i], now[2] + 1 };
				if (checkValid(next, visited)) {
					visited[next[0]][next[1]] = true;
					queue.add(next);
				}
			}
		}

		return -1;
	}
}