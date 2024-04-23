import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 주의할 점
 * start의 값은 1부터 가능 0부터 시작하면 mid가 0이 될 수 있어서 안 됨
 * mid의 자료형 최댓값은 int의 최댓값 - 1임 때문에 거의 무조건 int 의 범위를 넘어섬 오버플로우 주의
 */

class solve1654{

    int numOfLine;
    int target;
    int [] lines;
    int max = 0;

    solve1654(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfLine = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());
        
        lines = new int [numOfLine];
        for(int i=0;i<numOfLine;i++){
            int input = Integer.parseInt(br.readLine());
            lines[i] = input;
            max = Math.max(max, input);
        }
    }

    long getNumOfLines(long length){

        int count = 0;
        for(int i=0;i<numOfLine;i++){
            count += lines[i] / length;
        }

        return count;
    }


    void run(){

        long start = 1;
        long end = max;
        long result = 0;

        while(start <= end){

            long mid = (start + end) / 2;
            
            if(target <= getNumOfLines(mid)){
                result = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1654 p = new solve1654(br);
        p.run();
        
        br.close();
    }
}