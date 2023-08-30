package pack2;

import java.util.Scanner;


//1193
class Test{
    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        int left = 1;
        int right = 1;
        int i = 1;
        while(true){
            if(i == num){
                break;
            }

            if(right%2 == 1 && left == 1){
                right++;
                i++;
            }
            else if(right%2 == 0 && left == 1){
                
                while(i < num && right != 1){
                    right--;
                    left++;
                    i++;
                }
                
            }
            else if(left%2 == 0 && right == 1){
                left++;
                i++;
            }
            else if(left%2 == 1 && right ==1){
                while(i < num && left != 1){
                    right++;
                    left--;
                    i++;
                }
            }
        }

        System.out.println(left+"/"+right);

        scan.close();

    }
}