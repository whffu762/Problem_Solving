package pack2;

import java.util.Scanner;
//4344


class Run {
    int input;
    
    Run(int input){
        this.input = input;
    }

    double getRate(int [] scores, int sum){

        int avg = sum/scores.length;
        int count = 0;

        for(int i=0;i<scores.length;i++){
            if(scores[i] > avg){
                count++;
            }
        }

        return (double)count/(double)scores.length * 100;
    }
    

    void run(Scanner scan){
        
        for(int i=0;i<input;i++){
            int num = scan.nextInt();
            int [] scores = new int [num];
            int sum = 0;

            for (int j=0;j<num;j++){
                scores[j] = scan.nextInt();
                sum += scores[j];
            }
            
            System.out.println(String.format("%.3f", getRate(scores, sum))+"%");
        }
    }
}

public class Main { 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();

        Run run = new Run(input);
        run.run(scan);
        
        scan.close();
    }
}
