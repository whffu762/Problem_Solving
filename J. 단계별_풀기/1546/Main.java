package pack2;

import java.util.Scanner;

//1546

class Run {
    double [] score;
    double sum = 0;
    public void calAvg(double max){

        
        for(int i=0;i<score.length;i++){
            score[i] = score[i]/max*100;
            sum += score[i];
        }

        System.out.println(sum/score.length);
        
    }

    public void run(Scanner scan) {
        
        score=new double [scan.nextInt()];
        double max = -1;

        for(int i=0;i<score.length;i++){
            score[i]=scan.nextDouble();
            max = Math.max(max, score[i]);
        } 

        calAvg(max);
    }
}

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Run x = new Run();
        x.run(scan);

        scan.close();
    }
}
