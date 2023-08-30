package pack2;

import java.util.Scanner;

//0으로 나눌 때 오류가 나는거지 0이 나눠질 땐 오류 안남
//2720
class Processor{
    int [] ary;
    int [] change = {25, 10, 5, 1};
    int [] result = new int [4];

    public int divide(int target, int unit){
        
        if(target == 0){
            return 0;
        }
        
        return target / unit;
    }
    public void check(int i){
        int target = ary[i];

        for(int j=0;j<change.length;j++){
            result[j] = divide(target, change[j]);
            target = target % change[j];
        }

        for(int j=0;j<result.length;j++){
            System.out.print(result[j]+ " ");
        }
        System.out.println();
    }

    //이거
    public void check2(int i){
        int target = ary[i];

        for(int j=0;j<change.length;j++){
            result[j] = target / change[j];
            target %= change[j];
            System.out.print(result[j] + " ");
        }
        System.out.println();    
    }
    
    public void run(Scanner scan){
        int num_case = scan.nextInt();
        ary = new int [num_case];

        for(int i=0;i<num_case;i++){
            ary[i] = scan.nextInt();
        }

        for(int i=0;i<num_case;i++){
            //check(i);
            check2(i);
        }
        
    }
 }
class Test{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        
        Processor p = new Processor();
        p.run(scan);
        
        scan.close();
    } 
}