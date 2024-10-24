import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 이걸 굳이 그리디기 보단 정렬 같은데 일단 분류는 그리디라 돼 있으니..
 * 그리디 문제의 특징인지는 모르겠는데 문제를 되게 헷갈리게 적어둠 
 * 점수로 설명했다가 입력은 순위로 받아서 되게 헷갈렸는데 간단히 설명하면
 * 
 * 두 개의 시험이 있는데 각 점수 순위를 입력받고 임의의 인원보다 두 순위가 모두 
 * 낮은 인원은 제외하는 것임 
 * 즉 뽑힌 사람은 모두 둘 중 하나의 시험이라도 다른 사람보다 높은 것임
 * 
 * 이걸 구하는 방법은 간단함
 * 1. 서류 점수 순위로 정렬
 * 2. 1등을 기준으로 확인
 * 3. 서류 점수에서 붙은 사람들로 다시 면접 점수로 정렬
 * 4. 1등을 기준으로 확인
 * 근데 이 방식으로 하면 시간 초과가 발생함 시간 복잡도 계산해보면 2억보다 한참 낮은데도
 * 안됨 그래서 질문 게시판에 올려놨고 답볌이 오면 추가하는걸로
 * -> 답변 왔음 : 1등을 기준으로 확인하는 코드에서 나는 List에서 remove 하는 방식으로 구현했는데
 * List에서 remove()는 이후의 값들을 앞으로 당겨오는 작업이 수행되야하므로 O(N^2)의 시간 복잡도가 생김
 * 오! 그럼 remove하지 않고 순회하면 해결되겠네~ ㅇㅇ 시간 초과는 뜨지 않지만 틀렸다고 나옴 ㅋㅋ
 * 
 * 
 * 그래서 되는 방법
 * 1. 서류 점수로 정렬
 * 2. 1등의 면접 순위로 최소 면접 순위 초기화
 * 3. 최소 면접 순위 > 현재 면접 순위를 만족할 때만 처리
 * - 합격 인원 ++
 * - 최소 면접 순위 = 현재 면접 순위 
 */
class solve1946 {
    
    PriorityQueue<int []> scores = new PriorityQueue<>((after, before) -> {
        return after[0] - before[0];
    });
    int numOfCase;
    solve1946 (BufferedReader br) throws IOException{

        numOfCase = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfCase;i++){
           
            int num = Integer.parseInt(br.readLine());
        
            for(int j=0;j<num;j++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                scores.add(new int [] {
                    Integer.parseInt(st.nextToken()), 
                    Integer.parseInt(st.nextToken())
                });
            }

            sb.append(run()).append("\n");
        }
        System.out.println(sb);
    }

    int run(){
        
        int result = 1;
        int min = scores.poll()[1];
        while(!scores.isEmpty()){

            int now = scores.poll()[1];
            if(now < min){
                min = now;
                result++;
            }
        }

        return result;
    }
}

public class Main {

    public static void main (String [] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1946 p = new solve1946(br);
    
        br.close();
    }
}