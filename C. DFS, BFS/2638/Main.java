import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class solve2638 {

    int row;
    int col;
    int [][] store;
    boolean [][] visited;
    Queue<int []> queue = new ArrayDeque<>();
    int [] moveX = {-1, 1, 0, 0};
    int [] moveY = {0, 0, -1, 1};

    solve2638 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        store = new int [row+2][col+2];
        for(int i=0;i<row;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                store[i+1][j+1] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void run() {

        int end = (row + 2) * (col + 2);
        int count = 0;
        while(true){

            if(checkOutside(end)){
                break;
            }

            count++;
            
            for(int i=1;i<row;i++){

                for(int j=1;j<col;j++){
                    
                    if(store[i][j] == 1){
                        checkCheese(i, j);
                    }
                }
            }
        }

        System.out.println(count);
    }

    boolean checkOutside(int end){

        int count = 1;
        visited = new boolean[row+2][col+2];

        queue.add(new int [] {0, 0});
        visited[0][0] = true;

        while(!queue.isEmpty()) {

            int [] now = queue.poll();
            store[now[0]][now[1]] = -1;

            for(int i=0;i<moveX.length;i++){

                int [] next = {now[0] + moveX[i], now[1] + moveY[i]};
                
                if(checkVaild(next)){
                    count++;
                    visited[next[0]][next[1]] = true;
                    queue.add(next);
                }
            }
        }
        
        if(count == end) {
            return true;
        }

        return false;
    }

    boolean checkVaild(int [] next){

        if(next[0] > -1 && next[0] < store.length && 
        next[1] > -1 && next[1] < store[0].length){

            if(store[next[0]][next[1]] == -1 || store[next[0]][next[1]] == 0){
                return !visited[next[0]][next[1]];
            }
        }

        return false;
    }

    void checkCheese(int row, int col){

        int count = 0;
        for(int i=0;i<moveX.length;i++){

            int [] near = {row + moveX[i], col + moveY[i]};
            if(store[near[0]][near[1]] == -1){
                count++;
            }
        }

        if(count > 1){
            store[row][col] = 0;
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2638 p = new solve2638(br);
        p.run();
        
        br.close();
    }
}