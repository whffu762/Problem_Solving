import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
 * 24511
 * 입력과 sq의 갯수만큼 for문 돌리면 시간 초과 뜸
 * 
 * 스택은 변경이 없기 때문에 무시하고 Q에 해당하는 값만 저장
 * 서로 다른 Q에 저장된 값이라도 하나에 저장하면 하나의 Q 처럼 동작됨
 * 예제 입력 1 보면
 * 
 * 1 4 만 저장되고 입력은 2 4 7이 들어감
 * 
 * 2 입력
 * 2 1 4 - 4 출력
 * 
 * 4 입력
 * 4 2 1 - 1 출력
 * 
 * 7 입력
 * 7 4 2 - 2 출력
 * 
 * 이런 식으로 다른 Q에 저장되어 있지만 하나의 Q 처럼 동작
 * 이를 이용해서 for문을 입력값 만큼만 돌리면 됨
 */
class solve24511 {

    void run(BufferedReader br) throws IOException {

        int n = Integer.parseInt(br.readLine());
        int[] sqSeq = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            sqSeq[i] = Integer.parseInt(st.nextToken());
        }

        Deque<Integer> sqList = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int tmp = Integer.parseInt(st.nextToken());
            if (sqSeq[i] == 0) {
                sqList.add(tmp);
            }
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (st.hasMoreTokens()) {
            int input = Integer.parseInt(st.nextToken());
            sqList.addFirst(input);
            sb.append(sqList.removeLast()).append(" ");
        }

        System.out.println(sb);

    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve24511 p = new solve24511();
        p.run(br);

        br.close();

    }
}