import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.Set;
import java.util.HashSet;

/*
 * 특정 값의 저장여부만 확인하면 됨 
 * 예를 들어 5라는 값이 몇개 저장되었는지는 신경쓰지 않음 저장됐냐 되지않았냐만 확인하면 되기 때문에 Set을 이용
 * ArrayList로 해도 기능은 하지만 448MB로 제한되어 있어서 메모리 초과 뜸
 * Set으로 해야 간신히 315MB 나옴
 * 
 * 이 방법 말고 비트마스크를 사용하는 방법도 있음
 */

class solve11723 {

    Set<Integer> group = new HashSet<>();

    void add(int value){
        if(!group.contains(value)){
            group.add(value);
        }
    }

    void remove(int value){
        if(group.contains(value)){
            group.remove(value);
        }
    }

    int check(int value){
        if(group.contains(value)){
            return 1;
        }

        return 0;
    }

    void toggle(int value){
        if(group.contains(value)){
            group.remove(value);
        } else{
            group.add(value);
        }
    }

    void all(){
        for(int i=1;i<21;i++){
            group.add(i);
        }
    }

    void empty(){
        group.clear();
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
                    add(value);
                    break;
            
                case "remove":
                    remove(value);
                    break;

                case "check" :
                    sb.append(check(value)).append("\n");
                    break;

                case "toggle":
                    toggle(value);
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

class Main2 {
    public static void main (String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11723 p = new solve11723();
        p.run(br);

        br.close();
    }
}