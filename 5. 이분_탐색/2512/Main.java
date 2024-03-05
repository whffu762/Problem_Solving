import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.List;
import java.util.Collections;

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
        Collections.sort(budgets);

        totalBudget = Integer.parseInt(br.readLine());
    }

    void init(){

        result = totalBudget/numOflocal;
        totalBudget -= result * numOflocal;

        for(int i=0;i<budgets.size();i++){
            int tmp = budgets.get(i) - result;
            budgets.set(i, tmp);
            if(tmp < 0){
                totalBudget -= tmp;
            }
        }

        budgets = budgets.stream()
            .filter(budget -> budget > 0)
            .sorted()
            .toList();
    }

    void init3(){

        int sum = budgets.stream()
            .reduce(0, (a, b) -> a + b);

        if(sum > totalBudget){
            result += totalBudget / budgets.size();
        } else {
            result += budgets.get(budgets.size()-1);
        }
    }

    int getSumOfLocal(){

        return budgets.stream()
            .reduce(0, (a, b) -> a + b);
    }

    void run(BufferedReader br) throws IOException{
        
        input(br);

        int sum = getSumOfLocal();
        if(sum < totalBudget){
            result += budgets.get(budgets.size() - 1);
        }
        else {
            init();
        }

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
