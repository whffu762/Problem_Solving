package pack2;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;


//Wrapper 클래스의 특징을 알 수 있는 문제였다.. 하하..
/*
 * Wrapper 클래스 중 Integer는 -127부터 127 까지는 == 연산자를 같은 값인지 비교할 수 있음
 * 근데 범위를 넘은 숫자는 == 연산자로 비교했을 때 false를 반환함 .equals()를 이용해 비교해야 함
 * 아니면 int 형 변수에 저장하면 (언박싱) == 으로 비교 가능
 * 
 * 아마 다른 클래스도 마찬가지겠지 그 부분을 유념하고 코드 짜야 함
 * 
 * 사칙연산이나 비교연산은 다 가능함 == 만 안됨
 */

class Processor{;
    int cnt = 0;
    List<List<Integer>> sides = new ArrayList<>();
    
    void classifier(List<Integer> side){
        //int [] tmp = side.stream().mapToInt(Integer::intValue).toArray();
        //List를 int 배열로 바꿔서 다루면 편할 수도 있음

        if(side.get(0).equals(side.get(1)) && side.get(1).equals(side.get(2))){
            System.out.println("Equilateral");
        }
        else if(checkTri(side)){
            System.out.println("Invalid");
        }
        else if(check(side)){
            System.out.println("Isosceles");
        }
        else {
            System.out.println("Scalene");
        }
    }

    boolean checkTri(List<Integer> side){
        int max = 0;
        int idx = 0;
        int sum = 0;
        
        for(int i=0;i<3;i++){
            if(max != Math.max(side.get(i), max)){
                max = Math.max(side.get(i), max);
                idx = i;
            }
        }

        for(int i=0;i<3;i++){
            if(i == idx){
                continue;
            }
            sum += side.get(i);
        }

        if(sum <= side.get(idx)){
            return true;
        }
        else{
            return false;
        }

    }
    
    boolean check(List<Integer> side){
        for(int i=0;i<2;i++){
            for(int j=i+1;j<3;j++){
                if(side.get(i).equals(side.get(j))){
                    return true;
                }
            }
        }

        return false;
    }

    void run(Scanner scan){
        while(true){
            Integer [] side = new Integer [3];
            
            for(int i=0;i<3;i++){
                side[i] = scan.nextInt();
            }

            if(side[0] == side[1] && side[1] == side[2] && side[0] == 0){
                break;
            }
            sides.add(cnt, Arrays.asList(side));
            cnt++;
            
        }
        for(int i=0;i<cnt;i++){   
            classifier(sides.get(i));

        }
    }
}

class Test{

    public static void main(String[] args){

        Scanner scan = new Scanner(System.in);
        Processor p = new Processor();
        p.run(scan);

        scan.close();
    }
}