import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

/*
 * 확률 계산은 평범하게 하면 되는데 double 형 연산을 할 때 BigDecimal을 이용해야만 함
 * double 나눗셈 할 때 정수와 다르게 정확하게 연산이 되지 않기 때문에
 * 소숫점 아래가 많아질수록 값이 부정확해짐 이를 해결하기 위한게 BigDecimal 
 */
class solve1344{

    int maxGoal = 18;
    int [] goals = { 0, 1, 4, 6, 8, 9, 10, 12, 14, 15, 16, 18 };

    BigDecimal calculateNotPrimeProb(double input){
    
        BigDecimal prob = new BigDecimal(input / (double)100);
        BigDecimal oppositeProb = BigDecimal.ONE.subtract(prob);

        BigDecimal result = BigDecimal.ZERO;

        for(int i=0;i<goals.length;i++){

            int numOfGoals = goals[i];
            BigDecimal probOfGoal = prob.pow(numOfGoals); 
            BigDecimal probOfNotGoal = oppositeProb.pow(maxGoal - numOfGoals);

            long numerateor = 1;
            int start1 = maxGoal;
            long denominator = 1;
            int start2 = numOfGoals;
            for(int j=0;j<numOfGoals;j++){
                numerateor *= start1--;
                denominator *= start2--;
            }

            long numOfCases = numerateor / denominator;
            
            result = result.add(
                probOfGoal
                .multiply(probOfNotGoal)
                .multiply(BigDecimal.valueOf(numOfCases))
            );
        }

        return result;
    }

    
    void run(BufferedReader br) throws IOException{

        int inputA = Integer.parseInt(br.readLine());
        int inputB = Integer.parseInt(br.readLine());

        BigDecimal result = BigDecimal.ONE.subtract(
            calculateNotPrimeProb(inputA)
            .multiply(calculateNotPrimeProb(inputB))
        );

        System.out.println(result.setScale(16, RoundingMode.HALF_UP));
    }

}

class Main{

    public static void main(String [] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve1344 p = new solve1344();
        p.run(br);

        br.close();
    }
}