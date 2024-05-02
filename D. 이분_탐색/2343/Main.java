import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 
 * 이분 탐색으로 풀 때 주의해야 할 점
 * 1. 최솟값과 최댓값
 * 2. 어떤 값이 결과값인지
 * 
 * 최솟값과 최댓값
 * 최솟값 : 1이라고 생각할 수 있지만 아님 모든 강의가 하나의 블루 레이에 들어가야 하기 때문에 가장 긴 강의가 최솟값으로 들어가야 함
 * 최댓값 : 모든 강의가 하나의 블루레이에 들어갈 수 있기 때문에 모든 영상의 합이 해당함
 * 
 * 어떤 값이 결과값인지
 * 만약 정답이 3인 경우의 값의 분포는 다음과 같음
 * 4444444..4433333...332222...222
 * 위와 같이 블루레이의 길이가 짧을수록 갯수가 많아짐 때문에 즉 3이 나올 수 있는 길이 중 최솟값을 구하는 것이기 때문에 low를 반환하면 됨
 * 직관적으로 이해가 가지는 않으나 최솟값일 경우 low 최댓값일 경우 high를 반환하면 됨
 * 
 * 하지만 이건 이해가 안 가니 더 직관적인 방법이 있음 정답에 해당 하는 경우를 result로 저장하는 것임
 * 
 * count가 numOfBluray의 값과 같은 경우가 필요함 아래 코드를 보면 checkLenght()의 반환값이 false일 때가 해당됨
 * 그때의 최종적으로 할당되는 mid 값이 정답에 해당함
 */
class solve2343{

    int max = 0;
    int sum = 0;
    int [] lecutures;
    int numOfBluray;
    
    solve2343(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfLectures = Integer.parseInt(st.nextToken());
        numOfBluray = Integer.parseInt(st.nextToken());

        lecutures = new int [numOfLectures];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfLectures;i++){

            int now = Integer.parseInt(st.nextToken());
            lecutures[i] = now;
            max = Math.max(max, now);
            sum += now;
        }
    }

    boolean checkLength(int length){

        int sum = 0;
        int count = 1;
        for(int i=0;i<lecutures.length;i++){
            sum += lecutures[i];

            if(sum > length){
                count++;
                sum = 0;
                i--;
            }
        }

        if(count <= numOfBluray){
            return false;
        }

        return true;
    }

    void run(){

        int result = 0;
        int low = max;
        int high = sum;

        while(low <= high){

            int mid = (low + high) / 2;

            if(checkLength(mid)){
                low = mid + 1;
            } else {
                result = mid;
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}

public class Main{
    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2343 p = new solve2343(br);
        p.run();

        br.close();
    }
}