import java.util.Scanner;

//3003
class Run {
    int [] target = {1,1,2,2,2,8};
    int result;
    void run(Scanner scan){
        for(int i=0;i<6;i++){
            int have = scan.nextInt();
            result = target[i] - have;
            System.out.print(result+" ");
        }
    }
}

public class Main { 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        Run run = new Run();
        run.run(scan);
        
        scan.close();
    }
}
