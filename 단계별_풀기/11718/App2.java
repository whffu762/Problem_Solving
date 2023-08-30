package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//백준 11718
//문제에서 입력의 횟수를 정하지 않았기 때문에 한 번 읽고 종료되지 않게끔 해야 함

class App2 {
    public static void main(String[] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = "";

        while((str= br.readLine()) != null){
            System.out.println(str);
        }

    }
}