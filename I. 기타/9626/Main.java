import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 단순히 문자열 출력하는 문제
 * row와 col의 인덱스 짝홀을 기반으로 # 혹은 . 을 추가하는 방식
 * 
 * 배타 OR를 활용
 * row와 col이 둘 다 짝이거나 홀이면 # 
 * 서로 다르면 . 
 * 이걸 배타 OR를 활용해서 판단하도록 함
 * 
 * 이 덕분에 현재 인덱스만 잘 신경쓰면 됨
 * 짝인지 홀인지 복잡하게 생각할 것 없이 단순히 현재 인덱스가 어딘지만
 * 잘 계산하면 # 혹은 .은 알아서 계산됨
 * 
 * 현재 위치 계산
 * row : 0 ~ up
 * col : 0 ~ left + word + right
 * 
 * row : up ~ up+row
 * col : 0 ~ left
 * col : word
 * col : left+word ~ left + word + right
 * 
 * row : up+row ~ up+row+down
 * col : 0 ~ left + word + right
 * 
 * up, down, left, right, word 를 이용해서 인덱스만 잘 계산하면 됨
 */
class solve9626{

    int row, col;
    int up, left, right, down;
    String [] words;
    solve9626(BufferedReader br )throws IOException{

        StringTokenizer st = new StringTokenizer(br.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        up = Integer.parseInt(st.nextToken());
        left = Integer.parseInt(st.nextToken());
        right = Integer.parseInt(st.nextToken());
        down = Integer.parseInt(st.nextToken());

        words = new String[row];
        for(int i=0;i<row;i++){
            words[i] = br.readLine();
        }
    }

    StringBuilder sb = new StringBuilder();
    void run(){

        makeUpDown(0, up);

        for(int i=up;i<up+row;i++){

            for(int j=0;j<left;j++){
                print(i, j);
            }

            sb.append(words[i-up]);

            int nowCol = left + words[i-up].length();
            for(int j=nowCol;j<nowCol+right;j++){
                print(i, j);
            }
            sb.append("\n");
        }

        makeUpDown(up + row, row + up + down);

        System.out.println(sb);

    }

    void makeUpDown(int nowRow, int endRow){

        for(int i=nowRow;i<endRow;i++){

            for(int j=0;j<col + left + right;j++){
                print(i, j);
            }
            sb.append("\n");
        }
    }

    void print(int nowRow, int nowCol){

        if(nowCol % 2 == 0 ^ nowRow % 2 == 0){
            sb.append(".");
        } else {
            sb.append("#");
        }
    }
}

public class Main {

    public static void main(String [] args) throws IOException{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        solve9626 p = new solve9626(br);
        p.run();

        br.close();
    }
}