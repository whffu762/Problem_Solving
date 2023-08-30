package pack1;

import java.util.Scanner;
//2941

class Run {
    String [] croatia = {"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

    void run(Scanner scan){
        String target = scan.nextLine();
        int count = 0;
    
        for(int i=0;i<croatia.length;i++){
            if(target.contains(croatia[i])){
                target = target.replaceFirst(croatia[i]," ");
                count++;
                i--;
            }
        }
        target = target.strip();
        target = target.replace(" ", "");
        count += target.length();


        System.out.print(count);
    }
}

public class Main { 
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    
        Run run = new Run();
        run.run(scan);
        
        scan.close();
    }
}
