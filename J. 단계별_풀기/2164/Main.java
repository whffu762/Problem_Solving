import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

//2164
//LinkedList로 풀면 간단한 문제

class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
           int n = scan.nextInt();
        
        Deque<Integer> q = new LinkedList<>();
        for(int i=1;i<n+1;i++){
            q.add(i);
        }

        while(q.size() != 1){
            q.remove();
            q.addLast(q.remove());
        }

        System.out.println(q.getFirst());

        
        scan.close();
    }

}