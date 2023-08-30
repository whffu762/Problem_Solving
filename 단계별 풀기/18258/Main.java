import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;


/*
 * ArrayList로 구현하면 느려서 시간 초과 뜸 
 * 느린 연산(O(n))
 * - index 0 add
 * - index 0 remove
 * 
 * 빠른 연산(O(1))
 * - index 이용한 get/set
 * 
 * LinkedList로 구현하면 
 * 빠른 연산(O(1))
 * - index 0 add
 * - index 0 remove
 * 
 * 느린 연산(O(n))
 * - index 이용한 get/set
 * 
 * 
	Deque로 해야 함
 */

class solve18258 {

    void usingQueue(BufferedReader br) throws IOException{
        int n = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        Deque<Integer> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            String result = null;

            switch (command) {
                case "push":
                    q.add(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    result = q.isEmpty() ? "-1" : String.valueOf(q.remove());
                    break;
                case "size":
                    result = String.valueOf(q.size());
                    break;
                case "empty":
                    result = q.isEmpty() ? "1" : "0";
                    break;
                case "front":
                    result = q.isEmpty() ? "-1" : String.valueOf(q.getFirst());
                    break;
                case "back":
                    result = q.isEmpty() ? "-1" : String.valueOf(q.getLast());
            }
            if (result != null) {
                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);
    }
}

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve18258 p = new solve18258();
        //p.usingMine(br);
        p.usingQueue(br);

        br.close();

    }
}