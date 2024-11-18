import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 각 층 별로 필요한 시간을 구해서 최소를 구하면 됨
 * 
 * 여기서 구해야 할 것
 * - 구해야 하는 층의 범위
 * - 시간을 구하는 방법
 * 
 * 층의 범위
 * 입력된 값 중 최소층부터 최대층만 구하면 됨
 * 나머지 층은 어차피 그 범위보다 시간이 많이 소요됨
 * 
 * 시간을 구하는 방법
 * - 해당 층을 구하기 위해 필요한 제거해야 할 블록, 추가해야 할 블록
 * 2 * 제거 블록 + 추가 블록 -> 소요 시간
 * 제거 블록 + 가지고 있는 블록 < 추가 블록 이 경우엔 해당 층을 만들 수 없음
 * 
 * 가능한 층이 여러 개면 더 높은 층을 고르라고 했기 때문에 
 * min부터 max까지 반복하고 크거나 같으면 층이 바뀌도록 함 
 * 
 * 근데 시간을 좀 더 줄일 수 있을 것 같은데
 */
class solve18111{

    int row;
    int col;
    int [][] map;
    int item;

    int min = Integer.MAX_VALUE;
    int max = -1;

    solve18111 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        item = Integer.parseInt(st.nextToken());

        map = new int [row][col];
        for(int i=0;i<row;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<col;j++){
                int now = Integer.parseInt(st.nextToken());
                map[i][j] = now;
                min = Math.min(now, min);
                max = Math.max(now, max);
            }
        }
    }

    void run() {

        int time = Integer.MAX_VALUE;
        int floor = 0;
        for(int i=min;i<max+1;i++){
            int tmp = getTime(i);
            if(tmp <= time){
                time = tmp;
                floor = i;
            }
        }

        System.out.println(time + " " + floor);
    }

    int getTime(int floor){

        int add = 0;
        int remove = 0;
        for(int i=0;i<row;i++){
            
            for(int j=0;j<col;j++){
                int now = map[i][j];
                if(now < floor){
                    add += floor - now;
                } else {
                    remove += now - floor;
                }
            }
        }

        if(remove + item < add){
            return Integer.MAX_VALUE;
        }

        return add + 2 * remove;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve18111 p = new solve18111(br);
        p.run();
    
        br.close();
    }
}