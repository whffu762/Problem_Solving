import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 파라메트릭 서치를 이용해서 해결해야 함 경우의 수가 많아서 시간초과 뜸
 * 다만 이전 2512번 문제와 조금 다름
 * 1. low, high, mid로 이용되는 값
 * 2. 결정 조건에 따라 저장되는 결과값
 * 
 * low, high, mid로 이용되는 값 즉 탐색하는 대상
 * 2512번에선 예산 자체를 이용했지만 여기선 전투력 자체로 하면 결정 조건을 만들지 못 함
 * 
 * 결정 조건에 따라 저장되는 결과값
 * 2512에선 정해진 예산 이내로 최대값을 사용하는 문제였지만 여기선 전투력 기준표에서 
 * 입력된 전투력보다 큰 값 중 최솟값을 찾는 과정임
 * 때문에 target이 기준표의 값보다 작으면 해당 기준을 저장하는 방식으로 결과를 얻음  
 */


class solve19637{

    String [] names;
    int [] powers;

    String judgeName(int target){

        int high = powers.length - 1;
        int low = 0;
        int result = 0;

        while(low <= high){
            
            int mid = (low + high) / 2;

            if(target <= powers[mid]){
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return names[result];
    }


    void run(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int numOfName = Integer.parseInt(st.nextToken());
        int numOfUser = Integer.parseInt(st.nextToken());
        names = new String[numOfName];
        powers = new int[numOfName];


        for(int i=0;i<numOfName;i++){

            st = new StringTokenizer(br.readLine());
            names[i] = st.nextToken();
            powers[i] = Integer.parseInt(st.nextToken());
        }
        
        
        for(int i=0;i<numOfUser;i++){

            int target = Integer.parseInt(br.readLine());
            sb.append(judgeName(target)).append("\n");
        }

        System.out.println(sb);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve19637 p = new solve19637();
        p.run(br);

        br.close();
    }
}