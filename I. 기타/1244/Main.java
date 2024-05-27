import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 남자 : 배수만 바꿈
 * 여자 : 본인 기준으로 양 사이드가 같은 것까지만 바꿈
 * 
 * 주의 사항
 * 출력이 20 넘어가면 20개씩 출력해야 함
 * 
 */
class solve1244{

    int [] switches;
    List<int []> students = new ArrayList<>();

    solve1244(BufferedReader br) throws IOException{

        int nOfSwitches = Integer.parseInt(br.readLine());
        switches = new int[nOfSwitches+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<nOfSwitches+1;i++){
            switches[i] = Integer.parseInt(st.nextToken());
        }

        int nOfStudents = Integer.parseInt(br.readLine());
        for(int i=0;i<nOfStudents;i++){
            st = new StringTokenizer(br.readLine());

            students.add(new int [] {
                    Integer.parseInt(st.nextToken())
                    , Integer.parseInt(st.nextToken())
            });
        }
    }

    void boy(int input){

        for(int i=input;i<switches.length;i+=input){
            change(i);
        }
    }

    void girl(int input){

        change(input);

        int left = input - 1;
        int right = input + 1;

        while(left > 0 && right < switches.length){

            if(switches[left] == switches[right]){
                change(left);
                change(right);
            } else {
                break;
            }

            left--;
            right++;
        }
    }

    void change(int input){
        switches[input] = (switches[input] == 0) ? 1 : 0;
    }

    void run(){

        for(int i=0;i<students.size();i++){
            
            int [] now = students.get(i);
            if(now[0] == 1){
                boy(now[1]);
            } else {
                girl(now[1]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1;i<switches.length;i++){

            sb.append(switches[i]).append(" ");
            if(i % 20 == 0){
                sb.append("\n");
            }
        }

        System.out.print(sb);
    }
}


public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1244 p = new solve1244(br);
        p.run();

        br.close();
    }
}