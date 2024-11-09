import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 누적 합 알고리즘 기본
 * 
 * N ~ M 까지의 합 = 1 ~ M까지의 합 - 1 ~ N까지의 합
 * 이를 위해 각 인덱스까지의 합을 미리 구해서 배열에 저장해두면 됨
 * 
 */
public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfValue = Integer.parseInt(st.nextToken());
        int numOfTarget = Integer.parseInt(st.nextToken());

        int [] value = new int [numOfValue + 1];

        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i=1;i<numOfValue + 1;i++){

            value[i] = sum += Integer.parseInt(st.nextToken());   
        }

        for(int i=0;i<numOfTarget;i++){

            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            sb.append(value[end] - value[start - 1]).append("\n");
        }

        System.out.println(sb);
        
        br.close();
    }
}