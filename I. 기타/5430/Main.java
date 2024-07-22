import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 5430
 * 문제는 별거 없음
 * 
 * 거꾸로 뒤집는 동작을 실제로 수행하는 것이 아닌 출력할 떄만 바꿔주는 방식으로 하면 됨
 * 그냥 링크드 리스트 구현 비스무리 한 방식
 * 
 * 근데 웃긴건 toString 메소드에서 문자열에 + 연산을 수행하면 시간 초과남
 * 반드시 StringBuilder로 문자열 합성해야 함
 * 뭐 이딴 문제가 다 있어
 */
class AC {

    String command;
    String [] ary;
    int start;
    int end;
    boolean flag = true;

    public AC(String command, String arySize, String input) {
        this.command = command;
        this.ary = input.substring(1, input.length()-1).split(",");
        this.start = 0;
        this.end = this.ary.length;
        
        if(arySize.equals("0")){
            this.end = 0;
        } 
    }


    String run() {

        for (int i = 0; i < command.length(); i++) {

            if (command.charAt(i) == 'R') {
                reverse();
            } else {
                delete();
            }
        }

        return toString();
    }

    void reverse() {

        if (flag) {
            int tmp = end - 1;
            end = start - 1;
            start = tmp;
        } else {
            int tmp = end + 1;
            end = start + 1;
            start = tmp;
        }

        flag = !flag;
    }

    void delete() {

        if (flag) {
            start += 1;
        } else {
            start -= 1;
        }
    }

    //이렇게 하면 + 연산으로 문자열 처리하면 오류남
    public String toStringError() {


        if ((flag && start > end) || (!flag && start < end)) {
            return "error";
        }

        if (start == end) {
            return "[]";
        }

        String result = "[" + ary[start];
        if(flag){
            for(int i=start+1;i<end;i++){
                result += "," + ary[i];
            }
        } else {
            for(int i=start-1;i>end;i--){
                result += "," + ary[i];
            }

        }
        result += "]";
        
        return result;
    }


    public String toString() {

        StringBuilder sb = new StringBuilder();

        if ((flag && start > end) || (!flag && start < end)) {
            return sb.append("error").toString();
        }

        if (start == end) {
            return sb.append("[]").toString();
        }

        sb.append("[").append(ary[start]);
        if (flag) {
            for (int i = start + 1; i < end; i++) {
                sb.append(",").append(ary[i]);
            }
        } else {
            for (int i = start - 1; i > end; i--) {
                sb.append(",").append(ary[i]);
            }

        }
        sb.append("]");

        return sb.toString();
    }
}

public class Main {


    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numOfCase = Integer.parseInt(br.readLine());

        for (int i = 0; i < numOfCase; i++) {
            String result = new AC(br.readLine(), br.readLine(), br.readLine()).run();
            sb.append(result).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}