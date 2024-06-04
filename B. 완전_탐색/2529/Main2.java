import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Main의 리팩토링 버전
 * 
 * 달라진 점
 * - visited[] 를 이용해 nums에 존재하는지 확인하는 대신 이전에 방문한 적 있는지 확인
 * dfs에 들어가지 전에 true로 하고 나온 후엔 다신 false로 바꿔서 다음 주기 때 다시 사용될 수 있도록 함
 * 
 * 
 * - 0인지 확인하는 부분과 부등호 확인을 나눴는데 그걸 하나의 조건으로 묶어둠
 * 0이 아닐 때만 부등호를 검사하니까..
 * 0이면 무조건 실행
 * 0이 아니면 checkSign()까지 만족해야 실행
 * 이런 식으로 구현
 * 
 * 
 * 조건을 하나로 묶음으로써 코드가 엄청 간단해짐
 * 원래 0일때와 부등호 별로 하나씩 해서 총 3개의 반복을 돌렸는데
 * 이젠 하나의 반복만으로 가능 그 반복 안에서 모든 조건을 검사할 수 있음
 * 
 */

class solve2529{

    int size;
    String [] signs;
    List<String> result = new ArrayList<>();
    boolean [] visited = new boolean[10];

    solve2529(BufferedReader br) throws IOException{

        size = Integer.parseInt(br.readLine()) + 1;
        signs = br.readLine().split(" ");
    }

    void run(){

        dfs(0, "");

        System.out.println(result.get(result.size()-1));
        System.out.println(result.get(0));        
    }

    void dfs(int depth, String nums){
        
        if(depth == size){
            result.add(nums);
            return;
        }

        for(int i=0;i<10;i++){

            if(visited[i]){
                continue;
            }

            if(depth != 0 && !checkSign(depth, nums, i)){
                continue;
            }

            visited[i] = true;
            dfs(depth + 1, nums + i);
            visited[i] = false;
        }
    }

    boolean checkSign(int depth, String nums, int now){

        char previous = nums.charAt(depth - 1);
        String sign = signs[depth - 1];

        if(sign.equals(">") && previous - '0' > now){
            return true;
        }

        if(sign.equals("<") && previous - '0' < now){
            return true;
        }

        return false;
    }
}

public class Main2 {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve2529 p = new solve2529(br);
        p.run();

        br.close();
    }
}