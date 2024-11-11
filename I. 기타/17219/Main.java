import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/*
 * HashMap 쓰기 이게 끝임
 */
public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int numOfSites = Integer.parseInt(st.nextToken());
        int numOfReq = Integer.parseInt(st.nextToken());

        HashMap<String, String> passwords = new HashMap<>();

        for(int i=0;i<numOfSites;i++){

            st = new StringTokenizer(br.readLine());
            passwords.put(st.nextToken(), st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfReq;i++){

            sb.append(passwords.get(br.readLine())).append("\n");
        }
        System.out.println(sb);

        br.close();
    }
}