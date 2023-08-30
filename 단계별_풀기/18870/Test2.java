package pack2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;


import java.util.concurrent.atomic.AtomicInteger;
/*
18870
 * 시도했던 방법과 동일한데 원본 배열과 idx를 매핑하는 과정이
 * hashMap을 이용해서 시간을 단축시키는 방법
 * 기존엔 다 각자 비교해서 같으면 idx를 매핑했음 n^2 근데 이건 한번만 순회하면 됨..
 * 이걸 생각못하네;;
 */

class Processor{

    void mine(BufferedReader br) throws IOException{
        
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> points = new ArrayList<>();
        for(int i=0;i<n;i++){
            points.add(Integer.parseInt(st.nextToken()));
        }

        List<Integer> sorted = new ArrayList<>(points);
        Collections.sort(sorted);

        Map<Integer, Integer> resultMap = new HashMap<>();

        int idx = 0;
        for(Integer x : sorted){
            if(resultMap.containsKey(x)){
                continue;
            }
            resultMap.put(x, idx++);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append(resultMap.get(points.get(i)) + " ");
        }
        System.out.println(sb);

    }

    //stream 이용
    void run(BufferedReader br) throws IOException{
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        String[] arr = new String[N];
        for(int i=0;i<N;i++){
            arr[i] = st.nextToken();
        }

        AtomicInteger idx = new AtomicInteger();

        Map<Integer, Integer> map = 
            Arrays.stream(arr)
                .mapToInt(Integer::parseInt)
                .boxed()
                .sorted()
                .distinct()
                .collect(Collectors.toMap(
                    s->s, 
                    s->idx.getAndIncrement(), 
                    (s1, s2)->s1,
                    LinkedHashMap::new
                ));

        for(int i=0; i<arr.length; i++) {
            sb.append(map.get(Integer.parseInt(arr[i])) + " ");
        }

        System.out.println(sb);
    }
}
class Test2{

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Processor p = new Processor();
        p.run(br);
        p.mine(br);
        
        br.close();
    }    
    
}


        