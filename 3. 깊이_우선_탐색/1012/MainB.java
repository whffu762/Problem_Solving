import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * BFS로도 구현할 수 있음
 * BFS시 주의할 점은 Visited[] 유지해야 한다는 점임
 * field[][]가 true인 것만 조회하게끔 해놔도 field[][]값이 바뀌기 전에 Queue에 또 들어갈 수 있기 때문임
 * 예를 들어
 * 1 1 1 
 * 1 1 1
 * 0 1에 의해 1 1이 Queue에 들어가고 1 0에 의해 1 1이 한 번 더 들어가게 되서 두 번 연산하게 됨
 * 때문에 Queue에 들어가기 전에 Visited를 한 번 접근한 적이 있는지 확인해야 함
 */
class solve1012 {

    boolean[][] field;
    boolean[][] visited;
    int[] canMove = { 1, -1 };
    Queue<int []> queue;

    boolean checkValid(int row, int col){

        if(row < 0 || row > field.length-1 || col < 0 || col > field[0].length-1){
            return false;
        }

        return field[row][col] && !visited[row][col];
    }

    void getNear(int row, int col){
        
        for(int i=0;i<canMove.length;i++){

            int nowRow = row + canMove[i];
            if(checkValid(nowRow, col)){
                visited[nowRow][col] = true;
                queue.add(new int [] { nowRow, col });
            }
        }


        for(int i=0;i<canMove.length;i++){

            int nowCol = col + canMove[i];
            if(checkValid(row, nowCol)){
                visited[row][nowCol] = true;
                queue.add(new int [] { row, nowCol });
            }
        }
    }

    void cleanField(int row, int col) {

        queue = new ArrayDeque<>();
        queue.add(new int [] {row, col});

        while(!queue.isEmpty()){

            int [] now = queue.poll();
            field[now[0]][now[1]] = false;
            getNear(now[0], now[1]);

            for(int [] near : queue){
                row = near[0];
                col = near[1];
            }
        }
    }

    int getResult() {

        int count = 0;
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                if (field[i][j]) {
                    cleanField(i, j);
                    count++;
                }
            }
        }
        return count;
    }

    void run(BufferedReader br) throws IOException {

        int numOfCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numOfCase; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            int numOfRow = Integer.parseInt(st.nextToken());
            int numOfCol = Integer.parseInt(st.nextToken());
            int numOfCabbage = Integer.parseInt(st.nextToken());

            field = new boolean[numOfRow][numOfCol];
            visited = new boolean[numOfRow][numOfCol];
            for (int j = 0; j < numOfCabbage; j++) {

                st = new StringTokenizer(br.readLine());
                field[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }
            sb.append(getResult()).append("\n");
        }
        System.out.println(sb);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1012 p = new solve1012();
        p.run(br);

        br.close();
    }
}