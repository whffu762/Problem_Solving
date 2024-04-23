import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 그래프의 구조는 다 무시하고 그냥 PriorityQueue 구현해서 넣고 빼고 하면 됨
 * 뭐 문제를 읽을 필요도 없이 숫자 정렬하는건 PrioirtyQueue 쓰면 되는 듯
 * 
 * 이전에도 했지만 최소 힙을 구현해서 remove(), poll(), peek()을 하면 알아서 최솟값을 반환해줌
 * 이를 이용한 기본 흐름
 * 1. pQ에 먼저 N개 들어가있음
 * 2. pQ의 최솟값과 들어오는 입력을 비교해서 입력이 더 크면 pQ에 삽입
 * 3. 끝까지 반복
 * 
 * 이를 이용해 큰 순서대로 N개를 넣을 수 있음
 */

class solve2075{

    Queue<Integer> pQueue = new PriorityQueue<>();

    solve2075(BufferedReader br) throws IOException{

        int n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            pQueue.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=1;i<n;i++){

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){

                int now = Integer.parseInt(st.nextToken());
                if(now > pQueue.peek()){
                    pQueue.remove();
                    pQueue.add(now);
                }
            }
        }
    }

    void run(){

        System.out.println(pQueue.remove());
    }
}

public class Main2{

    public static void main(String [] args) throws IOException{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2075 p = new solve2075(br);
        p.run();

        br.close();
    }
}