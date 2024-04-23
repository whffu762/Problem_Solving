import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//2828
// 지금 당장 최소한으로 움직이게끔
// screen은 필요가 없는데..

class solve2828 {

    void run(BufferedReader br) throws IOException {
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int screen = Integer.parseInt(st.nextToken());
        int basket_size = Integer.parseInt(st.nextToken());
        int apple_num = Integer.parseInt(br.readLine());

        int player_loc = 1;
        int player_end = player_loc + basket_size - 1;
        int total = 0;
        for (int i = 0; i < apple_num; i++) {
            int apple_loc = Integer.parseInt(br.readLine());
            int move = 0;
            while (true) {
                if (apple_loc >= player_loc && apple_loc <= player_end) {
                    total += move;
                    break;
                } else if(player_end < apple_loc){
                    player_loc++;
                } else if(apple_loc < player_loc){
                    player_loc--;
                }

                player_end = player_loc + basket_size - 1;
                move++;
            }
        }

        System.out.println(total);

    }
}

class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2828 p = new solve2828();
        p.run(br);

        br.close();

    }
}