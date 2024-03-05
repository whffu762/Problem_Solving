import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class solve3085{
    
    char [][] field;
    int size;

    char [][] getCopyOfField(){

        char [][] copyOfField = new char [size][size];

        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                copyOfField[i][j] = field[i][j];
            }
        }

        return copyOfField;
    }

    int getNumOfCandy(int nowRow ,int nowColumn){

        return Math.max(
            changeAtColumn(getCopyOfField(), nowRow, nowColumn), 
            changeAtRow(getCopyOfField(), nowRow, nowColumn)
        );
    }

    int changeAtRow(char[][] copy, int nowRow, int nowColumn){

        int nextRow = nowRow + 1;
        if(nextRow < size){
            char tmp = copy[nowRow][nowColumn];
            copy[nowRow][nowColumn] = copy[nextRow][nowColumn];
            copy[nextRow][nowColumn] = tmp;

            int resultOfcolumn = Math.max(checkRow(copy, nowRow), checkRow(copy, nextRow));
            return Math.max(checkColumn(copy, nowColumn), resultOfcolumn);
        }

        return 0;
    }

    int changeAtColumn(char[][] copy, int nowRow, int nowColumn){

        int nextColumn = nowColumn + 1;
        if(nextColumn < size){
            char tmp = copy[nowRow][nowColumn];
            copy[nowRow][nowColumn] = copy[nowRow][nextColumn];
            copy[nowRow][nextColumn] = tmp;

            int resultOfRow = Math.max(checkColumn(copy, nowColumn), checkColumn(copy, nextColumn));
            return Math.max(checkRow(copy, nowRow), resultOfRow);
        }

        return 0;
    }

    int checkRow(char[][] copy, int row){
        
        int result = 1;
        int max = 0;
        for(int i=0;i<size-1;i++){
            if(copy[row][i] == copy[row][i+1]){
                result++;
            } else{
                max = Math.max(max, result);
                result = 1;
            }
        }

        max = Math.max(max, result);
        return max;
    }


    int checkColumn(char[][] copy, int column){
        
        int result = 1;
        int max = 0;
        for(int i=0;i<size-1;i++){
            if(copy[i][column] == copy[i+1][column]){
                result++;
            } else{
                max = Math.max(max, result);
                result = 1;
            }
        }

        max = Math.max(max, result);
        return max;
    }


    void run(BufferedReader br) throws IOException{
        
        size = Integer.parseInt(br.readLine());
        field = new char [size][size];

        for(int i=0;i<size;i++){
            String inputOfRow = br.readLine();
            for(int j=0;j<size;j++){
                field[i][j] = inputOfRow.charAt(j);
            }
        }

        int result = 0;
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                result = Math.max(result, getNumOfCandy(i, j));
            }
        }

        System.out.println(result);
    }
}

public class Main{

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve3085 p = new solve3085();
        p.run(br);

        br.close();
    }
}