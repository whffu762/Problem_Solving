import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class solve17218{

    String first;
    String second;

    int [] moveRow = { -1, 0 };
    int [] moveCol = { 0, -1 };

    int [][] cache;
    boolean [][] visited;
    StringBuilder sb = new StringBuilder();
    
    solve17218 (BufferedReader br) throws IOException{

        first = br.readLine();
        second = br.readLine();

        cache = new int [first.length()+1][second.length()+1];
        visited = new boolean [first.length()+1][second.length()+1];
    }

    void run(){

        makeDP();
        dfs(new int [] {first.length(), second.length()});

        System.out.println(sb.reverse());
    }

    void makeDP(){

        for(int i=1;i<first.length()+1;i++){

            for(int j=1;j<second.length()+1;j++){

                if(first.charAt(i-1) == second.charAt(j-1)){
                    cache[i][j] = cache[i-1][j-1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i-1][j], cache[i][j-1]);
                }
            }
        }
    }

    void dfs(int [] now){
        
        if(cache[now[0]][now[1]] == 0){
            return;
        }

        boolean flag = true;
        for(int i=0;i<moveCol.length;i++){

            int [] next = { now[0] + moveRow[i], now[1] + moveCol[i]};
            if(cache[now[0]][now[1]] == cache[next[0]][next[1]]){

                flag = false;
                if(!visited[next[0]][next[1]]){

                    dfs(next);
                    visited[next[0]][next[1]] = true;
                }
            }
        }

        if(flag){

            sb.append(first.charAt(now[0]-1));
            dfs(new int [] {now[0]-1, now[1]-1});
        }
    }
}

public class MainD {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve17218 p = new solve17218(br);
        p.run();

        br.close();
    }
}