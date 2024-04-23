package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Arrays;

//10815
/*
 * Map 내장 함수 쓰거나 이진 탐색을 써야 함
 * 완전 탐색 이용하면 시간 초과 뜸
 */
class solve10815 {

    int [] input(int x, BufferedReader br) throws IOException{
        
        int n = Integer.parseInt(br.readLine());
        int [] ary = new int [n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            ary[i] = Integer.parseInt(st.nextToken());        
        }
        return ary;
    }

    String binarySearch(int x, int [] target){
        int start = 0;
        int end = target.length-1;

        while(start <= end){
            
            int mid = (start + end) / 2;
            
            if(x > target[mid]){
                start = mid + 1;
            }
            else if(x < target[mid]){
                end = mid - 1;
            }
            else{
                return "1";
            }   
        }
        return "0";
    }

    void runSearch(BufferedReader br) throws IOException{
        //이진 탐색 이용
        int n = Integer.parseInt(br.readLine());
        int [] own = input(n, br);
        Arrays.sort(own);


        int m = Integer.parseInt(br.readLine());
        int [] target = input(m, br);
        
        StringBuilder sb = new StringBuilder();
    
        for(int i=0;i<target.length;i++){
            sb.append(binarySearch(target[i], own) + " ");  
        }

        System.out.println(sb);
        
    }

    void runMap(BufferedReader br) throws IOException{
        //map 이용
        int n = Integer.parseInt(br.readLine());
        int [] own = new int [n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            own[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int [] target = new int [m];
        Map<Integer, Integer> map = new HashMap<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            int input = Integer.parseInt(st.nextToken());
            target[i] = input;
            map.put(input, 0);
        }

        for(int x : own){
            if(map.containsKey(x)){
                map.put(x, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int x : target){
            sb.append(map.get(x) + " ");
        }

        System.out.println(sb);
        
    }
}

class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve10815 p = new solve10815();
        //p.runMap(br);
        p.runSearch(br);

        br.close();
    }    
    
}