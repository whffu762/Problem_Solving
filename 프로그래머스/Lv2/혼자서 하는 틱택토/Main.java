/*
 * O와 X의 갯수가 같을 때 O가 빙고가 아님
 * O가 X보다 하나 많을 때 X가 빙고가 아님
 * 이 두 상황 빼고 모두 false 임
 * 
 * 빙고를 구하는건 노가다하는게 가장 편한 듯 함
 * 모든 가로 체크
 * 모든 세로 체크
 * 오른쪽 대각선 체크
 * 왼쪽 대각선 체크
 * 이렇게 네 가지 경우를 모두 탐색해보면 됨
 */

class Solution {

	String[] board;

	public int solution(String[] board) {

		this.board = board;

		int o = 0;
		int x = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length(); j++) {
				if (board[i].charAt(j) == 'O') {
					o++;
				} else if (board[i].charAt(j) == 'X') {
					x++;
				}
			}
		}

		if (o == x && !checkGame('O')) {
			return 1;
		}

		if (o - 1 == x && !checkGame('X')) {
			return 1;
		}

		return 0;
	}

	boolean checkGame(char target) {

		for (int i = 0; i < 3; i++) {

			int count = 0;
			for (int j = 0; j < 3; j++) {
				if (board[i].charAt(j) == target) {
					count++;
				}
			}

			if (count == 3) {
				return true;
			}
		}

		for (int i = 0; i < 3; i++) {

			int count = 0;
			for (int j = 0; j < 3; j++) {
				if (board[j].charAt(i) == target) {
					count++;
				}
			}

			if (count == 3) {
				return true;
			}
		}

		int count = 0;
		for (int i = 0; i < 3; i++) {

			if (board[i].charAt(i) == target) {
				count++;
			}
		}

		if (count == 3) {
			return true;
		}

		count = 0;
		for (int i = 0; i < 3; i++) {

			if (board[i].charAt(2 - i) == target) {
				count++;
			}
		}

		if (count == 3) {
			return true;
		}

		return false;
	}
}