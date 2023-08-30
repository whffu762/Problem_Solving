package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Random;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

//10816
class Test3{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            int card = Integer.parseInt(st.nextToken());
            if(map.containsKey(card)){
                int cnt = map.get(card);
                cnt++;
                map.put(card, cnt);
            }
            else{
                map.put(card, 1);
            }
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<m;i++){
            int t = Integer.parseInt(st.nextToken());
            if(map.containsKey(t)){
                sb.append(map.get(t)+" ");
            }
            else{
                sb.append("0 ");
            }
        }
        
        System.out.println(sb);


        br.close();
    }    
    
}