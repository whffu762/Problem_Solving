import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

/*
 * 
 * 1. .과 X를 구분해서 각 갯수를 셈
 * 2. .로 시작하는지 X로 시작하는지 구분
 * 3. X로 시작하면 짝수번째만 checkMino() .으로 시작하면 홀수만 checkMino()
 * 4. 중간에 부합하지 않는게 있으면 flag 고치고 -1 출력
 *
 * 근데 그냥 replaceAll()로 굉장히 간단하게 처리할 수 있음.. 
 * 1. XXXX 부터(큰 거 부터) 바꾸고 XX 바꿈
 * 2. 결과에 X 남아있는지 확인 
 * 
 * 내가 한 방식은 동적 프로그래밍으로 문제를 작은 단위로 쪼개서 처리했음
 * 근데 이걸 그리디 알고리즘으로 간단하게 해결할 수 있음 단순히 지금 당장
 * 가장 최적인 것을 먼저 해결하고 다음을 생각하는 방식
 * AAAA를 먼저 넣어두고 남은거에 BB 넣어보면 된다...
 * 현재 선택이 이후 선택에 영향을 끼칠지 고려하지 않음 지금 하는 최선의 선택이
 * 이후에도 최선임
 * 
 */
class solve1343 {
    
    List<Integer> list = new ArrayList<>();
    
    String checkMino(int cnt) {

        String a = "AAAA";
        String b = "BB";

        int s = cnt / 4;
        int r = cnt % 4;

        String result = "";
        switch (r) {
            case 0:
                for (int i = 0; i < s; i++) {
                    result += a;
                }
                break;

            case 1:
                break;

            case 2:
                for (int i = 0; i < s; i++) {
                    result += a;
                }
                result += b;
                break;

            case 3:
                break;
        }

        return result;
    }

    boolean checkStart(String target){
        
        if(target.charAt(0) == '.'){
            return false;
        }
        else{
            return true;
        }

    }

    void countElement(String target){        
        int cntX = 0;
        int cntD = 0;
        for (int i = 0; i < target.length(); i++) {
            if (target.charAt(i) == '.') {
                if(cntD != 0){
                    list.add(cntD);
                    cntD=0;
                }
                cntX++;
            }
            else{
                if(cntX != 0){
                    list.add(cntX);
                    cntX=0;
                }
                cntD++;
            }
        }

        if(cntD != 0){
            list.add(cntD);
        }
        if(cntX != 0){
            list.add(cntX);
        }
    }

    void run(Scanner scan) {

        String target = scan.nextLine();

        boolean flagS = checkStart(target);
        countElement(target);
        boolean flagN = false;

        StringBuilder sb = new StringBuilder();
        if(flagS){
            for(int i=0;i<list.size();i++){
                if(i % 2 == 0){
                    String tmp = checkMino(list.get(i));
                    if(tmp.equals("")){
                        flagN = true;
                        break;
                    }
                    sb.append(tmp);
                }
                else{
                    for(int j=0;j<list.get(i);j++){
                        sb.append(".");
                    }
                }
            }
        }
        else{
            for(int i=0;i<list.size();i++){
                if(i % 2 != 0){
                    String tmp = checkMino(list.get(i));
                    if(tmp.equals("")){
                        flagN = true;
                        break;
                    }
                    sb.append(tmp);
                }
                else{
                    for(int j=0;j<list.get(i);j++){
                        sb.append(".");
                    }
                }
            }
        }

        if(flagN){
            System.out.println("-1");
        }
        else{
            System.out.println(sb);
        }

    }


    void run2(Scanner scan){
    //이런 간단한 방법이..
        String target = scan.nextLine();

        String result = "";
        String a = "AAAA";
        String b = "BB";

        result = target.replaceAll("XXXX", a);
        result = result.replaceAll("XX", b);

        if(result.contains("X")){
            System.out.println(-1);
        }
        else{
            System.out.println(result);
        }
    }
}

class Main{
    
    public static void main(String [] args){
        Scanner scan = new Scanner(System.in);
        
        solve1343 p = new solve1343();
        p.run2(scan);

        scan.close();
    }
}