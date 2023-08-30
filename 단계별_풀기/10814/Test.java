package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
    10814
 * 1. String 에 대한 비교는 == 연산으론 안됨 .equals()를 이용해야 제대로 비교함
 * 2.Comparator 혹은 Comparable 의 반환값이 상수면 동작이 달라짐..
 * 3. 내장 함수 Collections.sort()와 Arrays.sort(), stream().sorted()는 알아서 입력이 먼저 된 것을 앞세움 그래서 내장 함수를 이용할 경우 따로 처리할 필요가 없음
 * 
 */

class Member implements Comparable<Member>{
    int age;
    String name;

    Member(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Member p){
        return this.age - p.age;
    }

}


class Processor{

    void useMember(BufferedReader br) throws IOException{
        //의외로 이게 제일 빠르고 사용한 메모리도 더 적음 객체를 쓰는 방법이 제일 좋은 듯..?    
        int num = Integer.parseInt(br.readLine());
        List<Member> list = new ArrayList<>();

        for(int i=0;i<num;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            list.add(new Member(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        StringBuilder sb = new StringBuilder();

        
        list.stream()
        .sorted((a, b) -> {
            if(a.age ==  b.age){
                return 0;  //return -1 인 경우 요소 간 대소 비교를 하지 않고 현재 인덱스의 역순으로 정렬함
            }
            return a.age - b.age;
        })
        .forEach(e -> sb.append(e.age + " " + e.name).append("\n"));

        System.out.println(sb);
        
    }

    void usingJavaSort(BufferedReader br) throws IOException{
        int n = Integer.parseInt(br.readLine());

        String [][] ary = new String [n][2];
        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            ary[i][0] = st.nextToken();
            ary[i][1] = st.nextToken();
        }

        StringBuilder sb = new StringBuilder();
        
        
        Arrays.stream(ary)
        .sorted((pre, pro)-> Integer.parseInt(pre[0]) - Integer.parseInt(pro[0]))
        .forEach(e -> sb.append(e[0] + " " + e[1]).append("\n"));
        
        System.out.println(sb);

    }

    void usingJavaSort2(BufferedReader br) throws IOException{

        int n = Integer.parseInt(br.readLine());

        List<List<String>> lists = new ArrayList<>();

        for(int i=0;i<n;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            List<String> list = new ArrayList<>();
            list.add(st.nextToken());
            list.add(st.nextToken());
            lists.add(list);
        }

        StringBuilder sb = new StringBuilder();
        
        lists.stream()
        .sorted((pre, pro)->{
            return Integer.parseInt(pre.get(0)) - Integer.parseInt(pro.get(0));
        })
        .forEach(e -> sb.append(e.get(0) + " " + e.get(1)).append("\n"));

        System.out.println(sb);
    }
}
class Test{
    public static void main(String[] args) throws IOException{
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        Processor p = new Processor();
        //p.useMember(br);
        p.usingJavaSort(br);

        br.close();
    }    
    
}