import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 부모 배열을 이용한 트리 풀이
 * 중요한 부분
 * 1. 루트 구하기
 * 2. Integer의 자동 언박싱 범위
 * 
 * 루트 구하기
 * 부모를 구하는 방법은 두 가지가 있음
 * 1. 입력이 부모 - 자식 이기 때문에 루트는 자식 쪽에 존재할 수 없음 이걸 카운트하면 됨
 * 2. 부모 배열로 저장시 0(초기값)이 저장되어 있는 인덱스가 루트임
 * 
 * Integer의 자동 언박싱 범위
 * Wrapper Wrapper 끼리 비교시 127 까지만 == 연산자로 비교됨 그보다 큰 값은 int 변수로 직접 꺼내던가
 * 아니면 .equals()로 비교해야 정상적으로 동작함
 * 
 */

class solve3584{

    int [] tree;
    int [] target = new int [2];
    int root;

    solve3584(BufferedReader br) throws IOException{

        int numOfNodes = Integer.parseInt(br.readLine());
        tree = new int [numOfNodes+1];

        for(int i=0;i<numOfNodes-1;i++){
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            tree[child] = parent;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<target.length;i++){
            target[i] = Integer.parseInt(st.nextToken());
        }
    }

    void run(){

        List<Integer> result1 = toRoot(target[0]);
        List<Integer> result2 = toRoot(target[1]);
        
        int result3 = 0;
        boolean flag = false;
        for(int i=0;i<result1.size();i++){

            //int tmp = result1.get(i);
            for(int j=0;j<result2.size();j++){
                //int tmp2 = result2.get(j);
                if(result1.get(i).equals(result2.get(j))){
                    result3 = result1.get(i);
                    flag = true;
                    break;
                }
            }

            if(flag){
                break;
            }
        }

        System.out.println(result3);
    }

    List<Integer> toRoot(int now){

        List<Integer> result = new ArrayList<>();

        while(now != 0){

            result.add(now);
            now = tree[now];
        }

        return result;
    }
}

public class Main {

    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());

        for(int i=0;i<size;i++){
            solve3584 p = new solve3584(br);
            p.run();
        }
        
        br.close();
    }
}