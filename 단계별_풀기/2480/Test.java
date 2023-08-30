package pack3;

import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;

//백준 2480 출력은 제대로 되는데.. 틀렸다고 나옴..

class Dice{
    HashMap<Integer, Integer> input = new HashMap<>();
    int value = 1;
    int dice;
    int ct = 0;

    public void checkDice(int dice){

        if(input.containsKey(dice)){
            input.put(dice, input.get(dice) + 1);
        }
        else{
            input.put(dice, value);
        }

        ++ct;
        
    }

    public void cal1(){
        System.out.println(Collections.max(input.keySet()) * 100);
    }

    public void cal2(){
        for(int dice : input.keySet()){
            if(input.get(dice) == 2){
                System.out.println(dice * 100 + 1000);
            }
        }
    }

    public void cal3(int dice){
        System.out.println(dice * 1000 + 10000);

    }

    public void checkPrice(int dice){

        switch(Collections.max(input.values())){
            case 1 : cal1(); break;
            case 2 : cal2(); break;
            case 3 : cal3(dice); break;
            default : System.out.println("err"); break;
        }
    }

    public void run(Scanner scan){
        

        System.out.print("input : ");

        while(true){
            dice = scan.nextInt();
            checkDice(dice);

            if(ct == 3){
                break;
            }

        }
        checkPrice(dice);
        ct = 0;
        input.clear();
    }
}


 
public class Test {
    public static void main(String [] args){

        Scanner scan = new Scanner(System.in);
        
        Dice x = new Dice();
        x.run(scan);

        scan.close();
    }
}