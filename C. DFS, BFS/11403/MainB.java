import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * BFS로 풀이
 * 동작 방식은 DFS와 동일함 진행 경로만 다름
 */

class solve11403 {

    int numOfLine;
    boolean[][] map;
    int[][] result;
    
    void checkPath(int start, Queue<Integer> queue, boolean [] visited) {

        while(!queue.isEmpty()){
            
            int now = queue.poll();
            for(int i=0;i<numOfLine;i++){
                if(map[now][i]){

                    if(visited[i]){
                        continue;
                    }

                    result[start][i] = 1;
                    visited[i] = true;
                    queue.add(i);
                }
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
            
            boolean [] visited = new boolean[numOfLine];
            Queue<Integer> queue = new ArrayDeque<>();
            queue.add(i);
            checkPath(i, queue, visited);
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