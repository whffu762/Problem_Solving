import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;

/*
 * 연속된 배열의 일부를 구하기 때문에 투포인터를 이용해야 함
 * 완전 탐색으로 해도 만들어지긴 하는데 시간 초과 뜸
 * 
 * 투포인터 알고리즘 자체는 되게 단순함
 * 조건에 맞으면 end를 움직이고 조건에 맞지 않으면 start를 움직이는 방식
 * 근데 그 조건과 그에 따른 동작 순서가 중요함
 * 
 * 조건의 종류
 * 1. limit과 end의 갯수가 같음
 * 2. limit보다 end의 갯수가 더 큼
 */

 //limit == end의 갯수
class solve20922{

    int limit;
    List<Integer> input = new ArrayList<>();
    int [] count;

    solve20922(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        limit = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int max = 0;
        for(int i=0;i<length;i++){
            input.add(Integer.parseInt(st.nextToken()));
            max = Math.max(max, input.get(i));
        }

        count = new int [max+1];
    }

    void run(){

        int result = 0;

        int start = 0;
        int end = 0;

        while(end != input.size()){

            int now = input.get(end);
            int numOfNow = count[now];

            if(numOfNow == limit){
                result = Math.max(result, end - start);
                count[input.get(start)]--;
                start++;
            } else {
                count[now]++;
                end++;
            }
        }

        result = Math.max(result, end - start);
        System.out.println(result);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve20922 p = new solve20922(br);
        p.run();

        br.close();
    }
}