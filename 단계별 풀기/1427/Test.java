package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.Collections;



/*

//Scanner 이용
//BuffredReader 이용
 * 입력 방법
 * 1. String - parseInt 이용
 * 2. int - 나머지 연산 이용
 * 
 * 정렬 방법
 * 1. counting sort
 * 2. .sort()

scan_int_cnt : 4
bf_str_.sort : 6
scan_str_cnt :  7
bf_int_cnt : 7
bf_str_cnt : 8
bf_int_sort : 10
scan_int_.sort : 11
scan_str_.sort : 14
//전체적으로 counting sort, BufferedReader가 우세
근데 최선은 scanner + counting sort
 */

class Processor{

    int cnt = 0;
    long [] times = new long [8];

    void useString1(String target){
        //counting sort
        StringBuilder sb = new StringBuilder();

        int [] ary = new int[target.length()];
        int [] result = new int [target.length()];
        int [] cnt = new int [10];

        //카운트
        for(int i=0;i<target.length();i++){
            ary[i] = Character.getNumericValue(target.charAt(i)); 
            cnt[ary[i]]++;
        }
        //누적합
        for(int i=1;i<10;i++){
            cnt[i] += cnt[i-1];
        }

        //정렬
        for(int i=0;i<target.length();i++){
            result[--cnt[ary[i]]] = ary[i];
        }

        for(int i=target.length()-1;i>-1;i--){
            sb.append(result[i]);
        }

        System.out.println(sb);
    }

    void useString2(String target){
        //collections.sort()
        StringBuilder sb = new StringBuilder();
        List<Integer> ary = new ArrayList<>();

        for(int i=0;i<target.length();i++){
            ary.add(Character.getNumericValue(target.charAt(i))); 
        }
        
        Collections.sort(ary);

        for(int i=target.length()-1;i>-1;i--){
            sb.append(ary.get(i));
        }

        System.out.println(sb);
        
    }
    void useInt1(int target){
        //counting sort
        StringBuilder sb = new StringBuilder();
        List<Integer> tmp = new ArrayList<>();

        while(target != 0){
            int x = target % 10;
            tmp.add(x);
            target /= 10;
        }

        int [] ary = new int[tmp.size()];
        int [] result = new int [tmp.size()];
        int [] cnt = new int [10];

        //카운트
        for(int i=0;i<tmp.size();i++){
            ary[i] = tmp.get(i);
            cnt[ary[i]]++;
        }
        //누적합
        for(int i=1;i<10;i++){
            cnt[i] += cnt[i-1];
        }

        //정렬
        for(int i=0;i<ary.length;i++){
            result[--cnt[ary[i]]] = ary[i];
        }

        for(int i=ary.length-1;i>-1;i--){
            sb.append(result[i]);
        }

        System.out.println(sb);

    }

    void useInt2(int target){
        //Collections.sort()
        StringBuilder sb = new StringBuilder();
        List<Integer> ary = new ArrayList<>();

        while(target != 0){
            int x = target % 10;
            ary.add(x);
            target /= 10;
        }

        Collections.sort(ary);

        for(int i=ary.size()-1;i>-1;i--){
            sb.append(ary.get(i));
        }

        System.out.println(sb);
    }
    
    void run1(Scanner scan){
        System.out.println("Scanner");

        String target = scan.nextLine();
        long beforeTime = System.currentTimeMillis();
        useString1(target);
        long afterTime = System.currentTimeMillis();
        times[0] = times [0] + (afterTime - beforeTime);
        System.out.println("string counting sort = " + (afterTime - beforeTime));

        beforeTime = System.currentTimeMillis();
        useString2(target);
        afterTime = System.currentTimeMillis();
        times[1] = times [1] + (afterTime - beforeTime);
        System.out.println("string .sort() = " + (afterTime - beforeTime));


        int target1 = scan.nextInt();

        beforeTime = System.currentTimeMillis();
        useInt1(target1);
        afterTime = System.currentTimeMillis();
        times[2] = times [2] + (afterTime - beforeTime);
        System.out.println("int counting sort = " + (afterTime - beforeTime));

        beforeTime = System.currentTimeMillis();
        useInt2(target1);
        afterTime = System.currentTimeMillis();
        times[3] = times [3] + (afterTime - beforeTime);
        System.out.println("int .sort() = " + (afterTime - beforeTime));

        System.out.println("================");
        
    }

    void run2(BufferedReader br) throws IOException{
        System.out.println("BufferedReader");

        String target = br.readLine();
        long beforeTime = System.currentTimeMillis();
        useString1(target);
        long afterTime = System.currentTimeMillis();
        times[4] = times [4] + (afterTime - beforeTime);
        System.out.println("string counting sort = " + (afterTime - beforeTime));

        beforeTime = System.currentTimeMillis();
        useString2(target);
        afterTime = System.currentTimeMillis();
        times[5] = times [5] + (afterTime - beforeTime);
        System.out.println("string .sort() = " + (afterTime - beforeTime));


        int tmp = Integer.parseInt(target);

        beforeTime = System.currentTimeMillis();
        useInt1(tmp);
        afterTime = System.currentTimeMillis();
        times[6] = times [6] + (afterTime - beforeTime);
        System.out.println("int counting sort = " + (afterTime - beforeTime));
        
        beforeTime = System.currentTimeMillis();
        useInt2(tmp);
        afterTime = System.currentTimeMillis();
        times[7] = times [7] + (afterTime - beforeTime);
        System.out.println("int .sort() = " + (afterTime - beforeTime));
        

        System.out.println("===============");
    }

    void print(){
        for(int i=0;i<times.length;i++){
            System.out.println(i + " " + times[i]);
        }
    }
}

class Test{

    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Scanner scan = new Scanner(System.in);        
        Processor p = new Processor();
        while(true){
        scan.nextLine();
        p.run1(scan);
        p.run2(br);
        p.print();
        }
        //br.close();
        //scan.close();
    }    
    
}