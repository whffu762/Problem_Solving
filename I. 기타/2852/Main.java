import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 점수가 났을 때 현재 점수의 상황마다 처리가 달라지기 때문에 귀찮음
 * 점수가 누가 더 크냐에 따라
 * - 어떤 팀에서 어떤 팀의 시간을 뺄건지
 * - 어떤 팀의 시간을 저장할건지
 * 구분해야 함
 * 
 * 귀찮으니까 그냥 0 부터 48분까지 for문 돌려서 각 초마다 이기고 있는 팀을 판단해서 더하는 방식을 이용함
 * 2880초기 때문에 1초 안에 무조건 가능함
 * 
 * 여기서 주목할 점은 문자열 포멧임 String.format()
 */

class solve2852{

    List<int []> record = new ArrayList<>();

    solve2852 (BufferedReader br) throws IOException{

        int numOfInput = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfInput;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            int team = Integer.parseInt(st.nextToken());
            int time = getTime(st.nextToken());
            
            record.add(new int [] { team, time });
        }
    }

    int getTime(String input){

        String [] tmp = input.split(":");
        
        return Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
    }

    String getTime(int input){

        int min = input / 60;
        int sec = input % 60;

        return String.format("%02d:%02d", min, sec);
    }

    int checkWinner(int [] score, int teamOfScore){

        if(teamOfScore == 1){
            score[0]++;
        } else {
            score[1]++;
        }

        if(score[0] > score[1]){
            return 1;
        } else if(score[0] < score[1]){
            return 2;
        }
        
        return 0;
    }

    void run(){

        int fullTime = 48 * 60;
        int winner = 0;
        int idx = 0;

        int [] score = new int [2];
        int [] time = new int [2]; 

        for(int i=0;i<fullTime;i++){

            if(i == record.get(idx)[1]){
                winner = checkWinner(score, record.get(idx)[0]);

                if(idx < record.size()-1){
                    idx++;
                }
            }

            if(winner == 1){
                time[0]++;
            }

            if(winner == 2){
                time[1]++;
            }
        }

        System.out.println(getTime(time[0]));
        System.out.println(getTime(time[1]));
    }
}

public class Main{

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2852 p = new solve2852(br);
        p.run();

        br.close();
    }
}