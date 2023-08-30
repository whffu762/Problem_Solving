package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


//1181
//Stream의 사용
//문자열 비교는 compareTo()로 하는데 String 클래스에 이미 정의되어 있음
//Collection 간 변환은 Collection 객체 생성하면서 생성자 인자로 넣어주면 변환됨
//copyOf(), of() 이런거 쓰면 ImmutableCollection으로 반환되서 값 변경이 안됨
class Processor{


    void stream1(Set<String> set){

        //내꺼
        StringBuilder sb = new StringBuilder();

        set.stream()
            .sorted((pre, pro)->{
                if(pre.length() == pro.length()){
                    return pre.compareTo(pro);
                }
                return pre.length() - pro.length();
            })
            .forEach(e -> sb.append(e).append("\n"));
        
        System.out.println(sb);
    }

    void stream2(Set<String> set){
        //딴 사람꺼
        //정렬 두 번 하기 때문에 이게 좀 더 느림
        StringBuilder sb = new StringBuilder();
        
        set.stream()
            .sorted()
            .sorted(Comparator.comparingInt(String::length))
            .forEach(e -> sb.append(e).append("\n"));

        System.out.println(sb);
    }

    void notStream(Set<String> set){
        //이게 제일 느렸던거 같음
        StringBuilder sb = new StringBuilder();
        //List<String> list = List.copyOf(set);// 이거로 쓰면 안됨 반환형이 ImmutableCollections라서 값 변경이 안됨

        List<String> list2 = new ArrayList<>(set);  //ArrayList 생성하면서 인자로 넣어주면 평범한 ArrayList 만들어 줌

        Collections.sort(list2, (pre, pro)->{
            if(pre.length() == pro.length()){
                return pre.compareTo(pro);
            }
            return pre.length() - pro.length();
        });

        for(int i=0;i<list2.size();i++){
            sb.append(list2.get(i)).append("\n");
        }

        System.out.println(sb);

    }
}
class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int num = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();

        for(int i=0;i<num;i++){
            set.add(br.readLine());
        }
        
        Processor p = new Processor();
        p.stream1(set);
        p.stream2(set);
        p.notStream(set);
            


        br.close();
    }    
    
}