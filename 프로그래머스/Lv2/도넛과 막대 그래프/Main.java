import java.util.List;
import java.util.ArrayList;

/*
 * 나가는 간선의 갯수에 따라 어떤 그래프인지 알 수 있음
 * 
 * 막대 : 나가는 갯수가 0인 경우
 * 도넛 : 나가는 갯수가 1에서 시작해서 시작으로 돌아오는 경우
 * 8자 : 나가는 갯수가 1에서 시작해서 탐색하던 중에 나가는 갯수가 2인 노드가 존재하는 경우
 * 생성된 정점 : 나가는 갯수 0, 1인 경우를 모두 조회한 후 남은 것 중 나가는 갯수가 2인 것
 * 
 * 나가는 갯수가 1인 경우의 연산
 * 모든 그래프가 나가는 갯수가 1인 노드를 가지고 있음 그에 따라 각 그래프가 가지고 있는 
 * 특이한 노드에 따라 결과를 판단해야 함
 *  
 * - 나가는 갯수가 0이면 막대기 때문에 0을 반환
 * - 나가는 갯수가 1이면 그냥 마저 탐색을 진행함
 * - 시작 점으로 다시 돌아오면 도넛이기 떄문에 1을 반환
 * - 나가는 갯수가 2이면 8자 2를 반환
 * 이를 이용해 1과 2일 때만 각각 도넛과 8을 추가함
 * 
 * 8자 교차 지점의 visited 관리를 주의해야 함
 * 생성된 정점의 조건은 방문하지 않고 나가는 갯수가 2 이상인 노드임
 * 그에 따라 8자에 존재하는 나가는 갯수가 2 이상인 노드는 방문 처리를 해야 함 
 * 또한 8자를 조회할 때 어떤 지점에서 시작될지 모르기 떄문에 어떤 지점에서 시작되든 딱 한 번만
 * 연산되도록 처리가 필요함
 * 
 * 노드의 넘버링을 조심해야 함
 * 문제에서 1부터 연속되는 숫자로 노드에 넘버링 된다는 조건이 없음 즉 1부터 max까지 무작위 숫자가 가능함
 * 그래서 존재하는 숫자만 true로 초기화해서 사용해야 함 즉 코드에선 보통 visited 처리와 반대로 이루어져야 함
 * (보통은 false면 방문하지 않은 것으로 간주하는데 여기선 true여야 방문하지 않은 겻으로 간주)
 * 
 */

 class Solution {
    
    boolean [] visited;
    List<List<Integer>> graph = new ArrayList<>();
    
    public int [] solution(int[][] edges) {
        
        int max = getMax(edges);
        init(max, edges);
        
        int stick = checkZero();
        int [] donutAndEight = checkOne();
        int add = checkTwoOrMore();
    
        return new int [] {add, donutAndEight[0], stick, donutAndEight[1]};
    }
    
    int getMax(int [][] edges){
        
        int max = 0;
        
        for(int i=0;i<edges.length;i++){
            for(int j=0;j<2;j++){    
                max = Math.max(max, edges[i][j]);
            }
        }
        
        return max;
    }
    
    void init(int max, int [][] edges){
        
        visited = new boolean [max+1];
        
        for(int i=0;i<max+1;i++){
            graph.add(new ArrayList<>());
        }
        
        for(int i=0;i<edges.length;i++){
            graph.get(edges[i][0]).add(edges[i][1]);
            visited[edges[i][0]] = true;
            visited[edges[i][1]] = true;
        }
    }
    
    int checkZero(){
        
        int count = 0;
        for(int i=1;i<graph.size();i++){
            
            if(graph.get(i).size() == 0 && visited[i]){
                count++;
                visited[i] = false;
            }
        }
        
        return count;
    }
    
    int [] checkOne(){
        
        int donut = 0;
        int eight = 0;
        for(int i=1;i<graph.size();i++){
            
            if(graph.get(i).size() == 1 && visited[i]){
                
                visited[i] = false;
                int result = dfs(i, graph.get(i).get(0));
                if(result == 1){
                    donut++;
                }
                
                if(result == 2){
                    eight++;
                }
            }   
        }
        
        return new int [] {donut, eight};
    }
    
    int dfs(int start, int now){
        
        if(graph.get(now).size() == 0){
            return 0;
        }
        
        if(now == start){
            return 1;
        }
        
        if(graph.get(now).size() == 2){
            
            if(visited[now]){
                visited[now] = false;
                return 2;
            }
            
            return 0;
        }
        
        visited[now] = false;
        return dfs(start, graph.get(now).get(0));
    }
    
    int checkTwoOrMore(){
        
        for(int i=1;i<graph.size();i++){
            
            if(graph.get(i).size() > 1 && visited[i]){
                return i;
            }
        }
        
        return -1;
    }
}