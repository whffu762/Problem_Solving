package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;


/*
4134
 * 입력의 범위가 long 이기 때문에 입출력을 long으로 처리해야 함(readLine()과 sb.append에서 모두)
 * 0, 1, 2에 대한 처리는 따로 해야 함 이거 때문에 시간초과 난 거임
 * 그 외에는 루트값 이내로만 탐색하면 됨
 * 
 * 다른 방법으로 BigInteger 사용
 */
class solve4134{

    void run2(BufferedReader br) throws IOException{

        StringBuilder sb = new StringBuilder();

        long target;
        long targetR;
        boolean flag;
        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++){
            target = Long.parseLong(br.readLine());
            targetR = (long)Math.sqrt(target);
            flag = true;
            //0, 1, 2 처리
            if(target < 3){
                sb.append("2").append("\n");
                continue;
            }
            while(flag){
                for(int j=2;j<targetR+2;j++){
                    if(target % j == 0){
                        target++;
                        //targetR = (long)Math.sqrt(++target); target보다 큰 수 중 가장 작은 소수를 찾기 때문에 루트값은 변하지 않아도 됨
                        break;
                    }
                    
                    if(j == targetR+1){
                        sb.append((long)target).append('\n');
                        flag = false;
                    }
                }
            }
        }

        System.out.println(sb);

    }

    void run(BufferedReader br) throws IOException{
        //BigInteger 사용
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            BigInteger target = new BigInteger(br.readLine());
            if(target.isProbablePrime(10)){
                sb.append(target).append('\n');
            }
            else{
                sb.append(target.nextProbablePrime()).append('\n');
            }
        }
        System.out.println(sb);
    }
}

class Test{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve4134 p = new solve4134();
        //p.run(br);
        p.run2(br);

        br.close();
    }    
    
}