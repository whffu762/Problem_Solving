package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

//14425
//탐색을 할 땐 HashMap의 containsKey() 를 이용하는게 좋아 보임
class solve14425{
    
    void usingMap(BufferedReader br) throws IOException{
        //Map 이용 이게 훨씬 빠름 엄청 빠름 거의 6배는 더 빠름
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> own = new HashMap<>();
        for(int i=0;i<n;i++){
            own.put(br.readLine(), 0);
        }

        int cnt = 0;
        for(int i=0;i<m;i++){
            if(own.containsKey(br.readLine())){
                cnt++;
            }
        }

        System.out.println(cnt);

    }
    
    void fullSearch(BufferedReader br) throws IOException{
        //완전 탐색 이용
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        String [] own = new String [n];
        for(int i=0;i<n;i++){
            own[i] = br.readLine();
        }

        String [] target = new String [m];
        for(int i=0;i<m;i++){
            target[i] = br.readLine();
        }

        int cnt = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(own[i].equals(target[j])){
                    cnt++;
                }
            }
        }

        System.out.println(cnt);
    }
}
class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve14425 p = new solve14425();
        p.fullSearch(br);
        p.usingMap(br);

        br.close();
    }    
    
}