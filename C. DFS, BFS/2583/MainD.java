import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.PriorityQueue;

/*
 * DFS 방식
 * 
 * area를 전역 변수로 유지하면 편하지만 전역 변수 사용하는건 찝찝하고 계속 초기화해줘야 함
 * 지역 변수로 이용하기 위해 dfs 시작점에선 항상 0으로 시작해서 각 결과를 더하는 방식을 이용
 * 
 * DFS의 시작점에선 항상 0으로 시작, 각 재귀 함수의 결과를 더해서 최종 결과를 구하는 방식
 * 그 과정에서 범위 밖으로 나가면 0을 리턴해서 결과에 영항을 주지 않도록 함
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

    int dfs(int x, int y, int area){

        if(x < 0 || width <= x || y < 0 || height <= y || isVisted[x][y]){
            return 0;
        }

        area++;
        isVisted[x][y] = true;


        for(int i=0;i<canMove.length;i++){
            
            area += dfs(x + canMove[i], y, 0);
            area += dfs(x, y + canMove[i], 0);
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
                    areas.add(dfs(x, y, 0));
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

public class MainD{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2583 p = new solve2583(br);
        p.run();

        br.close();
    }
}