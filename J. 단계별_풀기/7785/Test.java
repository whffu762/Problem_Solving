package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Comparator;

//7785
//문제를 제대로 읽도록 하죠...
class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Map<String, String> map = new HashMap<>();
        StringTokenizer st;
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String log = st.nextToken();

            if(log.equals("leave")){
                map.remove(name);
            }
            else if(log.equals("enter")){
                map.put(name, log);
            }
        }

        StringBuilder sb = new StringBuilder();

        new ArrayList<>(map.keySet()).stream()
            .sorted(Comparator.reverseOrder())
            .forEach(e -> sb.append(e+"\n"));
        
        
        System.out.println(sb);

        br.close();
    }    
    
}