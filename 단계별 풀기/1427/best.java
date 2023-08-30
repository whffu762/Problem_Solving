package pack2;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

class Test{
    public static void main(String[] args){
        
        Scanner scan = new Scanner(System.in); 
        List<Integer> tmp = new ArrayList<>();

        int target = scan.nextInt();

        while(target != 0){
            int x = target % 10;
            tmp.add(x);
            target /= 10;
        }

        int [] ary = new int[tmp.size()];
        int [] cnt = new int [10];
        int [] result = new int [tmp.size()];

        for(int i=0;i<tmp.size();i++){
            ary[i] = tmp.get(i);
            cnt[ary[i]]++;
        }

        for(int i=1;i<10;i++){
            cnt[i] += cnt[i-1];
        }

        for(int i=0;i<ary.length;i++){
            result[--cnt[ary[i]]] = ary[i];
        }

       
        StringBuilder sb = new StringBuilder();

        for(int i=ary.length-1;i>-1;i--){
            sb.append(result[i]);
        }

        System.out.println(sb);

        scan.close();
    }    
    
}