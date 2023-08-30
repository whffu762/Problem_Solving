import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/*
2346
 * ArrayDeque를 쓰지 않으면 무조건 메모리 초과 뜸
 * 각 자료형 별로 속도, 메모리를 좀 알아야 될 듯 이거 말고
 * 이전엔 속도 문제로 ArrayList 말고 LinkedList 써야 했음(18258)
 * 
 * Stack과 Queue, Deque 는 ArrayDeque 로 만드는게 제일 빠르고 가벼움 
 */

class Balloon {
    int idx;
    int move;

    Balloon(int idx, int move) {
        this.idx = idx;
        this.move = move;
    }
}

class solve2346 {

    Deque<Balloon> list = new ArrayDeque<>();

    void run(BufferedReader br) throws IOException {

        StringBuilder sb = new StringBuilder("1 ");

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            list.add(new Balloon(i + 1, Integer.parseInt(st.nextToken())));
        }

        int move = list.getFirst().move;
        int idx = list.getFirst().idx;
        list.remove();

        while (!list.isEmpty()) {
            if (move > 0) {
                move -= 1;
                for (int i = 0; i < move; i++) {
                    list.addLast(list.remove());
                }
            } else {
                for (int i = move; i < 0; i++) {
                    list.addFirst(list.removeLast());
                }
            }

            move = list.getFirst().move;
            idx = list.getFirst().idx;
            sb.append(idx).append(" ");
            list.remove();
        }

        System.out.println(sb);
    }
}

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2346 p = new solve2346();
        p.run(br);

        br.close();

    }
}