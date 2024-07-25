import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백트랙킹 이용
 *
 * 조건
 * - 자음이 2개 이상 모음이 1개 이상
 * - 사전 순으로 정렬
 * 
 * 조건 1
 * code의 길이가 목표치에 도달하면 checkValid() 메소드로 검사
 * 
 * 조건 2
 * 사전 순으로 정렬하기 위해 입력을 미리 정렬하고 for문으로 순회할 때 index부터 시작
 * index를 재귀 파라미터로 넘김으로써 시작 위치를 조절할 수 있고 결과적으로 중복도 방지하고 
 * 순회 전에 미리 사전 순으로 정렬했기 때문에 순서가 꼬이지도 않음
 * 
 * 
 * 주의할 점
 * 백트래깅에서 내가 관리해야 할 기준은 두 가지임
 * 1. 재귀의 깊이 : code의 길이
 * 2. 재귀 내부의 진행도 : 재귀 내부 for 문의 i 
 * 
 * 
 * 다른 백트랙킹과의 차이점
 * 여기서 for 문의 i를 index부터 시작함으로써 조건 2를 해결할 수 있음
 * 그에 따라 index는 종료 조건에 영향을 주지 않음 순회 조건에 영향을 주는 값임
 * 근데 다른 백트랙킹 문제에선 이 재귀 파라미터를 depth로 취급하는 경우가 잦음 이걸 잘 생각해야 함
 * 
 * 재귀 파라미터가 종료 조건에 영향을 주는가
 */

class solve1759{
    
    int sizeOfCode;
    int numOfChar;

    String [] chars;
    List<String> vowelList = Arrays.asList(new String [] {"a", "e", "i", "o", "u"});
    
    StringBuilder result = new StringBuilder();

    public solve1759(BufferedReader br) throws IOException{
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        sizeOfCode = Integer.parseInt(st.nextToken());
        numOfChar = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        chars = new String[numOfChar];
        for(int i=0;i<numOfChar;i++){
            chars[i] = st.nextToken();
        }

        Arrays.sort(chars);
    }

    void run() {

        backTracking(0, "");
        System.out.println(result);
    }

    void backTracking(int index, String code){

        if(code.length() == sizeOfCode){

            if(checkValid(code)){
                result.append(code).append("\n");
            }

            return;
        }

        for(int i=index;i<numOfChar;i++){

            backTracking(i + 1, code + chars[i]);
        }
    }

    boolean checkValid(String code){

        int consonant = 0;
        int vowel = 0;

        for(int i=0;i<code.length();i++){

            if(vowelList.contains(Character.toString(code.charAt(i)))){
                vowel++;
            } else {
                consonant++;
            }
        }

        if(0 < vowel && 1 < consonant){
            return true;
        }

        return false;
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1759 p = new solve1759(br);
        p.run();

        br.close();
    }
}