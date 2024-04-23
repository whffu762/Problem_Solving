import java.util.Scanner;

//14916
class solve14916{

    void run(Scanner scan){
        //난 5를 먼저 넣어보는게 현재 선택에서 최선이라고 생각했음

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


    void run2(Scanner scan){
        //5로 나눠보고 나머지가 남으면 2를 먼저 넣고 5를 연산하는 방식
       
        int target = scan.nextInt();
        int cnt = 0;
        while(true){
            if(target % 5 == 0){
                cnt += target / 5;
                System.out.println(cnt);
                break;
            }
            else{
                target -= 2;
                cnt++;
            }
            if(target < 0){
                System.out.println(-1);
                break;
            }
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