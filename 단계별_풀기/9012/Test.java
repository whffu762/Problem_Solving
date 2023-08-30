package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//9012
class solve9012{

    boolean mine(String target) {
        int cnt = 0;
        for(int i=0;i<target.length();i++){
            if(cnt < 0){
                return false;
            }

            if(target.charAt(i) == '('){
                cnt++;
            }
            else{
                cnt--;
            }
        }
        if(cnt == 0){
            return true;
        }
        else{
            return false;
        }
        

    }

    public void run(BufferedReader br) throws IOException {

        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String target = br.readLine();
            if(mine(target)){
                sb.append("YES").append("\n");
            }
            else{
                sb.append("NO").append("\n");
            }
            
        }

        System.out.println(sb);
    }
}

class Test {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9012 p = new solve9012();
        p.run(br);

        br.close();
    }
}