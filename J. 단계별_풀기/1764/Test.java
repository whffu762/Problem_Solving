package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Collections;
import java.util.Set;

//1764
class solve1764{

    void sec(BufferedReader br) throws IOException{
	//이게 더 느렸음..
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> map = new HashMap<>();

        for(int i=0;i<n+m;i++){
            String tmp = br.readLine();

            if(map.containsKey(tmp)){
                map.put(tmp, 2);
            }
            else{
                map.put(tmp, 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        
        List<String> result = map.keySet().stream()
            .filter(name -> map.get(name).toString().equals("2"))
            .sorted()
            .collect(Collectors.toList());
            
        
        for(int i=0;i<result.size();i++){
            sb.append(result.get(i)+ "\n");
        }
        
        System.out.println(result.size());
        System.out.println(sb);
    }

    void first(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        for(int i=0;i<n;i++){
            set.add(br.readLine());
        }

        List<String> result = new ArrayList<>();
        for(int i=0;i<m;i++){
            String tmp = br.readLine();
            if(set.contains(tmp)){
                result.add(tmp);
            }
        }

        Collections.sort(result);

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<result.size();i++){
            sb.append(result.get(i)+"\n");
        }
        System.out.println(result.size());
        System.out.println(sb);

    }
}

class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1764 s = new solve1764();
        s.sec(br);

        br.close();
    }    
    
}