import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;
import java.util.Map;

/*
 * 내가 구현한 건 따로 순서까지 유지했지만 원래 값이 같으면 입력된 순서대로 출력되는 듯
 * 입력 순서를 유지하기 위해 LinkedHashMap을 사용해야 함 원래는 keySet()의 순서가 유지되지 않는데
 * Linked가 붙어있으면 유지됨
 */
class solve2910{

    Map<String, Integer> messages = new LinkedHashMap<>();

    void run(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfInput = Integer.parseInt(st.nextToken());
        int max = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        
        for(int i=0;i<numOfInput;i++){
            String content = st.nextToken();
            messages.compute(content, (key, oldValue) -> (oldValue == null) ? 1 : oldValue + 1);
        }

        messages.keySet().stream()
                .sorted((after, before) -> {
                    return messages.get(before) - messages.get(after);
                })
                .forEach(key -> {

                    for(int i=0;i<messages.get(key);i++){
                        System.out.print(key + " ");
                    }
                });
    }
}

public class Main2 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2910 p = new solve2910();
        p.run(br);

        br.close();
    }
}