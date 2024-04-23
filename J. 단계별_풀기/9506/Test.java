package pack2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//9506
class Processor{

    List<Integer> list = new ArrayList<>();
    List<Integer> measure = new ArrayList<>();
    int cnt = 0;
    int idx;

    boolean getMeasure(int x){
        int tmp = 0;
        idx = 0;

        for(int i=1;i<x;i++){
            if(x % i == 0){
                measure.add(idx++, i);
                tmp += i;
            }
        }

        if(tmp == x){
            return true;
        }

        return false;
    }

    String getResult(int x){
        String result = x + " = ";
        
        for(int i=0;i<idx;i++){
            result += measure.get(i);
            if(i == idx - 1){
                break;
            }
            result += " + ";
        }

        return result;
    }

    String getResult2(int x){
        return x + " is NOT perfect.";
    }

    void run(Scanner scan){
        int target = scan.nextInt();
    
        while(target != -1){
            list.add(cnt++, target);
            target = scan.nextInt();
        }
        
        for(int i=0;i<cnt;i++){
            String result;

            if(getMeasure(list.get(i))){
                result = getResult(list.get(i));
            }
            else{
                result = getResult2(list.get(i));
            }

            System.out.println(result);
            measure.clear();
        }
        
    }
}

class Test{

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        
        Processor a = new Processor();
        a.run(scan);
        
        scan.close();
    }
}