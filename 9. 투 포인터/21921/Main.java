import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 이중 반복문을 돌리면 시간초과가 발생함
 * 방문자수를 빠르게 구할 수 있는 방법이 필요함
 * 이전값 - 이전 값의 첫 인덱스 + 현재 값의 마지막 인덱스 를 하면 현재 값을 빠르게 받아올 수 있음
 * 
 * 1 2 3 4 에서 3개씩 묶는다면
 * 1 2 3 에서 1 을빼고 4를 더하는 방식
 */

class solve21921{

    int numOfDays;
    int target;
    int [] visitors;

    void input(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfDays = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        visitors = new int [numOfDays];

        for(int i=0;i<numOfDays;i++){
            visitors[i] = Integer.parseInt(st.nextToken());
        }
    }

    void print(int max, int numOfMax){

        StringBuilder sb = new StringBuilder();
        if(max == 0){
            System.out.println("SAD");
        } else {
            sb.append(max).append("\n").append(numOfMax);
            System.out.println(sb);
        }
    }

    void run(BufferedReader br) throws IOException{

        input(br);
        
        int numOfMax = 1;
        int previousValue = 0;
        for(int i=0;i<target;i++){
            previousValue += visitors[i];
        }
        int max = previousValue;
        
        for(int i=1;i<numOfDays-target+1;i++){

            previousValue = previousValue - visitors[i-1] + visitors[i+target-1];

            if(max < previousValue){
                numOfMax = 1;
                max = previousValue;
            } else if(previousValue == max){
                numOfMax++;
            }       
        }

        print(max, numOfMax);
    }
}

public class Main{

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve21921 p = new solve21921();
        p.run(br);

        br.close();
    }
}