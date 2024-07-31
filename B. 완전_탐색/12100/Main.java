import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 백트랙킹 이용
 * 백트랙킹 자체는 어렵지 않음 다만 다른게 더 큰 문제
 * 1. 상하좌우로 이동에 대한 구현
 * 2. 한쪽 방향으로 이동시켰을 때의 결과 연산
 * 
 * 1번
 * 상하좌우로 이동시켰을 때 연산이 적용되는건 각 행 또는 열임 상하의 경우 열 좌우의 경우 행
 * 각각의 열 혹은 행을 배열 단위로 저장해서 각각 이동 연산을 수행하면 됨
 * 위 : 같은 열의 값들을 0부터 N으로 진행하면서 저장
 * 아래 : 같은 열의 값들은 N부터 0으로 진행하면서 저장 
 * 왼쪽 : 같은 행의 값들을 0부터 N으로 진행하면서 저장
 * 오른쪽 : 같은 행의 값들을 N부터 0으로 진행하면서 저장
 * 위와 같이 움직임에 따라 다르게 배열에 저장하면 2번 연산은 하나의 메소드로 처리할 수 있음
 * 연산 결과물을 저장할 때도 각 움직임에 맞게 구현해야 함
 * 단점은 이렇게 움직임에 따라 세부사항만 다른 구현이 필요하고 결과적으로 코드가 길어짐
 * 
 * 
 * 2번
 * 1번을 통해 얻은 배열을 1부터 순회
 * 
 * 1.0인지 확인
 * - 0이면 continue
 * - 0이 아니면 마저 진행
 * 
 * 2.saved와 현재 값이 같은지 확인
 * - 같으면 result에 더한값 저장하고 saved를 0으로 초기화
 * - 다르면 result에 saved값 저장하고 saved를 현재값으로 초기화
 * 
 * 3.1번으로 이동
 * 
 * 4. 순회가 종료되면 saved가 0인지 확인하고 0이 아니면 result에 저장
 */
class solve12100 {

    int sizeOfMap;
    int[][] map;

    solve12100(BufferedReader br) throws IOException {

        sizeOfMap = Integer.parseInt(br.readLine());
        map = new int[sizeOfMap][sizeOfMap];

        for (int i = 0; i < sizeOfMap; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < sizeOfMap; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    int result = 0;
    void run() {

        backTracking(0, map);
        System.out.println(result);

    }

    void backTracking(int depth, int [][] map) {

        if (depth == 5) {
            int max = getMax(map);
            result = Math.max(result, max);
            return;
        }

        
        for(int i=0;i<4;i++){
            int [][] tmp = move(copyMap(map), i);
            backTracking(depth+1, tmp);
        }
    }

    int [][] copyMap(int [][] map){

        int [][] copy = new int [sizeOfMap][sizeOfMap];
        for(int i=0;i<sizeOfMap;i++){
            
            for(int j=0;j<sizeOfMap;j++){
                copy[i][j] = map[i][j];
            }
        }

        return copy;
    }

    int[][] move(int[][] tmpMap, int direction) {

        int[][] result = new int[sizeOfMap][sizeOfMap];

        for(int i=0;i<sizeOfMap;i++){
            int [] nowRowOrCol = getNowAry(tmpMap, i, direction);
            int [] each = getEach(nowRowOrCol);
            setEach(result, i, direction, each);
        }

        return result;
    }

    int [] getNowAry(int [][] tmpMap, int now, int direction){

        int [] result = new int [sizeOfMap];
        
        //up
        if(direction == 0){
            for(int i=0;i<sizeOfMap;i++){
                result[i] = tmpMap[i][now];
            }
        }
        //down
        else if(direction == 1){
            for(int i=0;i<sizeOfMap;i++){
                result[i] = tmpMap[sizeOfMap-i-1][now];
            }
        }
        //left
        else if(direction == 2){
            for(int i=0;i<sizeOfMap;i++){
                result[i] = tmpMap[now][i];
            }
        }
        //down
        else if(direction == 3){
            for(int i=0;i<sizeOfMap;i++){
                result[i] = tmpMap[now][sizeOfMap-i-1];
            }
        }

        return result; 
    }

    int [] getEach(int[] tmp) {

        int count = 0;
        int[] each = new int[sizeOfMap];
        int saved = tmp[0];
        for (int i = 1; i < sizeOfMap; i++) {

            if(tmp[i] == 0){
                continue;
            }

            if (saved == tmp[i]) {
                each[count] = saved * 2;
                saved = 0;
            } else {
                each[count] = saved;
                saved = tmp[i];
            }
            count++;
        }

        if(saved != 0){
            each[count] = saved;
        }

        return each;
    }

    void setEach(int [][] result, int now, int direction, int [] each){

        //up
        if(direction == 0){
            int count = 0;
            for (int j = 0; j < sizeOfMap; j++) {
                if (each[j] == 0) {
                    continue;
                }
                result[count++][now] = each[j];
            }
        }
        //down
        else if(direction == 1){
            int count = sizeOfMap - 1;
            for (int j = 0; j < sizeOfMap; j++) {
                if (each[j] == 0) {
                    continue;
                }
                result[count--][now] = each[j];
            }
        }
        //left
        else if(direction == 2){
            int count = 0;
            for (int j = 0; j < sizeOfMap; j++) {
                if (each[j] == 0) {
                    continue;
                }
                result[now][count++] = each[j];
            }
        }
        //down
        else if(direction == 3){
            int count = sizeOfMap - 1;
            for (int j = 0; j < sizeOfMap; j++) {
                if (each[j] == 0) {
                    continue;
                }
                result[now][count--] = each[j];
            }
        }
    }

    int getMax(int[][] tmpMap) {

        int result = 0;
        for (int i = 0; i < sizeOfMap; i++) {

            for (int j = 0; j < sizeOfMap; j++) {
                result = Math.max(result, tmpMap[i][j]);
            }
        }

        return result;
    }

    void print(int [][] result){
        System.out.println();
        for (int i = 0; i < sizeOfMap; i++) {

            for (int j = 0; j < sizeOfMap; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve12100 p = new solve12100(br);
        p.run();

        br.close();
    }
}