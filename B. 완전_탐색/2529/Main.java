import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * *****개어려움*****
 * Main2와 똑같이 DFS와 백트랙킹을 이용한 방식인데 코드가 조금 난잡함
 * 
 * 기본적인 흐름
 * 각 자리별로 0부터 9까지 순회하면서 조건을 만족하는지 확인하고 최종자리까지 채워지면
 * List<>에 저장하는 방식 모든 자리를 0부터 9 순서로 조건을 확인하기 때문에 List엔
 * 오름차순으로 숫자가 저장됨
 * 
 * 조건
 * - 중복되는 숫자가 존재하면 안 됨
 * - 0번째 자리는 부등호를 확인하지 않고 일단 넣는다
 * - 0번째 자리가 아니면 부등호와 이전 숫자를 이용해 만족하는지 확인해야 함
 * 
 * 
 * 재귀 호출의 종료 조건
 * 1. 마지막 자리까지 입력되면 종료
 * 2. 조건에 만족하는 숫자가 더 이상 없으면 종료
 * 
 * 
 * 재귀 호출의 입력
 * - 각 자리가 저장될 문자열
 * - 현재 조건을 확인 중인 자리
 * 
 * 재귀 호출의 출력
 * 뭘 출력하면 좀 어려워짐;; 백트랙킹을 써야해서..
 * 
 * 
 * 주의할 점
 * 문자열을 파라미터로 사용해야만 함
 * 각 부등호를 만족하는 자리의 숫자를 처음엔 배열에 저장했는데 그렇게 하면 안 됨
 * 배열은 주솟값을 이용해서 배열에 접근하기 때문에 만약 배열의 내부 값이 바뀌면 
 * 이미 List<>에 저장된 배열도 바뀌게 됨 만약 하고 싶으면 모든 경우마다 새로운 배열을
 * 생성해줘야 하는데 시간이 너무 낭비될 듯 코드도 많이 복잡해지고
 * 그래서 매 경우마다 새로운 인스턴스가 생성되는 문자열에 저장하는게 편함 
 * 이거 때문에 개고생한듯
 * 
 * 
 * 백트랙킹
 * DFS를 진행하다가 조건에 맞지 않으면 다음 이전 경로로 되돌아와서 다음 경로로
 * 마저 진행하는 방식인데 아마 재귀호출 내부에서 반복문을 쓸 수 있는 경우 사용될 수 있을 듯 함
 * 자세한건 노션에서
 * 
 * 
 * 동작 흐름
 * 1. 현재 depth를 확인해서 마지막 자리까지 가득 찼으면 List<>에 추가
 * 2. 현재 depth가 0인지 확인
 * 2-1. 0이면 depth 늘리고, nums에 i 추가해서 dfs 진행(i는 0부터 9까지 반복)
 * 2-2. 0이 아니면 nums에 1가 존재하는지 확인 (i는 0부터 9까지 반복)
 * 3. 존재하지 않을 때만 nums, i가 부등호에 만족하는지 확인
 * 4. 만족하면 dfs 진행
 * 
 * 글로 쓰면 되게 단순한데 반복문과 조건을 어떤 순서로 둬야할지 매우 헷갈림;;
 * 
 */

class solve2529{

    String [] signs;
    List<String> result = new ArrayList<>();

    solve2529(BufferedReader br) throws IOException{

        int numOfsign = Integer.parseInt(br.readLine());
        signs = new String [numOfsign];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfsign;i++){
            signs[i] = st.nextToken();
        }
    }

    void run(){

        StringBuilder sb = new StringBuilder();

        dfs(0, "");

        sb.append(result.get(result.size()-1)).append("\n");
        sb.append(result.get(0));

        System.out.println(sb);
    }

    void dfs(int depth, String nums){
        
        if(depth == signs.length + 1){
            result.add(nums);
            return;
        }
        
        if(depth == 0){

            for(int i=0;i<10;i++){

                dfs(depth + 1, nums + i);
            }
        } else {

            int previous = Character.getNumericValue(nums.charAt(depth-1));
            String sign = signs[depth-1];

            for(int i=0;i<10;i++){
                
                if(!checkContains(nums, i)){
                    if(checkBigger(previous, i, sign)){

                        dfs(depth + 1, nums + i);
                    }
                    
                    if(checkSmaller(previous, i, sign)){

                        dfs(depth + 1, nums + i);
                    }
                }
            }
        }
    }

    boolean checkContains(String nums, int input){

        if(nums.contains(String.valueOf(input))){
            return true;
        }

        return false;
    }

    boolean checkBigger(int previous, int now, String sign){

        if(sign.equals("<") && previous < now){
            return true;
        }

        return false;
    }

    boolean checkSmaller(int previous, int now, String sign){

        if(sign.equals(">") && previous > now){
            return true;
        }

        return false;
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2529 p = new solve2529(br);
        p.run();

        br.close();
    }
}