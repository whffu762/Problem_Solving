package pack2;

import java.util.Scanner;
//2476

class Run {
    int input;

    Run(int input){
        this.input = input;
    }

    int getMax(int [] playerN){
        int max = 0;
        
        for(int i=0;i<3;i++){
            max = Math.max(max, playerN[i]);
        }

        return max;
    }
    int getSame(int [] playerN){
        int same = 0;

        if(playerN[0] == playerN[1]){
            same = playerN[0];
        }
        else if(playerN[1] == playerN[2]){
            same = playerN[1];
        }
        else{
            same = playerN[0];
        }

        return same;
    }
    
    int cal(int [] playerDice){
        int a = playerDice[0];
        int b = playerDice[1];
        int c = playerDice[2];
        int result;
        if(a == b && b == c){
            result = 10000 + a * 1000;
        }
        else if(a != b && a != c && b != c){
            int max = getMax(playerDice);
            result = max * 100;
        }
        else{
            int same = getSame(playerDice);
            result = 1000 + same*100;
        }

        return result;
    }


    void run(Scanner scan){
        int [][] playerN = new int [input][3];
        int max = 0;
        int result;
        for(int i=0;i<input;i++){
            System.out.print(i+1 + " player :");
            for (int j=0;j<3;j++){
                playerN[i][j] = scan.nextInt();
            }
            result = cal(playerN[i]);
            max = Math.max(max, result);
        }
        System.out.print(max);
    }
}

public class Main { 
    public static void main(String[] args) {
        System.out.print("input : ");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        Run run = new Run(input);
        run.run(scan);
        
        scan.close();
    }
}
