package pack2;

import java.util.Scanner;


//2231
//복잡하게 생각하지 말고 그냥 전부 다 순회하는게 베스트..
//괜히 뭐 반 이하니 뭐니 생각하면 오히려 구멍 생김
class Test{

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int target = scan.nextInt();

        int min = target;
        for(int i=0;i<target+1;i++){
            if(i == target && min == target){
                min = 0;
                break;
            }

            int result = i;
            
            String tmp = String.valueOf(result);
            for(int j=0;j<tmp.length();j++){
                result += Integer.parseInt(String.valueOf(tmp.charAt(j)));
            }

            if(result == target){
                min = Math.min(i, min);
            }
        }
        

        System.out.println(min);
        scan.close();
    }    
    
}