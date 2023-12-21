import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.List;

class solve2512{

    Integer numOflocal;
    List<Integer> budgets = new ArrayList<>();
    Integer totalBudget;
    Integer result = 0;

    void input(BufferedReader br) throws IOException{
        
        numOflocal = Integer.parseInt(br.readLine());
        StringTokenizer inputBudgets = new StringTokenizer(br.readLine());
        for(int i=0;i<numOflocal;i++){
            budgets.add(Integer.parseInt(inputBudgets.nextToken()));
        }
        totalBudget = Integer.parseInt(br.readLine());
    }

    void init(){
        result = totalBudget/numOflocal;
        totalBudget -= result * numOflocal;
    }

    void init2(){
        
        for(int i=0;i<budgets.size();i++){
            int tmp = budgets.get(i) - result;
            if(tmp < 0){
                totalBudget -= tmp;
            }
            budgets.set(i, tmp);
        }

        budgets = budgets.stream()
            .filter(budget -> budget > 0)
            .sorted()
            .collect(Collectors.toList());
    }

    void init3(){

        int sum = budgets.stream()
            .reduce(0, (a, b) -> a + b);

        if(sum > totalBudget){
            result += totalBudget / budgets.size();
        }
        else {
            result += budgets.get(budgets.size()-1);
        }
    }

    void run(BufferedReader br) throws IOException{
        
        input(br);
        init();
        init2();
        init3();

        System.out.println(result);
    }
}

public class Main {
    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve2512 p = new solve2512();
        p.run(br);

        br.close();
    }
}
