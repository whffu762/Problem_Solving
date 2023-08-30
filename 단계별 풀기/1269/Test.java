package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.Set;

//1269
class solve1269{

    void sec(BufferedReader br) throws IOException{
        //스트림을 이용할 순 없는듯 ...?
    }

    void first(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            set.add(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<m;i++){
            String tmp = st.nextToken();

            if(set.contains(tmp)){
                set.remove(tmp);
            }
            else{
                set.add(tmp);
            }
        }

        System.out.println(set.size());
    }
}

class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1269 s = new solve1269();
        s.first(br);

        br.close();
    }    
    
}