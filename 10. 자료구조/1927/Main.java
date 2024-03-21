import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 최소 힙에 관해선 정리 필요
 */
class solve1927{

    int [] heap;
    int now = 1;

    void inputHeap(int input){

        int indexOfNow = now++;
        heap[indexOfNow] = input;
        whenInput(indexOfNow);
    }

    void whenInput(int indexOfNow){

        int input = heap[indexOfNow];

        while(true){
        
            int indexOfParent = indexOfNow / 2;            
            int parent = heap[indexOfParent];

            if(input < parent){
                int tmp = input;
                heap[indexOfNow] = parent;
                heap[indexOfParent] = tmp;

                indexOfNow = indexOfParent;
            } else {
                break;
            }

            if(indexOfParent == 1){
                break;
            }
        }
    }

    int getMin(){

        if(now == 1){
            return 0;
        }
        
        int result = heap[1];
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
            
            int indexOfChild = getMinChild(indexOfLeft, indexOfRight);
            if(indexOfChild == -1){
                break;
            }

            int tmpChild = heap[indexOfChild];
            
            if(tmpChild < target){
                int tmp = tmpChild;
                heap[indexOfChild] = target;
                heap[indexOfNow] = tmp;
                
                indexOfNow = indexOfChild;
            } else {
                break;
            }
        }
    }

    int getMinChild(int indexOfLeft, int indexOfRight){

        int left = heap[indexOfLeft];
        int right = heap[indexOfRight];

        if(left == 0 && right == 0){
            return -1;
        } else if(left == 0 && right != 0){
            return indexOfRight;
        } else if (left != 0 && right == 0){
            return indexOfLeft;
        } else {
            int tmp = Math.min(left, right);
            return (tmp == left) ? indexOfLeft : indexOfRight;
        }
    }


    void run(BufferedReader br) throws IOException{
        
        int numOfInput = Integer.parseInt(br.readLine());
        heap = new int [numOfInput * 2 + 2];
        heap[0] = -1;

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
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1927 p = new solve1927();
        p.run(br);

        br.close();
    }
}