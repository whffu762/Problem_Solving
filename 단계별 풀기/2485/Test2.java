package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
2485
모든 가로수가 같은 간격이 되려면 각 간격의 최대공약수 n을 구하면 됨
 */
class solve2485{

    int cal(int max, int min){
        int tmp = 1;
        while(min != 0){
            tmp = max % min;
            max = min;
            min = tmp;
        }
        
        return max;
    }

    void ver1(int [] trees){

        int n = trees.length;
        int [] gap = new int [n-1];
        for(int i=0;i<n-1;i++){
            gap[i] = trees[i+1] - trees[i]; 
        }

        int common = gap[0];
        for(int i=0;i<n-2;i++){
            if(common > gap[i+1]){
                common = cal(common, gap[i+1]);
            }
            else{
                common = cal(gap[i+1], common);
            }
        }

        int total_need = ((trees[n-1] - trees[0]) / common ) + 1;
        int result = total_need - n;

        System.out.println(result);
    }

    void ver2(int [] trees){
        // 스트림으로 처리하는 거...
    }
    void run(BufferedReader br) throws IOException{
        
        int n = Integer.parseInt(br.readLine());
        int [] trees = new int [n];
        for(int i=0;i<n;i++){
            trees[i] = Integer.parseInt(br.readLine());
        }

        ver1(trees);


        
    }
}


class Test2{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2485 p = new solve2485();
        p.run(br);


        br.close();
    }    
    
}


        