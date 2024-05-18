import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 등수는 now 보다 덩치가 큰 사람의 수 + 1
 * 
 * 정렬해서 해도 되는데 원본을 유지해야 하고 그 과정에서 원본과 정렬된 값을
 * 매핑할 방법이 필요하기 때문에 번거로움
 * 
 * 그래서 중복이 존재하는 순위를 매길 땐 특정 조건에 맞는 것의 갯수를 세는게 더 간편함
 *  
 */

class Person implements Comparable<Person>{

    int height;
    int weight;
    int rank = 1;

    Person(int height, int weight){

        this.height = height;
        this.weight = weight;
    }

    @Override
    public int compareTo(Person o) {

        if(this.height < o.height && this.weight < o.weight){
            return -1;
        } 

        return 1;
    }
}
class solve7586{

    List<Person> list = new ArrayList<>();

    solve7586 (BufferedReader br) throws IOException{

        int numOfInput = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfInput;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Person(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    void run(){

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<list.size();i++){

            Person now = list.get(i);
            for(int j=0;j<list.size();j++){

                Person target = list.get(j);
                if(now.compareTo(target) < 0){
                    now.rank++;
                }
            }

            sb.append(now.rank).append(" ");
        }

        System.out.println(sb);
    }
}

public class Main {

    public static void main(String [] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve7586 p = new solve7586(br);
        p.run();

        br.close();
    }
}