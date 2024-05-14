import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 투 포인터 슬라이딩 윈도우를 이용하는 방식
 * 각 초밥을 먹은 갯수를 이용해 먹은 초밥의 가짓수를 계산하는 방식
 * 
 * 동작 방식 예시
 * 1. table[0] ~ table[3]까지 먹는 경우로 초기화 
 *      이때 각 초밥의 먹은 갯수와 먹은 가짓수를 초기화 함
 * 
 * 2. 다음은 table[1] ~ table[4]까지 먹는 경우를 계산 table[0]을 지우고 table[4]를 넣어야 함
 *      table[0]에 해당하는 초밥의 갯수를 ++ 그 값이 0이면 먹은 초밥의 가짓수를 --
 *      table[4]에 해당하는 초밥의 갯수를 -- 그 값이 1이면 먹은 초밥의 가짓수를 ++
 * 
 * 3. 이 동작을 마지막 초밥으로 시작하는 경우까지 반복
 * 
 * 
 * 이렇게하면 기존 1700ms가 걸리던 작업이 184ms로 줄어듦
 * 그 이유는 일단 연산의 횟수가 압도적으로 작음 (최대 30000 - table.length) 
 * 또한 각 반복에서 수행하는 연산이 매우 간단함 (단순하게 2개의 index에 대해서만 연산)
 * 그에 따라 메모리도 당연히 적게 사용함 15배는 차이나는 듯
 */

class solve2531{

    int [] table;
    int [] sushi;
    int canEat;
    int coupon;

    solve2531(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int onTheTable = Integer.parseInt(st.nextToken());
        int sortOfSushi = Integer.parseInt(st.nextToken());
        canEat = Integer.parseInt(st.nextToken());
        coupon = Integer.parseInt(st.nextToken());

        sushi = new int [sortOfSushi+1];

        table = new int [onTheTable];
        for(int i=0;i<onTheTable;i++){
            table[i] = Integer.parseInt(br.readLine());
        }
    }

    void run(){
        
        int result = 1;
        sushi[coupon]++;

        //슬라이딩 윈도우에서 첫번째 입력은 초기화해줘야 함
        for(int i=0;i<canEat;i++){

            if(sushi[table[i]]++ == 0){
                result++;
            }
        }


        int tmpResult = result;
        //슬라이딩 윈도우 수행
        for(int i=1;i<table.length;i++){

            //이전 start를 지움
            int oldStart = --sushi[table[i - 1]];
            if(oldStart == 0){
                tmpResult--;
            }
            
            //새로운 end를 추가함
            int newEnd = ++sushi[table[(i + canEat - 1) % table.length]];
            if(newEnd == 1){
                tmpResult++;
            }
            
            //그렇게 해서 연산된 결과와 최댓값을 비교
            result = Math.max(result, tmpResult);
        }

        System.out.println(result);
    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2531 p = new solve2531(br);
        p.run();

        br.close();
    }
}