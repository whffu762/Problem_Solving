import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * DFS 풀 때 주의할 점
 * 1. 순환을 감지해야 함
 * 2. 순환된 경우 바로 끝내는게 아니라 그 다음까지 연산해야 함
 * 
 * 순환은 한 번 방문한 경우 중단하면 됨
 * 근데 순환한다고 바로 return 하면 순환구조 이후를 탐색하지 못 함
 * 때문에 return 할게 아니라 그냥 넘어가야 함
 * 
 * 또한 중요한 점
 * 중간 지점까지 유지해야 결과를 생성할 수 있음
 * 시작과 끝만 파라미터로 넘기면 시작과 끝의 연결 여부만 저장할 수 있음
 * 하지만 여기서 필요한 건 모든 노드 간 연결이기 때문에 그 중간 연결까지 전달해줘야
 * 시작과 중간, 중간과 끝의 연결까지 저장할 수 있음
 */

class solve11403 {

    int numOfLine;
    boolean[][] map;
    int[][] result;

    void checkPath(int start, int tmp, int end, boolean[] visited) {

        for (int i = 0; i < numOfLine; i++) {

            //if((map[tmp][i] && !visited[i]){} 이걸로 하면 continue 신경 안 써도 됨
            if (map[tmp][i]) {

                if (visited[i]) {
                    //return; 이거 하면 순환 구조 이후는 탐색하지 못 함
                    continue;
                }

                visited[i] = true;
                result[start][i] = 1;
                checkPath(start, i, end, visited);
            }
        }
    }

    public void run(BufferedReader br) throws IOException {

        numOfLine = Integer.parseInt(br.readLine());
        map = new boolean[numOfLine][numOfLine];
        result = new int[numOfLine][numOfLine];

        StringTokenizer st;
        for (int i = 0; i < numOfLine; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < numOfLine; j++) {
                String input = st.nextToken();
                if (input.equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        for (int i = 0; i < numOfLine; i++) {
            for (int j = 0; j < numOfLine; j++) {
                boolean[] visited = new boolean[numOfLine];
                checkPath(i, i, j, visited);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfLine; i++) {
            for (int j = 0; j < numOfLine - 1; j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append(result[i][numOfLine - 1]).append("\n");
        }

        System.out.println(sb);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11403 p = new solve11403();
        p.run(br);

        br.close();
    }
}