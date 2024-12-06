import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * BFS로 탐색하지 않고 오로지 DP만 이용하는 방식(이게 더 빠름)
 * 경로가 최대 두 가지중 하나고 이미 접근한 노드는 다시 접근하지 않기 때문에 
 * 데이터를 입력받을 때 사용한 배열을 그대로 이용해도 됨
 * 
 * 간단한 흐름
 * 현재 노드 = 이전 노드 둘 중 더 큰 값 + 현재 노드
 * 
 * 여기서 주의할 점
 * 매 depth의 0번째와 마지막 인덱스는 딱 하나의 노드하고만 연결되어 있음
 * - 그래서 0 번째는 반복에 포함시키지 않고 따로 0번째하고만 더하고
 * - 마지막은 배열의 인덱스를 한 칸 더 추가해서 더미 데이터 0을 넣어둠으로써 해결
 */
class solve1932{

    int num;
    int [][] tri;

    solve1932 (BufferedReader br) throws IOException{

        num = Integer.parseInt(br.readLine());
        
        tri = new int [num][];
        for(int i=0;i<num;i++) {
            
            tri[i] = new int [i+2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<i+1;j++){
                tri[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    void run(){
        
        for(int i=1;i<num;i++){

            tri[i][0] += tri[i-1][0];
            for(int j=1;j<i+1;j++) tri[i][j] += Math.max(tri[i-1][j-1], tri[i-1][j]);
        }

        int result = 0;
        for(int i=0;i<num;i++) result = Math.max(result, tri[num-1][i]);

        System.out.println(result);
    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1932 p = new solve1932(br);
        p.run();

        br.close();
    }
}