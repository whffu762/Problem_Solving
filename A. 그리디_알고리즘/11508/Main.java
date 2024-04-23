import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;


class solve11508V2{
    
    List<Integer> products = new ArrayList<>();

    void run(BufferedReader br) throws IOException{
        int numOfInput = Integer.parseInt(br.readLine());

        for(int i=0;i<numOfInput;i++){
            products.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(products, (pre, pro)->{
            return pro - pre;
        });
        
        int count = 1;
        int sum = 0;
        for(int i=0;i<numOfInput;i++){
            if(count == 3){
                count = 1;               
            } else{
                sum += products.get(i);
                count++; 
            }
        }
        
        System.out.println(sum);
    }
}


class solve11508{

    List<Integer> list = new ArrayList<>();

    int solve1(){

        int sum = 0;
        for(int i=0;i<list.size();i++){
            if(i % 3 == 2){
                continue;
            }
            sum += list.get(i);
        }

        return sum;
    }

    int solve2(){        
        int sum = 0;
        while(!list.isEmpty()){
            if(list.size() > 2){
                for(int i=0;i<2;i++){
                    sum += list.remove(0);
                }
                list.remove(0);
            }
            else{
                sum += list.remove(0);
            }
        }

        return sum;
    }

    void run(BufferedReader br) throws IOException{
        int num = Integer.parseInt(br.readLine());

        for(int i=0;i<num;i++){
            list.add(Integer.parseInt(br.readLine()));
        }

        Collections.sort(list, (pre, pro)->{
            return pro - pre;
        });

        int sum = solve1();
        //int sum = solve2();

        System.out.println(sum);   
    }
}

class Main{
    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve11508 p = new solve11508();
        p.run(br);

        br.close();
    }
}