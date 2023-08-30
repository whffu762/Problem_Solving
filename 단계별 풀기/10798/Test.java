package pack2;
//백준 10798
//방법이 두 가지
import java.util.Scanner;

//char 2차 배열로 저장
class App{
    private char [][] input = new char[5][];
    private int max = 0;


    public void printAry(int index){
        for(int i=0;i<5;i++){
            if(input[i].length > index){
                System.out.print(input[i][index]);
            }
        }
    }

    public void run(Scanner scan){

        for(int i=0;i<5;i++){
            String tmp = scan.next();
            input[i] = new char[tmp.length()];
            max = Math.max(max, tmp.length());
            
            for(int j=0;j<tmp.length();j++){
                input[i][j] = tmp.charAt(j);
            }

        }

        for(int i=0;i<max;i++){
            printAry(i);
        }
    }
}

//문자열 배열로 저장 - 수치상 성능, 코드 길이가 이게 더 좋은 듯?
class App2{
    private String [] input = new String[5];
    private int max = 0;


    public void printAry(int index){
        for(int i=0;i<5;i++){
            if(input[i].length() > index){
                System.out.print(input[i].charAt(index));
            }
        }
    }

    public void run(Scanner scan){

        for(int i=0;i<5;i++){
            input[i] = scan.next();
            max = Math.max(max, input[i].length());
        }
        
        for(int i=0;i<max;i++){
            printAry(i);
        }
    }
}

class Test{
    public static void main(String [] args){
        
        Scanner scan = new Scanner(System.in);

        App app = new App();
        App2 app2 = new App2();
        
        app.run(scan);

        scan.close();
    }
}



