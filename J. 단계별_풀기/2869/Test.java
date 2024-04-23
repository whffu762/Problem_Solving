package pack2;

import java.util.Scanner;

//2869
class Test{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int up = scan.nextInt();
        int down = scan.nextInt();
        int tree = scan.nextInt();
        
        int height_per_day = up - down;
        int lastHeight = tree - up;
        int lastDay = lastHeight / height_per_day;
        int start = lastDay * height_per_day;

        start += up;
        lastDay++;
        while(true){
            if(start >= tree){
                break;
            }
            else{
                lastDay++;
                start -= down;
            }
            start += up;
        }
        
        System.out.println(lastDay);

        scan.close();

    }
}