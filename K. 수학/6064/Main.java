import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

/*
 * 기본적인 흐름
 * 한 주기를 구하고 주기를 더하면서 구하는 방식
 * 예를 들어 10 12 3 9는 
 * 9 9 -> 1 9 -> 3 9
 * 이런 식으로 M과 N의 차를 X에 더함으로써 구할 수 있음
 * 이는 9 + 12 + 12번째 조합임
 * 만약 한 바퀴를 돌았는데도 없으면 -1을 반환 
 * 
 * 주의할 점
 * 첫번째 주기를 구할 때
 * M 또는 N이 1일 때 
 * 기존에 주기를 구할 땐 현재 값이 최댓값보다 클 때만 나머지 연산을 했음
 * 그렇게 하면 1일 땐 1로 나누기 때문에 항상 나머지가 0으로 나옴
 * 그래서 일단 나머지 연산을 하고 나머지가 0이면 1로 바꾸는 방식으로 해야 함 
 * 
 * M과 N 중 더 큰값에 따라 연산 대상이 달라짐 
 * start : 첫번째 주기에서 뽑은 시작될 수
 * abs : M과 N의 차로 start에 더해질 수
 * cycleOfStart : start의 주기
 * result : 몇번째 해인지가 저장될 변수 start의 위치부터 시작 
 * target : 찾을 해
 * checkVisited : 방문했는지 확인하기 위한 모든 해의 총합
 * 
 * M > N 이면 전체 주기는 M에 맞춰지므로 start는 y
 * 반대면 전체 주기는 N에 맞춰지므로 start는 x 
 * 이거에 맞춰서 위 파라미터들 잘 조절해주면 됨
 * 
 * 근데 시간이 엄처 오래 걸림 다른 방법이 있는 듯
 */
class solve6064{

    int M, N, x, y;
    int cycleSize;
    int [][] cycle; 
    
    solve6064(int [] input) {

        M = input[0];
        N = input[1];
        x = input[2];
        y = input[3];

        cycleSize = M > N ? M : N;
        cycle = new int [cycleSize+1][2];
    }

    int run(){

        for(int i=0;i<cycleSize;i++){
            cycle[i+1][0] = getNext(i, 1, M);
            cycle[i+1][1] = getNext(i, 1, N);
        }

        if(M > N) return getResult(cycle[x][1], M - N, N, x, y, IntStream.rangeClosed(1, N).sum());
        else return getResult(cycle[y][0], N - M, M, y, x, IntStream.rangeClosed(1, M).sum());
    }

    int getResult(int start, int abs, int cycleOfStart, int result, int target, int checkVisited){

        int visited = start;
        while(true){

            if(start == target){
                break;
            }

            start = getNext(start, abs, cycleOfStart);
            result += cycleSize;
            visited += start;

            if(visited > checkVisited){
                result = -1;
                break;
            }
        }

        return result;
    }

    int getNext(int now, int add, int cycle){

        int tmp = now + add;

        tmp %= cycle;
        if(tmp == 0) tmp = cycle;
        return tmp;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int num = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<num;i++){

            StringTokenizer st = new StringTokenizer(br.readLine());
            int [] input = new int [4];
            for(int j=0;j<4;j++){
                input[j] = Integer.parseInt(st.nextToken());
            }

            solve6064 p = new solve6064(input);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}