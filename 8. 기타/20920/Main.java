import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 시간은 1초고 입력 최댓값은 100000개 이므로 중첩 for를 쓰면 시간 초과에 걸릴 것 같음
 * 그래서 tim sort만 이용해서 풀어야 함
 * tim sort의 시간 복잡도는 n log n
 * 
 * 추가적으로 Map의 compute()와 computeIfAbsent() 메소드를 이용함
 * 입력된 key에 해당하는 값이 map에 있거나 없는 것에 대한 실행문을 간소화하는 메소드임
 * 
 */

class solve20920 {

    int numOfWords;
    int moreThan;
    Map<Integer, List<String>> words = new HashMap<>();

    void input(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        numOfWords = Integer.parseInt(st.nextToken());
        moreThan = Integer.parseInt(st.nextToken()) - 1;

        Map<String, Integer> tmpMap = new HashMap<>();
        for (int i = 0; i < numOfWords; i++) {
            String input = br.readLine();
            tmpMap.compute(input, (key, oldValue) -> (oldValue == null) ? 1 : oldValue + 1);
        }

        tmpMap.keySet().stream()
                .forEach(input -> {
                    int numOfNow = tmpMap.get(input);
                    words.computeIfAbsent(numOfNow, key -> new ArrayList<>()).add(input);
                });
    }

    void print() {

        StringBuilder sb = new StringBuilder();

        words.keySet().stream()
                .sorted((later, previous) -> previous - later)
                .forEach(num -> {
                    words.get(num).stream()
                            .sorted((later, previous) -> {
                                if (previous.length() == later.length()) {
                                    return later.compareTo(previous);
                                }
                                return previous.length() - later.length();
                            })
                            .forEach(word -> {
                                if(moreThan < word.length()){
                                    sb.append(word).append("\n");
                                }
                            });

                });

        System.out.println(sb);
    }

    void run(BufferedReader br) throws IOException {

        input(br);
        print();
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve20920 p = new solve20920();
        p.run(br);

        br.close();
    }
}