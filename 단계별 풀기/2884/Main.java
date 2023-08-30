package pack2;
import java.util.Scanner;

class Run{

    int hour;
    int min;
    int time = 45;

    public void calMin(){

        hour--;
        min = 60 + min - time;
        
    }

    public void calHour(){

        hour = 24 + hour;
    }

    public void run(Scanner scan){
        
        hour = scan.nextInt();
        min = scan.nextInt();
        
        int target = min - time;

        if(target  < 0){
            calMin();
        }
        else{
            min = target;
        }

        if(hour < 0){
            calHour();
        }

        System.out.println(hour + " " + min);

    }
}

public class Main{
    public static void main(String [] args){
        
        Scanner scan = new Scanner(System.in);

        Run x = new Run();
        x.run(scan);

        scan.close();
    }
}

