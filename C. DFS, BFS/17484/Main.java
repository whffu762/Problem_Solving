import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/*
    유효조건 확인
 * isVaild()로 값이 0과 col 사이에 존재하는지 검사
 * dir != i 로 이전에 이동한 방향인지 검사 초기값은 -1로 넣어서 해당 조건 무시
 * 
 * 
 * move[] : 이동할 수 있는 방향을 배열로 저장함으로써 모든 이동을 순차적으로 검사할 수 있음
 * tmp[] : 내가 했던 방식과 마찬가지로 각 행의 열이 저장됨
 * 
 * 내가 한 방식과의 차이점은 난 전수 조사로 했지만 
 * 이 방법은 move[] 를 이용해서 이동 가능 범위만 검사함..
 */
class solve17484Dfs{
    int row;
    int col;
    int [][] target;
    int [] tmp;
    int result;
    int [] move = {-1, 0, 1};

    void dfs(int depth, int nowCol, int dir){
        if(depth == row){   //마지막인지 확인
            int sum = target[0][tmp[0]];
            for(int i=1;i<row;i++){
                sum += target[i][tmp[i]];
            }

            result = Math.min(result, sum);
            return;
        }

        for(int i=0;i<3;i++){
            int nextCol = nowCol + move[i];
            if(isValid(nextCol) && dir != i){   //유효조건 확인
                tmp[depth] = nextCol;
                dfs(depth+1, nextCol, i);
            }
        }
    }

    boolean isValid(int x){
        if(x<0 || x >= col){
            return false;
        }
        return true;
    }

    void run(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        target = new int [row][col];
        for(int i=0;i<row;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                target[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        result = row * 100;
        for(int i=0;i<col;i++){
            tmp = new int [row];
            tmp[0] = i;
            dfs(1, i, -1);
        }

        System.out.println(result);
    }

}
class Main{
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve17484Dfs p = new solve17484Dfs();
        p.run(br);

        br.close();
    }
}