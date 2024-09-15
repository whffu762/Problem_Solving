import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/*
 * DFS 이용
 * 두 번을 한 번의 for문에서 수행하는 것보다 각각 나누는게 더 효율적이라고 함
 * 구현하는데 편한 것도 있지만 성능면에서도 그게 더 낫다고 함
 * 
 * 복잡도가 높아지는게 오히려 더 오버헤드를 증가시킴
 * 캐시를 사용할 때도 내부에서 계속 사용하는 데이터가 변경되기 때문에 많은 조건이 들어가는게 비효율적임
 * 병렬 처리가 가능할 경우 분리할 수 있음
 */

class solve10026{

    int size;
    int [][] graph;
    int [][] graph2;
    boolean [][] visited;

    solve10026(BufferedReader br) throws IOException{

        size = Integer.parseInt(br.readLine());
        graph = new int[size][size];
        graph2 = new int[size][size];
        visited = new boolean[size][size];

        for(int i=0;i<size;i++){
            
            String input = br.readLine();
            for(int j=0;j<size;j++){
                char now = input.charAt(j);
                if(now == 'R'){
                    graph[i][j] = 0;
                } else if(now == 'G'){
                    graph[i][j] = 1;
                } else {
                    graph[i][j] = 2;
                    graph2[i][j] = 1;
                }
            }
        }
    }

    void run() {

        int countNormal = 0;
        int countHandi = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!visited[i][j]){
                    dfs(new int [] {i,j}, false);
                    countNormal++;
                }
            }
        }

        init();

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(!visited[i][j]){
                    dfs(new int [] {i,j}, true);
                    countHandi++;
                }
            }
        }

        System.out.println(countNormal + " " + countHandi);
    }

    int [] move = { -1, 1, 0, 0 };
    int [] move2 = { 0, 0, -1, 1};

    void dfs(int [] now, boolean check){

        int [][] tmp = this.graph;
        if(check){
            tmp = graph2;
        }

        int color = tmp[now[0]][now[1]];
        visited[now[0]][now[1]] = true;

        for(int i=0;i<move.length;i++){

            int [] next = {now[0] + move[i], now[1] + move2[i]};
            if(checkValid(next) && color == tmp[next[0]][next[1]] ){
                dfs(next, check);
            }
        }   
    }

    boolean checkValid(int [] now){

        if(now[0] < 0 || now[0] >= size || now[1] < 0 || now[1] >= size){
            return false;
        }

        return !visited[now[0]][now[1]];
    }

    void init(){

        for(int i=0;i<size;i++){
            Arrays.fill(visited[i], false);
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve10026 p = new solve10026(br);    
        p.run();

        br.close();
    }
}