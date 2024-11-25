import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * PriorityQueue와 Comparator를 이용할 줄 안다면 할 수 있음
 */
public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> queue = new PriorityQueue<>((after, before) -> {

            if (Math.abs(after) == Math.abs(before)) {
                return after - before;
            }

            return Math.abs(after) - Math.abs(before);
        });

        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine());
        for(int i=0;i<num;i++){

            int now = Integer.parseInt(br.readLine());
            if(now == 0){
                Integer tmp = queue.poll();
                sb.append(tmp == null ? 0 : tmp).append("\n");
            } else {
                queue.add(now);
            }
        }

        System.out.println(sb);
        br.close();
    }
}