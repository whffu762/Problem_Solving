package pack2;
//백준 1316

import java.util.Scanner;

class App{
    int num;

    App(int num){
        this.num = num;
    }

    boolean cal(String target){

        for(int i=0;i<target.length() - 1;i++){
            if(target.charAt(i) != target.charAt(i+1)){
                if(target.substring(i+1).contains(String.valueOf(target.charAt(i)))){
                    return false;
                }
            }

        }

        return true;
    }

    void run(Scanner scan){
        int result = 0;

        for(int i=0;i<num;i++){
            String target = scan.next();
            if(cal(target)){
                result++;
            } 
        }

        System.out.print(result);
    }
}


class Test{
    public static void main(String [] args){
        
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();

        App app = new App(num);
        app.run(scan);

        scan.close();
    }
}
