import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 위 방식의 코드량, 중복에 대한 해결책
 * 2차 배열을 90도 회전하는 방식
 * 
 * 이동 방향은 왼쪽으로 통일해두고 2차 배열을 돌리면 각 방향으로 이동하게 됨
 * 예를 들어 90도 돌리면 아래 방향으로 이동시킨 것과 동일한 결과임
 * 이렇게 하면 각 배열을 이동 방향마다 다르게 뽑을 필요도 없고
 * result에 저장할 때도 각기 다른 구현이 필요하지도 않아서 코드량이 확실히 줄어듦
 * 
 */
class solve12100 {

    int sizeOfMap;
    int[][] map;
    int result = 0;

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
            map = rotate(map);
            int [][] tmp = move(copyMap(map));
            backTracking(depth+1, tmp);
        }
    }

    int [][] rotate(int [][] map){

        int [][] result = new int [sizeOfMap][sizeOfMap];

        for(int i=0;i<sizeOfMap;i++){
            
            for(int j=0;j<sizeOfMap;j++){
                result[i][j] = map[sizeOfMap-j-1][i];
            }
        }

        return result;
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

    int[][] move(int[][] tmpMap) {

        int[][] result = new int[sizeOfMap][sizeOfMap];

        for(int i=0;i<sizeOfMap;i++){
            
            int [] each = getEach(tmpMap[i]);
            int count = 0;
            for(int j=0;j<sizeOfMap;j++){
                if(each[j] == 0){
                    continue;
                }
                result[i][count++] = each[j];
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

    int getMax(int[][] tmpMap) {

        int result = 0;
        for (int i = 0; i < sizeOfMap; i++) {

            for (int j = 0; j < sizeOfMap; j++) {
                result = Math.max(result, tmpMap[i][j]);
            }
        }

        return result;
    }
}

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve12100 p = new solve12100(br);
        p.run();

        br.close();
    }
}