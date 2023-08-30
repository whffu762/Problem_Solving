package pack2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import java.util.Scanner;
import java.util.Collections;
import java.util.List;

//2751
//정렬 알고리즘을 Collections.sort()를 이용해야 함(Arrays.sort()는 안됨)
//출력을 한꺼번에 해야 시간에 맞출 수 있음(문자열로 만들든 Buffer를 쓰든)
//run2의 방법은 중복되는 값이 없어야 가능한 방법
//입력된 값으로 인덱스를 만들어서 true면 출력에 포함시키는 방식으로 가장 빠름
class Processor{


    void run(Scanner scan){
        int num = scan.nextInt();
        List<Integer> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        for(int i=0;i<num;i++){
            list.add(scan.nextInt());
        }

        Collections.sort(list);

        for(int i=0;i<num;i++){
            sb.append(list.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    

    void run1(BufferedReader br, BufferedWriter bw) throws IOException{
        int num = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<num;i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list);

        for(int i=0;i<num;i++){
            bw.write(list.get(i).toString());
            bw.newLine();
        }

        bw.flush();        

    }

    void run2(BufferedReader br, BufferedWriter bw)throws IOException{
        int num = Integer.parseInt(br.readLine());
        
        boolean [] arr = new boolean[2000001];

        for(int i=0;i<num;i++){
            arr[Integer.parseInt(br.readLine()) + 1000000] = true;
        }

        for(int i=0;i<arr.length;i++){
            if(arr[i]){
                bw.write(String.valueOf(i-1000000));
                bw.newLine();
            }
        }

        bw.flush();

    }

}


class Test{

    public static void main(String[] args) throws IOException{
        Scanner scan = new Scanner(System.in);
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Processor p = new Processor();        
        //p.run(scan);
        //p.run1(br, bw);
        p.run2(br, bw);

        br.close();
        bw.close();

        scan.close();
    }    
    
}