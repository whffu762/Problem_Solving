package pack2;

import java.util.Scanner;

//2501
class Test{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int target = scan.nextInt();
        int index = scan.nextInt();
        int [] result = new int [target];
        int cnt = 0;

        for(int i=1;i<target+1;i++){
            if(target % i == 0){
                result[cnt++] = i;
            }
        }

        System.out.println(result[index-1]);

        scan.close();

    }
}