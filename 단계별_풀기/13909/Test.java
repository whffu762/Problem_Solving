package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 13909
 * 제곱해서 나오는 수만 남음 왤까용
 * 
 * 약수가 홀수개인 값들만 남는거임
 * 
 */


class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        System.out.println((int) Math.sqrt(Integer.parseInt(br.readLine())));

        br.close();
    }       
}