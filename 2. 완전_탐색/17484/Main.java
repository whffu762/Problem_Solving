import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*

난 이상하게 풀었는데 dfs 로 간단하게 풀 수 있다고 함..

 * 각 행의 열을 배열 tmp 에 저장하고 유효 조건을 검사한 후 
 * 유효 조건에 부합하면 배열에 저장된 열을 이용하여 비용을 계산
 * 계산된 비용은 result 와 비교하여 최소값을 출력함
 * 
 * 배열에 저장된 tmp 의 값은 0부터 시작해서 1 씩 증가하여 입력으로 가능한 모든 값으로 순회한다
 * 배열에 저장되는 값은 각 행의 열임
 * 
 * 예를 들어 4 x 3 행렬이 입력으로 주어진 경우
 * 12 3 7
 * 1 5 6
 * 2 9 4
 * 1 2 3
 * 
 * 0 0 0 0 - 12 1 2 1 
 * 0 0 0 1 - 12 1 2 2
 * 0 0 0 2 - 12 1 2 3   (3열이기 때문에 2 이상의 값은 필요없음)
 * 0 0 1 0 - 12 1 9 1   (다음 행의 값이 증가하면 이전 행의 값은 0으로 초기화)
 * 0 0 1 1 - 12 1 9 2
 * ...
 * 3 3 3 3 - 7 6 4 3
 * 
 * 배열 tmp에 저장된 값은 각각에 해당하는 열 index 이고
 * 이런 식으로 1 씩 증가해서 유효 조건에 검사됨
 * 
 * 유효 조건
 * 1. 마지막인지 확인 - 모든 행의 열이 col 값에 다다르면 마지막임
 * - 위 경우 행이 4개고 열이 3이니까 3 3 3 3이 마지막 입력
 * 
 * 2. 1씩 이동했는지 확인 - 연속된 모든 행의 열의 차가 1 이하여야 함
 * 3. 연속으로 같은 방향으로 이동했는지 확인 - 연속된 행의 열의 차가 동일한 값이 존재하면 안 됨
 * - 0 0 0 1 의 경우 연속된 행의 열의 차가 1 이지만 동일한 값 0 이 존재해서 부합하지 않음
 * - 0 2 1 3 의 경우 열의 차가 각각 -2 1 -2 로 연속되진 않지만 차가 1 이상이라 부합하지 않음
 * - 0 1 0 1 의 경우 열의 차가 1 -1 1 로 연속되지도 않고 1 이하기 때문에 조건에 부함
 * 
 * 모든 유효 조건에 만족하면 비용을 계산해서 최소값을 비교한다
 */

class solve17484{

    int row;
    int col;
    int [][] target;

    boolean checkLast(int [] list){
        
        //마지막인지 확인
        for(int i=0;i<list.length;i++){
            if(list[i] != col-1){
                return false;
            }
        }
        
        return true;
    }

    boolean checkVaild(int [] list){

        int [] diff = new int [list.length-1];
        for(int i=0;i<list.length-1;i++){
            diff[i] = list[i] - list[i+1];
        }

        //이동 거리를 1씩 이동했는지 확인
        for(int x : diff){
            if(Math.abs(x) > 1){
                return false;
            }
        }

        //동일한 방향을 연속으로 이동하지 않았는지 확인
        for(int i=0;i<diff.length-1;i++){
            if(diff[i] == diff[i+1]){
                return false;
            }
        }

        return true;
    }

    //다음 열의 배열을 구함
    int [] getNext(int [] list){

        for(int i=0;i<list.length;i++){
            //현재 값을 올림(200 -> 300)
            if(list[i] < col-1){
                list[i]++;
                break;
            }
            
            //자릿수 올림(300 -> 010)
            if(list[i] == col-1){
                list[i] = 0;
            }
        }
    
        return list;
    }

    int getResult(int [] list){
        
        int result = 0;
        for(int i=0;i<target.length;i++){
            result += target[i][list[i]];
        }

        return result;
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

        int result = 100 * row;

        //각 행의 열을 저장하는 배열
        int [] tmp = new int [row];
        while(true){

            if(checkLast(tmp)){
                break;
            }
            
            if(checkVaild(tmp)){
                int tmpResult = getResult(tmp);
                result = Math.min(result, tmpResult);
            }
            tmp = getNext(tmp);  
        }

        System.out.println(result);
    }
}

class Main{
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve17484 p = new solve17484();
        //solve17484Dfs p = new solve17484Dfs();
        p.run(br);

        br.close();
    }
}