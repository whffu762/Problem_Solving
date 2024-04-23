package pack3;

import java.util.Scanner;

//백준 2480
class Run {
    
    public int cal1(int x) {
        return x * 100;
    }

    public int cal2(int x) {
        return x * 100 + 1000;
    }

    public int cal3(int x) {
        return x * 1000 + 10000;
    }

    public void run(Scanner scan) {

        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();
        int result = 0;


        if (a == b && b == c) {
            result = cal3(a);
        } else if (a != b && a != c && b != c) {
            int tmp = Math.max(a, b);
            int max = Math.max(tmp, c);

            result = cal1(max);
        } else if (a != b) {
            if (a == c) {
                result = cal2(a);
            }

            else if (b == c) {
                result = cal2(b);
            }
        } else if (a != c) {
            if (a == b) {
                result = cal2(a);
            } else if (c == b) {
                result = cal2(c);
            }
        } else if (b != c) {
            if (b == a) {
                result = cal2(b);
            } else if (c == a) {
                result = cal2(a);
            }
        }

        System.out.println(result);
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
