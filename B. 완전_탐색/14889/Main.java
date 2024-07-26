import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백트랙킹 이용
 * 
 * 팀 나누기를 백 트랙킹 이용
 * 점수 계산은 이중 for문으로 가능함
 */
class solve14889{

    int n;
    int [][] map;
    boolean [] checked;
    int result = Integer.MAX_VALUE;

    solve14889(BufferedReader br ) throws IOException{

        n = Integer.parseInt(br.readLine());

        map = new int [n][n];
        for(int i=0;i<n;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        checked = new boolean[n];
    }

    void run(){

        backTracking(0, 0);
        System.out.println(result);
    }

    
    void backTracking(int depth, int index){
        
        if(depth == n/2){
            int [][] teams = getTeam();
            int diff = Math.abs(getScore(teams[0]) - getScore(teams[1]));
            result = Math.min(result, diff);
        }

        for(int i=index;i<n;i++){

            checked[i] = true;
            depth++;
            backTracking(depth, i+1);
            checked[i] = false;
            depth--;
        }
    }

    int [][] getTeam(){

        int [][] team = new int [2][n/2];
        int j = 0;
        int k = 0;
        for(int i=0;i<checked.length;i++){
            if(checked[i]){
                team[0][j++] = i;
            } else {
                team[1][k++] = i;
            }
        }

        return team;        
    }




    int getScore(int [] team){

        int score = 0;

        for(int i=0;i<team.length;i++){
            for(int j=i+1;j<team.length;j++){
                score += map[team[i]][team[j]];
                score += map[team[j]][team[i]];
            }
        }

        return score;
    }
}

public class Main {
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve14889 p = new solve14889(br);
        p.run();

        br.close();
    }
}