import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 햄버거를 기준으로 닿을 수 있는 거리에 있는 사람 중 더 앞에 있는 사람에게 먼저 할당되는 방식으로 진행
 */

class solve19941 {

    Character[] table;
    int distance;

    solve19941(BufferedReader br) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        table = new Character[size];
        distance = Integer.parseInt(st.nextToken());

        String input = br.readLine();
        for (int i = 0; i < input.length(); i++) {
            table[i] = input.charAt(i);
        }
    }


    public int check(int now){

        if (table[now] == 'H') {
            int start = (now - distance < 0) ? 0 : now - distance;
            int end = (now + distance >= table.length) ? table.length - 1 : now + distance;

            for (int j=start;j<=end;j++) {
                if(table[j] == 'P'){
                    table[j] = 'X';
                    return 1;
                }
            }
        }

        return 0;
    }

    public void run() {

        int count = 0;
        
        for (int i=0;i<table.length;i++) {
            count += check(i);
        }

        System.out.println(count);
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve19941 p = new solve19941(br);
        p.run();

        br.close();
    }
}