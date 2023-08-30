package pack2;

import java.util.Scanner;

//2750

class Processor{

    int [] selection(int [] target){

        for(int i=0;i<target.length-1;i++){
            for(int j=i+1;j<target.length;j++){
                if(target[i]>target[j]){
                    int tmp = target[i];
                    target[i] = target[j];
                    target[j] = tmp;
                }
            }
        }



        return target;
    }

    int [] insertion(int [] target){

        for(int i=1;i<target.length;i++){
            for(int j=0;j<i;j++){
                if(target[i] < target[j]){
                    int tmp = target[i];
                    target[i] = target[j];
                    target[j] = tmp;
                }
            }
        }

        return target;
    }

    int [] bubble(int [] target){

        for(int i=0;i<target.length;i++){
            for(int j=0;j<target.length-i-1;j++){
                if(target[j]>target[j+1]){
                    int tmp = target[j];
                    target[j] = target[j+1];
                    target[j+1] = tmp;
                }
            }
        }

        return target;
    }

    
    void print(int [] target){
        for(int i=0;i<target.length;i++){
            System.out.println(target[i]);
        }
        System.out.println();
    }

    void run(Scanner scan){
        int num = scan.nextInt();
        int [] target = new int [num];

        for(int i=0;i<num;i++){
            target[i] = scan.nextInt();
        }

        selection(target);
        print(target);

        //insertion(target);
        //print(target);

        //bubble(target);
        //print(target);

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