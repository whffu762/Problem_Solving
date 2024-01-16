import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.List;

/*
 * 방법 1 
 * DP를 이용
 * 목적지까지 이동하면서 각 지점마다 소요되는 최소 이동 경로를 저장하는 방식
 * 현재 지점 location까지 도달할 수 있는 경우의 수
 * 1. 이전 지점 + 1
 * 2. 지름길 이용
 * 
 * 이 둘중 더 작은 값을 저장하는데 주의할 점
 * 1. 동일한 루트의 지름길이 여러 개 존재할 수 있으니 지름길 중에서도 최소를 가려내야 함
 * 2. 지름길을 이용한 소요 거리 = 지름길 시작점까지의 소요 거리 + 지름길의 거리
 * 
 * 
 * 
 * 방법 2
 * dfs 이용해서 도착하면 최솟값 비교
 * 각 지름길을 거치는 경우와 거치지 않는 경우 모두 계산해야 함 그러기 위해 재귀 호출을 이용
 * 
 * 1. List<>에 지름길이 저장됨
 * 2. 저장된 지름길을 순차적으로 순회해서 현재 위치에 지름길이 있으면 접근, 없으면 +1
 * 3. 우선 지름길이 존재하면 먼저 접근하고 목적지에 도달하면 이동한 거리와 저장되어 있는 최솟값을 비교
 * 4. 지름길 탐색이 끝나면 마지막에 접근했던 갈래길에서 다른 길로 진행(+1)
 * 5. 목적지에 도달하면 다시 최솟값 비교
 * 
 * 쉽게 말하면 목적지를 찍으면 직전의 갈래길로 돌아가서 가보지 않은 길을 이용해 다시 탐색하는 방식
 */


//DFS 이용
class solve1446{

    List<List<Integer>> ways = new ArrayList<>();
    int destination;
    int minimum;

    void input(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfWay = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        minimum = destination;

        for(int i=0;i<numOfWay;i++){
            st = new StringTokenizer(br.readLine());
            List<Integer> way = new ArrayList<>();
            
            int numOfTokens = st.countTokens();
            for(int j=0;j<numOfTokens;j++){
                way.add(Integer.parseInt(st.nextToken()));
            }
            ways.add(way);
        }
    }
    
    void dfs(int location, int distanceOfMove){

        if(location == destination){
            minimum = Math.min(minimum, distanceOfMove);  //목적지에 도착했으면 역대 최솟값과 움직인 거리를 비교해서 최솟값을 최신화
            return;     //재귀 호출을 종료하고 원래 있던 location과 distanceOfMove로 복귀
        }
        if(location > destination){
            return; //목적지보다 멀리 왔으면 최솟값 변경하지 말고 원래 장소로 복귀
        }

        for(List<Integer> way : ways){
            if(location == way.get(0)){
                dfs(way.get(1), distanceOfMove + way.get(2));   //지름길을 거치는 경우 계산
            }
        }

        dfs(location+1, distanceOfMove+1);      //지름길을 거치지 않는 경우 계산
    }

    void run(BufferedReader br) throws IOException{

        input(br);
        int start = 0;
        int distanceOfMove = 0;
        dfs(start, distanceOfMove);

        System.out.println(minimum);
    }
}


//DP를 이용
class other1446{

    List<List<Integer>> ways = new ArrayList<>();
    int destination;
    int [] minimum; //각 지점까지 소요되는 최소거리가 저장됨

    void input(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfWay = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        minimum = new int [destination+1];
    
        for(int i=0;i<numOfWay;i++){
            st = new StringTokenizer(br.readLine());
            List<Integer> way = new ArrayList<>();
            
            int numOfTokens = st.countTokens();
            for(int j=0;j<numOfTokens;j++){
                way.add(Integer.parseInt(st.nextToken()));
            }
            ways.add(way);
        }
    }

    void compute(){

        for(int location=1;location<destination+1;location++){  //1부터 목적지까지 진행

            int byWay = destination;    //지름길을 이용할 때의 소요 거리가 저장될 변수로 초기값은 max 값을 넣어둠

            for(List<Integer> way : ways){
                if(way.get(1) == location){ //현재 지점이 지름길의 도착 지점이면
                    byWay = Math.min(byWay, way.get(2) + minimum[way.get(0)]);
                    //지름길을 이용할 때의 소요 거리 = 지름길의 시작 지점까지의 소요 거리 + 지름길의 거리
                    //동일한 지름길 중 가장 최소 거리를 저장
                }
            }

            minimum[location] = Math.min(minimum[location-1]+1, byWay); //지름길을 이용한 경우와 이전값 + 1 중 최소값을 연산
        }
    }
    

    void run(BufferedReader br) throws IOException{

        input(br);
        minimum[0] = 0; //초기값 0까지의 소요거리 = 0
        compute();

        System.out.println(minimum[destination]);
    }

}
 
public class Main{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //solve1446 p = new solve1446();
        other1446 p = new other1446();
        p.run(br);

        br.close();
    }
}
