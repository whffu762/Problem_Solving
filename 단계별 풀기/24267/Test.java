package pack2;

import java.util.Scanner;

//24267

class Processor{
    int wrong(int a){
        //이런 식으로 직접 카운트하면 시간 초과 뜸
        //또한 자료형을 int로 하면 값 범위 때문에 오류 남
        int cnt = 0;
        for(int i=0;i<a-2;i++){
            for(int j=i+1;j<a-1;j++){
                for(int k=j+1;k<a;k++){
                    cnt++;
                }
            }
        }
        
        return cnt;
    }

    long mine(int a){
        
        //값이 늘어나는 값도 늘어나는 방식을 이용
        /*
         * x = 1 : result = 0
         * x = 2 : result = 0
         * x = 3 : result = 0 + 1
         * x = 4 : result = 1 + 1 + 2
         * x = 5 : result = 4 + 1 + 2 + 3
         * x = 6 : result = 10 + 1 + 2 + 3 + 4 + 5 
         * 와 같이 이전 값에 이전에 더한 값에 +1을 더 더한 값이 적용됨을 이용
         */
        long tmp = 0L;
        long result = 0L;
        for(int i=1;i<a-1;i++){
            tmp += i;
            result += tmp;
        }

        return result;
    }
    long sigmaSum(int a){
        /*
         * 시그마 합 연산을 직접 수행
         * 각 시그마 x, x^2, x^3 까지 알고 있으면 좀만 고생하면 직접 연산할 수 있음
         */
        return a *(a-1) * (a-2) / 6;

    }
    void run(Scanner scan){

        int x = scan.nextInt();
        
        //이건 동작만 제대로 하지 이렇게 하면 안됨
        //int result1 = wrong(x);
        //System.out.println(result1);
        
        int result2 = mine(x);
        System.out.println(result2);
        
        int result3 = sigmaSum(x);
        System.out.println(result3);
    }
}
class Test{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        Processor p = new Processor();
        p.run(scan);
        
        scan.close();
    }    
    
}