package pack2;

import java.util.Scanner;


//2798
//이게 하나씩 비교하기 때문에 시간 초과가 발생할 것 같지만
//전체 배열의 크기가 100 이하기 때문에 시간 내 충분히 가능함..
class Test{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int target = scan.nextInt();
        

        int [] cards = new int[n];
        for(int i=0;i<n;i++){
            cards[i] = scan.nextInt();
        }

        int diff = target;
        int result = 0;
        for(int i=0;i<n-2;i++){
            for(int j=i+1;j<n-1;j++){
                for(int k=j+1;k<n;k++){
                    int sum  = cards[i] + cards[j] + cards[k];
                    int tmp = target - sum;
                    if(sum <= target && tmp < diff){
                        diff = tmp;
                        result = sum;
                    }
                }
            }
        }

        System.out.println(result);



        
        scan.close();
    }    
    
}