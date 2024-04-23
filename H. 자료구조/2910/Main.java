import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;

/*
 * 정렬 기준
 * 1. 입력된 갯수
 * 2. 입력된 순서
 * 
 * 저장될 내용
 * - 값
 * - 입력 횟수
 * - 입력 순서
 * 
 * 저장될 내용을 저장하고 정렬 기준대로 정렬하면 됨
 * 다만 갯수는 많을 수록 먼저 나와야 하니 내림차순
 * 순서는 더 빠를 수록 먼저 나와야 하니 오름차순으로 정렬
 * 
 * 이거 말고 LinkedList를 이용하는 방법도 있네..?
 */
class Message{

    String content;
    int numOfContent;
    int sequence;

    Message(String content, int numOfContent, int sequence){
        this.content = content;
        this.numOfContent = numOfContent;
        this.sequence = sequence;
    }

    void addNumOfContent(){
        this.numOfContent++;
    }

    boolean compare(String compareString){

        if(content.equals(compareString)){
            return true;
        } else {
            return false;
        }
    }

}

class solve2910{

    List<Message> messages = new ArrayList<>();
    
    void run(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfInput = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());
        

        st = new StringTokenizer(br.readLine());
        int count = 0;
        for(int i=0;i<numOfInput;i++){
            String content = st.nextToken();
            count = checkExist(content, count);
        }

        messages.stream()
                .sorted((after, before) -> {

                    if(after.numOfContent == before.numOfContent){
                        return after.sequence - before.sequence; 
                    }
                    return before.numOfContent - after.numOfContent;
                })
                .forEach(message -> {

                    for(int i=0;i<message.numOfContent;i++){
                        System.out.print(message.content + " ");
                    }
                });
    }

    int checkExist(String content, int count){


        for(int i=0;i<messages.size();i++){
            if(messages.get(i).compare(content)){
                messages.get(i).addNumOfContent();
                return count;
            }
        }

        messages.add(new Message(content, 1, ++count));

        return count;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2910 p = new solve2910();
        p.run(br);

        br.close();
    }
}