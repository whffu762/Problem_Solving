package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/*
4948 1929 둘이 비슷함
BigIntger와 직접 연산 성능 비교
직접 값에 대해 루트값까지 비교하는 방법과 BigInteger로 소수 구하는 방법 두 가지로 했는데
엄청 큰 수를 연산할 땐 BigInteger가 효과적이었지만
평범한 수에 대해선 직접 연산하는게 훨씬 효과적임 6배 정도 속도차이 나는 듯

//아니 이거 원래 for문으로 못 푼다네? 아라토스테네스의 체로 소수 구해야 됨..?
이건 어캐 된거임?
 */

class solve4948{

    boolean checkPrime(int target){

        if(target == 1){
            return false;
        }
        if(target == 2){
            return true;
        }

        for(int i=2;i<Math.sqrt(target)+1;i++){
            if(target % i == 0){
                return false;
            }
        }

        return true;
    }

    void usingCheckPrime(int n, int m){
        // 4948 1929 둘 다 무난한게 동작함
        int cnt = 0;
        for(int i=n+1;i<m+1;i++){
            if(checkPrime(i)){
                cnt++;
            }
        }

        System.out.println(cnt);
    }

    void usingBigInteger(int n, int m){
        // 4948은 이거로 하니까 시간초과 뜸
        // 1929는 시간 겁나 오래 걸림 시간 초과 안 뜬게 기적
        int cnt = 0;
        for(int i=n+1;i<m+1;i++){
            BigInteger target = new BigInteger(String.valueOf(i));
            if(target.isProbablePrime(10)){
                cnt++;
            }
        }

        System.out.println(cnt);
    }
     
    void run(BufferedReader br) throws IOException{
        
        List<Integer> list = new ArrayList<>();
        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0){
                break;
            }

            list.add(n);
            
            //usingCheckPrime(n, 2*n);      여기서 직접 호출하는 것보다 list 통하는게 더 빨랐는데 유의미한 차이는 아닌 듯
        }

        for(int i=0;i<list.size();i++){
            usingCheckPrime(list.get(i), 2* list.get(i));
        }

    }
}

class Test{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve4948 p = new solve4948();
        p.run(br);

        br.close();
    }    
    
}

//main 에 직접 넣어서 불필요한 호출을 최소화해봤는데
//30ms 차이로 큰 차이는 아닌거 같기도 하고..
//참고로 List에 집어넣어서 하는 방식이 List 없이 하는 것 보다 빨랐음 왜지..?
/*
class Test3{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringBuilder sb = new StringBuilder();
        while(true){
            int n = Integer.parseInt(br.readLine());
            if(n == 0){
                break;
            }

            int cnt = 0;
            for(int i=n+1;i<2*n+1;i++){
                if(i == 2){
                    cnt++;
                }
                int tmp = (int)Math.sqrt(i);
                for(int j=2;j<tmp+2;j++){
                    if(i % j == 0){
                        break;
                    }

                    if(j == tmp + 1){
                        cnt++;
                    }
                }
            }

            sb.append(cnt).append("\n");
        }

        System.out.println(sb);

        br.close();
    }    
    
}
 */