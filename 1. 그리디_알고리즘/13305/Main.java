import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class solve13305{

    int [] distance;
    int [] price_per_liter;
    int city_num;


    long notMine(){

        long price = price_per_liter[0];
        long result = 0;
        for(int i=0;i<city_num-1;i++){
            
            
            if(price > price_per_liter[i]){
                price = price_per_liter[i];
            }

            result += price * distance[i];
        }

        return result;
    }

    long usingPrice(){
        
        int now_loc = 0;
        long price = price_per_liter[0];
        long result = 0L;
        while(true){
            
            if(now_loc == city_num - 2){
                result += price * distance[now_loc];
                break;
            }

            result += price * distance[now_loc++];
            if(price_per_liter[now_loc] < price){
                price = price_per_liter[now_loc];
            }
        }

        return result;
    }

    long usingDistance(){
        //반례가 뭐가 있지..?
        int now_loc = 0;
        int tmp_loc = now_loc;
        long tmp_distance = distance[0];
        long result = 0L;

        while(true){

            if(tmp_loc == city_num - 2){
                result += tmp_distance * price_per_liter[now_loc];
                break;
            }
            
            if(price_per_liter[tmp_loc] > price_per_liter[tmp_loc+1]){  //현재가 더 비싸
                result += tmp_distance * price_per_liter[now_loc];
                tmp_distance = 0;
                now_loc = tmp_loc + 1;
            }

            tmp_distance += distance[++tmp_loc];   
        }
        return result;
    }

    void run(BufferedReader br) throws IOException{
        
        city_num = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        distance = new int [city_num - 1];
        for(int i=0;i<city_num-1;i++){
            distance[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        price_per_liter = new int [city_num];
        for(int i=0;i<city_num;i++){
            price_per_liter[i] = Integer.parseInt(st.nextToken());
        }

        long result = usingPrice();
        long result2 = usingDistance();
        long result3 = notMine();

        System.out.println(result);
        System.out.println(result2);
        System.out.println(result3);
    }
}

class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        solve13305 p = new solve13305();
        p.run(br);

        br.close();
    }
}
 