package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 17103
 *  아라토스테네스의 체를 이용하지 않으면 절대 못 푸는 문제 
 * 2부터 시작해서 각 수의 배수는 true로 바꿈
 * false(소수)면 그 수의 배수를 다 true로 바꿈
 * 그렇게 하면 소수 빼고 다 true로 됨...
 * 
 */
class solve17103{
    boolean [] base = primes();
    
    boolean [] primes(){
        
        boolean [] base = new boolean[1000001];
        //base[0] = base[1] = true; 0과 1은 따로 처리
        for(int i=2;i<Math.sqrt(base.length)+1;i++){
            if(!base[i]){
                for(int j=i+i;j<base.length; j+=i){
                    base[j] = true;
                }
            }
        }
        
        return base;
    }

    int getResult(int n){

        int cnt = 0;
        for(int i=2;i<n/2+1;i++){
            if(!base[i] && !base[n-i]){
                cnt++;
            }
        }

        return cnt;
    }

    void run(BufferedReader br) throws IOException{

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<t;i++){
            int n = Integer.parseInt(br.readLine());
            sb.append(getResult(n)).append("\n");
        }

        System.out.println(sb);
    }
}

class Test{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve17103 p = new solve17103();
        p.run(br);

        br.close();
    }       
}