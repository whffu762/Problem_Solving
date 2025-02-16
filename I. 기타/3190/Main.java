import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * 주목할 점은 두 개
 * - Queue를 이용해서 뱀의 상태를 유지해야 함
 * - 3 -> 2 -> 1 -> 0 -> 3 -> 2 .. 수열의 식
 * 
 * 간단한 동작 흐름
 * 1. 커맨드를 확인해서 방향을 먼저 확인함
 * 2. 방향이 결정되면 머리를 내밀어 유효성 검사
 * 3. 사과가 있으면 꼬리를 냅두고 사과가 없으면 꼬리를 제거함
 * 4. 사과 유무와 상관없이 머리는 추가
 * 
 * 여기서 그냥 head와 tail의 좌표만 유지해서 하려고 해봤는데 다음 꼬리를 추적할 방법이 없음 
 * 그냥 Queue 쓰는게 편함
 * 
 * 3 -> 2 -> 1 -> 0 -> 3.. 수열 오름차순으로 순환하는건 자주 써봤어도 내림차순은 처음봤음
 * x = (x + 최댓값) % (최댓값 + 1) 로하면 순환됨 와우~
 * 
 */
class solve3190{

    int size;
    int [][] map;
    Map<Integer, String> input = new HashMap<>();

    solve3190 (BufferedReader br) throws IOException{

        size = Integer.parseInt(br.readLine());
        map = new int[size+2][size+2];
        Arrays.fill(map[0], -1);
        Arrays.fill(map[size+1], -1);
        for(int i=1;i<size+2;i++){
            map[i][0] = -1;
            map[i][size+1] = -1;
        }
        
        int numOfApple = Integer.parseInt(br.readLine());
        for(int i=0;i<numOfApple;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
        }

        int numOfInput = Integer.parseInt(br.readLine());
        for(int i=0;i<numOfInput;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            input.put(Integer.parseInt(st.nextToken()), st.nextToken());
        }
    }

    void run(){

        Deque<int []> snake = new ArrayDeque<>();
        snake.add(new int [] {1, 1});
        int direction = 0;
        int count = 0;
        setMap(snake.peek(), -1);

        while(true) {

            direction = getDirection(count, direction);
            int [] head = getNext(direction, Arrays.copyOf(snake.peekLast(), snake.peekLast().length));
            if(!checkVaild(head)){
                break;
            }
            
            eat(head, snake);
            count++;
        }

        System.out.println(count + 1);
    }

    int getDirection(int count, int direction) {

        String command = input.get(count);

        if(command == null){
            return direction;
        }

        if(command.equals("D")){
            return (direction + 1) % 4;
        }

        return (direction + 3) % 4;
    }

    int [] getNext(int direction, int [] head){

        switch (direction) {
            case 0:
                head[1]++;
                break;
            case 1:
                head[0]++;
                break;
            case 2:
                head[1]--;
                break;
            case 3:
                head[0]--;
                break;
        }

        return head;
    }

    void eat(int [] head, Deque<int []> snake){
        
        if(getMap(head) != 1){
            int [] tail = snake.pop();
            setMap(tail, 0);
        }

        snake.add(head);
        setMap(head, -1);
    }

    boolean checkVaild(int [] head){

        if(getMap(head) == -1){
            return false;
        }

        return true;
    }

    int getMap(int [] headOrTail){

        return map[headOrTail[0]][headOrTail[1]];
    }

    void setMap(int [] headOrTail, int value){

        map[headOrTail[0]][headOrTail[1]] = value;
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve3190 p = new solve3190(br);
        p.run();

        br.close();
    }
}