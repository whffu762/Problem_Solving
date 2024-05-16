import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.HashSet;
import java.util.Set;

/*
 * 최대 가짓수가 30000(테이블에 올라간 초밥의 최대 갯수) * 3000(최대 초밥의 가짓수) 이기 때문에 완전 탐색 비슷학 돌려도 1초 안에 가능함
 * 그래서 단순하게 i부터 i+canEat까지의 초밥을 Set에 저장 후 원소의 갯수를 비교하는 방식으로 구현함
 * 회전 초밥이기 때문에 나머지 연산을 이용해서 순환해서 접근이 가능하도록 함
 * 쿠폰으로 먹을 수 있는 초밥이 있기 때문에 항상 추가해저야 함
 * 
 * 코드가 매우 간단해서 직관적임 통과는 됨
 * 근데 이러면 시간이 1700ms 정도 걸림 다른 사람 코드 보면 300 언저리인데 반해 많이 느림 메모리도 많이 씀
 * 
 * 또한 sortOfSushi 변수를 아예 활용하지 않기 때문에 좀 찝찝함
 * 
 */
class solve2531{

    int [] table;
    int canEat;
    int coupon;
    Set<Integer> eat = new HashSet<>();

    solve2531(BufferedReader br) throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        int onTheTable = Integer.parseInt(st.nextToken());
        //int sortOfSushi = Integer.parseInt(st.nextToken());
        canEat = Integer.parseInt(st.nextToken());
        coupon = Integer.parseInt(st.nextToken());

        table = new int [onTheTable];
        for(int i=0;i<onTheTable;i++){
            table[i] = Integer.parseInt(br.readLine());
        }
    }

    void run(){
        
        int result = 0;

        for(int i=0;i<table.length;i++){

            eat.add(coupon);
            for(int j=i;j<i+canEat;j++){
                eat.add(table[j % table.length]);
            }

            result = Math.max(result, eat.size());
            eat.clear();
        }

        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2531 p = new solve2531(br);
        p.run();

        br.close();
    }
}