import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 파라메트릭 서치를 이용해서 해결해야 함 경우의 수가 많아서 시간초과 뜸
 * 다만 이전 2512번 문제와 조금 다름
 * 1. while문의 조건
 * 2. low, high, mid로 이용되는 값
 * 3. low와 high의 변화
 * 크게 이 세 가지가 다름
 * 
 * while문의 조건은 low와 high의 변화와 연관이 있음
 * 2512 문제에선 low와 high가 mid로 되는 식으로 구현했음 즉 low 혹은 high가
 * 특정 지점에서 멈추는 경우가 발생하기 때문에 다른 멈추는 지점에 도달할 경우 
 * while문을 종료하는 방식으로 구현함
 * 하지만 여기선 low와 high가 겹치는 경우가 있고 그 경우도 확인해야 하기 때문에
 * low와 high를 강제로 1씩 이동시킴
 * 
 * name - power의 갯수가 적으면 겹치는 부분을 확인하지 않아도 되는 것처럼 보이지만
 * 기준의 수가 많아지면 확인해야 하는 경우가 생김 
 * 기준 : 1 3 5 7 9 11 13 15 17 
 * 입력 : 2
 * low와 high가 1에서 만나는데 이는 기댓값이 아님
 * 
 * 
 * low, high, mid로 이용되는 값 즉 탐색하는 대상
 * 2512번에선 예산 자체를 이용했지만 여기선 전투력으로 하기엔 값이 너무 큼
 * 또한 입력된 기준 중에서 고르는 것이기 때문에 기준이 저장된 배열의 인덱스를 이용해
 * 이진 탐색을 진행함
 */


class solve19637{

    String [] names;
    int [] powers;

    String judgeName(int target){

        int high = powers.length - 1;
        int low = 0;

        while(low <= high){
            
            int mid = (low + high) / 2;

            if(powers[mid] < target){
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return names[low];
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