package pack2;
//백준 2566

import java.util.Scanner;

class App{
    private int [][] input = new int[9][9];
    private int max = -1;
    private int row = 0;
    private int col = 0;

    public void run(Scanner scan){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                input[i][j] = scan.nextInt();
                int tmp = max;
                max = Math.max(max, input[i][j]);   
                if(tmp != max){
                    row = i + 1;
                    col = j + 1;
                }
            }
        }

        System.out.println(max);
        System.out.print(row + " " + col);
    }
}

class Test{
    public static void main(String [] args){
        
        Scanner scan = new Scanner(System.in);

        App app = new App();
        
        app.run(scan);

        scan.close();
    }
}



