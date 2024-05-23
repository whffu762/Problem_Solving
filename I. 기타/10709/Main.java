import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 구름 : T
 * 구름 없음 : F
 * 
 * -1 : 앞에 T가 없을 때 F
 * 0 : 지금 T
 * 그 외 양수 : 앞에 T가 있을 때 F
 * 
 * 앞에 T가 존재했음을 유지하기 위한 장치가 필요함 그게 state
 */


class solve10709{

    boolean [][] sky;
    int [][] result;

    solve10709(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());
        int w = Integer.parseInt(st.nextToken());

        sky = new boolean[h][w];
        result = new int [h][w];
        for(int i=0;i<h;i++){

            String input = br.readLine();
            for(int j=0;j<w;j++){
                if(input.charAt(j) == 'c'){
                    sky[i][j] = true;
                }
            }
        }
    }

    void checkCloud(int h){

        boolean [] now = sky[h];

        boolean state = false;
        int cloud = -1; 
    
        for(int i=0;i<now.length;i++){

            if(now[i]){
                state = true;
                cloud = 0;
            } else if(state){
                cloud++;
            }

            result[h][i] = cloud;
        }
    }

    void run(){

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<sky.length;i++){
            
            checkCloud(i);
            for(int j=0;j<sky[0].length;j++){
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}


public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve10709 p = new solve10709(br);
        p.run();

        br.close();
        
    }
}