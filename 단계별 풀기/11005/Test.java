package pack2;

import java.util.Scanner;


class Processor{
    

    String translate(int n){

        if(n > 9){
            return String.valueOf((char)(n + 55));
        }

        return String.valueOf(n);
    }

    void reverse(String result){

        for(int i=result.length()-1;i > -1;i--){

            System.out.print(result.charAt(i));
        }
    }

    public void run(Scanner scan){
        int target = scan.nextInt();
        int num = scan.nextInt();
        int tmp;
        String result = "";

        while(true){
            if(target == 0){           
                break;
            }
            tmp = target % num;
            result += translate(tmp);
            
            target = target / num;
        }
    
        reverse(result);
    
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