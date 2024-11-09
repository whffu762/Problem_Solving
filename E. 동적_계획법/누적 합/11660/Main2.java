import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BFS를 이용한 방법
 * 
 * (x, y)까지의 합 = (x-1, y) + (x,y-1) - (x-1,y-1)
 * 즉 (1, 1) 방향에서의 인접 노드의 합으로 이루어짐(그 중 겹치는 부분은 한 번 빼야 함)
 * 
 * 예를 들어 1, 3은 (1, 2) + (0, 3) - (0, 2)(참고로 0은 없는 영역)
 * 2 , 4는 (1, 4) + (2, 3) - (1, 3)의 합으로 이루어짐 그림으로 보는게 더 이해가 편함
 * 하튼 인접 노드의 합이라고 생각하면 됨
 * 
 * 목표값을 구할 때도 똑같이 이용하면 됨
 * (x, y) ~ (a, b)까지의 합 = (a, b) - (x-1, b) - (a, y-1) + (a-1, b-1)
 * 합을 구할때와 반대로 인접 노드까지의 합들을 빼고 겹치는 부분은 더해주면 됨 
 */
class solve11660{

    int size;
    int [][] cache;
    StringBuilder sb = new StringBuilder();

    solve11660(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        size = Integer.parseInt(st.nextToken());
        int numOfReq = Integer.parseInt(st.nextToken());

        cache = new int [size+1][size+1];

        for(int i=1;i<size+1;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=1;j<size+1;j++){

                cache[i][j] = Integer.parseInt(st.nextToken()) + cache[i][j-1] + cache[i-1][j] - cache[i-1][j-1];
            }
        }

        for(int i=0;i<numOfReq;i++){

            st = new StringTokenizer(br.readLine());

            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            int endRow = Integer.parseInt(st.nextToken());
            int endCol = Integer.parseInt(st.nextToken());

            int result = cache[endRow][endCol] - cache[startRow-1][endCol] - cache[endRow][startCol-1] + cache[startRow-1][startCol-1];
            sb.append(result).append("\n");
        }
    }

    void run(){

        System.out.println(sb);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve11660 p = new solve11660(br);
        p.run();

        br.close();
    }
}