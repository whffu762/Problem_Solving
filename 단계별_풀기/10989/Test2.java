package pack2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

//10989
//Scanner로 입력 받으면 시간초과 뜸 BufferedReader가 필수
//Scanner는 입력 과정에서 정규식이 사용되기 때문에 입력량이 많으면 느림
class Main{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        int [] ary = new int [10001];

        for(int i=0;i<num;i++){
            ary[Integer.parseInt(br.readLine())]++;
        }
        
        for(int i=1;i<10001;i++){
            if(ary[i] != 0){
                for(int j=0;j<ary[i];j++){
                    bw.write(String.valueOf(i));
                    bw.newLine();
                }
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }    
    
}