import java.util.Scanner;

/*
 * 백트랙킹 비스무리하게 동작시키면 됨
 * 
 * 가지치기가 중요함
 * - 앞자리로 가능한거 2 3 5 7
 * - 뒷자리로 가능한거 1 3 7 9
 * 이걸 조합해서 소수인지 판단하면 됨 전체 숫자 판단하면 시간 초과 뜸
 * 
 * 또 주목할 점은 두 가지
 * - 소수 판단할 때 제곱근까지 계산하면 훨씬 빠름(당연한 소리지만 까먹을 수 있음)
 * - 재귀 호출에서 종료 조건에 다다르면 반드시 return 해야 함(이걸 까먹네;;)
 * 이 두가지를 지키지 않아도 로직만 맞으면 통과하긴 하는데 아깝자네
 */

class solve2023{

    int size;

    solve2023(Scanner scan){

        size = scan.nextInt();
    }

    StringBuilder sb = new StringBuilder();
    int [] next = {1, 3, 7, 9};

    void run(){

        int [] start = { 2, 3, 5, 7};
        
        for(int i=0;i<start.length;i++){
            backTracking(start[i], 1);
        }

        System.out.println(sb);
    }

    void backTracking(int value ,int depth){

        if(depth == size){
            sb.append(value).append("\n");
            return;
        }

        value *= 10;
        
        for(int i=0;i<next.length;i++){
            
            value += next[i];
            if(checkPrime(value)){
                backTracking(value, depth+1);
            }
            value -= next[i];
        }

    }

    boolean checkPrime(int value){

        for(int i=2;i<=Math.sqrt(value);i++){
            if(value % i == 0){
                return false;
            }
        }

        return true;
    }
}

public class Main{

    public static void main (String [] args) {

        Scanner scan = new Scanner(System.in);

        solve2023 p = new solve2023(scan);
        p.run();

        scan.close();
    }
}