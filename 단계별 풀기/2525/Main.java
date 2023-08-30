package pack2;
import java.util.Scanner;

class Run{

    int hour;
    int min;
    int time;

    public void calMin(int target){

        int tHour = target/60;

        hour = hour + tHour;
        min = target - tHour * 60;
        
    }

    public void calHour(){

        int tHour = hour/24;

        hour = hour - tHour * 24;
    }

    public void run(Scanner scan){
        
        hour = scan.nextInt();
        min = scan.nextInt();
        time = scan.nextInt();

        int target = min + time;

        if(target >= 60){
            calMin(target);
        }
        else{
            min = target;
        }

        if(hour >= 24){
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