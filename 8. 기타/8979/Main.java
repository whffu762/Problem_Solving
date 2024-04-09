import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 정렬하고 공동 순위를 다루기만 하면 됨
 * 
 * 필요한 것 
 * 1. 이 전과 같은지 확인 후 같으면 이전 순위와 같은 순위를 부여
 * 2. 같은 순위를 가진 국가의 갯수 카운트해서 그 다음 순위에게 부여
 */

class Nation{
    
    int index;
    int gold;
    int silver;
    int bronze;
    int rank;

    Nation(int index, int gold, int silver, int bronze){
        this.index = index;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
    }

    boolean equals(Nation previous){

        if(this.gold == previous.gold && this.silver == previous.silver && this.bronze == previous.bronze){
            return true;
        }

        return false;
    }   
}


class solve8979{

    Nation [] medals;
    int targetNation;

    solve8979(BufferedReader br) throws IOException{
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        int numOfNations = Integer.parseInt(st.nextToken());
        targetNation = Integer.parseInt(st.nextToken());

        medals = new Nation[numOfNations];

        for(int i=0;i<numOfNations;i++){
            st = new StringTokenizer(br.readLine());
            medals[i] = new Nation(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    void run(){


        Arrays.sort(medals, (after, before)->{

            if(after.gold == before.gold){

                if(after.silver == before.silver){
                    return before.bronze - after.bronze;
                }
                return before.silver - after.silver;
            }
            return before.gold - after.gold;
        });

        medals[0].rank = 1;
        int numOfSameRank = 1;
        for(int i=1;i<medals.length;i++){
            
            if(medals[i].equals(medals[i-1])){
                medals[i].rank = medals[i-1].rank;
                numOfSameRank++;
            } else {
                medals[i].rank = medals[i-1].rank+numOfSameRank;
                numOfSameRank = 1;
            }
        }

        for(int i=0;i<medals.length;i++){

            if(medals[i].index == targetNation){
                System.out.println(medals[i].rank);
                break;
            }
        }
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve8979 p = new solve8979(br);
        p.run();

        br.close();
    }
}