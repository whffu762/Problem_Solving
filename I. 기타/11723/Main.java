import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 비트마스크는 값의 유무를 1과 0으로 표현하는 방법임
 * 예를 들어 8은 1000으로 4가 존재함을 의미함 7은 111로 3, 2, 1이 존재함을 의미함
 * 이런 식으로 하나의 정수로 여러 개의 값이 존재하는지 확인할 수 있어서 메모리도 절약할 수 있고
 * 비트 연산이라 연산 속도도 빠름 다만 한 번 입력된 값이 몇 개 입력되는지 저장되지 않기 때문에 
 * 입력되는 값의 존재 유무를 확인할 때만 사용될 수 있음
 * 
 * group에 저장할 때 저장할 숫자만큼 1을 이동시킴 
 * 예를 들어 10을 저장하면 1 << 9를 이용해서 group엔 이진수 10 0000 0000가 십진수로 저장됨
 * 
 * group | input : 값이 추가될 수 있음
 * 111 | 1000 = 1111
 * 
 * group & input : 값이 존재하는지 확인할 수 있음 결과가 0이 아니면 존재함
 * 111 & 1000 = 0 (값이 존재하지 않음)
 * 111 & 100 = 100 (값이 존재함)
 * 
 * group & ~input : 값을 삭제할 수 있음
 * 111 & 011 = 011
 * 
 */

class solveBitMask{
    
    int group = 0;

    void add(int value){
        group = group | (1 << value);
    }

    void remove(int value){
        group = group & ~(1 << value);
    }

    int check(int value){
        if((group & (1 << value)) != 0){
            return 1;
        }
        return 0;
    }

    void toggle(int value){
        group = group ^ (1 << value);
    }

    void all(){
        group = 0;
        for(int i=0;i<20;i++){
            group += i;
        }
    }

    void empty(){
        group = 0;
    }

    void run(BufferedReader br) throws IOException{

        int numOfInput = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfInput;i++){
            st = new StringTokenizer(br.readLine());
            String command = st.nextToken();
            Integer value = null;
            if(st.hasMoreTokens()){
                value = Integer.parseInt(st.nextToken());
            }

            switch (command) {
                case "add":
                    add(value-1);
                    break;
            
                case "remove":
                    remove(value-1);
                    break;

                case "check" :
                    sb.append(check(value-1)).append("\n");
                    break;

                case "toggle":
                    toggle(value-1);
                    break;

                case "all":
                    all();
                    break;

                case "empty":
                    empty();
                    break;
            }
        }

        System.out.println(sb);
    }

}

public class Main{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solveBitMask p = new solveBitMask();
        p.run(br);

        br.close();
    }
}