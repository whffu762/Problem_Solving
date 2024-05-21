import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

/*
 * 유지해야 할 데이터
 * 1. 각 팀당 선수의 명수
 * 2. 각 팀 당 4등까지의 합과 5등의 순위
 * 
 * 여기서 알게된 새로운 방식은 Map을 정렬하는 방식임
 * keySet을 List로 바꿔주고 Comparator<>에선 key를 통해 value에 접근해서 그 value의 내용을 비교하면 됨
 * getFirst() 메소드 참고
 * 
 * 알고 보면 단순하지만 생각을 못 했네..
 */

class Team implements Comparable<Team>{

    int numOfPlayer = 0;
    List<Integer> scoreOfFour = new ArrayList<>();
    int fifth = 0;

    int getScore(){

        if(this.numOfPlayer != 6){
            return Integer.MAX_VALUE;
        }

        int sum = 0;
        for(int i=0;i<scoreOfFour.size();i++){
            sum += scoreOfFour.get(i);
        }

        return sum;
    }

    @Override
    public int compareTo(Team o) {

        if(this.getScore() == o.getScore()){
            return this.fifth - o.fifth;
        }

        return this.getScore() - o.getScore();
    }
}


class solve9017{
    
    int [] rank;
    Map<Integer, Team> teams= new HashMap<>();

    solve9017(BufferedReader br) throws IOException{

        int numOfPlayer = Integer.parseInt(br.readLine());
        rank = new int [numOfPlayer];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<numOfPlayer;i++){

            rank[i] = Integer.parseInt(st.nextToken());
            teams.computeIfAbsent(rank[i], value -> new Team()).numOfPlayer++;
        }
    }

    //선택 정렬과 비슷하게 가장 작은 것을 하나씩 비교하는 방식
    int getFirst2(){

        Team winner = teams.get(rank[0]);
        int winnerId = rank[0];

        for(int teamId : teams.keySet()){

            int compare = winner.compareTo(teams.get(teamId));
            if(compare > 0 ){
                winner = teams.get(teamId);
                winnerId = teamId;
            }
        }

        return winnerId;
    }

    //아예 정렬을 해버리는 방식
    int getFirst(){

        List<Integer> keys = new ArrayList<>(teams.keySet());
        Collections.sort(keys, (after, before) -> {

            Team a = teams.get(after);
            Team b = teams.get(before);

            if(a.compareTo(b) == 0){
                return a.fifth - b.fifth;
            }

            return a.compareTo(b);
        });

        return keys.get(0);
    }

    int run(){

        int score = 1;
        for(int i=0;i<rank.length;i++){

            if(teams.get(rank[i]).numOfPlayer != 6){
                continue;
            }

            Team now = teams.get(rank[i]);

            List<Integer> teamScore = now.scoreOfFour;

            if(teamScore.size() < 4){
                teamScore.add(score);
            } else if(teamScore.size() == 4 && now.fifth == 0){
                now.fifth = score;
            }
            
            score++;
        }


        int winnerId = getFirst();
        //int winnerId = getFirst2();

        return winnerId;
    }
}



public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfCase = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<numOfCase;i++){

            solve9017 p = new solve9017(br);
            sb.append(p.run()).append("\n");
        }

        System.out.println(sb);

        br.close();
    }
}