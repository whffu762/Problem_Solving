import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;

/*
 * 주의할 점
 * Stack을 pop하기 전에 반드시 Stack이 비어있는지 확인해줘야 함 그렇지 않으면 런타임 에러가 발생할 가능성이 존재함
 * 이 검사를 시작할 때만 하는게 아니라 pop이 일어나는 곳에서 수행해줘야 제대로된 연산이 수행됨
 * 
 * 이전엔 doDelayPlan() 메소드를 호출하기 전에만 delay.isEmpty()를 수행했음
 * doDelayPlan() 메소드 내부에선 pop이 일어나기 때문에 pop 이후에 비게된다면 더 이상 검사를 하지 않아서 런타임 에러가 발생했음
 * 이를 해결하기 위해 doDelayPlan() 내부에서 while의 조건으로 stack을 검사해야 함
 */
class Todo {
    
    String name;
    int start;
    int playtime;
    
    Todo (String [] plan){
        
        this.name = plan[0];
        String [] time = plan[1].split(":");
        this.start = Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]);
        this.playtime = Integer.parseInt(plan[2]);
    }
    
    int getDiffOfTime(Todo previous){
        return this.start - previous.start;
    }
    
    void updatePlaytime(int remainTime){
        this.playtime = remainTime;
    }
}
class Solution {
    
    Stack<Todo> delay = new Stack<>();
    String [] done;
    int head = 0;
    
    public String[] solution(String[][] plans) {
        
        Arrays.sort(plans, (after, before) -> {
            return after[1].compareTo(before[1]); 
        });
        
        done = new String [plans.length];
        Todo [] todos = new Todo [plans.length];
        
        for(int i=0;i<plans.length;i++){
            todos[i] = new Todo(plans[i]);
        }
        
        Todo now = todos[0];
        
        for(int i=1;i<todos.length;i++){
            
            int diff = todos[i].getDiffOfTime(now);
            int remainTime = diff - now.playtime;
            
            if(remainTime >= 0){
                done[head++] = now.name;
                doDelayPlan(remainTime);
            }
            
            if(remainTime < 0){
                now.updatePlaytime(-remainTime);
                delay.push(now);
            }
            
            now = todos[i];
        }
        
        done[head++] = todos[todos.length-1].name;
        while(!delay.isEmpty()){
            done[head++] = delay.pop().name;
        }
        
        return done;
    }
    
    void doDelayPlan(int remainTime){
        
        while(!delay.isEmpty()){
            
            Todo now = delay.pop();
            remainTime -= now.playtime;
            
            if(remainTime < 0){
                now.updatePlaytime(-remainTime);
                delay.push(now);
                break;
            }
            
            done[head++] = now.name;
        }
    }
}