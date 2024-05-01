import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

/*
 * BFS 자체는 그냥 어렵지 않음
 * 허나 걸린 부분이 PriorityQueue에 저장하는 경우임
 * 
 * PriorityQueue는 최솟값 혹은 최댓값을 반환하기 때문에 저장될 때도 정렬될줄 알았지만
 * 그렇지 않음 반환에 최적화된 형태로 내부에 저장되어 있음 (heap 형태) 
 * 그래서 원소가 적을 땐 정렬된 것 처럼 보이지만 실제론 그렇지 않음
 * 
 * 아래 코드처럼 반환하면서 출력하던가 혹은 List<>에 저장하고 직접 정렬해야 함
 *
 * 
 */
class solve2583{
    
    int height;
    int width;
    boolean [][] isVisted;
    int [] canMove = {-1 , 1};

    solve2583(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        
        isVisted = new boolean[width][height];
        int numOfSquare = Integer.parseInt(st.nextToken());

        for(int i=0;i<numOfSquare;i++){
            st = new StringTokenizer(br.readLine());
            int minX = Integer.parseInt(st.nextToken());
            int minY = Integer.parseInt(st.nextToken());
            int maxX = Integer.parseInt(st.nextToken());
            int maxY = Integer.parseInt(st.nextToken());
            
            for(int j=minX;j<maxX;j++){
                for(int k=minY;k<maxY;k++){
                    isVisted[j][k] = true;
                }
            }
        }
    }


    int bfs(int x, int y){

        Queue<int []> queue = new ArrayDeque<>();
        queue.add(new int [] {x, y});
        int area = 1;

        while(!queue.isEmpty()){

            int [] now = queue.remove();
            isVisted[now[0]][now[1]] = true;

            for(int i=0;i<canMove.length;i++){

                int nextX = now[0] + canMove[i];

                if(-1<nextX && nextX<width && !isVisted[nextX][now[1]]){
                    queue.add(new int [] {nextX, now[1]});
                    isVisted[nextX][now[1]] = true;
                    area++;
                }
            }

            for(int i=0;i<canMove.length;i++){

                int nextY = now[1] + canMove[i];

                if(-1<nextY && nextY <height && !isVisted[now[0]][nextY]){
                    queue.add(new int [] {now[0], nextY});
                    isVisted[now[0]][nextY] = true;
                    area++;
                }
            }
        }

        return area;

    }
    
    void run(){
        
        int count = 0;
        Queue<Integer> areas = new PriorityQueue<>();
        //List<Integer> areas = new ArrayList<>();

        for(int x=0;x<width;x++){
            for(int y=0;y<height;y++){
                if(!isVisted[x][y]){
                    count++;
                    areas.add(bfs(x, y));
                }
            }
        }

        System.out.println(count);
        StringBuilder sb = new StringBuilder();
        while(!areas.isEmpty()){
            sb.append(areas.remove()).append(" ");
        }
        System.out.println(sb);


    }
}

public class MainB{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2583 p = new solve2583(br);
        p.run();

        br.close();
    }
}