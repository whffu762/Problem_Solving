import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * MST 문제 크루스칼과 프림 모두 가능
 */
class Computer{

    int pc1;
    int pc2;
    int weight;

    Computer(int pc1, int pc2, int weight){
        this.pc1 = pc1;
        this.pc2 = pc2;
        this.weight = weight;
    }
}

class solve1922{

    int [] parent;
    PriorityQueue<Computer> queue = new PriorityQueue<>((after, before) ->{
        return after.weight - before.weight;
    });
    
    solve1922 (BufferedReader br) throws IOException{

        int numOfPC = Integer.parseInt(br.readLine());
        int numOfConnection = Integer.parseInt(br.readLine());

        parent = new int [numOfPC+1];
        for(int i=0;i<numOfPC+1;i++){
            parent[i] = i;
        }

        for(int i=0;i<numOfConnection;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.add(new Computer(Integer.parseInt(st.nextToken())
                                    ,Integer.parseInt(st.nextToken())
                                    ,Integer.parseInt(st.nextToken())));
        }

    }

    void run(){

        int weight = 0;

        while(!queue.isEmpty()){
            
            Computer now = queue.poll();
            // if(now.pc1 == now.pc2){
            //     continue;
            // }

            int pOf1 = getParent(now.pc1);
            int pOf2 = getParent(now.pc2);
            if(pOf1 == pOf2){
                continue;
            }

            weight += now.weight;
            setParent(pOf1, pOf2);
        }

        System.out.println(weight);
    }

    int getParent(int now){

        if(now == parent[now]){
            return now;
        }

        return parent[now] = getParent(parent[now]);
    }

    void setParent(int pc1, int pc2){

        if(pc1 < pc2){
            parent[pc2] = pc1;
        } else {
            parent[pc1] = pc2;
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve1922 p = new solve1922(br);
        p.run();

        br.close();
    }
}