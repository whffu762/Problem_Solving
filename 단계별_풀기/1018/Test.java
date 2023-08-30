package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;


//2231
//정수 입력 후에 문자열 입력할 때 주의해야 함
//엔터 값이 버퍼에 남아있기 때문에 입력 하나가 무시됨
//이건 되게 고전인데 이걸 까먹으면 어떡하냐;;
//비교 대상을 만들어두고 비교하는게 있고 하나하나 칠해가는게 있음

//1. 비교 대상을 만들어서 비교..내꺼
class Processor{
    
    String [] black;
    String [] white;
    String [] board;

    Processor(){
        
        black = new String [8];
        white = new String [8];
        String b = "BWBWBWBW";
        String w = "WBWBWBWB";

        for(int i=0;i<8;i++){
            if(i % 2 == 0){
                black[i] = b;
                white[i] = w;
            }
            else{
                black[i] = w;
                white[i] = b;
            }
        }
    }

    int check(int left, int right, int up, int down, String [] ex){
        int cnt = 0;
        int k = 0, l = 0;
        for(int i=up;i<down;i++){
            l = 0;
            for(int j=left;j<right;j++){
                
                if(board[i].charAt(j) != ex[k].charAt(l)){
                    cnt++;
                }
                l++;
            }
            k++;
        }

        return cnt;
    }


    void run(Scanner scan){
        
        int row = scan.nextInt();
        int col = scan.nextInt();
        scan.nextLine();

        board = new String [row];

        for(int i=0;i<row;i++){
            board[i] = scan.nextLine();
        }
        

        int min = row * col;

        for(int i=0;i<row-8+1;i++){
            
            for(int j=0;j<col-8+1;j++){
                int tmp1 = check(j, j+8, i, i+8, black);
                int tmp2 = check(j, j+8, i, i+8, white);
                
                int tmp3 = Math.min(tmp1, tmp2);
                min = Math.min(min, tmp3);
            }
        }

        System.out.println(min);
    }
}

//한 칸 씩 칠해가면서 비교..
class Prcoessor2{
    boolean [][] arr;
    int min = 64;

    void check(int row, int col){
        int cnt = 0;
        boolean now = true;

        for(int i=row;i<row+8;i++){
            for(int j=col;j<col+8;j++){
                if(arr[i][j] != now){
                    cnt++;
                }
                now = (!now);
            }
            now = !now;
        }

        cnt = Math.min(cnt, 64-cnt);
        min = Math.min(min, cnt);
    }
    

    void run(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        arr = new boolean[y][x];

        for(int i=0;i<y;i++){
            String tmp = br.readLine();
            for(int j=0;j<x;j++){
                if(tmp.charAt(j) == 'W'){
                    arr[i][j] = true;
                }
                else{
                    arr[i][j] = false;
                }
            }
        }

        for(int i=0;i<y-7;i++){
            for(int j=0;j<x-7;j++){
                check(i, j);
            }
        }

        System.out.println(min);
    }

}

class Test{

    public static void main(String[] args) throws IOException{
        // Scanner scan = new Scanner(System.in);
        
        // Processor p = new Processor();
        // p.run(scan);
        
        // scan.close();


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Prcoessor2 p2 = new Prcoessor2();
        p2.run(br);


    }    
    
}