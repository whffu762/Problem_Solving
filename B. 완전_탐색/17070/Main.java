import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 파이프의 현재 상태에 따라 움직임이 다름 
 * 그에 따라 DFS를 구현할 때 유지해야 할 것은 크게 두 가지임
 * 1. 현재 파이프의 상태
 * 2. 파이프의 상태에 따른 움직임
 * 
 * 가로 : 0
 * 대각선 : 1
 * 세로 : 2
 * 이런 식으로 정의해두고 각 상태 별 움직임을 보면 다음과 같음
 * 
 * 가로 -> 가로
 * 가로 -> 대각선
 * 
 * 대각선 -> 가로
 * 대각선 -> 대각선
 * 대각선 -> 세로
 * 
 * (더미) -> (더미)
 * 세로 -> 대각선
 * 세로 -> 세로
 * 
 * 숫자로 치환하면 세로를 제외하고선 움직임 그 자체가 다음 상태를 의미함
 * 가로 -> 가로 0 -> 0
 * 대각선 -> 가로 1 -> 0
 * 이 규칙을 맞춰주기 위해 세로에 더미 데이터를 넣어두고 동작할 땐 생략하면 됨
 * 이렇게 하고 각 움직임과 상태를 인덱스로 맞춰주고 3차 배열을 작성해주면 구현은 매우 간단해짐
 * 
 * 간단한 동작 흐름
 * 1. 먼저 현재 row와 col이 목적지에 도착했는지 확인
 * 2-1. 도착했으면 카운트 증가하고 종료
 * 2-2. 그렇지 않으면 마저 진행
 * 3. 현재 상태에 따른 가능한 움직임을 move에서 받아옴
 * 4. 각 움직임 별로 범위, 벽을 확인
 * 5-1. 유효하지 않으면 continue
 * 5-2. 유요하면 재귀 호출
 * 6. 재귀 호출할 때 다음 위치와 상태를 같이 파라미터로 넘기는데 상태는 현재 움직임 index임
 * 
 * 근데 DP로도 풀 수 있는듯..
 */
class solve17070{

    int size;
    boolean [][] map;
    int [][][] move = {
        {
            {0, 1}, {1, 1}
        },
        {
            {0, 1}, {1, 1}, {1, 0}
        },
        {
            {0, 0}, {1, 1}, {1, 0}
        }
    };
    int count = 0;

    solve17070(BufferedReader br) throws IOException{

        size = Integer.parseInt(br.readLine());
        map = new boolean [size+1][size+1];

        for(int i=1;i<size+1;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=1;j<size+1;j++){
                
                if(Integer.parseInt(st.nextToken()) == 1){
                    map[i][j] = true;
                }
            }
        }
    }

    void run(){
        
        backTracking(1, 2, 0);
        System.out.println(count);
    }

    void backTracking(int row, int col, int state){

        if(row == size && col == size){
            count++;
            return;
        }

        for(int i=0;i<move[state].length;i++){

            if(state == 2 && i == 0){
                continue;
            }

            int nextRow = row + move[state][i][0];
            int nextCol = col + move[state][i][1];
            
            if(checkRange(nextRow, nextCol)){
                continue;
            }

            if(i == 1 && checkSide(row, col)){
                continue;
            } 

            backTracking(nextRow, nextCol, i);
        }
    }

    boolean checkRange(int nextRow, int nextCol){

        if(nextRow > size || nextCol > size){
            return true;
        }

        return map[nextRow][nextCol];
    }

    boolean checkSide(int row, int col){

        return map[row+1][col] || map[row][col+1];
    }
}

public class Main {

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve17070 p = new solve17070(br);
        p.run();

        br.close();
    }
}