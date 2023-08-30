package pack2;

import java.util.Scanner;

class Processor{

}

class Test{

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int [][] point = new int [3][2];
        int x =0,y=0;

        for(int i=0;i<3;i++){
            point[i][0] = scan.nextInt();
            point[i][1] = scan.nextInt();
        }

        for(int i=0;i<2;i++){
            for(int j=i+1;j<3;j++){
                if(point[i][0] == point[j][0]){
                    x = point[3-(i+j)][0];
                    break;
                }
            }

            for(int j=i+1;j<3;j++){
                if(point[i][1] == point[j][1]){
                    y = point[3-(i+j)][1];
                    break;
                }
            }
        }

        System.out.print(x + " " + y);

        scan.close();
    }
}