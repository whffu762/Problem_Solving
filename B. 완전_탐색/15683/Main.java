import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * 구현 + 백트랙킹
 * 주의할 점이 하나씩 있음
 * 
 * 구현에서 주의할 점 
 * 카메라가 존재하는 곳은 감시 구역으로 바꾸면 안 됨
 * 문제 상으로는 카메라가 존재하는 곳도 감시할 수 있기 때문에 감시 구역으로 바꿔도 문제 없을 것 같지만
 * 바뀐 구역이 카메라였는지 알 수 없어지기 때문에 오류가 발생함
 * 물론 카메라에 대한 접근은 원본 그래프에서 하면 문제 없지만 여기선 메소드의 파라미터로 복사본을 던지고 
 * 그 복사본만을 다루도록 해놨기 때문에 카메라가 유지되야 함(이걸 왜 생각을 못 했지...)
 * 
 * 예시
 * 0 0 1 0
 * 0 0 1 0
 * 0 1 1 0
 * 0 0 0 0
 * 
 * 여기서 (0, 2)에 존재하는 카메라가 아래를 바라보게 한다면
 * 
 * 0 0 1 0
 * 0 0 9 0
 * 0 1 9 0
 * 0 0 9 0
 * 
 * 위와 같이 바뀜 그 결과 (1, 2)와 (2, 2)에 존재하는 카메라에는 접근할 수 없게됨
 * 이게 무서운 점은 어차피 최솟값만 찾기 때문에 거의 대부분의 예제는 정상적으로 동작한다는 점임
 * 
 * 
 * 백트랙킹에서 주의할 점
 * 백트랙킹 안에서 카메라를 순회하면 안 됨 카메라는 depth만 가지고도 접근이 가능함
 * 오히려 순회하면 불필요한 호출이 생긱기 때문에 시간 초과가 발생함
 * 이 덕분에 visited를 유지할 필요가 없음
 * 
 * 
 * 
 */

class CCTV {

    int mode;

    int[][] tmpGraph;

    int totalRow;
    int totalCol;

    int row;
    int col;

    CCTV(int row, int col) {
        this.row = row;
        this.col = col;
    }

    void setMode(int mode) {
        this.mode = mode;
    }

    void setGraph(int[][] graph) {
        this.tmpGraph = graph;
        this.totalRow = tmpGraph.length;
        this.totalCol = tmpGraph[0].length;
    }

    int[][] setObserveSite(int[][] graph) {

        setGraph(graph);
        int model = graph[row][col];

        switch (model) {
            case 1:
                setModel1();
                break;

            case 2:
                setModel2();
                break;

            case 3:
                setModel3();
                break;

            case 4:
                setModel4();
                break;

            case 5:
                setModel5();
                break;

            default :
                break;
        }

        return tmpGraph;
    }

    void setModel1() {

        if (mode == 0) {
            checkRight(col, row);
        } else if (mode == 1) {
            checkDown(row, col);
        } else if (mode == 2) {
            checkLeft(col, row);
        } else if (mode == 3) {
            checkUp(row, col);
        }
    }

    void setModel2() {

        if (mode == 0) {
            checkRight(col, row);
            checkLeft(col, row);
        } else if (mode == 1) {
            checkUp(row, col);
            checkDown(row, col);
        }
    }

    void setModel3() {

        if (mode == 0) {
            checkUp(row, col);
            checkRight(col, row);
        } else if (mode == 1) {
            checkRight(col, row);
            checkDown(row, col);
        } else if (mode == 2) {
            checkDown(row, col);
            checkLeft(col, row);
        } else if (mode == 3) {
            checkLeft(col, row);
            checkUp(row, col);
        }

    }

    void setModel4() {

        if (mode == 0) {
            checkUp(row, col);
            checkLeft(col, row);
            checkRight(col, row);
        } else if (mode == 1) {
            checkUp(row, col);
            checkRight(col, row);
            checkDown(row, col);
        } else if (mode == 2) {
            checkLeft(col, row);
            checkRight(col, row);
            checkDown(row, col);
        } else if (mode == 3) {
            checkUp(row, col);
            checkLeft(col, row);
            checkDown(row, col);
        }
    }

    void setModel5() {

        checkUp(row, col);
        checkLeft(col, row);
        checkRight(col, row);
        checkDown(row, col);
    }

    void checkRight(int start, int row) {
        for (int i = start + 1; i < totalCol; i++) {
            if (tmpGraph[row][i] == 6) {
                break;
            }

            if(tmpGraph[row][i] == 0){
                tmpGraph[row][i] = 9;
            }
            //tmpGraph[row][i] = 9; 이렇게 하면 안 됨(구현에서 주의 사항)
        }
    }

    void checkDown(int start, int col) {

        for (int i = start + 1; i < totalRow; i++) {
            if (tmpGraph[i][col] == 6) {
                break;
            }

            if(tmpGraph[i][col] == 0){
                tmpGraph[i][col] = 9;
            }
            //tmpGraph[i][col] = 9;
        }
    }

    void checkLeft(int start, int row) {
        for (int i = start - 1; i > -1; i--) {
            if (tmpGraph[row][i] == 6) {
                break;
            }

            if(tmpGraph[row][i] == 0){
                tmpGraph[row][i] = 9;
            }
            //tmpGraph[row][i] = 9;
        }
    }

    void checkUp(int start, int col) {

        for (int i = start - 1; i > -1; i--) {
            if (tmpGraph[i][col] == 6) {
                break;
            }

            if(tmpGraph[i][col] == 0){
                tmpGraph[i][col] = 9;
            }
            //tmpGraph[i][col] = 9;
        }
    }
}


class solve15683 {

    int row;
    int col;
    int[][] graph;
    List<CCTV> cctvList = new ArrayList<>();

    int[] mode = { 4, 2, 4, 4, 1 };
    int blank = Integer.MAX_VALUE;

    solve15683(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        graph = new int[row][col];

        for (int i = 0; i < row; i++) {

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < col; j++) {
                int now = Integer.parseInt(st.nextToken());

                if (now != 0 && now != 6) {
                    cctvList.add(new CCTV(i, j));
                }
                graph[i][j] = now;
            }
        }
    }

    void run() {
        dfs(0);
        System.out.println(blank);
    }

    void dfs(int depth){

        if (depth == cctvList.size()) {

            int tmp = observe();
            blank = Math.min(blank, tmp);
            return;
        }

        CCTV now = cctvList.get(depth);
        int model = graph[now.row][now.col];
        for (int j = 0; j < mode[model - 1]; j++) {
            now.setMode(j);
            dfs(depth + 1);
        }
    }

    int observe(){

        int[][] tmp = new int[row][col];

        for (int i = 0; i < row; i++) {
            tmp[i] = Arrays.copyOf(graph[i], col);
        }

        for (int i = 0; i < cctvList.size(); i++) {

            CCTV now = cctvList.get(i);
            tmp = now.setObserveSite(tmp);
        }

        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                
                if (tmp[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve15683 p = new solve15683(br);
        p.run();

        br.close();
    }
}