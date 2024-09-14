import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * BFS를 이용하면 되는 간단한 문제인데 조건이 많아서 오래 걸림 사실상 구현이 본체 
 * 
 * 1. 먹을 수 있는 물고기를 완전 탐색
 * 2. 그 중 최솟값을 골라서 먹음
 * 3. 이걸 먹을게 없을 때까지 처리
 * 
 * 로직은 아주 간단한데 이 사이 사이에 신경써야 할 변수들이 너무 많음
 * 완전 탐색할 때 필요한 조건
 * - 기본적으로 BFS에서 지켜져야할 조건들(size보다 작아야 되고, 방문한거 유지해야 되고..)
 * - 최단 거리를 구하기 위해 distance 테이블을 따로 유지해야 됨(가중치가 1 뿐이라 가능)
 * - 물고기의 크기 > 상어의 크기 -> 이 경우 통과하지 못 함
 * - 이 와중에 최소 거리 구해야 함(사실 이건 따로 나누는게 더 편할듯 아래서 자세히)
 * 
 * 최솟값 구할 때 필요한 조건
 * - 물고기의 크기 == 상어의 크기 -> 지나갈 순 있지만 먹지 못 함(canEat로 초기화 불가)
 * - 물고기의 크기 < 상어의 크기 -> 이럴 때만 먹을 수 있음(canEat로 초기화)
 * - 거리가 동일한 물고기가 다수인 경우 좌측 상단에 존재하는 물고기를 먹음
 * - 이걸 canEat이 초기엔 null 이니까 이 경우까지 같이 판단해야 함
 * 
 * 먹을 때의 처리
 * - 먹은 물고기의 갯수 증가
 * - 먹은 물고기의 갯수 == 상어의 크기 -> 이 경우 상어의 크기가 증가하고 먹은 물고기 갯수를 0으로 초기화
 * - 먹은 물고기 위치 제거
 * - 상어 위치 변경
 * - 먹은 물고기의 거리만큼 버틴 시간에 추가
 * 
 * 알고리즘은 매우 간단한데 이런 조건들을 구현하는게 복잡함 최솟값 구하는 작업을 
 * 나는 BFS 돌리면서 최솟값만 유지하는 방식으로 구현했음 그래서 탐색 조건에 만족하는 값은 모두 최솟값 비교를 수행했음
 * 근데 완전 탐색 + 최솟값 구하기 이 두 동작을 동시에 하니까 구현이 불편함 신경써야 할게 너무 많음..
 * 그래서 차라리 BFS로 각 물고기로의 최소 거리 구하고 다 끝난 다음에 (0,0) ~ (n,n)까지 먹을 수 있는 물고기를 판단하는게
 * 더 편해보임 그러면 canEat이 null 인 경우와 거리가 동일한 물고기에 대해선 신경쓰지 않아도 됨
 * null 인 경우는 애초에 다 끝나고 조회하니까 업소
 * 동일한 물고기는 어차피 0 0 부터 순회해서 가장 먼저 나오는게 그에 해당하니까..
 * 
 * 이렇게 루프 두 번 돌리면 시간 초과 발생할 것 같아서 이렇게 했는데 최대값이 20이니까 오히려 이게 더 빠를 듯..  
 * 
 * 
 */


class Fish {

    int[] where;
    int size;
    int distance;

    Fish() {}

    Fish(int[] where, int size) {
        this.where = where;
        this.size = size;
    }

    Fish(int[] where, int size, int distance) {
        this.where = where;
        this.size = size;
        this.distance = distance;
    }
}

class Shark extends Fish {

    Fish canEat;
    int numOfEat;
    int time;

    Shark(int[] where, int size) {
        super(where, size);
    }

    public void eat(int[][] graph, int [][] distance) {

        //버틴 시간 증가(최소 거리 추가)
        time += distance[canEat.where[0]][canEat.where[1]];
        
        //상어 위치 제거
        graph[where[0]][where[1]] = 0;

        //먹은 물고기 제거
        this.where = canEat.where;
        graph[where[0]][where[1]] = 9;

        //먹을 물고기 초기화
        canEat = null;
        numOfEat++;
        //size 키울지 판단
        if (size == numOfEat) {
            size++;
            numOfEat = 0;
        }
    }

    //여기엔 작거나 같은 크기의 물고기만 들어옴(0 포함)
    public void setCanEat(Fish fish){

        if(fish.size == 0 || fish.size == this.size){
            return;
        }

        if(canEat == null && fish.size < this.size){
            canEat = fish;
            return;
        }

        if(fish.distance == canEat.distance){

            if(canEat.where[0] == fish.where[0] && canEat.where[1] > fish.where[1]){
                canEat = fish;
                return;
            } else if (canEat.where[0] > fish.where[0]){
                canEat = fish;
                return;
            }

        } else if(fish.distance < canEat.distance){
            canEat = fish;
            return;
        }
    }


    public boolean nothingToEat() {

        return canEat == null ? true : false;
    }

}

class solve16236 {

    int size;
    int [][] graph;
    Shark shark;

    Queue<Fish> queue = new ArrayDeque<>();
    int [] move = { -1, 1 };
    boolean [][] visited;
    int [][] distance;

    solve16236(BufferedReader br) throws IOException {
        size = Integer.parseInt(br.readLine());
        graph = new int[size][size];
        visited = new boolean[size][size];
        distance = new int[size][size];

        StringTokenizer st;
        for (int i = 0; i < size; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < size; j++) {

                int now = Integer.parseInt(st.nextToken());
                if (now == 9) {
                    shark = new Shark(new int[] { i, j }, 2);
                }
                graph[i][j] = now;
            }
        }
    }

    void run() {
        
        while (true) {

            bfs();

            if (shark.nothingToEat()) {
                break;
            }
            shark.eat(graph, distance);
        
            init();
        }

        System.out.println(shark.time);
    }

    void bfs() {

        queue.add(shark);
        visited[shark.where[0]][shark.where[1]] = true;

        while(!queue.isEmpty()){

            Fish now = queue.poll();
            int nowRow = now.where[0];
            int nowCol = now.where[1];

            for(int i=0;i<move.length;i++){

                int nextRow = nowRow + move[i];
                int [] tmp = {nextRow, nowCol};

                if(checkValid(tmp)){

                    distance[nextRow][nowCol] = distance[nowRow][nowCol] + 1;
                    visited[nextRow][nowCol] = true;

                    Fish fish = new Fish(tmp, graph[nextRow][nowCol], distance[nextRow][nowCol]);
                    shark.setCanEat(fish);
                    queue.add(fish);
                }
            }

            for(int i=0;i<move.length;i++){

                int nextCol = nowCol + move[i];
                int [] tmp = {nowRow, nextCol};

                if(checkValid(tmp)){

                    distance[nowRow][nextCol] = distance[nowRow][nowCol] + 1;
                    visited[nowRow][nextCol] = true;
                    Fish fish = new Fish(tmp, graph[nowRow][nextCol], distance[nowRow][nextCol]);
                    shark.setCanEat(fish);
                    queue.add(fish);
                }
            }
        }
    }

    boolean checkValid(int[] next) {

        if (next[0] < 0 || next[0] >= size || next[1] < 0 || next[1] >= size) {
            return false;
        }

        if(shark.size < graph[next[0]][next[1]]){
            return false;
        }

        return !visited[next[0]][next[1]];
    }

    void init() {

        for (int i = 0; i < size; i++) {
            Arrays.fill(distance[i], 0);
            Arrays.fill(visited[i], false);
        }
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve16236 p = new solve16236(br);
        p.run();
        
        br.close();
    }
}