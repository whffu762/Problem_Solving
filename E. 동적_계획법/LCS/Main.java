import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class solve17218{

    int [][] cache;
    String first;
    String second;
    int [] moveRow = { -1, 0 };
    int [] moveCol = { 0, -1 };
    char [] result;

    solve17218 (BufferedReader br) throws IOException{

        first = br.readLine();
        second = br.readLine();

        cache = new int [first.length()+1][second.length()+1];
    }

    void run(){

        makeDP();
        result = new char [cache[first.length()][second.length()]];
        dfs(new int [] {first.length(), second.length()}, result.length-1);

        //System.out.println(sb);

        for(char c : result){
            sb.append(c);
        }
        System.out.println(sb);
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

    StringBuilder sb = new StringBuilder();

    void dfs(int [] now, int pointer){
        
        sb.append(now[0] + " " + now[1]).append("\n");
        
        if(cache[now[0]][now[1]] == 0){
            return;
        }

        boolean flag = true;
        for(int i=0;i<moveCol.length;i++){

            int [] next = { now[0] + moveRow[i], now[1] + moveCol[i]};
            if(checkValid(now, next)){
                dfs(next, pointer);                
                flag = false;
            }
        }

        if(flag){
            result[pointer] = first.charAt(now[0]-1);
            int [] next = {now[0]-1, now[1]-1};
            dfs(next, pointer-1);
        }
    }

    boolean checkValid(int [] now, int [] next){

        if(cache[next[0]][next[1]] != cache[now[0]][now[1]]){
            return false;
        }

        return true;
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve17218 p = new solve17218(br);
        p.run();

        br.close();
    }
}