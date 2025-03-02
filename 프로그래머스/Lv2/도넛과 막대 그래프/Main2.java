
/*
 * 각 그래프의 특수한 노드는 하나씩 존재하기 때문에 이를 이용하는 방식
 * 
 */
class Solution {
    
    int [] out;
    int [] in;
    
    public int [] solution(int[][] edges) {
        
        int max = getMax(edges);
        init(max, edges);
        
        int node = -1;
        int stick = 0;
        int eight = 0;
        
        for(int i=1;i<max+1;i++){
            
            if(in[i] == 0 && out[i] > 1){
                node = i;
            }
            
            if(in[i] > 1 && out[i] == 2){
                eight++;
            }
            
            if(in[i] > 0 && out[i] == 0){
                stick++;
            }
        }
            
        return new int [] {node, out[node] - stick - eight, stick, eight};
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
        
        out = new int [max+1];
        in = new int[max+1];
        
        for(int i=0;i<edges.length;i++){
            
            out[edges[i][0]]++;
            in[edges[i][1]]++;
        }
    }
}