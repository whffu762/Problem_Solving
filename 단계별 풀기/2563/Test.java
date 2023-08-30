package pack2;

import java.util.Scanner;

/*
// 내꺼
class Processor2{
    int [][]paper;
    int width = 0;

    int getLeft(int [][] paper){

        int min = paper[0][0];

        for(int i=1;i<paper.length;i++){
            min = Math.min(min, paper[i][0]);
        }

        return min;
    }

    int getRight(int [][] paper){
        int max = paper[0][0] + 10;

        for(int i=1;i<paper.length;i++){
            max = Math.max(max, paper[i][0]+10);
        }

        return max;
    }

    
    int getdown(int [][] paper){

        int min = paper[0][1];

        for(int i=1;i<paper.length;i++){
            min = Math.min(min, paper[i][1]);
        }

        return min;
    }

    int getUp(int [][] paper){
        int max = paper[0][1] + 10;

        for(int i=1;i<paper.length;i++){
            max = Math.max(max, paper[i][1]+10);
        }

        return max;
    }

    void check(int x, int y){

        for(int i=0;i<paper.length;i++){
            int left = paper[i][0];
            int right = paper[i][0] + 10;
            int up = paper[i][1] + 10;
            int down = paper[i][1];

            if(left<=x && x<right && down<=y && y<up){
                System.out.println(i + " "+ x + " " + y);
                width++;
                break;
            }
        }
    }
    public void run(Scanner scan){
        int num_paper = scan.nextInt();
        paper = new int[num_paper][2];

        for(int i=0;i<num_paper;i++){
            for(int j=0;j<2;j++){
                paper[i][j] = scan.nextInt();
            }
        }

        int left = getLeft(paper);
        int right = getRight(paper);
        int up = getUp(paper);
        int down = getdown(paper);

        System.out.println(left + " " + right + " " + up + " " + down);

        for(int i=left;i<right;i++){
            for(int j=down;j<up;j++){
                check(i, j);
            }
        }

        System.out.println(width);
    }
}
 */

 class Processor{
    int total = 0;
    public void run(Scanner scan){
        int num_paper = scan.nextInt();
        int [][] paper = new int [101][101];
        for(int i=0;i<num_paper;i++){
            int x = scan.nextInt();
            int y = scan.nextInt();

            for(int j=x;j<x+10;j++){
                for(int k=y;k<y+10;k++){
                    if(paper[j][k] != 1){
                        paper[j][k] = 1;
                        total++;
                    }
                }
            }
        }

        System.out.println(total);
        
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