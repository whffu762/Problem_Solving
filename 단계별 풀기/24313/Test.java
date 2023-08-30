package pack2;

import java.util.Scanner;

//24313
//문제를 잘 읽어봅시다 음수가 될 수 있는 값이 있읍니다 하하..
class Test{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);

        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int x0 = scan.nextInt();

        int result = -1;
        if(b <= 0){
            if(a <= c){
                result = 1;
            }
            else{
                result = 0;
            }
        }
        else{
            if(b <= (c-a) * x0){
                result = 1;
            }
            else{
                result = 0;
            }
        }

        System.out.println(result);
        
        scan.close();
    }    
    
}