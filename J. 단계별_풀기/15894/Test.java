package pack2;

import java.util.Scanner;


//15894
//문제를 풀 떄 어려운게 아니라 입력 값 범위에 대해 어떤 자료형을 써야 할 지 확인해야 함 
class Test{

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        Long num = scan.nextLong();
        
        System.out.println(num * 4);
        
        scan.close();
    }
}