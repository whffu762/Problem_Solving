import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

/*
 * ArrayDeque를 비교적 편하게 구현할 수 있음
 * 
 * 뒤집어 졌을 때 실제로 뒤집지 않기 때문에 맨 앞과 맨 뒤의 요소에 쉽게 접근할 수 있어야 하는데
 * ArrayDeque의 removeFirst()와 removeLast() 메소드가 있어서 구현하기 편함
 * 
 * 여기서도 마찬가지로 핵심은 StringBuilder로 문자열 합치기
 * 사실상 로직보다 이게 더 중요함 ;;;;
 */
class AC {

    String command;
    Deque<String> deque = new ArrayDeque<>();
    boolean flag = true;

    public AC(String command, String arySize, String input) {
        this.command = command;
        String[] tmp = input.substring(1, input.length() - 1).split(",");

        for (int i = 0; i < Integer.parseInt(arySize); i++) {
            deque.add(tmp[i]);
        }
    }

    String run() {

        for (int i = 0; i < command.length(); i++) {

            if (command.charAt(i) == 'R') {
                flag = !flag;
            } else {
                try {
                    delete();
                } catch (NoSuchElementException e){
                    return "error";
                }
            }
        }

        return toString();
    }

    void delete() throws NoSuchElementException {

        if (flag) {
            deque.removeFirst();
        } else {
            deque.removeLast();
        }

    }

    public String toString() {

        if(deque.isEmpty()){
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        if (flag) {
            sb.append(deque.removeFirst());
            while(!deque.isEmpty()){
                sb.append(",").append(deque.removeFirst());
            }
        } else {

            sb.append(deque.removeLast());
            while(!deque.isEmpty()){
                sb.append(",").append(deque.removeLast());
            }
        }
        sb.append("]");

        return sb.toString();
    }
}


public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int numOfCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < numOfCase; i++) {
            sb.append(new AC(br.readLine(), br.readLine(), br.readLine()).run()).append("\n");
        }
        System.out.println(sb);

        br.close();
    }
}