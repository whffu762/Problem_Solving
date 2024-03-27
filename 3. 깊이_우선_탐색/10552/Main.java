import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 재귀 자체는 복잡할건 없음 다만 주의해야 할 점이 있음 반복문을 돌리면 무조건 시간 초과가 발생함
 * 하지만 현재 채널을 싫어하는 사람이 없는지를 확인하려면 반복을 사용할 수 밖에 없음 그래서 문제에서 조건을 하나 더 제시함
 * "싫어하는 사람이 여럿이면 무조건 젊은 사람이 움직인다"
 * 
 * 싫어하는 사람을 전부 저장할 필요 없이 가장 젊은 사람 한 명씩만 저장하면 됨 
 * 이 덕분에 Map을 통해 싫어하는 사람의 존재 여부를 한 번에 확인할 수 있음
 * 근데 Map으로 하면 왜 인지 모르겠는데 메모리 초과가 발생함; 그래서 배열을 이용함
 * index는 싫어하는 채널이고 그 index의 value는 싫어하는 사람이 좋아하는 채널임
 * 
 * 같은 알고리즘을 Map<>으로 구현하면 메모리 초과가 발생함 왜??????
 */
/*
void checkHateChannel(int nowChannel) {
    Map<Integer, Integer> seniors = new HashMap<>();
    int start;
    int count = 0;

    if (count == 0) {
        start = nowChannel;
    } else if (start == nowChannel) {
        count = -1;
        return;
    }

    Integer likeOfSenior = seniors.get(nowChannel);
    if (likeOfSenior != null) {
        count++;
        checkHateChannel(likeOfSenior);
    }
} 
*/

class solve10552 {

    int[] seniors;
    boolean[] visited;
    int start;
    int count = 0;

    void checkHateChannel(int nowChannel) {

        if (visited[nowChannel]) {
            count = -1;
            return;
        }

        if (seniors[nowChannel] != 0) {
            count++;
            visited[nowChannel] = true;
            checkHateChannel(seniors[nowChannel]);
        }
    }

    public void run(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfSeniors = Integer.parseInt(st.nextToken());
        int numOfChannels = Integer.parseInt(st.nextToken());
        int nowChannel = Integer.parseInt(st.nextToken());

        seniors = new int[numOfChannels + 1];
        visited = new boolean[numOfChannels + 1];

        for (int i = 0; i < numOfSeniors; i++) {

            st = new StringTokenizer(br.readLine());
            int like = Integer.parseInt(st.nextToken());
            int hate = Integer.parseInt(st.nextToken());

            if (seniors[hate] == 0) {
                seniors[hate] = like;
            }
        }

        checkHateChannel(nowChannel);
        System.out.println(count);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve10552 p = new solve10552();
        p.run(br);

        br.close();
    }
}