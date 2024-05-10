import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 모든 노드에서 모든 노드로 향하는 최단 경로 = 플로이드 워샬
 * 모든 노드를 경유지로 활용했을 때의 경우를 계산해서 최솟값만을 저장하는 방식임
 * 3중 반복이 활용되기 때문에 노드수가 100개보다 많아지면 1초보다 더 걸림
 * 
 * 또한 다익스트라와 마찬가지로 최단 경로가 저장될 shortPath[][]의 초깃값이 중요한데
 * 이 문제에선 간선의 최대 갯수가 10만이고 최대 비용이 10만 이므로 10만^2 + 1로 설정
 * 
 */

class sovle11404{

    int numOfCity;
    long [][] shortPath;
    long maxValue = 10000000001L;

    sovle11404(BufferedReader br) throws IOException{

        numOfCity = Integer.parseInt(br.readLine());

        shortPath = new long [numOfCity+1][numOfCity+1];
        for(int i=0;i<shortPath.length;i++){
            Arrays.fill(shortPath[i], maxValue);
        }

        int numOfLines = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfLines;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            shortPath[start][end] = (shortPath[start][end] < weight) ? shortPath[start][end] : weight;
        }
    }

    void run(){

        for(int transfer=1;transfer<numOfCity+1;transfer++){
            for(int start=1;start<numOfCity+1;start++){
                for(int end=1;end<numOfCity+1;end++){

                    if(start == end || start == transfer || end == transfer){
                        continue;
                    }

                    long oldPath = shortPath[start][end];
                    long newPath = shortPath[start][transfer] + shortPath[transfer][end];

                    if(newPath < oldPath){
                        shortPath[start][end] = newPath;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<shortPath.length;i++){
            for(int j=1;j<shortPath.length;j++){

                long now = shortPath[i][j];
                if(now == maxValue){
                    sb.append("0").append(" ");
                } else {
                    sb.append(shortPath[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sovle11404 p = new sovle11404(br);
        p.run();

        br.close();
    }
}