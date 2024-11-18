import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 원상태에서 가장 낮은 층을 먼저 구하고
 * 그 층에서부터 N+1 층까지 구하는데 이전 층을 구할 때 썼던 불필요한 시간을 빼는 방식..
 */
class Main2 {

    static final int MAX = 256;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int time = Integer.MAX_VALUE;
        int height = 0;


        int[] heights = new int[MAX + 1];
        int mapSize = N * M;
        int blocks = 0;

        int minHeight = MAX;
        int maxHeight = 0;

        for (int i = 0; i < N; i++) {

            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){

                int h = Integer.parseInt(st.nextToken());
                heights[h]++;
                blocks += h;
                if (minHeight > h) minHeight = h;

            }
        }

        maxHeight = B + blocks < (mapSize << 8) ? (B + blocks) / mapSize : MAX;
        int t = (blocks - minHeight * mapSize) << 1;
        blocks = 0;

        time = t;
        height = minHeight;

        for (int h = minHeight + 1; h <= maxHeight; h++) {

            //N층 미만인 블럭의 갯수
            blocks += heights[h - 1];

            //지울 필요 없는 부분을 제거했던 시간 구하기
            t -= (mapSize - blocks) << 1;

            //N-1인 부분은 쌓아야 함
            t += blocks;

            if (time >= t) {
                time = t;
                height = h;
            }

        }

        System.out.print(time + " " + height);
    }
}