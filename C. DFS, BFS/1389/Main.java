import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * N 부터 다른 모든 노드로의 최단 거리를 구하는 문제
 * 다익스트라 같지만 가중치가 모두 1이기 때문에 BFS로 풀면 됨
 */
class solve1389{

    int numOfUser;
    int [] user;
    List<List<Integer>> relation = new ArrayList<>();

    solve1389 (BufferedReader br ) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfUser = Integer.parseInt(st.nextToken());
        int numOfRelation = Integer.parseInt(st.nextToken());

        user = new int [numOfUser+1];
        for(int i=0;i<numOfUser+1;i++){
            relation.add(new ArrayList<>());
        }

        for(int i=0;i<numOfRelation;i++){

            st = new StringTokenizer(br.readLine());

            int user1 = Integer.parseInt(st.nextToken());
            int user2 = Integer.parseInt(st.nextToken());

            relation.get(user1).add(user2);
            relation.get(user2).add(user1);
        }
    }

    void run(){

        int min = Integer.MAX_VALUE;
        int result = -1;
        for(int i=1;i<numOfUser+1;i++){
            
            int tmp = getKevin(i);
            if(min > getKevin(i)){
                result = i;
                min = tmp;
            }
        }

        System.out.println(result);
    }

    int getKevin(int start){

        boolean [] visited = new boolean[numOfUser+1];
        Queue<Integer> queue = new ArrayDeque<>();
        int [] kevin = new int [numOfUser+1];

        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){

            int now = queue.poll();

            for(int linked : relation.get(now)){

                if(!visited[linked]){

                    visited[linked] = true;
                    kevin[linked] = kevin[now] + 1;
                    queue.add(linked);
                }
            }
        }

        int result = 0;
        for(int i=1;i<numOfUser+1;i++){
            result += kevin[i];
        }

        return result;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1389 p = new solve1389(br);
        p.run();

        br.close();
    }
}