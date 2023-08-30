package pack2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Processor{
    List<Integer> list = new ArrayList<>();
    int cnt = 0;

    boolean checkPrime(int x){

        for(int i=2;i<x;i++){
            if(x % i == 0){
                return false;
            }
        }
        return true;
    }

    int getResult(int x){
        
        for(int i=2;i<x+1;i++){
            if(x % i == 0){
                list.add(cnt++, i);
                return x /= i;
            }
        }
        return x;
    }

    //간단한 버전
    void getResult2(int x){
        
        for(int i=2;i<x+1;i++){
            while(x % i == 0){
                System.out.println(i);
                x /= i;
            }
        }
    }


    void run(Scanner scan){
        int target = scan.nextInt();
         
        while(!checkPrime(target)){
            target = getResult(target);
        }
        list.add(cnt++, target);
        
        getResult2(target);

        if(target != 1){
            for(int i=0;i<cnt;i++){
                System.out.println(list.get(i));
            }   
        }
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