/*
 * 기본적으로 완전 탐색임 BFS 혹은 DFS로 풀면 되는데 문제는 여러 조건들
 * - 이동 방식
 * - 배제 조건
 * - 종료 조건
 * 
 * 이동 방식
 * 기존 방식처럼 노드 하나씩 이동하는게 아니라 경계 혹은 장애물을 만날 때까지 계속 나아감
 * 이동하다가 장애물 혹은 경계를 만날 경우 움직임에 따라 row 혹은 col를 +-1 해주면 됨
 * 예를 들어 위로 움직일 경우 row를 계속 -1 해주다가 장애물을 만날 경우 -1을 해주면 다음 이동 위치가 됨
 * 
 * 배제 조건
 * 왔던 방향을 다시 돌아가면 최단 경로가 될 수가 없음 그래서 파라미터로 이전에 움직인 방향을 유지
 * 장애물 혹은 경계로 둘러쌓여 있는 경우 이동 방식과 연계되서 움직인 후 위치와 그 전의 위치가 같다면
 * 해당 방향으로는 갈 수 없음을 의미하므로 다음 방향으로 움직여야 함
 * 
 * 종료 조건
 * G에 도달하면 종료되야 함
 * visited에 해당 위치로 이동하기 위한 최단 경로를 저장해둠 이후 현재 count된 값이 그 값보다 크거나 같으면
 * 더 이상 불필요한 움직임이므로 종료함
 * 그렇지 않다면(count 된 값이 현재 visited 값보다 작다면) 최신화
 */

class Solution {

	char[][] map;
	int[][] visited;
	int count = 0;

	public int solution(String[] board) {

		this.map = new char[board.length][board[0].length()];
		this.visited = new int[board.length][board[0].length()];

		int[] start = new int[2];
		int[] target = new int[2];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				map[i][j] = board[i].charAt(j);
				if (board[i].charAt(j) == 'R') {
					start[0] = i;
					start[1] = j;
				}

				if (board[i].charAt(j) == 'G') {
					target[0] = i;
					target[1] = j;
				}
			}
		}

		visited[start[0]][start[1]] = -1;
		backTracking(start, Integer.MAX_VALUE);

		if (visited[target[0]][target[1]] == 0) {
			return -1;
		}

		return visited[target[0]][target[1]];
	}

	void backTracking(int[] now, int direction) {

		// 최단 경로가 아닌 경우 배제
		if (visited[now[0]][now[1]] > 0 && visited[now[0]][now[1]] <= count) {
			return;
		}

		visited[now[0]][now[1]] = count;

		if (map[now[0]][now[1]] == 'G') {
			return;
		}

		count++;
		for (int i = 0; i < 4; i++) {
			int[] next = new int[2];

			// 왔던 방향 배제
			if (direction - i == 2 || direction - i == -2) {
				continue;
			}

			if (i == 0) {
				next = moveUp(now);
			}

			if (i == 1) {
				next = moveRight(now);
			}

			if (i == 2) {
				next = moveDown(now);
			}

			if (i == 3) {
				next = moveLeft(now);
			}

			// 더 이상 나아갈 수 없는 경우
			if (next[0] == now[0] && next[1] == now[1]) {
				continue;
			}

			backTracking(next, i);
		}
		count--;
	}

	boolean checkMove(int[] now) {

		if (-1 < now[0] && now[0] < map.length && -1 < now[1] && now[1] < map[0].length) {
			return map[now[0]][now[1]] != 'D';
		}

		return false;
	}

	int[] moveRight(int[] now) {

		int[] next = new int[2];
		next[0] = now[0];
		next[1] = now[1];

		while (true) {

			if (!checkMove(next)) {
				next[1]--;
				break;
			}
			next[1]++;
		}

		return next;
	}

	int[] moveUp(int[] now) {

		int[] next = new int[2];
		next[0] = now[0];
		next[1] = now[1];

		while (true) {

			if (!checkMove(next)) {
				next[0]++;
				break;
			}
			next[0]--;
		}

		return next;
	}

	int[] moveDown(int[] now) {

		int[] next = new int[2];
		next[0] = now[0];
		next[1] = now[1];

		while (true) {

			if (!checkMove(next)) {
				next[0]--;
				break;
			}
			next[0]++;
		}

		return next;
	}

	int[] moveLeft(int[] now) {

		int[] next = new int[2];
		next[0] = now[0];
		next[1] = now[1];

		while (true) {

			if (!checkMove(next)) {
				next[1]++;
				break;
			}
			next[1]--;
		}

		return next;
	}
}