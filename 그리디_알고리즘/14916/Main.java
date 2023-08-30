import java.util.Scanner;

//14916
class solve14916{

    void run(Scanner scan){

        int target = scan.nextInt();

        int fiveCnt = target / 5;
        int remain = target % 5;
        int twoCnt = 0;

        while(true){
            twoCnt = remain / 2;
            remain = remain % 2;
            if(remain == 0){
                break;
            }

            remain = target - (--fiveCnt * 5);
            if(fiveCnt < 0){
                break;
            }
        }

        if(fiveCnt < 0){
            System.out.println(-1);
        }
        else{
            System.out.println(fiveCnt + twoCnt);
        }
    }
}

class Main{
    
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        
        solve14916 p = new solve14916();
        p.run(scan);

        scan.close();
    }
}