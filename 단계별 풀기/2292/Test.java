package pack2;

import java.util.Scanner;


//2292
class Test{
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int base = 1;
        int level = 1;

        for(int i=1;i<num;i++){
            base = base + i * 6;
            if(base >= num){
                level = i+1;
                break;
            }
        }

       System.out.println("level =" + level);

       scan.close();

    }
}