import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/*
 * Java Collection엔 최소 힙이 구현되어 있는 Priority Queue가 존재함
 * add()로 넣고 poll() 혹은 remove()로 뺄 수 있음
 * 
 * 사용자 지정 클래스를 저장하거나 다른 정렬 규칙을 이용하고 싶으면
 * Object 클래스의 CompareTo를 오버라이딩 하거나
 * Comparator<>를 상속받아 구현한 클래스 혹은 람다식을 생성자 인자로 넣어주면 됨
 */

class solve1927{

    PriorityQueue<Integer> heap = new PriorityQueue<>();

    void run(BufferedReader br) throws IOException{

        Integer numOfInput = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfInput;i++){
            int input = Integer.parseInt(br.readLine());
            if(input == 0){
                Integer result;
                if((result = heap.poll()) == null){
                    result = 0;
                }
                sb.append(result).append("\n");
            } else {
                heap.add(input);
            }
        }
        
        System.out.println(sb);
    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1927 p = new solve1927();
        p.run(br);

        br.close();
    }
}