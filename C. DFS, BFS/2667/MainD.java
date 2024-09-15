import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * DFS로 풀어보자
 * count를 유지하는 것만 신경쓰면 됨
 */
class solve2667 {

    int size;
    boolean [][] graph;

    solve2667 (BufferedReader br) throws IOException{
        
        size = Integer.parseInt(br.readLine());
        graph = new boolean[size][size];

        for(int i=0;i<size;i++){

            String input = br.readLine();

            for(int j=0;j<size;j++){
                if(input.charAt(j) == '1'){
                    graph[i][j] = true;
                }
            }
        }
    }

    void run(){

        PriorityQueue<Integer> result = new PriorityQueue<>();

        for(int i=0;i<size;i++){

            for(int j=0;j<size;j++){

                if(graph[i][j]){
                    graph[i][j] = false;
                    result.add(dfs(new int [] {i, j}, 0));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append("\n");
        while(!result.isEmpty()){
            sb.append(result.remove()).append("\n");
        }
        System.out.println(sb);
    }

    int [] move = { -1, 0, 0, 1};
    int [] move2 = { 0, -1, 1, 0};

    int dfs(int [] now, int count){
        
        for(int i=0;i<move.length;i++){

            int [] next = {now[0] + move[i], now[1] + move2[i]};
            if(checkValid(next)){
                graph[next[0]][next[1]] = false;
                count = dfs(next, ++count);
            }
        }

        //맨 마지막 재귀 호출에서 반환된 값이 반환됨
        return count;
    }

    boolean checkValid(int [] now){

        if(now[0] < 0 || now[0] >= size || now[1] < 0 || now[1] >= size){
            return false;
        }

        return graph[now[0]][now[1]];
    }
}

public class MainD {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2667 p = new solve2667(br);
        p.run();

        br.close();
    }
}