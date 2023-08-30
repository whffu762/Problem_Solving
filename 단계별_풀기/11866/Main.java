import java.util.Scanner;
import java.awt.List;
import java.util.LinkedList;
import java.util.Queue;

//11866
/*
 * 순환 큐 직접 구현(mine, wow1)
 * 요소 하나씩 순회하면서 직접 순환시키기(Queue)
 */

class solve11866 {

    StringBuilder usingQueue(LinkedList<Integer> q, int k) {

        StringBuilder sb = new StringBuilder("<");

        while (q.size() != 1) {
            for (int i = 0; i < k - 1; i++) {
                int tmp = q.remove();
                q.add(tmp);
            }

            sb.append(q.remove()).append(", ");
        }
        sb.append(q.remove()).append(">");

        return sb;

    }

    StringBuilder wow1(LinkedList<Integer> list, int k){
        
        StringBuilder sb = new StringBuilder("<");
        
        int index = 0;
        while(list.size() != 1){
        index = (index + (k - 1)) % list.size();
          
        sb.append(list.remove(index)).append(", ");
        }
        sb.append(list.remove()).append(">");
    
        return sb;
    }

    StringBuilder mine(LinkedList<Integer> list, int k) {

        StringBuilder sb = new StringBuilder("<");
        int i = 0;
        int cnt = 1;
        if (k == 1) {
            while (list.size() != 1) {
                sb.append(list.remove(0)).append(", ");
            }
        } else {
            while (list.size() != 1) {
                i = (i + 1) % list.size();
                if (++cnt == k) {
                    sb.append(list.remove(i)).append(", ");
                    cnt = 1;
                }
            }

        }

        sb.append(list.getFirst()).append(">");

        return sb;

    }

    void run(Scanner scan) {
        int n = scan.nextInt();
        int k = scan.nextInt();

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i < n + 1; i++) {
            list.add(i);
        }

        StringBuilder sb;
        sb = mine(list, k);
        sb = usingQueue(list, k);
        sb = wow1(list, k);

        System.out.println(sb);

    }
}

class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        solve11866 p = new solve11866();
        p.run(scan);

        scan.close();
    }
}