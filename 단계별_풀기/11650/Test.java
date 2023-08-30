package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//11650
//Arrays.sort()의 compare() 오버라이딩으로 정렬 방식 재정의(Comparator 익명 함수 이용)
//Collections.sort() 도 가능함 인자가 똑같기 때문
class Processor{

    void arrays(int [][] ary){
        
        Arrays.sort(ary, (pre, post)->{     
            if(pre[0] == post[0]){
                return pre[1] - post[1];
            }
            else {
                return pre[0] - post[0];
            }
        });
        /*
        pre post라고만 했는데 어떻게 1차 배열로 취급될까?
        sort()에서 a의 자료형은 T[] c 의 자료형은 T
        위를 현재 인자에 대입해보면 ary는 int [][] T는 int []
        익명 함수의 인자는 각각 일차 배열로 차수가 내려감 
        */

        StringBuilder sb = new StringBuilder();

        for(int i=0;i<ary.length;i++){
            for(int j=0;j<2;j++){
                sb.append(ary[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    void collections(List<List<Integer>> lists){

        /* 직접 익명객체 만들어서 Compartor 전달
        Collections.sort(lists, new Comparator<List<Integer>>(){
            @Override
            public int compare(List<Integer> pre, List<Integer> pro){
                if(pre.get(0) == pro.get(0)){
                    return pre.get(1) - pro.get(1);
                }
                else{
                    return pre.get(0) - pro.get(0);
                }
            }
        });
 */

        //람다로 전달
        Collections.sort(lists, (pre, pro)->{
            if(pre.get(0) == pro.get(0)){
                return pre.get(1) - pro.get(1);
            }
            else{
                return pre.get(0) - pro.get(0);
            }
        });



        StringBuilder sb = new StringBuilder();

        for(int i=0;i<lists.size();i++){
            for(int j=0;j<2;j++){
                sb.append(lists.get(i).get(j)).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    void run(BufferedReader br) throws IOException{
        
        int n = Integer.parseInt(br.readLine());

        int [][] ary = new int [n][2]; 
        List<List<Integer>> lists = new ArrayList<>();

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<Integer> list = new ArrayList<>();
        
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            ary[i][0] = x;
            ary[i][1] = y;
            
            list.add(x);
            list.add(y);
            lists.add(list);
        }
        System.out.println();
        arrays(ary);
        collections(lists);
    }
}

class listComparator implements Comparator<List<Integer>>{

    public int compare(List<Integer> pre, List<Integer> pro){
        if(pre.get(0) == pro.get(0)){
            return pre.get(1) - pro.get(1);
        }
        else{
            return pre.get(0) - pro.get(0);
        }
    }
}

class Test{
    public static void main(String[] args) throws IOException{
        
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Processor p = new Processor();
        p.run(br);

        br.close();
    }    
    
}