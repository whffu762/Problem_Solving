package pack2;

import java.util.Scanner;

//백준 2525
class Test {
    int time;
    int hour;
    int min;


    public void calMin() {

        int target = min + time;

        if (target >= 60) {
            int tHour = target / 60;

            hour = hour + tHour;
            min = target - tHour * 60;

        } else {
            min = target;
        }

    }

    public void calHour() {

        if (hour >= 24) {
            int tHour = hour / 24;

            hour = hour - tHour * 24;
        }
    }

    public void run(Scanner scan) {

        hour = scan.nextInt();
        min = scan.nextInt();
        time = scan.nextInt();

        calMin();

        calHour();

        System.out.println(hour + " " + min);
    }
}

class App2 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        Test x = new Test();
        x.run(scan);

    }
}