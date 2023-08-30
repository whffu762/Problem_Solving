package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


/*
 * 1620
 * 문자열인지 숫자인지 구분하는 메소드
 * 예외 잡히면 false return
 */
class solve1620{

    boolean checkInteger(String x){
        try{
            Integer.parseInt(x);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    void run(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Map<String, Integer> mapName = new HashMap<>();
        Map<Integer, String> mapNum = new HashMap<>();
        for(int i=1;i<n+1;i++){
            String name = br.readLine();
            mapName.put(name, i);
            mapNum.put(i, name);
        }

        String [] p = new String[m];
        for(int i=0;i<m;i++){
            p[i] = br.readLine();
        }

        StringBuilder sb = new StringBuilder();
    
        for(int i=0;i<m;i++){
            if(checkInteger(p[i])){
                sb.append(mapNum.get(Integer.parseInt(p[i]))+"\n");
            }
            else{
                sb.append(mapName.get(p[i])+"\n");
            }
        }
        

    
        System.out.println(sb);

    }
}
class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1620 p = new solve1620();
        p.run(br);

        br.close();
    }    
    
}