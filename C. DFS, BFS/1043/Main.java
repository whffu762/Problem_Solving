import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 그래프 탐색하는 간단한 문제
 * 범위가 작아서 인접 행렬로 풀 수 있음
 */
class solve1043 {

    int numOfPeople;
    boolean [] people;

    int numOfKnow;
    int [] knows;

    int numOfParty;
    List<int []> parties = new ArrayList<>();

    boolean [][] graph;    

    solve1043 (BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfPeople = Integer.parseInt(st.nextToken());
        people = new boolean[numOfPeople+1];
        graph = new boolean[numOfPeople+1][numOfPeople+1];

        numOfParty = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        numOfKnow = Integer.parseInt(st.nextToken());
        knows = new int [numOfKnow];
        for(int i=0;i<numOfKnow;i++){
            knows[i] = Integer.parseInt(st.nextToken());
        }
        
        for(int i=0;i<numOfParty;i++){

            st = new StringTokenizer(br.readLine());
            int peopleOfParty = Integer.parseInt(st.nextToken());
            int [] party = new int [peopleOfParty];
            for(int j=0;j<peopleOfParty;j++){
                party[j] = Integer.parseInt(st.nextToken());
            }
            parties.add(party);
        }
    }

    void run(){

        makeGraph();
        
        for(int i=0;i<numOfKnow;i++){
            bfs(knows[i]);
        }

        int count = parties.size();
        for(int i=0;i<parties.size();i++){

            int [] party = parties.get(i);
            for(int j=0;j<party.length;j++){
                if(people[party[j]]) {
                    count--;
                    break;
                }
            }
        }

        System.out.println(count);
    }

    void bfs(int start) {

        boolean [] visited = new boolean[people.length];
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;

        while(!queue.isEmpty()){

            int now = queue.poll();
            people[now] = true;

            for(int i=1;i<graph.length;i++){

                if(graph[now][i] && !visited[i]){
                    visited[i] = true;
                    queue.add(i);
                }
            }
        }
    }

    void makeGraph() {

        for(int i=1;i<graph.length;i++){

            graph[i][i] = true;
        }

        for(int i=0;i<numOfParty;i++){
            
            int [] party = parties.get(i);
            for(int j=0;j<party.length-1;j++){

                for(int k=j+1;k<party.length;k++){
                    
                    graph[party[j]][party[k]] = true;
                    graph[party[k]][party[j]] = true;
                }
            }
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1043 p = new solve1043(br);
        p.run();

        br.close();
    }
}