import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * PriorityQueue 이용
 * 
 */
public class Main{

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));   
        
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        for(int i=0;i<num;i++){
            
            int now = Integer.parseInt(br.readLine());
            if(now == 0){
                
                if(queue.isEmpty()){
                    sb.append(0).append("\n");
                } else{
                    sb.append(queue.poll()).append("\n");
                }
            } else {
                queue.add(now);
            }
        }
        
        System.out.println(sb);
        br.close();
    }
}