import java.util.ArrayDeque;
import java.util.Queue;

/*
 * BFS로 풀기
 * 최단 경로 값을 구하는 것이기 때문에 BFS로 풀면 편함(가장 먼저 접근되는 경로가 최단경로임)
 * 이럴 경우 가중치를 어떻게 유지하느냐가 문제인데 큐에 함께 넣어주면 됨 이건 간단함
 * 
 * 신기한건 이동 방식의 구현임
 * 이전에 나는 4가지 방향이기 때문에 4가지 메소드를 구현했음 근데 이걸 하나의 중첩 반복으로 아주 간단하게 구현할 수 있음
 * 0. 외부 반복에선 평범한 BFS처럼 움직임을 구현해서 반복 내부 반복에선 가로 세로 중 긴 길이만큼 계속 움직임
 * 1. 움직이면서 범위를 벗어나거나 장애물을 만나면 움직임을 하나 뺌(거기가 다음 위치임) 내부 반복문 탈출
 * 2. 해당 위치 방문 여부 확인 후 방문 처리 및 큐에 추가
 * 3. 다음 방향으로 움직임
 * 
 * 즉 움직임을 보통의 BFS처럼 구하되 다른 점은 그래프의 크기만큼 내부 반복문으로 계속 진행하는 방식임
 */

class Solution {

	char[][] map;
	boolean[][] visited;
	int answer = -1;
	int max;

	public int solution(String[] board) {

		this.map = new char[board.length][board[0].length()];
		this.visited = new boolean[board.length][board[0].length()];

		max = Math.max(board.length, board[0].length());
		int[] start = new int[2];
		int[] target = new int[2];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {

				map[i][j] = board[i].charAt(j);
				if (board[i].charAt(j) == 'R') {
					start[0] = i;
					start[1] = j;
				}
			}
		}

		bfs(start);

		return answer;
	}

	void bfs(int[] start) {

		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { start[0], start[1], 0 });
		visited[start[0]][start[1]] = true;

		int[] moveX = { -1, 1, 0, 0 };
		int[] moveY = { 0, 0, -1, 1 };

		while (!queue.isEmpty()) {

			int[] now = queue.poll();

			if (map[now[0]][now[1]] == 'G') {
				answer = now[2];
				return;
			}

			int[] next = new int[3];
			for (int i = 0; i < 4; i++) {

				int row = now[0];
				int col = now[1];

				for (int j = 0; j < max; j++) {

					row += moveX[i];
					col += moveY[i];

					if (row == map.length || row == -1 || col == map[0].length || col == -1 || map[row][col] == 'D') {
						row -= moveX[i];
						col -= moveY[i];
						break;
					}
				}

				if (!visited[row][col]) {
					visited[row][col] = true;
					queue.add(new int[] { row, col, now[2] + 1 });
				}
			}
		}
	}
}