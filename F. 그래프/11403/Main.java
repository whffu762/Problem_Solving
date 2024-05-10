import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 플로이드 워샬로 풀기
 * 
 * 간단한 동작 방식
 * 그래프 구조가 저장된 graph [][]
 * 경로가 존재하는지에 대한 정보가 저장된 result[][]
 * 
 * 모든 노드를 중간 다리로 삼는 경우를 확인하는 방식임
 * result[start][mid]와 result[mid][end]의 경로가 존재하면 result[start][end]가 존재함을 이용해서
 * 경로 존재 여부를 확인
 * 
 */
class solve11403{

    int numOfNode;
    boolean [][] graph;
    int [][] result;

    solve11403(BufferedReader br) throws IOException{

        numOfNode = Integer.parseInt(br.readLine());

        graph = new boolean[numOfNode][numOfNode];
        result = new int [numOfNode][numOfNode];

        for(int i=0;i<numOfNode;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<numOfNode;j++){
                if(st.nextToken().equals("0")){
                    graph[i][j] = false;
                    result[i][j] = 0;
                } else {
                    graph[i][j] = true;
                    result[i][j] = 1;
                } 
            }
        }
    }

    void run(){

        for(int mid=0;mid<numOfNode;mid++){
            for(int start=0;start<numOfNode;start++){
                for(int end=0;end<numOfNode;end++){

                    if(start == mid || end == mid){
                        continue;
                    }

                    if(result[start][mid] == 1 && result[mid][end] == 1){
                        result [start][end] = 1;
                    }
                }
            }
        }

        for(int i=0;i<numOfNode;i++){
            for(int j=0;j<numOfNode;j++){

                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }

    }
}


public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11403 p = new solve11403(br);
        p.run();

        br.close();
    }
}