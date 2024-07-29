import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

/*
 * 기본적인 dfs 문제
 * 그래프 저장해서 깊이만 잘 관리하면 됨
 * 다만 그래프 저장할 때 인접 행렬에 저장하면 시간 초과 발생함(max가 2000이라서)
 * 그래서 인접 리스트로 저장해야 함
 * 
 * 분류는 일단은 백트랙킹이긴 함 워낙 dfs와 비슷하니까..
 * 
 * 또 기억해야 할 점
 * 단락 평가(short circuit evaluation) 이라는 개념이 있음
 * if(!visited[next] && dfs()) 에서 !visited[next] 가 false면 뒤 dfs를 호출하지도 않음
 * 때문에 불필요한 호출이 발생하지 않아서 시간이 단축될 수 있음 이걸 알고 있으면 if문 줄일 수도 있고  코드 짜기 더 편함
 */
class solve13023 {

    int numOfPerson;
    int numOfRelation;

    List<List<Integer>> relations = new ArrayList<>();
    boolean [] visited;

    solve13023(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        numOfPerson = Integer.parseInt(st.nextToken());
        numOfRelation = Integer.parseInt(st.nextToken());
    
        visited = new boolean[numOfPerson];
        
        for(int i=0;i<numOfPerson;i++){
            relations.add(new ArrayList<>());
        }

        for(int i=0;i<numOfRelation;i++){

            st = new StringTokenizer(br.readLine());
            int tmp1 = Integer.parseInt(st.nextToken());
            int tmp2 = Integer.parseInt(st.nextToken());
            
            relations.get(tmp1).add(tmp2);
            relations.get(tmp2).add(tmp1);
        }
    }

    void run() {

        boolean result = false;
        for(int i=0;i<numOfPerson;i++){
            if(dfs(0, i)){
                result = true;
                break;
            }
        }
        
        if(result){
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }

    boolean dfs(int depth, int now){

        if(depth == 4){
            return true;
        }

        visited[now] = true;
        List<Integer> nextNow = relations.get(now);
        for(int i=0;i<nextNow.size();i++){

            int next = nextNow.get(i);
            if(!visited[next] && dfs(depth+1, next)){
                return true;
            }
        }
        visited[now] = false;
        return false;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve13023 p = new solve13023(br);
        p.run();

        br.close();
    }
}