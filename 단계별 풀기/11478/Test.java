package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

//11478
class solve1269{

    void sec(BufferedReader br) throws IOException{
        //스트림을 이용할 순 없는듯 ...?
    }

    void first(BufferedReader br) throws IOException{

        String target = br.readLine();
        
        Set<String> set = new HashSet<>();

        int cnt = 1;
        for(int i=0;i<target.length();i++){
            for(int j=0;j<target.length()-i;j++){
                set.add(target.substring(j, j+cnt));   
            }
            cnt++;
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