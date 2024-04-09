import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 최소 힙
 * 데이터를 저장할 때 가장 작은 값이 루트에 위치하도록 정렬해서 저장
 * 
 * 조건
 * 1. 완전 이진 트리여야 함
 * 2. 부모의 노드는 자식의 노드보다 작아야 함
 * 3. 형제 노드 간에는 대소비교가 일어나지 않음
 * 
 * 인덱스 규칙
 * 1. 루트가 1부터 시작
 * 2. 부모 노드의 인덱스 = 자식 노드 인덱스 / 2
 * 3. 왼쪽 자식 인덱스 = 부모 노드 인덱스 * 2
 * 4. 오른쪽 자식 인덱스 = 부모 노드 인게스 * 2 + 1
 * 
 * 
 * 삽입 시 동작
 * 1. 우선 가장 마지막 인덱스에 삽입
 * 2. 부모 노드와 대소비교
 * 2-1. 부모 노드보다 작으면 교환
 * 2-2. 부모 노드보다 크면 종료
 * 3. 현재 위치가 루트 일때까지 반복
 * 
 * 
 * 삭제 시 동작
 * 1. 마지막 인덱스의 값을 루트에 삽입
 * 
 * 2. 왼쪽 인덱스가 유효한지 확인
 * 2-1. 왼쪽 인데스가 배열 밖에 있으면 종료
 * 
 * 3. 자식 노드 간 대소 비교
 * 3-1. 오른쪽 자식의 인덱스 > 배열의 크기 : 왼쪽 인덱스 리턴
 * 3-2. 오른쪽 자식의 인덱스 < 배열의 크기 : 대소 비교후 더 작은 값 리턴
 * 
 * 4. 자식 노드와 대소비교
 * 4-1. 자식 노드보다 작으면 종료
 * 4-2. 자식 노드보다 크면 교환
 * 
 * 5. 현 위치의 왼쪽 자식이 배열 밖에 있을 때까지 반복
 */

class solve1927{

    int [] heap;
    int now = 1;    //현재 위치(값이 입력될 인덱스)

    void run(BufferedReader br) throws IOException{
        
        int numOfInput = Integer.parseInt(br.readLine());

        heap = new int [numOfInput + 1];    //입력 횟수 + 1(최대 입력 횟수만큼만 초기화)
        heap[0] = -1;   //루트 인덱스를 1부터 시작하기 위해 0은 무시

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfInput;i++){

            int input = Integer.parseInt(br.readLine());
            if(input == 0){
                sb.append(getMin()).append("\n");
            } else {
                inputHeap(input);
            }
        }
   
        System.out.println(sb);
    }

    //Heap에 값 입력
    void inputHeap(int input){

        int indexOfNow = now++;     //현 위치를 저장하고 1 증가
        heap[indexOfNow] = input;
        whenInput(indexOfNow);
    }

    void whenInput(int indexOfNow){

        int input = heap[indexOfNow];

        //현 위치가 루트면 종료
        while(indexOfNow > 1){
            
            int indexOfParent = indexOfNow / 2;            
            int parent = heap[indexOfParent];

            if(input < parent){
                int tmp = input;
                heap[indexOfNow] = parent;
                heap[indexOfParent] = tmp;

                indexOfNow = indexOfParent;     //부모와 바뀔 경우 현 위치도 변경
            } else {
                break;
            }
        }
    }

    
    int getMin(){

        //heap이 비어있는 경우
        if(now == 1){
            return 0;
        }
        
        int result = heap[1];
        
        //마지막 인덱스의 값을 루트로 이동시키고 0으로 초기화
        int indexOfNow = --now;     
        heap[1] = heap[indexOfNow];
        heap[indexOfNow] = 0;

        whenRemove();

        return result;
    }

    void whenRemove(){

        int indexOfNow = 1;
        int target = heap[indexOfNow];

        while(true){
            
            int indexOfLeft = indexOfNow * 2;
            int indexOfRight = indexOfNow * 2 + 1;

            /*
             * 왼쪽 자식이 오른쪽 자식보다 항상 인덱스가 앞서기 때문에 
             * 왼쪽 자식이 없으면 오른쪽도 없음
             */
            //왼쪽 자식이 없으면 종료
            if(indexOfLeft >= now){
                break;
            }
            
            //왼쪽 자식과 오른쪽 자식 중 더 작은 값 구하기
            int indexOfChild = getMinChild(indexOfLeft, indexOfRight);
            int child = heap[indexOfChild];
            
            if(child < target){
                int tmp = child;
                heap[indexOfChild] = target;
                heap[indexOfNow] = tmp;
                
                indexOfNow = indexOfChild;
            } else {
                break;
            }
        }
    }

    int getMinChild(int indexOfLeft, int indexOfRight){

        //왼쪽 자식만 존재하는 경우 왼쪽 자식 반환
        if(indexOfRight >= now){
            return indexOfLeft;
        }

        //오른쪽 자식도 있는 경우 대소 비교
        return (heap[indexOfLeft] < heap[indexOfRight]) ? indexOfLeft : indexOfRight;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1927 p = new solve1927();
        p.run(br);

        br.close();
    }
}