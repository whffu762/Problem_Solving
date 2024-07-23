import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백트랙킹 문제긴 한데 핵심은 그게 아님
 * 
 * 핵심 1
 * 2차 배열을 1차 배열로 저장하지 못 하면 못 품
 * 퀸은 각 행당 하나씩밖에 존재하지 못 하므로 
 * 
 * - 1차 배열의 index를 행
 * - location[index]를 열
 * 위처럼 취급해서 풀어야 함
 * 
 * 핵심 2
 * 대각선에 있는지 판단하려면 두 좌표의 열 간 차와 행 간 차를 비교해야 함
 * 둘이 같으면 대각선에 존재하는 것임
 * 
 * 이렇게 안 풀면 메모리 초과 나거나 시간 초과 남
 */

class solve9663 {

    int n;
    int count = 0;
    int [] locations;

    solve9663(BufferedReader br) throws IOException {

        n = Integer.parseInt(br.readLine());
        locations = new int[n];
    }

    void run() {

        getCount(0);
        System.out.println(count);
    }

    void getCount(int depth) {

        if (depth == n) {
            count++;
            return;
        }

        for (int i = 0; i < n; i++) {

            locations[depth] = i;
            if (putQueen(depth)) {
                getCount(depth + 1);
            }
        }
    }

    boolean putQueen(int index) {

        for (int i = 0; i < index; i++) {

            if (index == i || locations[index] == locations[i]) {
                return false;
            }

            if (Math.abs(index - i) == Math.abs(locations[index] - locations[i])) {
                return false;
            }
        }

        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9663 p = new solve9663(br);
        p.run();

        br.close();
    }
}